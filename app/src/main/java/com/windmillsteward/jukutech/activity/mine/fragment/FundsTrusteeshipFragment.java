package com.windmillsteward.jukutech.activity.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.activity.FundsTrusteeshipDetailActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.FundsTrusteeshipAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.FundsTrusteeshipPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：资金托管fragment
 * author:cyq
 * 2018-03-07
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class FundsTrusteeshipFragment extends BaseFragment implements FundsTrusteeshipView {

    private RecyclerView rv_content;
    private CommonRefreshLayout common_refresh;

    private int status = 1;//类别 1待审核 2托管中 3纠纷订单 4已完成
    private int page, pageSize;
    private List<FundsTrusteeshipBean.ListBean> list;

    private FundsTrusteeshipPresenter presenter;

    private FundsTrusteeshipAdapter adapter;

    public static FundsTrusteeshipFragment getInstance(int status) {
        FundsTrusteeshipFragment fragment = new FundsTrusteeshipFragment();
        Bundle args = new Bundle();
        args.putInt(Define.INTENT_DATA, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            status = arguments.getInt(Define.INTENT_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateView(R.layout.fragment_funds_trusteeship);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        rv_content = (RecyclerView) view.findViewById(R.id.rv_content);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);

        rv_content.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new FundsTrusteeshipAdapter(getContext(), list);
        rv_content.setAdapter(adapter);
        rv_content.setHasFixedSize(true);
        adapter.setEnableLoadMore(true);
        adapter.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.view_empty_order,null));
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(), page, 10, status);
                }
            }
        }, rv_content);

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(), 1, 10, status);
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FundsTrusteeshipBean.ListBean listBean = list.get(position);
                String status_name = listBean.getStatus_name();
                int id = listBean.getId();
                FundsTrusteeshipDetailActivity.go(getContext(), id);

            }
        });
    }

    public void initData() {
        presenter = new FundsTrusteeshipPresenter(this);
        presenter.initData(getAccessToken(), 1, 10, status);
    }


    @Override
    public void initDataSuccess(FundsTrusteeshipBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(FundsTrusteeshipBean bean) {
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
    public void loadNextDataSuccess(FundsTrusteeshipBean bean) {
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
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }


    @Override
    public int registStartMode() {
        return singleTask;
    }
}
