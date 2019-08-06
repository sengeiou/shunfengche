package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SelectPhotoAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/1/26
 * 作者：xjh
 */

public class SelectPhotoActivity extends BaseActivity implements TakePhoto.TakeResultListener,InvokeListener, View.OnClickListener {
    public static final int IMAGE_ITEM_ADD = -10;
    public static final int RESULT_CODE = 200;
    public static final String RESULT_DATA = "RESULT_DATA";
    public static final String INIT_DATA = "INIT_DATA";
    private TakePhoto takePhoto;  // 选择图片或拍照
    private InvokeParam invokeParam; // 选择图片或拍照的参数设置

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private ArrayList<String> list;
    private SelectPhotoAdapter adapter;
    private int maxImgCount=9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectphoto);
        initView();
        initToolbar();
        initRecyclerView();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            ArrayList<String> arrayList = extras.getStringArrayList(INIT_DATA);
            if (arrayList!=null) {
                list.addAll(arrayList);
                adapter.setImages(list);
            }
        }
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new SelectPhotoAdapter(this,list,maxImgCount);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

        adapter.setOnItemClickListener(new SelectPhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position==IMAGE_ITEM_ADD) {
                    initTokePhoto();
                }
            }

            @Override
            public void onDeleteClick(View view, int position) {
                if (position<list.size()&& position>=0) {
                    list.remove(position);
                    adapter.setImages(list);
                }
            }
        });
    }

    private void initTokePhoto() {
        CompressConfig config = new CompressConfig.Builder().enablePixelCompress(true)
                .create();
        takePhoto.onEnableCompress(config,true);
        takePhoto.onPickMultiple(maxImgCount-list.size());
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("选取照片");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setText("确定");
        toolbar_tv_right.setOnClickListener(this);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }

    private TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        for (TImage image : images) {
            list.add(image.getCompressPath());
        }
        adapter.setImages(list);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_tv_right:
                Intent data = new Intent();
                data.putStringArrayListExtra(RESULT_DATA,list);
                setResult(RESULT_CODE,data);
                finish();
                break;
        }
    }
}
