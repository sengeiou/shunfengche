package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hyphenate.EMCallBack;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.hawk.Hawk;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MyApplication;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.EditMyInfoImpl;
import com.windmillsteward.jukutech.activity.mine.presenter.LogoutPresenter;
import com.windmillsteward.jukutech.activity.mine.presenter.MyInfoImpl;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.AddressListActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.AddressListBean;
import com.windmillsteward.jukutech.bean.CountOrderNumberBean;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.bean.UserInfo;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.StringUtil;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 描述：我的个人信息
 * author:cyq
 * 2018-02-10
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyInfoActivity extends BaseActivity implements View.OnClickListener, MyInfoView,
        EditMyInfoView, LogoutView {

    protected static final String TAG = "MyInfoActivity";
    public static final int REQUEST_CODE_MAKE_VIDEO = 104;

    public static final int NAME = 101;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView iv_head;
    private LinearLayout lay_ll_head;
    private TextView tv_nick_name;
    private LinearLayout lay_ll_nick_name;
    private TextView tv_sex;
    private LinearLayout lay_ll_sex;
    private LinearLayout lay_ll_address;
    private TextView tv_personal_authentication;
    private LinearLayout lay_ll_personal_authentication;
    private TextView tv_company_authentication;
    private LinearLayout lay_ll_company_authentication;
    private TextView tv_logout;

    private MyInfoImpl myInfoImpl;
    private EditMyInfoImpl editMyInfoImpl;

    private BaseDialog baseDialog;

    private Uri existUri;//指定路径uri
    private String img_url;// 图片上传成功后,后台返回来的图片URL
    private String img_sdcard;// 图片在本地SDCARD的路径

    private int type;//1图片2昵称3性别
    private int is_user_authen;//个人认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】
    private int is_company_authen;//企业认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】

    private LogoutPresenter logoutPresenter;
    private List<String> listPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
        initData();
    }

    private void initData() {

        editMyInfoImpl = new EditMyInfoImpl(this);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        lay_ll_head = (LinearLayout) findViewById(R.id.lay_ll_head);
        tv_nick_name = (TextView) findViewById(R.id.tv_nick_name);
        lay_ll_nick_name = (LinearLayout) findViewById(R.id.lay_ll_nick_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        lay_ll_sex = (LinearLayout) findViewById(R.id.lay_ll_sex);
        lay_ll_address = (LinearLayout) findViewById(R.id.lay_ll_address);
        tv_personal_authentication = (TextView) findViewById(R.id.tv_personal_authentication);
        lay_ll_personal_authentication = (LinearLayout) findViewById(R.id.lay_ll_personal_authentication);
        tv_company_authentication = (TextView) findViewById(R.id.tv_company_authentication);
        lay_ll_company_authentication = (LinearLayout) findViewById(R.id.lay_ll_company_authentication);
        tv_logout = (TextView) findViewById(R.id.tv_logout);

        toolbar_iv_title.setText("个人信息");

        toolbar_iv_back.setOnClickListener(this);
        lay_ll_head.setOnClickListener(this);
        lay_ll_nick_name.setOnClickListener(this);
        lay_ll_sex.setOnClickListener(this);
        lay_ll_address.setOnClickListener(this);
        lay_ll_personal_authentication.setOnClickListener(this);
        lay_ll_company_authentication.setOnClickListener(this);
        tv_logout.setOnClickListener(this);

        listPic = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String access_token = (String) Hawk.get(Define.ACCESS_TOKEN);
        if (TextUtils.isEmpty(access_token)) {
        } else {
            //请求用户数据
            myInfoImpl = new MyInfoImpl(this);
            myInfoImpl.getMyInfo();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_iv_back:
                finish();
                break;
            case R.id.lay_ll_head:
                type = 1;
                openXiangce();
                break;
            case R.id.lay_ll_nick_name:
                Intent nameIntent = new Intent(this, ChangeNameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA, "nickname");
                bundle.putString(Define.INTENT_DATA_TWO, tv_nick_name.getText().toString());
                nameIntent.putExtras(bundle);
                startActivityForResult(nameIntent, NAME);
                break;
            case R.id.lay_ll_sex:
                editMyInfoImpl.loadSexModule();
                break;
            case R.id.lay_ll_address:
                String default_address_json = Hawk.get(Define.DEFAULT_SHOPPING_ADDRESS);
                Bundle bundleAddress = new Bundle();
                if (!TextUtils.isEmpty(default_address_json)) {
                    AddressListBean.ListBean bean = JSON.parseObject(default_address_json, AddressListBean.ListBean.class);
                    bundleAddress.putInt(Define.INTENT_DATA, bean.getAddress_id());
                    bundleAddress.putInt(Define.INTENT_DATA_TWO, 1);//1表示是从个人中心进来的
                }
                startAtvDonFinish(AddressListActivity.class, bundleAddress);
                break;
            case R.id.lay_ll_personal_authentication:
                //个人认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】
                if (is_user_authen == 1) {//个人认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】
                    tv_personal_authentication.setText("待审核");
                } else if (is_user_authen == 0) {
                    tv_personal_authentication.setText("未认证");
                    startAtvDonFinish(PersonalAuthenticationActivity.class);
                } else if (is_user_authen == 2) {
                    tv_personal_authentication.setText("已认证");
                } else if (is_user_authen == 3) {
                    tv_personal_authentication.setText("审核不通过");
                    startAtvDonFinish(PersonalAuthenticationActivity.class);
                }
                break;
            case R.id.lay_ll_company_authentication:////企业认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】
                if (is_company_authen == 1) {
                    tv_company_authentication.setText("待审核");
                } else if (is_company_authen == 0) {
                    tv_company_authentication.setText("未认证");
                    startAtvDonFinish(BusinessAuthenticationActivity.class);
                } else if (is_company_authen == 2) {
                    tv_company_authentication.setText("已认证");
                } else if (is_company_authen == 3) {
                    tv_company_authentication.setText("审核不通过");
                    startAtvDonFinish(BusinessAuthenticationActivity.class);
                }
                break;
            case R.id.tv_logout:
                if (baseDialog == null) {
                    baseDialog = new BaseDialog(this);
                }
                baseDialog.showTwoButton("提示", "是否注销此账号", "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        logout();
                        baseDialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                    }
                });

                break;
        }
    }

    private void logout() {
        if (logoutPresenter == null) {
            logoutPresenter = new LogoutPresenter(this);
        }
        logoutPresenter.logout();
    }


    /**
     * 打开相册
     */
    public void openXiangce() {
        List<LocalMedia> selectList = new ArrayList<>();
        for (String s : listPic) {
            if (!StringUtil.isEmpty(s)) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setChecked(true);
                localMedia.setMimeType(PictureMimeType.ofImage());
                localMedia.setPath(s);
                selectList.add(localMedia);
            }
        }
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageSpanCount(3)// 每行显示个数 int
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .isGif(false)// 是否显示gif图片 true or false
                .enableCrop(false)
                .withAspectRatio(1, 1)
                .maxSelectNum(1)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    /**
     * 友盟推送移除别名，每次需要绑定新的别名，必须要移除旧的别名
     */
    private void removeAlias(final String uid, String type) {
        PushAgent pushAgent = MyApplication.instance.getmPushAgent();
        pushAgent.deleteAlias(uid, type, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String s) {
                if (isSuccess) {
                    Log.i(TAG, "delet alias was set successfully.");

                } else {
                    Log.i(TAG, "alias was set failed.");
                }
            }
        });
    }

    @Override
    public void loadSexModuleSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        if (id == 1) {
                            tv_sex.setText("男");
                        } else if (id == 2) {
                            tv_sex.setText("女");
                        }
                        showDialog("正在编辑...");
                        editMyInfoImpl.editMyInfo("sex", id + "");
                    }
                })
                .show();
    }


    @Override
    public void editSuccess() {
        if (type == 1) {
            x.image().bind(iv_head, img_url, ImageUtils.defaultPersonHeadOptions());
        }
        dismiss();
        showTips("编辑成功", 1);
    }

    @Override
    public void editFailed(int code, String msg) {
        dismiss();
        showTips(msg, 1);
    }


    @Override
    public void getMyInfoSuccess(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        int user_id = userInfo.getUser_id();
        String nickname = userInfo.getNickname();
        tv_nick_name.setText(TextUtils.isEmpty(nickname) ? "" : nickname);
        int sex = userInfo.getSex();
        String user_avatar_url = userInfo.getUser_avatar_url();
        if (sex == 1) {
            tv_sex.setText("男");
            x.image().bind(iv_head, user_avatar_url, ImageUtils.defaultBoyHeadOptions());
        } else if (sex == 2) {
            tv_sex.setText("女");
            x.image().bind(iv_head, user_avatar_url, ImageUtils.defaultGirlHeadOptions());
        }
        is_user_authen = userInfo.getUser_authen_status();
        if (is_user_authen == 1) {//个人认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】
            tv_personal_authentication.setText("待审核");
        } else if (is_user_authen == 0) {
            tv_personal_authentication.setText("未认证");
        } else if (is_user_authen == 2) {
            tv_personal_authentication.setText("已认证");
        } else if (is_user_authen == 3) {
            tv_personal_authentication.setText("审核不通过");
        }
        is_company_authen = userInfo.getCompany_authen_status();//企业认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】
        if (is_company_authen == 1) {
            tv_company_authentication.setText("待审核");
        } else if (is_company_authen == 0) {
            tv_company_authentication.setText("未认证");
        } else if (is_company_authen == 2) {
            tv_company_authentication.setText("已认证");
        } else if (is_company_authen == 3) {
            tv_company_authentication.setText("审核不通过");
        }
    }

    @Override
    public void getMyInfoFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getCountOrderNumber(CountOrderNumberBean bean) {

    }


    /**
     * 环信登出
     */
    private void hxLogout() {
        //注销环信
        DemoHelper.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Log.d("main", "注销已经登录的账号成功");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

    @Override
    public void logoutSuccess() {
        showTips("退出登录成功", 1);
        //注销成功
        removeAlias(Hawk.get(Define.USER_ID, 0) + "", "sfc_android");
        if (DemoHelper.getInstance().isLoggedIn()) {
            hxLogout();
        }
        Hawk.put(Define.ACCESS_TOKEN, "");
        Hawk.put(Define.USER_ID, 0);
        Hawk.put(Define.LOGIN_SUCCESS, new LoginSuccessInfo());
        startAtvAndFinish(LoginActivity.class);
    }

    @Override
    public void logoutFailed(int code, String msg) {
        showTips(msg, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            listPic.clear();
            if (!selectList.isEmpty()) {
                for (int i = selectList.size() - 1; i >= 0; i--) {
                    LocalMedia localMedia = selectList.get(i);
                    String currImageUrl = localMedia.getPath();
                    listPic.add(0, currImageUrl);
                }
                showDialog("正在上传...");
                //上传图片
                addCall(new NetUtil().setPic_path(listPic)
                        .buildUploadFile(new NetUtil.OnPicsUploadSuccessListener() {
                            @Override
                            public void onPicsUploadSuccess(List<String> pic_urls) {
                                dismiss();
                                if (pic_urls.size() != 0 && pic_urls != null) {
                                    img_url = pic_urls.get(0);
                                    showDialog("正在编辑...");
                                    editMyInfoImpl.editMyInfo("user_avatar_url", img_url);
                                }
                            }

                            @Override
                            public void onPicsUploadFail(String msg) {
                                dismiss();
                                showTips(msg);
                            }
                        }));
            }
        } else if (requestCode == NAME && resultCode == RESULT_OK) {
            String nick_name = data.getStringExtra(Define.INTENT_DATA);
            tv_nick_name.setText(TextUtils.isEmpty(nick_name) ? "" : nick_name);
        }
    }
}
