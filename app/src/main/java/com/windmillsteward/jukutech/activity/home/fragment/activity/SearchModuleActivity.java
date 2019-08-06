package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchResultActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.SearchModuleAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.SearchModulePresenter;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.SearchModuleBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：根据首页搜索关键字和所选模块跳到这个页面
 * author:cyq
 * 2018-03-30
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SearchModuleActivity extends BaseActivity implements View.OnClickListener, SearchModuleView {

    public static final String TYPE = "TYPE";
    public static final String KEYWORD = "KEYWORD";//上个页面传过来的搜索关键字

    private ImageView iv_back;
    private TextView tv_searchHint;
    private RecyclerView mRecyclerView;

    private SearchModulePresenter presenter;

    private SearchModuleAdapter adapter;

    private List<SearchModuleBean> list;
    private int type;//模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅行】
    private String keyword;//搜索关键字


    /**
     * 跳转
     *
     * @param context
     * @param type    模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅行】
     * @param keyword 搜索关键字
     */
    public static void go(Activity context, int type, String keyword) {
        Intent intent = new Intent(context, SearchModuleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        bundle.putString(KEYWORD, keyword);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_module_list);
        initView();
        initData();
    }


    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        handleBackEvent(iv_back);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(TYPE);
            keyword = extras.getString(KEYWORD);
            tv_searchHint.setText(TextUtils.isEmpty(keyword) ? "搜索" : keyword);
        }
    }


    private void initData() {
        list = new ArrayList<>();
        adapter = new SearchModuleAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchModuleBean bean = list.get(position);
                int type = bean.getType();
                //大模块类型：0全部 1：智慧生活 2：房屋信息
                if (type == 1) {
                    IntelligentFamilyListActivity.go(SearchModuleActivity.this, keyword);
                } else if (type == 2) {
                    HouseRentingActivity.go(SearchModuleActivity.this, keyword, 0,  "");
                }
            }
        });
        presenter = new SearchModulePresenter(this);
        presenter.searchModuleData(keyword, type, (int) Hawk.get(Define.CURR_CITY_ID, 0));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void getSearchModuleListSuccess(List<SearchModuleBean> bean) {
        if (bean == null) {
            return;
        }
        list.clear();
        list.addAll(bean);
        adapter.setNewData(list);
    }

    @Override
    public void getSearchModuleListFailed(int code, String msg) {
        showTips(msg, 1);
    }
}
