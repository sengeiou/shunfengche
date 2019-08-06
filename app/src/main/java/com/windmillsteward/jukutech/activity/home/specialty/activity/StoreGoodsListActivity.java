package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.GoodsListFragmentAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.presenter.StoreGoodsListActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.GoodsListBean;
import com.windmillsteward.jukutech.bean.StoreInfoBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 店铺
 * Created by Administrator on 2018/4/14 0014.
 */

public class StoreGoodsListActivity extends BaseActivity implements StoreGoodsListActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private ImageView iv_store_thumbnail;
    private TextView tv_store_name;
    private TextView tv_area;
    private LinearLayout linear_menu;
    private TextView tv_tab1;
    private LinearLayout linear_area;
    private TextView tv_tab2;
    private LinearLayout linear_salary;
    private TextView tv_tab3;
    private ImageView iv_tab3_down;
    private ImageView iv_tab3_up;
    private LinearLayout linear_more;

    private int store_id;
    private String store_name;

    private List<GoodsListBean.ListBean> list;
    private StoreInfoBean headBean;
    private GoodsListFragmentAdapter adapter;
    private int page;
    private int pageSize;

    private StoreGoodsListActivityPresenter presenter;
    private int commodity_category_id;
    private int sort_id=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goods);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            store_id = extras.getInt(Define.INTENT_DATA);
            store_name = extras.getString(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initRecyclerView();
        initHeader();

        presenter = new StoreGoodsListActivityPresenter(this);
        presenter.initData(1, 10, store_id, commodity_category_id, sort_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode ==200) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                commodity_category_id = extras.getInt(Define.INTENT_DATA);
                String name = extras.getString(Define.INTENT_DATA_TWO);
                presenter.initData(1, 10, store_id, commodity_category_id, sort_id);
            }
        }
    }


    private void initHeader() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_store_info, mRecyclerView, false);
        iv_store_thumbnail = (ImageView) header.findViewById(R.id.iv_store_thumbnail);
        tv_store_name = (TextView) header.findViewById(R.id.tv_store_name);
        tv_area = (TextView) header.findViewById(R.id.tv_area);
        linear_menu = (LinearLayout) header.findViewById(R.id.linear_menu);
        tv_tab1 = (TextView) header.findViewById(R.id.tv_tab1);
        linear_area = (LinearLayout) header.findViewById(R.id.linear_area);
        tv_tab2 = (TextView) header.findViewById(R.id.tv_tab2);
        linear_salary = (LinearLayout) header.findViewById(R.id.linear_salary);
        tv_tab3 = (TextView) header.findViewById(R.id.tv_tab3);
        iv_tab3_down = (ImageView) header.findViewById(R.id.iv_tab3_down);
        iv_tab3_up = (ImageView) header.findViewById(R.id.iv_tab3_up);
        linear_more = (LinearLayout) header.findViewById(R.id.linear_more);
        LinearLayout linear_header = (LinearLayout) header.findViewById(R.id.linear_header);
        adapter.addHeaderView(header);
        linear_menu.setOnClickListener(this);
        linear_more.setOnClickListener(this);
        tv_tab1.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
        linear_area.setOnClickListener(this);
        linear_salary.setOnClickListener(this);
        linear_more.setOnClickListener(this);
        linear_more.setTag(0);
        linear_header.setOnClickListener(this);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new GoodsListFragmentAdapter(list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page, 10, store_id, commodity_category_id, sort_id);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA, list.get(position).getCommodity_id());
                    startAtvDonFinish(SpecialtyDetailActivity.class, bundle);
                }
            }
        });

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1, 10, store_id, commodity_category_id, sort_id);
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText(store_name);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);
    }

    @Override
    public void initDataSuccess(GoodsListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void initHeaderDataSuccess(StoreInfoBean bean) {
        this.headBean = bean;
        Glide.with(this).load(bean.getStore_thumbnail()).into(iv_store_thumbnail);
        tv_store_name.setText(bean.getStore_name());
        tv_area.setText(bean.getAddress());

    }

    @Override
    public void refreshDataSuccess(GoodsListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        common_refresh.refreshComplete();
        checkEnd();
    }


    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void loadNextDataSuccess(GoodsListBean bean) {
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void loadNextFailure() {
        page--;
        adapter.loadMoreFail();
        checkEnd();
    }


    private void checkEnd() {
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_menu:
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,store_id);
                startAtvDonFinishForResult(GoodsClassActivity.class,100, bundle);
                break;
            case R.id.linear_area:
                sort_id = 3;
                presenter.initData(1,10,store_id,commodity_category_id,sort_id);

                tv_tab1.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                tv_tab2.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                tv_tab3.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                iv_tab3_up.setImageResource(R.mipmap.icon_select_up);
                iv_tab3_down.setImageResource(R.mipmap.icon_select_down);

                linear_more.setTag(0);
                break;
            case R.id.linear_salary:
                sort_id = 4;
                presenter.initData(1,10,store_id,commodity_category_id,sort_id);

                tv_tab1.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                tv_tab2.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                tv_tab3.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                iv_tab3_up.setImageResource(R.mipmap.icon_select_up);
                iv_tab3_down.setImageResource(R.mipmap.icon_select_down);

                linear_more.setTag(0);
                break;
            case R.id.linear_more:
                int tag = (int) linear_more.getTag();
                tv_tab1.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                tv_tab2.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                tv_tab3.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
                if (tag==0) {
                    tag = 1;
                    iv_tab3_up.setImageResource(R.mipmap.icon_select_up);
                    iv_tab3_down.setImageResource(R.mipmap.icon_select_down_y);
                } else if (tag==1) {
                    tag = 2;
                    iv_tab3_up.setImageResource(R.mipmap.icon_select_up_y);
                    iv_tab3_down.setImageResource(R.mipmap.icon_select_down);
                } else if (tag==2) {
                    tag = 1;
                    iv_tab3_up.setImageResource(R.mipmap.icon_select_up);
                    iv_tab3_down.setImageResource(R.mipmap.icon_select_down_y);
                }
                linear_more.setTag(tag);
                sort_id = tag;
                presenter.initData(1,10,store_id,commodity_category_id,sort_id);
                break;
            case R.id.linear_header:
                if (headBean!=null) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable(Define.INTENT_DATA,headBean);
                    startAtvDonFinish(StoreInfoActivity.class, bundle1);
                }
                break;
        }
    }
}
