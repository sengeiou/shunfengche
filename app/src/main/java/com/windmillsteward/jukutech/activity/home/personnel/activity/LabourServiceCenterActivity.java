package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.LabourServiceCenterFragment;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：劳务中心
 * author:cyq
 * 2018-07-31
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class LabourServiceCenterActivity extends BaseActivity implements View.OnClickListener {

    public static final String CURR_POSITION = "CURR_POSITION";
    public static final String CURR_CLASS = "CURR_CLASS";
    public static final String KEYWORD = "KEYWORD";

    private TextView tvPostJob;  // 标题栏发布
    private ImageView ivBack;    // 返回
    private LinearLayout linearSearch;  // 搜索
    private TextView tvSearchHint;  // 搜索提示
    private FrameLayout flContent;

    // 当前选中的fragment
    private int curr_position;
    // 由外部传过来的分类
    private int curr_class;
    //搜索页面过来的关键字
    private String keyword = "";

    private LabourServiceCenterFragment fragment;

    /**
     * 跳转
     *
     * @param context
     * @param keyword       搜索关键字
     * @param curr_position 需要显示的tab
     */
    public static void go(Context context, String keyword, int curr_position) {
        Intent intent = new Intent(context, LabourServiceCenterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEYWORD, keyword);
        bundle.putInt(CURR_POSITION, curr_position);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_service_center);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            curr_position = extras.getInt(CURR_POSITION,0);
            curr_class = extras.getInt(CURR_CLASS,0);
            keyword = extras.getString(KEYWORD, "");
        }
        initView();
        initToolbar();

    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        linearSearch = (LinearLayout) findViewById(R.id.linear_search);
        tvSearchHint = (TextView) findViewById(R.id.tv_searchHint);
        flContent = (FrameLayout) findViewById(R.id.fl_content);

        linearSearch.setOnClickListener(this);
        setParamInt(R.id.fl_content);
        fragment = LabourServiceCenterFragment.getInstance(keyword, curr_class);

        startFragment(null, fragment);

    }

    private void initToolbar() {
        handleBackEvent(ivBack);
        tvSearchHint.setText("搜索");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_search:
                SearchItemActivity.go(LabourServiceCenterActivity.this,80,0);
                break;
        }
    }
}
