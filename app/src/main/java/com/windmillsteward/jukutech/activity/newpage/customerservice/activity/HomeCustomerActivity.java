package com.windmillsteward.jukutech.activity.newpage.customerservice.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.PhoneUtils;
import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.newpage.customerservice.presenter.HomeCustomerActPresenter;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.HomeCustomerClassicModel;
import com.windmillsteward.jukutech.bean.HomeCustomerModel;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DistanceUtils;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeCustomerActivity extends BaseInitActivity implements HomeCustomerView, EasyPermissions.PermissionCallbacks {
    public static final int TYPE_HOME = 0;
    public static final int TYPE_CHILD = 1;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;
    private RecyclerView headerRecyclerView;
    private TextView tv_search;
    private TabLayout tl_1;
    private TextView tv_fujin;

    private HomeCustomerActPresenter presenter;

    private int type;

    //搜索距离(1.三公里，2.五公里，3.10公里)
    private int search_distance = 1;
    private int class_id = -1;

    private String[] mTitles = {"3公里", "5公里", "10公里"};

    private HeaderRecyclerViewAdater headerAadpter;
    private RecyclerViewAdapter adapter;

    private List<HomeCustomerClassicModel> headerList;
    private List<HomeCustomerModel> list;

    @Override
    protected void initView(View view) {
        if (getIntent() != null) {
            type = getIntent().getIntExtra("type", 0);
        }

        if (type == TYPE_HOME) {
            setMainTitle("便民服务");
        } else {
            if (getIntent() != null) {
                class_id = getIntent().getIntExtra("class_id", -1);
                String class_name = getIntent().getStringExtra("class_name");
                if (!StringUtil.isEmpty(class_name)) {
                    setMainTitle(class_name);
                }
            }
        }

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                addData();
            }
        });


    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    //添加头部视图
    private void addHeaderView() {
        final View headerView = View.inflate(this, R.layout.header_recycler_home_customer_list, null);
        headerRecyclerView = headerView.findViewById(R.id.recyclerView);
        tv_search = headerView.findViewById(R.id.tv_search);
        tl_1 = headerView.findViewById(R.id.tl);
        tv_fujin = headerView.findViewById(R.id.tv_fujin);

        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = tl_1.newTab();
            tab.setText(mTitles[i]);
            tl_1.addTab(tab, i == 0 ? true : false);
        }

        if (type == TYPE_HOME) {
            headerRecyclerView.setVisibility(View.VISIBLE);
            tv_search.setVisibility(View.VISIBLE);
            headerView.findViewById(R.id.view_line).setVisibility(View.VISIBLE);
            headerList = new ArrayList<>();
            headerAadpter = new HeaderRecyclerViewAdater(headerList);
            headerRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            headerRecyclerView.setAdapter(headerAadpter);

            headerAadpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(HomeCustomerActivity.this, HomeCustomerActivity.class);
                    intent.putExtra("class_id", headerList.get(position).getClass_id());
                    intent.putExtra("type", TYPE_CHILD);
                    intent.putExtra("class_name", headerList.get(position).getService_name());
                    startActivity(intent);
                }
            });
        } else {
            headerRecyclerView.setVisibility(View.GONE);
            tv_search.setVisibility(View.GONE);
            tv_fujin.setVisibility(View.GONE);
            headerView.findViewById(R.id.view_line).setVisibility(View.GONE);
        }

        adapter.setHeaderView(headerView);

        tl_1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                search_distance = tab.getPosition() + 1;
                showDialog();
                getListData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAtvDonFinish(HomeCustomerSearchActivity.class);
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home_customer;
    }

    @Override
    protected void initData() {
        presenter = new HomeCustomerActPresenter(this);

        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndText("—— 到底啦 ——");
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addHeaderView();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeCustomerModel bean = list.get(position);
                if (bean != null) {
                    if (!TextUtils.isEmpty(bean.getDesc_url())) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Define.INTENT_DATA, bean.getDesc_url());
                        startAtvDonFinish(CommonWebActivity.class, bundle);
                    }
                }
            }
        });
        addData();
    }

    /**
     * 获取数据
     */
    private void addData() {
        if (type == TYPE_HOME) {
            //获取头部数据
            presenter.getHeaderData();
        }

        getListData();
    }

    /**
     * 获取列表数据
     */
    private void getListData() {
        String longLat = KV.get(Define.CURR_LONGLAT_ADDRESS, "");
        if (!StringUtil.isEmpty(longLat)) {
            String longitude = longLat.split(",")[0];
            String latitude = longLat.split(",")[1];
            presenter.getListData(longitude, latitude, search_distance, class_id);
        } else {
            showTips("请确认是否开启了定位权限", 1);
            showErrorView();
        }
    }

    @Override
    protected void refreshPageData() {
        addData();
    }

    @Override
    public void showHeaderData(List<HomeCustomerClassicModel> list) {
        headerList.clear();
        headerList.addAll(list);
        headerAadpter.notifyDataSetChanged();
        commonRefresh.refreshComplete();
    }

    @Override
    public void showListData(List<HomeCustomerModel> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.loadMoreEnd();
        adapter.notifyDataSetChanged();
        commonRefresh.refreshComplete();
    }

    class HeaderRecyclerViewAdater extends BaseQuickAdapter<HomeCustomerClassicModel, BaseViewHolder> {

        public HeaderRecyclerViewAdater(@Nullable List<HomeCustomerClassicModel> data) {
            super(R.layout.item_recycler_home_customer_header, data, false);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeCustomerClassicModel item) {
            helper.setText(R.id.tv_title, item.getService_name());
            Glide.with(HomeCustomerActivity.this)
                    .load(item.getPic_url()).into((ImageView) helper.getView(R.id.iv_logo));
        }
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<HomeCustomerModel, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HomeCustomerModel> data) {
            super(R.layout.item_recycler_home_customer_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomeCustomerModel item) {
            helper.setText(R.id.tv_title, item.getService_name())
                    .setText(R.id.tv_phone, item.getContact_tel())
                    .setText(R.id.tv_distance, DistanceUtils.getFormatDistance(item.getDistance()));

            Glide.with(HomeCustomerActivity.this)
                    .load(item.getPic_url()).into((ImageView) helper.getView(R.id.iv_avatar));


            helper.getView(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapNaviUtils.showDaoHangDialog(HomeCustomerActivity.this, Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), item.getService_address());

                }
            });
            helper.getView(R.id.tv_phone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapNaviUtils.showDaoHangDialog(HomeCustomerActivity.this, Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), item.getService_address());

                }
            });
            helper.getView(R.id.tv_distance).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MapNaviUtils.showDaoHangDialog(HomeCustomerActivity.this, Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), item.getService_address());

                }
            });

            helper.getView(R.id.iv_play).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phoneStr = item.getContact_tel();
                    getPermission();
                }
            });
        }
    }

    private String phoneStr;

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(Define.ALL_JURISDICTION_APPLY)
    private void getPermission() {
        String[] perms = {android.Manifest.permission.CALL_PHONE};
        // 检查应用程序已经具有所需的权限
        if (EasyPermissions.hasPermissions(this, perms)) {// 有了
            if (!TextUtils.isEmpty(phoneStr)) {
                final BaseDialog dialog = new BaseDialog(this);
                dialog.showTwoButton("提示", "是否拨打以下电话\n" + phoneStr, "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        PhoneUtils.dial(phoneStr);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        } else {
            // 还没
            EasyPermissions.requestPermissions(this, "需要获取应用的相关权限",
                    Define.ALL_JURISDICTION_APPLY, perms);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // 某些权限被拒绝
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < perms.size(); i++) {
            String s = perms.get(i);
            switch (s) {
                case android.Manifest.permission.CALL_PHONE:// 照相机
                    stringBuffer.append("打电话");
                    break;
            }
        }
        //（可选）检查用户是否拒绝任何权限，并检查“不要再询问”。
        // 这将显示一个对话框，指示他们在应用设置中启用权限。
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            try {
                new AppSettingsDialog.Builder(this).setTitle("需要权限").setRationale(stringBuffer.toString() + "未授予"
                        + "继续运行可能会崩溃").build().show();
            } catch (java.lang.Exception Exception) {
                Toast.makeText(this, "当前系统不支持此方法,请手动设置应用权限", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        getPermission();
    }
}
