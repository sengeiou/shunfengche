package com.windmillsteward.jukutech.activity.mine.activity;

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

import com.bigkoo.pickerview.TimePickerView;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.mine.adapter.GridViewAddImgesAdpter;
import com.windmillsteward.jukutech.activity.mine.presenter.AddFundsTrusteeshipPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.customview.dialog.SelectTwoDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.EditTextUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.SDCardUtils;
import com.windmillsteward.jukutech.utils.StateButton;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：添加资金托管页面
 * author:cyq
 * 2018-03-07
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class AddFundsTrusteeshipActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, SelectTwoDialog.DialogListner, AddFundsTrusteeshipView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_trusteeship_module_name;
    private EditText et_trusteeship_id;
    private EditText et_trusteeship_money;
    private TextView tv_trusteeship_start_time;
    private TextView tv_trusteeship_end_time;
    private EditText et_trusteeship_mobile_phone;
    private EditText et_trusteeship_user_name;
    private EditText et_description;
    private MyGridView gv_content;
    private StateButton btn_commit;

    private GridViewAddImgesAdpter addImgesAdpter;
    private List<Map<String, Object>> pictureList = new ArrayList<>();//图片集合

    private SelectTwoDialog selectTwoDialog;//图片选择弹窗

    private Uri existUri;//指定路径uri
    private String img_url;// 图片上传成功后,后台返回来的图片URL
    private String img_sdcard;// 图片在本地SDCARD的路径

    private AddFundsTrusteeshipPresenter presenter;

    private int trusteeship_module_name_id;//托管模块 1人才驿站 2智慧家庭 3思想智库 4房屋租售 5住宿旅游
    private String start_date;
    private String end_date;
    private long start_date_long = 0;
    private long end_date_long = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditTextUtil.assistActivity(findViewById(android.R.id.content));
        setContentView(R.layout.activity_add_funds_trusteeship);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_trusteeship_module_name = (TextView) findViewById(R.id.tv_trusteeship_module_name);
        et_trusteeship_id = (EditText) findViewById(R.id.et_trusteeship_id);
        et_trusteeship_money = (EditText) findViewById(R.id.et_trusteeship_money);
        tv_trusteeship_start_time = (TextView) findViewById(R.id.tv_trusteeship_start_time);
        tv_trusteeship_end_time = (TextView) findViewById(R.id.tv_trusteeship_end_time);
        et_trusteeship_mobile_phone = (EditText) findViewById(R.id.et_trusteeship_mobile_phone);
        et_trusteeship_user_name = (EditText) findViewById(R.id.et_trusteeship_user_name);
        et_description = (EditText) findViewById(R.id.et_description);
        gv_content = (MyGridView) findViewById(R.id.gv_content);
        btn_commit = (StateButton) findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(this);
        tv_trusteeship_module_name.setOnClickListener(this);
        tv_trusteeship_start_time.setOnClickListener(this);
        tv_trusteeship_end_time.setOnClickListener(this);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("添加资金托管");
    }

    private void initData() {
        selectTwoDialog = new SelectTwoDialog(this, 0, this, "拍照", "选择图片", "取消");
        addImgesAdpter = new GridViewAddImgesAdpter(pictureList, this);
        addImgesAdpter.setMaxImages(6);
        gv_content.setAdapter(addImgesAdpter);
        gv_content.setOnItemClickListener(this);

        presenter = new AddFundsTrusteeshipPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                submit();
                break;
            case R.id.tv_trusteeship_module_name:
                presenter.loadTrusteeshipModule();
                break;
            case R.id.tv_trusteeship_start_time:
                TimePickerView start = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        start_date_long = date.getTime();
                        start_date = DateUtil.getYY_MM_DD(date, "yyyy-MM-dd");
                        tv_trusteeship_start_time.setText(start_date);
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .build();
                start.setDate(Calendar.getInstance());
                start.show();
                break;
            case R.id.tv_trusteeship_end_time:
                if (start_date_long == 0) {
                    showTips("请先选择起始时间", 1);
                    return;
                }
                TimePickerView end = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        end_date_long = date.getTime();
                        end_date = DateUtil.getYY_MM_DD(date, "yyyy-MM-dd");
                        tv_trusteeship_end_time.setText(end_date);
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .build();
                end.setDate(Calendar.getInstance());
                end.show();
                break;
        }
    }

    /**
     * 点击提交
     */
    private void submit() {
        if (trusteeship_module_name_id == 0) {
            showTips("请选择托管模块", 1);
            return;
        }
        String trusteeship_id = et_trusteeship_id.getText().toString();
        if (TextUtils.isEmpty(trusteeship_id)) {
            showTips("请输入托管ID", 1);
            return;
        }
        String trusteeship_money = et_trusteeship_money.getText().toString();
        if (TextUtils.isEmpty(trusteeship_money)) {
            showTips("请输入托管金额", 1);
            return;
        }
        if (start_date_long > end_date_long) {
            showTips("起始时间不能大于结束时间", 1);
            return;
        }
        String trusteeship_mobile_phone = et_trusteeship_mobile_phone.getText().toString();
        if (TextUtils.isEmpty(trusteeship_mobile_phone)) {
            showTips("请输入手机号", 1);
            return;
        }
        String trusteeship_user_name = et_trusteeship_user_name.getText().toString();
        if (TextUtils.isEmpty(trusteeship_user_name)) {
            showTips("请输入联系人", 1);
            return;
        }
        String trusteeship_description = et_description.getText().toString();
        if (TextUtils.isEmpty(trusteeship_description)) {
            showTips("请您描述一下具体情况", 1);
            return;
        }
        List<String> uploadList = new ArrayList<>();
        for (int i = 0; i < pictureList.size(); i++) {
            String url = (String) pictureList.get(i).get("path");
            uploadList.add(url);
        }
        if (uploadList.size() != 0) {
            presenter.uploadPic(uploadList);
        } else {
            JSONArray array = new JSONArray();
            presenter.addFundsTrusteeship(getAccessToken(), trusteeship_module_name_id, et_trusteeship_id.getText().toString(), et_trusteeship_money.getText().toString(),
                    start_date, end_date, et_trusteeship_mobile_phone.getText().toString(), et_trusteeship_user_name.getText().toString(), et_description.getText().toString(),
                    array.toString());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectTwoDialog.showAtBottom();
    }

    @Override
    public void selectDialogIndex(int action, int positon) {
        if (positon == 1) { //拍照
            takePhoto(Define.APP_TEMP_HEAD, SelectTwoDialog.TAKE_PHOTO);
        } else if (positon == 2) { //相册
            openPhotoAlbum(SelectTwoDialog.PHOTO_ALBUM);
        }
    }

    @Override
    public void addAddFundsTrusteeshipSuccess() {
        showTips("添加成功", 1);
        finish();
    }

    @Override
    public void addAddFundsTrusteeshipFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void uploadPicSuccess(List<String> pic_urls) {
        //上传完图片后，直接请求添加资金托管接口
        JSONArray array = new JSONArray();
        for (int i = 0; i < pic_urls.size(); i++) {
            array.put(pic_urls.get(i));
        }
        String upload_json = array.toString();
        presenter.addFundsTrusteeship(getAccessToken(), trusteeship_module_name_id, et_trusteeship_id.getText().toString(), et_trusteeship_money.getText().toString(),
                start_date, end_date, et_trusteeship_mobile_phone.getText().toString(), et_trusteeship_user_name.getText().toString(), et_description.getText().toString(),
                upload_json);
    }

    @Override
    public void uploadPicFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void loadTrusteeshipModuleSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        tv_trusteeship_module_name.setText(text);
                        trusteeship_module_name_id = id;
                    }
                })
                .show();
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
