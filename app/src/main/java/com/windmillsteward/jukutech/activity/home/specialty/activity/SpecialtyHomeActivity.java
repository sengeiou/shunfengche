package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.SpecialtyHomeAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.presenter.SpecialtyHomeDataPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.HomeBean;
import com.windmillsteward.jukutech.bean.SeckillBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.List;

/**
 * 描述：名优特产首页
 * author:cyq
 * 2018-06-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SpecialtyHomeActivity extends BaseActivity implements SpecialtyHomeRecommendListView, SpecialtyHomeAdapter.RecyclerViewDataCallBack {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout common_refresh;

    private SpecialtyHomeAdapter adapter;

    private SpecialtyHomeDataPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_home);
        initView();
        initData();
    }

    private void initView() {

        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (SmartRefreshLayout) findViewById(R.id.common_refresh);

        handleBackEvent(toolbar_iv_back);

    }

    private void initData() {

        toolbar_iv_title.setText("名优特产");

        common_refresh.setRefreshHeader(new ClassicsHeader(this));
        common_refresh.setEnableLoadMore(false);

        presenter = new SpecialtyHomeDataPresenter(this, this);

        List<HomeBean> beanList = presenter.SpecialtyHomeRecyclerViewData();

        adapter = new SpecialtyHomeAdapter(this, beanList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        //暂时链式请求，从上到下
        presenter.getBannerList(12);


        common_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getBannerList(12);
            }
        });
    }

    //----------------------------------点击事件-------------------------------------
    @Override
    public void setOnBannerClick(SliderPictureInfo data, int position) {
        presenter.bannerClick(data);
    }

    @Override
    public void setOnGuessYouLikeClick(GuessYouLikeBean.ListBean item) {
        if (item == null) {
            return;
        }
        int commodity_id = item.getCommodity_id();
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putInt(Define.INTENT_DATA, commodity_id);
        intent.putExtras(bundle);
        intent.setClass(this, SpecialtyDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void setOnRecommendClick(SpecialtyHomeRecommendBean.ListBean listBean) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putInt(Define.INTENT_DATA, listBean.getCommodity_id());
        intent.putExtras(bundle);
        intent.setClass(this, SpecialtyDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void setOnRecommendMoreClick(SpecialtyHomeRecommendBean recommendGridBean) {
        startAtvDonFinish(SpecialtyListActivity.class);
    }

    @Override
    public void setOnSeckillGoodsDetailClick(SeckillBean.ListBean bean) {
        if (bean == null) {
            return;
        }
        //此处接秒杀商品详情
        Bundle bundle = new Bundle();
        bundle.putInt(Define.INTENT_DATA, bean.getCommodity_id());
        Intent intent = new Intent(this, SeckillDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent, bundle);
    }

    @Override
    public void setOnSeckillMoreClick(SeckillBean seckillBean) {
        if (seckillBean == null) {
            return;
        }
        //此处接秒杀列表
        startAtvDonFinish(SeckillListActivity.class);
    }

    //-------------------------------以下是数据请求回调---------------------------------------


    @Override
    public void getBannerListSuccess(List<SliderPictureInfo> list) {
        common_refresh.finishRefresh();
        if (list == null) {
            return;
        }
        adapter.updateTopBanner(list);
        presenter.loadSpcialtyClassData();

    }

    @Override
    public void getBannerListFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }


    @Override
    public void initSpecialtyClassSuccess(List<SpecialtyClassBean> beanList) {
        common_refresh.finishRefresh();
        if (beanList == null) {
            return;
        }
        adapter.updateViewPageData(beanList);
        presenter.loadSpecialtySeckillData();//请求秒杀
    }

    @Override
    public void initSpecialtyClassFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }

    @Override
    public void getSeckillDataSuccess(SeckillBean bean) {
        common_refresh.finishRefresh();
        if (bean == null) {
            return;
        }
        adapter.updateSeckillData(bean);
        presenter.initData();//请求推荐
    }

    @Override
    public void getSeckillDataFaild(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }

    @Override
    public void initDataSuccess(List<SpecialtyHomeRecommendBean> bean) {
        common_refresh.finishRefresh();
        if (bean == null) {
            return;
        }
        adapter.updateRecommendList(bean);
        presenter.getGuessYouLikeList();
    }

    @Override
    public void initDataFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }

    @Override
    public void getGuessYouLikeListSuccess(GuessYouLikeBean bean) {
        common_refresh.finishRefresh();
        if (bean == null) {
            return;
        }
        adapter.updateGuessYouLike(bean);

    }

    @Override
    public void getGuessYouLikeListFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter.countDownUtils != null) {
            adapter.countDownUtils.stopCountDown();
        }
    }
}
