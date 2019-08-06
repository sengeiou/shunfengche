package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.GridViewAddImgesAdpter;
import com.windmillsteward.jukutech.activity.mine.presenter.FundsTrusteeshipDetailPresenter;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipDetailBean;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.customview.dialog.SelectTwoDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.SDCardUtils;
import com.windmillsteward.jukutech.utils.StateButton;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：举报(举证)资金托管
 * author:cyq
 * 2018-03-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ReportTrusteeshipActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, FundsTrusteeshipDetailView, SelectTwoDialog.DialogListner {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private EditText et_reason;
    private MyGridView gv_content;
    private StateButton btn_commit;

    private int trusteeship_id;//托管id
    private String type_name;//举报或者举证

    private GridViewAddImgesAdpter addImgesAdpter;
    private List<Map<String, Object>> pictureList = new ArrayList<>();//图片集合

    private SelectTwoDialog selectTwoDialog;//图片选择弹窗

    private Uri existUri;//指定路径uri

    private FundsTrusteeshipDetailPresenter presenter;

    /**
     * 跳转到资金托管详情页面
     *
     * @param context
     * @param trusteeship_id 托管id
     */
    public static void go(Context context, int trusteeship_id, String status_name) {
        Intent intent = new Intent(context, ReportTrusteeshipActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Define.INTENT_DATA, trusteeship_id);
        bundle.putString(Define.INTENT_DATA_TWO, status_name);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_trusteeship);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        et_reason = (EditText) findViewById(R.id.et_reason);
        gv_content = (MyGridView) findViewById(R.id.gv_content);
        btn_commit = (StateButton) findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(this);
        handleBackEvent(toolbar_iv_back);

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        trusteeship_id = bundle.getInt(Define.INTENT_DATA);
        type_name = bundle.getString(Define.INTENT_DATA_TWO);

        toolbar_iv_title.setText(type_name);

        selectTwoDialog = new SelectTwoDialog(this, 0, this, "拍照", "选择图片", "取消");
        addImgesAdpter = new GridViewAddImgesAdpter(pictureList, this);
        addImgesAdpter.setMaxImages(6);
        gv_content.setAdapter(addImgesAdpter);
        gv_content.setOnItemClickListener(this);

        presenter = new FundsTrusteeshipDetailPresenter(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                String reason = et_reason.getText().toString();
                if (TextUtils.isEmpty(reason)) {
                    showTips("请描述原因", 1);
                    return;
                }
                if (trusteeship_id == 0) {
                    return;
                }
                List<String> uploadList = new ArrayList<>();
                for (int i = 0; i < pictureList.size(); i++) {
                    String url = (String) pictureList.get(i).get("path");
                    uploadList.add(url);
                }
                if (pictureList.size() != 0) {
                    presenter.uploadPic(uploadList);
                } else {
                    JSONArray array = new JSONArray();
                    presenter.reportTrusteeship(getAccessToken(), trusteeship_id, reason, array.toString());
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectTwoDialog.showAtBottom();
    }

    @Override
    public void uploadPicSuccess(List<String> pic_urls) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < pic_urls.size(); i++) {
            array.put(pic_urls.get(i));
        }
        String upload_json = array.toString();

            presenter.reportTrusteeship(getAccessToken(), trusteeship_id, et_reason.getText().toString(), upload_json);

    }

    @Override
    public void getFundsTrusteeshipDetailSuccess(FundsTrusteeshipDetailBean fundsTrusteeshipDetailBean) {
        //用不上
    }

    @Override
    public void getFundsTrusteeshipDetailFailed(int code, String msg) {
        //用不上
    }

    @Override
    public void operateSuccess() {
        showTips("操作成功", 1);
        AppManager.getAppManager().finishActivity(FundsTrusteeshipDetailActivity.class);
        finish();
    }

    @Override
    public void operateFailed(int code, String msg) {
        showTips(msg, 1);
    }



    @Override
    public void uploadPicFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void selectDialogIndex(int action, int positon) {
        if (positon == 1) { //拍照
            takePhoto(Define.APP_TEMP_HEAD, SelectTwoDialog.TAKE_PHOTO);
        } else if (positon == 2) { //相册
            openPhotoAlbum(SelectTwoDialog.PHOTO_ALBUM);
        }
    }

    /**
     * 拍照
     *
     * @param path        指定照片位置
     * @param requestCode 请求码
     */
    public void takePhoto(String path, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (SDCardUtils.checkSDCard()) {
            existUri = Uri.fromFile(new File(path));
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    existUri);
        } else {
            showTips("请检查SD卡是否存在", 1);
            return;
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 打开相册
     *
     * @param requestCode 请求码
     */
    public void openPhotoAlbum(int requestCode) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
        startActivityForResult(intent, requestCode);
    }

    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Define.COMPRESS_TAG:
                    String img_sdcard = (String) msg.obj;
                    if (!TextUtils.isEmpty(img_sdcard)) {
                        // 加载本地图片
                        Map<String, Object> map = new HashMap<>();
                        map.put("path", img_sdcard);
                        pictureList.add(map);
                        addImgesAdpter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SelectTwoDialog.TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        if (SDCardUtils.checkSDCard()) {
                            Uri uri = data.getData();
                            ImageUtils.compressSave(SDCardUtils.getPath(uri, this), mainHandler);
                        } else {
                            showTips("请检查SD卡是否存在", 1);
                            return;
                        }
                    } else {
                        ImageUtils.compressSave(existUri.getPath(), mainHandler);
                    }
                }
                break;
            case SelectTwoDialog.PHOTO_ALBUM: //相册
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    ImageUtils.compressSave(SDCardUtils.getPath(uri, this), mainHandler);
                }
                break;
        }
    }

}
