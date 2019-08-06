package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.mine.adapter.WalletDetailAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.TuiGuangWalletDetailListPresenter;
import com.windmillsteward.jukutech.activity.newpage.me.TestFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.adapter.TabAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.WalletDetailBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：推广--已获得奖励
 * author:cyq
 * 2018-03-29
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class TuiGuangWalletListActivity extends BaseActivity implements View.OnClickListener, WalletListView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private RecyclerView mRecyclerView;
    private MagicIndicator magic_indicator;
    private CommonRefreshLayout common_refresh;
    private ViewPager viewPager;

    private WalletDetailAdapter adapter;

    private TuiGuangWalletDetailListPresenter presenter;

    private int type = 0;//类型【0.全部，1.底薪 2.育成奖 3.管理奖 4.分红】
    private int page;
    private int pageSize;

    private List<String> mTitleDataList;

    private List<WalletDetailBean.GroupListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_list);
        mImmersionBar.statusBarColor(R.color.color_white).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).keyboardEnable(true).init();
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);
        magic_indicator = findViewById(R.id.magic_indicator);
        viewPager = findViewById(R.id.viewpager);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("收支明细");

//        toolbar_iv_title.setOnClickListener(this);

        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("全部");
        mTitleDataList.add("底薪");
        mTitleDataList.add("育成奖");
        mTitleDataList.add("管理奖");
        mTitleDataList.add("分红");
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
//        commonNavigator.setSmoothScroll(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#394043"));
                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#3172f4"));
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                colorTransitionPagerTitleView.setTextSize(16);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        type = index;
                        presenter.refreshData(getAccessToken(), type);
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.parseColor("#3172f4"));
                return indicator;
            }
        });
        magic_indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magic_indicator, viewPager);
    }

    private void initData() {
        list = new ArrayList<>();
        adapter = new WalletDetailAdapter(this, list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page, getAccessToken(), type);
                }
            }
        }, mRecyclerView);

        List<BaseInitFragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(tabAdapter);

//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (position < list.size()) {
//
//                }
//            }
//        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {

                }
            }
        });
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(), type);
            }
        });

        presenter = new TuiGuangWalletDetailListPresenter(this);
        presenter.initData(getAccessToken(), type);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_iv_title:

                break;
        }
    }

    @Override
    public void initDataSuccess(WalletDetailBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPage_number();
        pageSize = bean.getTotal_page();
        adapter.notifyDataSetChanged();
        checkEnd();
        mRecyclerView.scrollTo(0, 0);
    }

    @Override
    public void refreshDataSuccess(WalletDetailBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPage_number();
        pageSize = bean.getTotal_page();
        adapter.notifyDataSetChanged();
        common_refresh.refreshComplete();
        checkEnd();
        mRecyclerView.scrollTo(0, 0);
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void loadNextDataSuccess(WalletDetailBean bean) {
        List<WalletDetailBean.GroupListBean> totalList = new ArrayList<>();

        List<WalletDetailBean.GroupListBean> load_more_list = bean.getList();

        for (int i = 0; i < load_more_list.size(); i++) {
            String month_id = load_more_list.get(i).getMonth_id();//3月，3条,2月2条

            List<WalletDetailBean.ListBean> wallet_list = load_more_list.get(i).getWallet_list();

            for (int j = 0; j < this.list.size(); j++) {//3月，10条

                String month_id1 = this.list.get(j).getMonth_id();

                if (month_id.equals(month_id1)) {
                    this.list.get(j).getWallet_list().addAll(wallet_list);
                }
            }
        }
        for (int i = 0; i < load_more_list.size(); i++) {

            String month_id = load_more_list.get(i).getMonth_id();//3月，3条,2月2条

            List<WalletDetailBean.ListBean> wallet_list = load_more_list.get(i).getWallet_list();
            WalletDetailBean.GroupListBean group_list = load_more_list.get(i);

            for (int j = 0; j < this.list.size(); j++) {//3月，10条

                String month_id1 = this.list.get(j).getMonth_id();

                if (!month_id.equals(month_id1)) {

                    this.list.add(group_list);
                }

            }

        }

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
    public void loadWalletCostTypeDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        TuiGuangWalletListActivity.this.type = id;
                        toolbar_iv_title.setText(text);
                        presenter.initData(getAccessToken(), type);
                    }
                })
                .show();
    }
}
