package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.FinancingDetailActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.LoanDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.BuyCarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.RentCarDetailActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyDetailActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.ResumeDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.StoreGoodsListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailActivity;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkDetailActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.MyCollectAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.MyCollectPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.HouseSealListBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
import com.windmillsteward.jukutech.bean.MyCollectBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：我的收藏
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public class MyCollectActivity extends BaseActivity implements View.OnClickListener, MyCollectView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private ImageView iv_select;
    private TextView tv_delete;
    private LinearLayout linear_bottom;

    private List<MyCollectBean.ListBean> list;
    private MyCollectAdapter adapter;
    private int page;
    private int pageSize;

    private MyCollectPresenter presenter;

    private boolean isEdit;
    private boolean isSelectAll;
    private List<Integer> deleteIds;
    private TextView tv_select_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);
        initView();
        initToolbar();
        initRecyclerView();

        presenter = new MyCollectPresenter(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initData(getAccessToken(), 1, 10);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("我的收藏");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setText("编辑");
        toolbar_tv_right.setOnClickListener(this);
        toolbar_tv_right.setTextColor(Color.parseColor("#101d37"));
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);
        iv_select = (ImageView) findViewById(R.id.iv_select);
        iv_select.setOnClickListener(this);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(this);
        linear_bottom = (LinearLayout) findViewById(R.id.linear_bottom);
        linear_bottom.setOnClickListener(this);
        tv_select_all = (TextView) findViewById(R.id.tv_select_all);
        tv_select_all.setOnClickListener(this);
    }

    private void initRecyclerView() {
        deleteIds = new ArrayList<>();
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(), 1, 10);
            }
        });
        list = new ArrayList<>();
        adapter = new MyCollectAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(), page, 10);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_select:
                        list.get(position).setSelect(!list.get(position).isSelect());
                        if (list.get(position).isSelect()) {
                            deleteIds.add(list.get(position).getCollect_id());
                        } else {
                            deleteIds.remove(Integer.valueOf(list.get(position).getCollect_id()));
                        }
                        if (deleteIds.size()==0) {
                            tv_delete.setTextColor(ContextCompat.getColor(MyCollectActivity.this,R.color.color_text_78));
                            tv_delete.setEnabled(false);
                        } else {
                            tv_delete.setTextColor(ContextCompat.getColor(MyCollectActivity.this,R.color.color_white));
                            tv_delete.setEnabled(true);
                        }
                        if (deleteIds.size()==list.size()) {
                            isSelectAll = true;
                            tv_select_all.setText("清空");
                            iv_select.setImageResource(R.mipmap.icon_select);
                        } else {
                            isSelectAll = false;
                            tv_select_all.setText("全选");
                            iv_select.setImageResource(R.mipmap.icon_select_n);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (isEdit) {
                    return;
                }
                MyCollectBean.ListBean listBean = list.get(position);
                int type = listBean.getType();
                int collect_id = listBean.getRelation_id();
                Bundle bundle = new Bundle();
                //收藏类型：	模块类型：1：智慧生活 2.房屋
                switch (type) {
                    case 1:
                        bundle.putInt(IntelligentFamilyDetailActivity.DETAIL_ID, collect_id);
                        startAtvDonFinish(IntelligentFamilyDetailActivity.class, bundle);
                        break;
                    case 2:
                        bundle.putInt(HouseDetailActivity.DETAIL_ID, collect_id);
                        startAtvDonFinish(HouseDetailActivity.class, bundle);
                        break;
                }
            }
        });
    }


    @Override
    public void initDataSuccess(MyCollectBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
        adapter.notifyDataSetChanged();

        checkEnd();
    }

    @Override
    public void refreshDataSuccess(MyCollectBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
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
    public void loadNextDataSuccess(MyCollectBean bean) {
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
    public void deleteCollectSuccess() {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).isSelect()) {
                list.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
        if (list.size()==0) {
            presenter.initData(getAccessToken(),1,10);
        }
        deleteIds.clear();
        iv_select.setImageResource(R.mipmap.icon_select_n);
        tv_delete.setEnabled(false);
        tv_delete.setTextColor(ContextCompat.getColor(this,R.color.color_text_78));
    }

    private void checkEnd() {
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_tv_right:
                if (!isEdit){
                    if (adapter != null) {
                        adapter.setEdit(true);
                    }
                    linear_bottom.setVisibility(View.VISIBLE);
                    toolbar_tv_right.setText("取消");
                    isEdit = true;
                    tv_delete.setEnabled(false);
                    tv_delete.setTextColor(ContextCompat.getColor(this,R.color.color_text_78));
                } else {
                    if (adapter != null) {
                        adapter.setEdit(false);
                    }
                    linear_bottom.setVisibility(View.GONE);
                    toolbar_tv_right.setText("编辑");
                    for (MyCollectBean.ListBean bean : list) {
                        bean.setSelect(false);
                    }
                    deleteIds.clear();
                    adapter.notifyDataSetChanged();
                    isEdit = false;
                }

                break;
            case R.id.iv_select:
                if (isSelectAll) {
                    for (MyCollectBean.ListBean bean : list) {
                        bean.setSelect(false);
                    }
                    deleteIds.clear();
                    adapter.notifyDataSetChanged();
                    isSelectAll = false;
                    tv_select_all.setText("全选");
                    iv_select.setImageResource(R.mipmap.icon_select_n);
                } else {
                    for (MyCollectBean.ListBean bean : list) {
                        bean.setSelect(true);
                        deleteIds.add(bean.getCollect_id());
                    }
                    adapter.notifyDataSetChanged();
                    isSelectAll = true;
                    tv_select_all.setText("清空");
                    iv_select.setImageResource(R.mipmap.icon_select);
                }
                if (deleteIds.size()==0) {
                    tv_delete.setTextColor(ContextCompat.getColor(MyCollectActivity.this,R.color.color_text_78));
                    tv_delete.setEnabled(false);
                } else {
                    tv_delete.setTextColor(ContextCompat.getColor(MyCollectActivity.this,R.color.color_white));
                    tv_delete.setEnabled(true);
                }
                break;
            case R.id.tv_delete:
                new AlertDialog(this).builder()
                        .setTitle("提示")
                        .setMsg("确定删除吗")
                        .setCancelable(true)
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                presenter.deleteCollect(getAccessToken(),deleteIds);
                            }
                        })
                        .show();
                break;
        }
    }
}
