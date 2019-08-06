package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.AfterSalesListActivityAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.AfterSealsListActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.AfterSealsListBean;
import com.windmillsteward.jukutech.bean.MyOrderBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public class AfterSalesListActivity extends BaseActivity implements AfterSealsListActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private AfterSealsListActivityPresenter presenter;

    private List<AfterSealsListBean.ListBean> list;
    private AfterSalesListActivityAdapter adapter;
    private int page;
    private int pageSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sales_list);
        initView();
        initToolbar();
        initRecyclerView();

        presenter = new AfterSealsListActivityPresenter(this);
        presenter.initData(getAccessToken(),1,10);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new AfterSalesListActivityAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),1,10);
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page,10);
                }
            }
        },mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AfterSealsListBean.ListBean listBean = list.get(position);
                if (listBean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA,listBean.getRecord_id());
                    startAtvDonFinish(AfterSalesDetailActivity.class, bundle);
                }
            }
        });

    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("售后订单");
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
    public void initDataSuccess(AfterSealsListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(AfterSealsListBean bean) {
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
    public void loadNextDataSuccess(AfterSealsListBean bean) {
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        checkEnd();
    }

    @Override
    public void loadNextDataFailure() {
        page--;
        adapter.loadMoreFail();
        checkEnd();
    }

    private void checkEnd() {
        if (page>=pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }
}
