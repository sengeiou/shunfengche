package com.windmillsteward.jukutech.activity.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.activity.LogisticsActivity;
import com.windmillsteward.jukutech.activity.mine.activity.ShoppingContinuePayActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.SpecialtyOrderListFragmentAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.SpecialtyOrderListFragmentPresenter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.SpecialtyOrderListBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 *
 * Created by Administrator on 2018/4/18 0018.
 */

public class SpecialtyOrderListFragment extends LazyLoadFragment implements SpecialtyOrderListFragmentView {

    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private int order_status;

    private SpecialtyOrderListFragmentPresenter presenter;
    private List<SpecialtyOrderListBean.ListBean> list;
    private SpecialtyOrderListFragmentAdapter adapter;
    private int page;
    private int pageSize;

    /**
     * 订单状态：【1：待付款，2：待发货，3：待收货，4：已完成，5：已完成，不能申请售后，6：已取消】
     * @param order_status 【1：待付款，2：待发货，3：待收货，4：已完成，5：已完成，不能申请售后，6：已取消】
     * @return SpecialtyOrderListFragment
     */
    public static SpecialtyOrderListFragment getInstance(int order_status) {
        SpecialtyOrderListFragment fragment = new SpecialtyOrderListFragment();
        Bundle args = new Bundle();
        args.putInt(Define.INTENT_DATA,order_status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments!=null) {
            order_status = arguments.getInt(Define.INTENT_DATA);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.initData(getAccessToken(),1,10,order_status);
    }

    private void initRecyclerView() {
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),1,10,order_status);
            }
        });
        list = new ArrayList<>();
        adapter = new SpecialtyOrderListFragmentAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page,10,order_status);
                }
            }
        }, mRecyclerView);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                SpecialtyOrderListBean.ListBean bean = list.get(position);
//                Bundle bundle = new Bundle();
//                bundle.putInt(Define.INTENT_DATA,bean.getOrder_id());
//                startAtvDonFinish(SpecialtyOrderDetailActivity.class, bundle);
//            }
//        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                final SpecialtyOrderListBean.ListBean bean = list.get(position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        new AlertDialog(getContext()).builder()
                                .setTitle("提示")
                                .setMsg("确定删除该订单？")
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        presenter.deleteOrder(getAccessToken(), bean.getOrder_id());
                                    }
                                }).show();
                        break;
                    case R.id.tv_close:
                        new AlertDialog(getContext()).builder()
                                .setTitle("提示")
                                .setMsg("确定关闭该订单？")
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        presenter.closeOrder(getAccessToken(),list.get(position).getOrder_id());
                                    }
                                }).show();
                        break;
                    case R.id.tv_look:
                        Bundle bundle0 = new Bundle();
                        bundle0.putString(Define.INTENT_DATA,bean.getOrder_sn());
                        bundle0.putString(Define.INTENT_DATA_TWO,bean.getLogistics_single_number());
                        startAtvDonFinish(LogisticsActivity.class, bundle0);
                        break;
                    case R.id.tv_continue:
                        String total_pay_fee = bean.getTotal_pay_fee();
                        String order_sn = bean.getOrder_sn();
                        String store_name = bean.getStore_name();
                        Bundle bundle = new Bundle();
                        bundle.putString(Define.INTENT_DATA,total_pay_fee);
                        bundle.putString(Define.INTENT_DATA_TWO,order_sn);
                        bundle.putString(Define.INTENT_DATA_THREE,store_name);
                        startAtvDonFinishForResult(ShoppingContinuePayActivity.class, bundle, 100);
                        break;
                    case R.id.tv_sure:
                        new AlertDialog(getContext()).builder()
                                .setTitle("提示")
                                .setMsg("确定收货吗？")
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        presenter.confirmOrder(getAccessToken(),list.get(position).getOrder_id());
                                    }
                                }).show();
                        break;
                }
            }
        });
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_specialty_order_list;
    }

    @Override
    protected void lazyLoad() {
        if (isLoad) {
            return;
        }
        presenter.initData(getAccessToken(),1,10,order_status);
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
        initRecyclerView();
        presenter = new SpecialtyOrderListFragmentPresenter(this);

    }

    @Override
    public void initDataSuccess(SpecialtyOrderListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(SpecialtyOrderListBean bean) {
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
    public void loadNextDataSuccess(SpecialtyOrderListBean bean) {
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

    @Override
    public void deleteOrderSuccess() {
        presenter.refreshData(getAccessToken(),1,10,order_status);
    }

    @Override
    public void closeOrderSuccess() {
        presenter.refreshData(getAccessToken(),1,10,order_status);
    }

    @Override
    public void confirmOrderSuccess() {
        presenter.refreshData(getAccessToken(),1,10,order_status);
    }

    private void checkEnd() {
        if (page>=pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }
}
