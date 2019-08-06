package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchResultActivity;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.SearchSelectTypeAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.SearchModuleBean;

import java.util.List;

/**
 * 描述：搜索人才驿站，房屋租售，住宿旅游后，进入的选择对应模块类别的页面
 * author:cyq
 * 2018-03-31
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SearchSelectTypeActivity extends BaseActivity implements View.OnClickListener {

    public static final String BEAN = "BEAN";//实体数据
    public static final String KEYWORD = "KEYWORD";//实体数据


    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;

    private RecyclerView mRecyclerView;

    private String keyword;//搜索关键字
    private SearchModuleBean bean;


    private SearchSelectTypeAdapter adapter;
    private List<SearchModuleBean.ListBean> child_list;

    /**
     * 跳转
     *
     * @param context
     * @param bean
     */
    public static void go(Context context, SearchModuleBean bean, String keyword) {
        Intent intent = new Intent(context, SearchSelectTypeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BEAN, bean);
        bundle.putString(KEYWORD, keyword);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_select_type);
        initView();
        initData();

    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        toolbar_iv_title.setText("请选择类别");
        handleBackEvent(toolbar_iv_back);
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bean = (SearchModuleBean) extras.getSerializable(BEAN);
            keyword = extras.getString(KEYWORD);
        }
        if (bean == null) {
            finish();
        }

        child_list = bean.getChild_list();

        adapter = new SearchSelectTypeAdapter(this,bean.getChild_list());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchModuleBean.ListBean data = bean.getChild_list().get(position);
               // 类型【1：人才驿站-职位，2：人才驿站-简历，3：房屋租售-买房，4：房屋租售-租房，5：住宿旅行-旅游，
                //6：住宿旅行-酒店，7：住宿旅行-家庭房源，8：车辆买卖-卖车，9：车辆买卖-买车】
                int type = data.getType();
                if (type == 1){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 0, 0);
                }else if (type == 2){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 1, 0);
                }else if (type == 3){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 30, 0);
                }else if (type == 4){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 31, 0);
                }else if (type == 5){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 10, 0);
                }else if (type == 6){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 15, 0);
                }else if (type == 7){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 16, 0);
                }else if (type == 8){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 40, 0);
                }else if (type == 9){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 41, 0);
                }else if (type == 10){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 70, 0);
                }else if (type == 11){
                    SearchResultActivity.go(SearchSelectTypeActivity.this, keyword, 71, 0);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
