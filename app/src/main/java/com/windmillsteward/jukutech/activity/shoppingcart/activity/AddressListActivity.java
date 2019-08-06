package com.windmillsteward.jukutech.activity.shoppingcart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.shoppingcart.adapter.AddressListActivityAdapter;
import com.windmillsteward.jukutech.activity.shoppingcart.presenter.AddressListActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.AddressListBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 *
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddressListActivity extends BaseActivity implements AddressListActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private TextView tv_add_area;

    private AddressListActivityPresenter presenter;
    private List<AddressListBean.ListBean> list;
    private AddressListActivityAdapter adapter;
    private int page;
    private int pageSize;
    private int address_id;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initView();
        initToolbar();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            address_id = extras.getInt(Define.INTENT_DATA);
            type = extras.getInt(Define.INTENT_DATA_TWO,0);
        }

        initRecyclerView();

        presenter = new AddressListActivityPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initData(getAccessToken(),1,10);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new AddressListActivityAdapter(list,type);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page,10);
                }
            }
        }, mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),1,10);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_select:
                        if (type == 1){//个人中心的收货地址传1表示不可选择
                            return;
                        }
                        Intent data = new Intent();
                        Bundle extras = new Bundle();
                        AddressListBean.ListBean bean = list.get(position);
                        extras.putString(Define.INTENT_DATA, JSON.toJSONString(bean));
                        data.putExtras(extras);
                        setResult(200, data);
                        finish();
                        break;
                    case R.id.tv_edit:
                        Bundle bundle = new Bundle();
                        AddressListBean.ListBean bean1 = list.get(position);
                        bundle.putString(Define.INTENT_DATA, JSON.toJSONString(bean1));
                        startAtvDonFinish(AddAddressActivity.class,bundle);
                        break;
                }
            }
        });
    }

    private void initToolbar() {

        toolbar_iv_title.setText("选择收货地址");
        toolbar_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelect=false;
                for (AddressListBean.ListBean listBean : list) {
                    if (listBean.isSelect()) {
                        Intent data = new Intent();
                        Bundle extras = new Bundle();
                        extras.putString(Define.INTENT_DATA, JSON.toJSONString(listBean));
                        data.putExtras(extras);
                        setResult(200, data);
                        isSelect = true;
                        finish();
                        break;
                    }
                }
                if (!isSelect) {
                    finish();
                }
            }
        });
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);
        tv_add_area = (TextView) findViewById(R.id.tv_add_area);
        tv_add_area.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(AddressListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        for (AddressListBean.ListBean listBean : list) {
            if (address_id==listBean.getAddress_id()) {
                listBean.setSelect(true);
            }
        }
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(AddressListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        for (AddressListBean.ListBean listBean : list) {
            if (address_id==listBean.getAddress_id()) {
                listBean.setSelect(true);
            }
        }
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
    }

    @Override
    public void loadNextDataSuccess(AddressListBean bean) {
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        for (AddressListBean.ListBean listBean : list) {
            if (address_id==listBean.getAddress_id()) {
                listBean.setSelect(true);
            }
        }
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void loadNextDataFailure() {
        page --;
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_area:
                startAtvDonFinish(AddAddressActivity.class);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            for (AddressListBean.ListBean listBean : list) {
                if (listBean.isSelect()) {
                    Intent data = new Intent();
                    Bundle extras = new Bundle();
                    extras.putString(Define.INTENT_DATA, JSON.toJSONString(listBean));
                    data.putExtras(extras);
                    setResult(200, data);
                    finish();
                    break;
                }
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
