package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.fragment.HouseKeeperDataQuickView;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.HousekeeperAlertsAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HouseKeeperDataQuickListPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：管家快讯列表
 * author:cyq
 * 2018-04-10
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HousekeeperAlertsListActivity extends BaseActivity implements HouseKeeperDataQuickView{

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private HouseKeeperDataQuickListPresenter presenter;

    private HousekeeperAlertsAdapter alertsAdapter;

    private List<HouseKeeperDataQuickBean.ListBean> list ;

    private int page;
    private int pageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeper_alerts);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("管家快讯");
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("0");
//        String s = strings.get(1);
    }

    private void initData(){

        list = new ArrayList<>();

        alertsAdapter = new HousekeeperAlertsAdapter(this,list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(alertsAdapter);
        alertsAdapter.setEnableLoadMore(true);

        alertsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(page);
                }
            }
        },mRecyclerView);
        alertsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    HouseKeeperDataQuickBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString(Define.INTENT_DATA, bean.getUrl());
                    startAtvDonFinish(CommonWebActivity.class, bundle);
//                    Bundle bundle = new Bundle();
//                    bundle.putString(HouseKeeperAlertsDetailActivity.NEWS_FLASH_URL, bean.getUrl());
//                    startAtvDonFinish(HouseKeeperAlertsDetailActivity.class, bundle);
                }
            }
        });
        alertsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData();
            }
        });

        if (presenter == null){
            presenter = new HouseKeeperDataQuickListPresenter(this);
        }
        presenter.initData();

    }


    @Override
    public void getHouseKeeperInitDataSuccess(HouseKeeperDataQuickBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPage_count();
        pageSize = bean.getTotal_page();
        alertsAdapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void getHouseKeeperRefreshDataSuccess(HouseKeeperDataQuickBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPage_count();
        pageSize = bean.getTotal_page();
        alertsAdapter.notifyDataSetChanged();
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void getHouseKeeperRefreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void getHouseKeeperLoadNextDataSuccess(HouseKeeperDataQuickBean bean) {
        list.addAll(bean.getList());
        alertsAdapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void getHouseKeeperLoadNextFailure() {
        page--;
        alertsAdapter.loadMoreFail();
        checkEnd();
    }

    private void checkEnd() {
        if (page>=pageSize) {
            alertsAdapter.loadMoreEnd();
        } else {
            alertsAdapter.loadMoreComplete();
        }
    }


    @Override
    public void getHouseKeeperDataQuickListSuccess(HouseKeeperDataQuickBean houseKeeperDataQuickBean) {
        //用不上
    }

    @Override
    public void getHouseKeeperDataQuickListFailed(int code, String msg) {
        //用不上
    }
}
