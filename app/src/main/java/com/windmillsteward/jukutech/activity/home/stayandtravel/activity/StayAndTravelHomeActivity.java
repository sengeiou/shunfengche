package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.TravelListAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.StayAndTravelHomePresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.BannerBean;
import com.windmillsteward.jukutech.bean.TravelListBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：住宿旅游首页
 * 时间：2018/1/25
 * 作者：xjh
 */

public class StayAndTravelHomeActivity extends BaseActivity implements StayAndTravelHomeView, View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private RecyclerView mRecyclerView;
    private FlyBanner flyBanner;
    private LinearLayout ll_around;
    private LinearLayout ll_group;
    private LinearLayout ll_free;
    private LinearLayout ll_one_day;
    private LinearLayout ll_hotel;

    private TravelListAdapter adapter;
    private StayAndTravelHomePresenter presenter;
    private List<TravelListBean.ListBean> list;
    private List<BannerBean> banners = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stayandtravelhome);
        initView();
        initToolbar();
        initRecyclerView();
        initHeader();
        initFooter();
        presenter = new StayAndTravelHomePresenter(this);

        presenter.initBannerData();
        presenter.initData(getCurrCityId());
    }

    private void initFooter() {
        View footer = LayoutInflater.from(this).inflate(R.layout.view_foot_end, (ViewGroup) mRecyclerView.getParent(), false);
        adapter.addFooterView(footer);
    }

    private void initHeader() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_stayandtravel, (ViewGroup) mRecyclerView.getParent(), false);
        flyBanner = (FlyBanner) header.findViewById(R.id.flyBanner);
        ll_around = (LinearLayout) header.findViewById(R.id.ll_around);
        ll_around.setOnClickListener(this);
        ll_group = (LinearLayout) header.findViewById(R.id.ll_group);
        ll_group.setOnClickListener(this);
        ll_free = (LinearLayout) header.findViewById(R.id.ll_free);
        ll_free.setOnClickListener(this);
        ll_one_day = (LinearLayout) header.findViewById(R.id.ll_one_day);
        ll_one_day.setOnClickListener(this);
        ll_hotel = (LinearLayout) header.findViewById(R.id.ll_hotel);
        ll_hotel.setOnClickListener(this);

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height = 575*layoutParams.width /1080  ;
        flyBanner.setLayoutParams(layoutParams);

        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position>=0&&position<banners.size()) {
                    BannerBean bean = banners.get(position);
                    if (bean.getJump_type()==1) {  // 跳转网页
                        Bundle bundle = new Bundle();
                        bundle.putString(Define.INTENT_DATA,bean.getHref_url());
                        startAtvDonFinish(CommonWebActivity.class, bundle);
                    } else if (bean.getJump_type()==2) {  // 跳转对应界面，如详情
                        Bundle bundle = new Bundle();
                        bundle.putInt(TravelDetailActivity.DETAIL_ID,bean.getJump_id());
                        startAtvDonFinish(TravelDetailActivity.class, bundle);
                    }
                }
            }
        });
        adapter.addHeaderView(header);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new TravelListAdapter(this,list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    TravelListBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(TravelDetailActivity.DETAIL_ID,bean.getTravel_id());
                    startAtvDonFinish(TravelDetailActivity.class,bundle);
                }
            }
        });
        adapter.setEnableLoadMore(false);
        adapter.removeEmptyView();
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText("搜索");
        linear_search.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }

    @Override
    public void initBannerDataSuccess(List<BannerBean> list) {
        List<String> urls = new ArrayList<>();
        for (BannerBean bean : list) {
            urls.add(bean.getPic_url());
        }
        if (urls.size()>0) {
            banners.addAll(list);
            flyBanner.setImagesUrl(urls);
        }
    }

    /**
     * 获取推荐旅游数据成功
     *
     * @param bean 固定返回最新的20条
     */
    @Override
    public void initDataSuccess(TravelListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        adapter.setNewData(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_search:
                startAtvDonFinish(StayAndTravelSearchActivity.class);
                break;
            case R.id.ll_around:
                TravelListActivity.go(this,44,"");
                break;
            case R.id.ll_group:
                TravelListActivity.go(this,45,"");
                break;
            case R.id.ll_free:
                TravelListActivity.go(this,46,"");
                break;
            case R.id.ll_one_day:
                TravelListActivity.go(this,47,"");
                break;
            case R.id.ll_hotel:
                startAtvDonFinish(HotelAndHouseHomeActivity.class);
                break;
        }
    }
}
