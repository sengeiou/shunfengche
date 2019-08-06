package com.windmillsteward.jukutech.activity.home.carservice.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarListFragmentView;
import com.windmillsteward.jukutech.activity.home.carservice.activity.QuickIndexCarActivity;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.CarGridAdapter;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.MoreGridViewAdapter;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.CarListFragmentPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.CarListBean;
import com.windmillsteward.jukutech.bean.RecommendCarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * File: CarListFragment.java
 * 作者: 大海
 * 创建日期: 2018/4/24 0024 11:13
 */

public class CarListFragment extends BaseFragment implements View.OnClickListener, CarListFragmentView {

    private TextView tv_tab1;
    private ImageView iv_area_point;
    private LinearLayout linear_area;
    private TextView tv_tab2;
    private ImageView iv_sex_point;
    private LinearLayout linear_sex;
    private TextView tv_tab3;
    private ImageView iv_salary_point;
    private LinearLayout linear_salary;
    private TextView tv_tab4;
    private ImageView iv_more_point;
    private LinearLayout linear_more;
    private LinearLayout linear_root_select;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private LimitHeightListView popList;
    private EasyPopup easyPopup;
    private EasyPopup morePop;
    private MyGridView gv_1;
    private MyGridView gv_2;
    private MyGridView gv_3;
    private MoreGridViewAdapter gridViewAdapter1;
    private MoreGridViewAdapter gridViewAdapter2;
    private MoreGridViewAdapter gridViewAdapter3;

    private CarGridAdapter adapter;
    private List<RecommendCarListBean> list;
    private int page,pageSize;
    private int corrSelect;
    private CarListFragmentPresenter presenter;
    // 地区id
    private int third_area_id;
    // 价格id
    private int price_id;
    private String price_name;
    private String keyword;
    // 品牌id
    private int brand_id;
    private String brand_name;
    // 公里数id
    private int mileage_id;
    // 排量id
    private int displacement_id;
    // 变速箱id
    private int gearbox_id;
    private List<Map<String,Object>> moreList1,moreList2,moreList3;

