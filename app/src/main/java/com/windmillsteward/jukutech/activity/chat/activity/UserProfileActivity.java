package com.windmillsteward.jukutech.activity.chat.activity;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.chat.domain.EaseUser;
import com.windmillsteward.jukutech.activity.chat.utils.EaseUserUtils;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;

public class UserProfileActivity extends BaseActivity implements OnClickListener {

    private static final int REQUESTCODE_PICK = 1;
    private static final int REQUESTCODE_CUTTING = 2;
    private ImageView headAvatar;
    private ImageView iconRightArrow;
    private TextView tvNickName;
    private TextView tv_go2chat;
    private TextView tvUsername;
    private ProgressDialog dialog;
    private RelativeLayout rlNickName;
    private String username;//环信用户名
    private EaseUser easeUser;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_user_profile);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        headAvatar = (ImageView) findViewById(R.id.user_head_avatar);

        tvUsername = (TextView) findViewById(R.id.user_username);
        tv_go2chat = (TextView) findViewById(R.id.tv_go2chat);
        tvNickName = (TextView) findViewById(R.id.user_nickname);
        rlNickName = (RelativeLayout) findViewById(R.id.rl_nickname);
        iconRightArrow = (ImageView) findViewById(R.id.ic_right_arrow);
    }

    private void initListener() {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        boolean enableUpdate = intent.getBooleanExtra("setting", false);
        if (enableUpdate) {
            iconRightArrow.setVisibility(View.VISIBLE);
            rlNickName.setOnClickListener(this);
        } else {
            iconRightArrow.setVisibility(View.INVISIBLE);
        }
        tv_go2chat.setOnClickListener(this);
//        if (username != null) {
//            if (username.equals(EMClient.getInstance().getCurrentUser())) {
//                tvUsername.setText(EMClient.getInstance().getCurrentUser());
//                EaseUserUtils.setUserNick(username, tvNickName);
//                EaseUserUtils.setUserAvatar(this, username, headAvatar);
//            } else {
//                tvUsername.setText(username);
//                EaseUserUtils.setUserNick(username, tvNickName);
//                EaseUserUtils.setUserAvatar(this, username, headAvatar);
////    			asyncFetchUserInfo(username);
//            }
//    }

    }

    private void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_HX_USER_INFO)
                .addParams("username", username)
                .setCallBackData(new BaseNewNetModelimpl<EaseUser>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<EaseUser> respnse, String source) {
                        easeUser = respnse.getData();
                        if (easeUser != null) {
                            setData(easeUser);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeReference<BaseResultInfo<EaseUser>>() {
                        }.getType();
                    }
                })
                .buildPost());
    }

    private void setData(EaseUser easeUser) {
        EaseUserUtils.setUserAvatar(this, easeUser.getUsername(), headAvatar);
        String nickname = easeUser.getNickname();
        String sex_str = "";
        int sex = easeUser.getSex();
        if (sex == 1) {
            sex_str = "男";
        } else if (sex == 2) {
            sex_str = "女";
        } else {
            sex_str = "保密";
        }
        tvUsername.setText(nickname + " " + sex_str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_go2chat:
                if (easeUser != null){
                    if (username.equals(easeUser.getUsername())){
                        showTips("不能和自己聊天");
                    }else{
                        startActivity(new Intent(this, ChatActivity.class).putExtra("userId", username));
                        finish();
                    }
                }
                break;
            case R.id.rl_nickname:
                final EditText editText = new EditText(this);
                new Builder(this).setTitle(R.string.setting_nickname).setIcon(android.R.drawable.ic_dialog_info).setView(editText)
                        .setPositiveButton(R.string.dl_ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nickString = editText.getText().toString();
                                if (TextUtils.isEmpty(nickString)) {
                                    Toast.makeText(UserProfileActivity.this, getString(R.string.toast_nick_not_isnull), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                updateRemoteNick(nickString);
                            }
                        }).setNegativeButton(R.string.dl_cancel, null).show();
                break;
            default:
                break;
        }

    }

//	public void asyncFetchUserInfo(String username){
//		DemoHelper.getInstance().getUserProfileManager().asyncGetUserInfo(username, new EMValueCallBack<EaseUser>() {
//
//			@Override
//			public void onSuccess(EaseUser user) {
//				if (user != null) {
//				    DemoHelper.getInstance().saveContact(user);
//				    if(isFinishing()){
//				        return;
//				    }
//					tvNickName.setText(user.getNickname());
//					if(!TextUtils.isEmpty(user.getAvatar())){
//						 Glide.with(UserProfileActivity.this).load(user.getAvatar()).into(headAvatar);
//					}else{
//					    Glide.with(UserProfileActivity.this).load(R.drawable.em_default_avatar).into(headAvatar);
//					}
//				}
//			}
//
//			@Override
//			public void onError(int error, String errorMsg) {
//			}
//		});
//	}


    private void uploadHeadPhoto() {
        Builder builder = new Builder(this);
        builder.setTitle(R.string.dl_title_upload_photo);
        builder.setItems(new String[]{getString(R.string.dl_msg_take_photo), getString(R.string.dl_msg_local_upload)},
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {
                            case 0:
                                Toast.makeText(UserProfileActivity.this, getString(R.string.toast_no_support),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(pickIntent, REQUESTCODE_PICK);
                                break;
                            default:
                                break;
                        }
                    }
                });
        builder.create().show();
    }


    private void updateRemoteNick(final String nickName) {
        dialog = ProgressDialog.show(this, getString(R.string.dl_update_nick), getString(R.string.dl_waiting));
        new Thread(new Runnable() {

            @Override
            public void run() {
                boolean updatenick = DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(nickName);
                if (UserProfileActivity.this.isFinishing()) {
                    return;
                }
                if (!updatenick) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatenick_fail), Toast.LENGTH_SHORT)
                                    .show();
                            dialog.dismiss();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatenick_success), Toast.LENGTH_SHORT)
                                    .show();
                            tvNickName.setText(nickName);
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:
                if (data == null || data.getData() == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * save the picture data
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(getResources(), photo);
            headAvatar.setImageDrawable(drawable);
            uploadUserAvatar(Bitmap2Bytes(photo));
        }

    }

    private void uploadUserAvatar(final byte[] data) {
        dialog = ProgressDialog.show(this, getString(R.string.dl_update_photo), getString(R.string.dl_waiting));
        new Thread(new Runnable() {

            @Override
            public void run() {
                final String avatarUrl = DemoHelper.getInstance().getUserProfileManager().uploadUserAvatar(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (avatarUrl != null) {
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatephoto_success),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserProfileActivity.this, getString(R.string.toast_updatephoto_fail),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        }).start();

        dialog.show();
    }


    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


    /**
     * back
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
