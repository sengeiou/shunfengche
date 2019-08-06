package com.windmillsteward.jukutech.activity.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.insurance.activity.BigHealthDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditPositionDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.OrderFamilyDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.OrderHotelDetailActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.MyOrderAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.MyOrderPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.MyOrderBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public class MyOrderFragment extends BaseFragment implements MyOrderView {
    private static final String STATUS = "STATUS";
    private static final int REQUEST_CODE_DELETE = 104;

    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private List<MyOrderBean.ListBean> list;
    private MyOrderAdapter adapter;
    private int status;
    private int classType;
    private int page,pageSize;

    private MyOrderPresenter presenter;

    public static MyOrderFragment getInstance(int status) {
        MyOrderFragment fragment = new MyOrderFragment();
        Bundle args = new Bundle();
        args.putInt(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    public void setClassType(int classType) {
        this.classType = classType;
        presenter.initData(getAccessToken(),1,10,classType,status);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if(arguments!=null) {
            status = arguments.getInt(STATUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myorder, container, false);
        initView(view);
        presenter = new MyOrderPresenter(this);
        presenter.initData(getAccessToken(),1,10,classType,status);
        return view;
    }

    private void initView(View view) {
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),1,10,classType,status);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new MyOrderAdapter(getContext(),list);
        adapter.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.view_empty_order,null));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page,10,classType,status);
                }
            }
        },mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyOrderBean.ListBean bean = list.get(position);
                if (bean.getOrder_type()==41) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(OrderFamilyDetailActivity.DETAIL_ID,bean.getOrder_id());
                    bundle.putInt(OrderFamilyDetailActivity.POSITION,position);
                    startAtvDonFinishForResult(OrderFamilyDetailActivity.class,bundle,REQUEST_CODE_DELETE);
                } else if(bean.getOrder_type()==61 || bean.getOrder_type()==62){
                    Bundle bundle = new Bundle();
                    bundle.putInt(OrderHotelDetailActivity.DETAIL_ID,bean.getOrder_id());
                    bundle.putInt(OrderHotelDetailActivity.POSITION,position);
                    startAtvDonFinishForResult(OrderHotelDetailActivity.class,bundle,REQUEST_CODE_DELETE);
                }else if(bean.getOrder_type()==122){
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA,bean.getRelate_id());
                    startAtvDonFinish(BigHealthDetailActivity.class, bundle);
                }
            }
        });
    }

    @Override
    public void initDataSuccess(MyOrderBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(MyOrderBean bean) {
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
    public void loadNextDataSuccess(MyOrderBean bean) {
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

    public void refreshData() {
        presenter.refreshData(getAccessToken(),1,10,classType,status);
    }
}