    public static CarListFragment getInstance(int brand_id,String brand_name, int price_id, String price_name, String keyword) {
        CarListFragment fragment = new CarListFragment();
        Bundle args = new Bundle();
        args.putInt("brand_id",brand_id);
        args.putString("brand_name",brand_name);
        args.putInt("price_id",price_id);
        args.putString("price_name",price_name);
        args.putString("keyword",keyword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments!=null) {
            brand_id = arguments.getInt("brand_id");
            brand_name = arguments.getString("brand_name");
            price_id = arguments.getInt("price_id");
            price_name = arguments.getString("price_name");
            keyword = arguments.getString("keyword");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_list, container, false);
        initView(view);
        initTabview();
        initRecyclerView();
        initPopup();
        initMorePopup();
        presenter = new CarListFragmentPresenter(this);
        presenter.initData(1,keyword,getCurrCityId(),third_area_id,brand_id,price_id,mileage_id,displacement_id,gearbox_id);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode == 200) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                brand_id = extras.getInt(QuickIndexCarActivity.ID);
                brand_name = extras.getString(QuickIndexCarActivity.TEXT);
                tv_tab2.setText(brand_name);
                presenter.initData(1,keyword,getCurrCityId(),third_area_id,brand_id,price_id,mileage_id,displacement_id,gearbox_id);
            }

        }
    }

    private void initMorePopup() {

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popup_more_three, null);
        morePop = new EasyPopup(getContext())
                .setContentView(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                //指定任意 ViewGroup 背景变暗
                .setDimView(common_refresh)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        iv_more_point.setRotation(0);
                    }
                })
                .createPopup();
        gv_1 = inflate.findViewById(R.id.gv_1);
        gv_2 = inflate.findViewById(R.id.gv_2);
        gv_3 = inflate.findViewById(R.id.gv_3);
        moreList1 = new ArrayList<>();
        moreList2 = new ArrayList<>();
        moreList3 = new ArrayList<>();
        gridViewAdapter1 = new MoreGridViewAdapter(getContext(),moreList1);
        gridViewAdapter2 = new MoreGridViewAdapter(getContext(),moreList2);
        gridViewAdapter3 = new MoreGridViewAdapter(getContext(),moreList3);
        gv_1.setAdapter(gridViewAdapter1);
        gv_2.setAdapter(gridViewAdapter2);
        gv_3.setAdapter(gridViewAdapter3);
        gv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                mileage_id = (int) item.get("id");
                gridViewAdapter1.setSelect(mileage_id);
            }
        });
        gv_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                displacement_id = (int) item.get("id");
                gridViewAdapter2.setSelect(displacement_id);
            }
        });
        gv_3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                gearbox_id = (int) item.get("id");
                gridViewAdapter3.setSelect(gearbox_id);
            }
        });
        inflate.findViewById(R.id.tv_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mileage_id = 0;
                displacement_id = 0;
                gearbox_id = 0;
                gridViewAdapter1.reset();
                gridViewAdapter2.reset();
                gridViewAdapter3.reset();
            }
        });
        inflate.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morePop.dismiss();
                presenter.initData(1,keyword,getCurrCityId(),third_area_id,brand_id,price_id,mileage_id,displacement_id,gearbox_id);
            }
        });
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        popList = (LimitHeightListView) inflate.findViewById(R.id.listView);

        easyPopup = new EasyPopup(getContext())
                .setContentView(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                //指定任意 ViewGroup 背景变暗
                .setDimView(common_refresh)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if (corrSelect == 0) {
                            iv_area_point.setRotation(0);
                        } else if (corrSelect == 2) {
                            iv_salary_point.setRotation(0);
                        } else if (corrSelect==3) {
                            iv_more_point.setRotation(0);
                        }
                    }
                })
                .createPopup();

        popList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                easyPopup.dismiss();
                if (corrSelect == 0) {
                    ThirdAreaBean item = (ThirdAreaBean) parent.getAdapter().getItem(position);
                    third_area_id =  item.getThird_area_id();
                    tv_tab1.setText(item.getThird_area_name());
                }else if (corrSelect==2) {
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    price_id = (int) item.get("id");
                    tv_tab3.setText((String) item.get("text"));
                }
                presenter.initData(1,keyword,getCurrCityId(),third_area_id,brand_id,price_id,mileage_id,displacement_id,gearbox_id);
            }
        });
    }

    private void initTabview() {
        linear_area.setOnClickListener(this);
        tv_tab1.setText("区域");
        linear_sex.setOnClickListener(this);
        tv_tab2.setText((TextUtils.isEmpty(brand_name))?"品牌":brand_name);
        linear_salary.setOnClickListener(this);
        tv_tab3.setText(TextUtils.isEmpty(price_name)?"价格":price_name);
        linear_more.setOnClickListener(this);
        tv_tab4.setText("筛选");
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new CarGridAdapter(getContext(),list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1,keyword,getCurrCityId(),third_area_id,brand_id,price_id,mileage_id,displacement_id,gearbox_id);
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(page,keyword,getCurrCityId(),third_area_id,brand_id,price_id,mileage_id,displacement_id,gearbox_id);
                }
            }
        },mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,list.get(position).getCar_id());
                startAtvDonFinish(CarDetailActivity.class, bundle);
            }
        });
    }

    private void initView(View view) {
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        iv_area_point = (ImageView) view.findViewById(R.id.iv_area_point);
        linear_area = (LinearLayout) view.findViewById(R.id.linear_area);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        iv_sex_point = (ImageView) view.findViewById(R.id.iv_sex_point);
        linear_sex = (LinearLayout) view.findViewById(R.id.linear_sex);
        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
        iv_salary_point = (ImageView) view.findViewById(R.id.iv_salary_point);
        linear_salary = (LinearLayout) view.findViewById(R.id.linear_salary);
        tv_tab4 = (TextView) view.findViewById(R.id.tv_tab4);
        iv_more_point = (ImageView) view.findViewById(R.id.iv_more_point);
        linear_more = (LinearLayout) view.findViewById(R.id.linear_more);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_area:
                presenter.loadAreaData(getCurrCityId());
                corrSelect = 0;
                break;
            case R.id.linear_sex:
                startActivityForResult(new Intent(getContext(),QuickIndexCarActivity.class),100);
                break;
            case R.id.linear_salary:
                presenter.loadPriceData();
                corrSelect = 2;
                break;
            case R.id.linear_more:
                presenter.loadMoreCarData();
                break;

        }
    }

    @Override
    public void initDataSuccess(CarListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(CarListBean bean) {
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
    public void loadNextDataSuccess(CarListBean bean) {
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
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
    public void loadAreaDataSuccess(List<ThirdAreaBean> list) {
        ThirdAreaBean areaBean = new ThirdAreaBean();
        areaBean.setThird_area_id(0);
        areaBean.setThird_area_name("全部");
        list.add(0, areaBean);
        AreaPopupAdapter adapter = new AreaPopupAdapter(getContext(), list);
        popList.setAdapter(adapter);
        if (easyPopup != null) {
            easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_area_point.setRotation(180);
        }
    }

    @Override
    public void loadPriceDataSuccess(List<Map<String, Object>> maps) {
        popList.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_salary_point.setRotation(180);
    }

    @Override
    public void loadMoreClassDataSuccess(List<Map<String, Object>> maps,List<Map<String, Object>> maps2,List<Map<String, Object>> maps3) {
        moreList1.clear();
        moreList1.addAll(maps);
        moreList2.clear();
        moreList2.addAll(maps2);
        moreList3.clear();
        moreList3.addAll(maps3);
        gridViewAdapter1.setData(moreList1);
        gridViewAdapter1.setSelect(mileage_id);
        gridViewAdapter2.setData(moreList2);
        gridViewAdapter2.setSelect(displacement_id);
        gridViewAdapter3.setData(moreList3);
        gridViewAdapter3.setSelect(gearbox_id);
//
//        gridViewAdapter1.notifyDataSetChanged();
//        gridViewAdapter2.notifyDataSetChanged();
//        gridViewAdapter3.notifyDataSetChanged();

        gv_1.setAdapter(gridViewAdapter1);
        gv_2.setAdapter(gridViewAdapter2);
        gv_3.setAdapter(gridViewAdapter3);

        if (morePop != null) {
            morePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            morePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_more_point.setRotation(180);
        }
    }
}
