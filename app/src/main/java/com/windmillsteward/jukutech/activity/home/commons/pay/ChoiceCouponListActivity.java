package com.windmillsteward.jukutech.activity.home.commons.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.MyCouponBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：
 * 时间：2018/3/26/026
 * 作者：xjh
 */
public class ChoiceCouponListActivity extends BaseActivity implements ChoiceCouponListActivityView, View.OnClickListener {

    public static final String CURR_PRICE = "CURR_PRICE";
    public static final String SELECT_ID = "SELECT_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_select;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private List<MyCouponBean.ListBean> list;
    private ChoiceCouponListAdapter adapter;
    private int page,pageSize;
    private float curr_price; // 如果是选优惠券进来，传递过来的价格
    private int select_id;

    private ChoiceCouponListActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicecoupon_list);

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null) {
            curr_price = arguments.getFloat(CURR_PRICE,-1);
            select_id = arguments.getInt(SELECT_ID);
        }

        initView();
        initToolbar();
        initRecyclerView();

        presenter = new ChoiceCouponListActivityPresenter(this);
        presenter.initData(getAccessToken(),1,10,0);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new ChoiceCouponListAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;

                }
            }
        },mRecyclerView);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page,10,0);
                }
            }
        },mRecyclerView);

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),1,10,0);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyCouponBean.ListBean listBean = list.get(position);
//                if (curr_price<Float.valueOf(listBean.getCoupon_money())) {
//                    showTips("无法使用该优惠券",1);
//                } else {
//                    Intent data = new Intent();
//                    Bundle extras = new Bundle();
//                    extras.putInt(SELECT_ID,listBean.getReceive_id());
//                    data.putExtras(extras);
//                    setResult(200, data);
//                    finish();
//                }
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("选择优惠券");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_select = (ImageView) findViewById(R.id.iv_select);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);

        if (select_id!=0) {
            iv_select.setImageResource(R.mipmap.icon_select_n);
        } else {
            iv_select.setImageResource(R.mipmap.icon_select);
        }
        iv_select.setOnClickListener(this);
    }


    @Override
    public void initDataSuccess(MyCouponBean bean) {
        list.clear();
        list.addAll(bean.getList());
        for (MyCouponBean.ListBean listBean : list) {
            if (select_id==listBean.getReceive_id()) {
                listBean.setSelect(true);
            }
        }
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(MyCouponBean bean) {
        list.clear();
        list.addAll(bean.getList());
        for (MyCouponBean.ListBean listBean : list) {
            if (select_id==listBean.getReceive_id()) {
                listBean.setSelect(true);
            }
        }
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
    public void loadNextDataSuccess(MyCouponBean bean) {
        list.addAll(bean.getList());
        for (MyCouponBean.ListBean listBean : list) {
            if (select_id==listBean.getReceive_id()) {
                listBean.setSelect(true);
            }
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_select:
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(SELECT_ID,0);
                data.putExtras(extras);
                setResult(200, data);
                finish();
                break;
        }
    }
}
