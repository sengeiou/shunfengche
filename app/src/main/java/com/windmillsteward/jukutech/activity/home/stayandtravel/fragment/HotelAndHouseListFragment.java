package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.HotelAndHouseAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.HotelAndHouseListPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.FourthAreaBean;
import com.windmillsteward.jukutech.bean.HotelAndHouseBean;
import com.windmillsteward.jukutech.bean.ThirdAndFourthAreaBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：
 * 时间：2018/2/7
 * 作者：xjh
 */

public class HotelAndHouseListFragment extends BaseFragment implements HotelAndHouseListView,View.OnClickListener {

    private TextView tv_tab1;
    private ImageView iv_area_point;
    private LinearLayout linear_area;
    private TextView tv_tab2;
    private ImageView iv_salary_point;
    private LinearLayout linear_salary;
    private TextView tv_tab3;
    private ImageView iv_more_point;
    private LinearLayout linear_more;
    private LinearLayout linear_root_select;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private EasyPopup areaEasyPopup;
    private LimitHeightListView popListViewMenu;
    private LimitHeightListView popListViewData;
    private EasyPopup easyPopup;
    private LimitHeightListView popListView;

    private List<HotelAndHouseBean.ListBean> list;
    private HotelAndHouseAdapter adapter;

    private int hotel_business_class;
    private String keyword;
    private int hotel_type;
    private String hotel_type_name;
    private int price_id;
    private String price_id_name;
    private int third_area_id;
    private int fourth_area_id;
    private String startTime;
    private String endTime;
    private int days;

    private int page;
    private int pageSize;
    private HotelAndHouseListPresenter presenter;
    private int menuIndex;
    public boolean needRefresh;

    /**
     *
     * @return
     */
    public static HotelAndHouseListFragment getInstance(Bundle bundle) {
        HotelAndHouseListFragment fragment = new HotelAndHouseListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getArguments();
        if (extras != null) {
            hotel_business_class = extras.getInt(HotelAndHouseListActivity.HOTEL_BUSINESS_CLASS);
            keyword = extras.getString(HotelAndHouseListActivity.KEYWORD, "");
            hotel_type = extras.getInt(HotelAndHouseListActivity.HOTEL_TYPE);
            hotel_type_name = extras.getString(HotelAndHouseListActivity.HOTEL_TYPE_NAME);
            price_id = extras.getInt(HotelAndHouseListActivity.PRICE_ID);
            price_id_name = extras.getString(HotelAndHouseListActivity.PRICE_ID_NAME);
            third_area_id = extras.getInt(HotelAndHouseListActivity.THIRD_AREA_ID);
            startTime = extras.getString(HotelAndHouseListActivity.START_TIME);
            endTime = extras.getString(HotelAndHouseListActivity.END_TIME);
            days = extras.getInt(HotelAndHouseListActivity.DAY_NUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hotelandhouse_list, container, false);
        initView(view);
        initRecyclerView();
        initAreaPopup();
        initPopup();

        presenter = new HotelAndHouseListPresenter(this);
        presenter.initData(hotel_business_class,1,10,keyword,hotel_type,price_id,getCurrCityId(),third_area_id,fourth_area_id);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(hotel_business_class,1,10,keyword,hotel_type,price_id,getCurrCityId(),third_area_id,fourth_area_id);
            needRefresh = false;
        }
    }

    private void initView(View view) {
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        iv_area_point = (ImageView) view.findViewById(R.id.iv_area_point);
        linear_area = (LinearLayout) view.findViewById(R.id.linear_area);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        iv_salary_point = (ImageView) view.findViewById(R.id.iv_salary_point);
        linear_salary = (LinearLayout) view.findViewById(R.id.linear_salary);
        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
        iv_more_point = (ImageView) view.findViewById(R.id.iv_more_point);
        linear_more = (LinearLayout) view.findViewById(R.id.linear_more);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);

        tv_tab1.setText("区域");
        tv_tab2.setText(TextUtils.isEmpty(hotel_type_name)?"星级":hotel_type_name);
        tv_tab3.setText(TextUtils.isEmpty(price_id_name)?"价格":price_id_name);
        linear_area.setOnClickListener(this);
        linear_salary.setOnClickListener(this);
        linear_more.setOnClickListener(this);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new HotelAndHouseAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page ++;
                    presenter.loadNextData(hotel_business_class,page,10,keyword,hotel_type,price_id,getCurrCityId(),third_area_id,fourth_area_id);
                }
            }
        },mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    HotelAndHouseBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(HotelAndHouseDetailActivity.HOTEL_ID,bean.getHotel_id());
                    bundle.putString(HotelAndHouseListActivity.START_TIME,startTime);
                    bundle.putString(HotelAndHouseListActivity.END_TIME,endTime);
                    bundle.putInt(HotelAndHouseListActivity.DAY_NUM,days);
                    startAtvDonFinish(HotelAndHouseDetailActivity.class,bundle);
                }
            }
        });
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(hotel_business_class,1,10,keyword,hotel_type,price_id,getCurrCityId(),third_area_id,fourth_area_id);
            }
        });
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        popListView = (LimitHeightListView) inflate.findViewById(R.id.listView);

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
                .setDimView(mRecyclerView)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if (menuIndex == 0) {
                            iv_area_point.setRotation(0);
                        } else if (menuIndex == 1) {
                            iv_salary_point.setRotation(0);
                        } else if (menuIndex == 2) {
                            iv_more_point.setRotation(0);
                        }
                    }
                })
                .createPopup();

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                easyPopup.dismiss();
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                if (menuIndex == 1) {
                    tv_tab2.setText((String) item.get("text"));
                    hotel_type = (int) item.get("id");
                    presenter.initData(hotel_business_class,1,10,keyword,hotel_type,price_id,getCurrCityId(),third_area_id, fourth_area_id);
                } else if (menuIndex == 2) {
                    tv_tab3.setText((String) item.get("text"));
                    price_id = (int) item.get("id");
                    presenter.initData(hotel_business_class,1,10,keyword,hotel_type,price_id,getCurrCityId(),third_area_id,fourth_area_id);
                }
            }
        });
    }

    private void initAreaPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_idea_class, null);
        popListViewMenu = ((LimitHeightListView) inflate.findViewById(R.id.listView_menu));
        popListViewData = ((LimitHeightListView) inflate.findViewById(R.id.listView_data));
        areaEasyPopup = new EasyPopup(getContext())
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
                .setDimView(mRecyclerView)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if (menuIndex == 0) {
                            iv_area_point.setRotation(0);
                        }
                    }
                })
                .createPopup();
    }

    @Override
    public void initDataSuccess(HotelAndHouseBean bean) {
        list.clear();
        list.addAll(bean.getList());
        adapter.setNewData(list);
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(HotelAndHouseBean bean) {
        list.clear();
        list.addAll(bean.getList());
        adapter.setNewData(list);
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void loadNextDataSuccess(HotelAndHouseBean bean) {
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void loadNextDataFailure() {
        page--;
        adapter.loadMoreFail();
        checkEnd();
    }

    @Override
    public void loadAreaDataSuccess(final List<ThirdAndFourthAreaBean> list) {
        if (list.isEmpty()) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        final List<Map<String,Object>> maps = new ArrayList<>();
        for (ThirdAndFourthAreaBean bean : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",bean.getThird_area_id());
            map.put("text",bean.getThird_area_name());
            maps.add(map);
        }
        popListViewMenu.setAdapter(new SimplePopupListAdapter(getContext(),maps));

        List<FourthAreaBean> secondClassList = list.get(0).getFourth_area_list();
        sb.append(list.get(0).getThird_area_name());
        final List<Map<String,Object>> maps_data = new ArrayList<>();
        for (int i = 0; i < secondClassList.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",secondClassList.get(i).getFourth_area_id());
            map.put("text",secondClassList.get(i).getFourth_area_name());
            maps_data.add(map);
        }

        popListViewData.setAdapter(new SimplePopupListAdapter(getContext(),maps_data));

        popListViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int first_id = (int) maps.get(position).get("id");
                String first_name = (String) maps.get(position).get("text");
                for (int i = 0; i < list.size(); i++) {
                    int curr_id = (int) maps.get(i).get("id");
                    if (curr_id == first_id) {
                        List<FourthAreaBean> secondClassList = list.get(i).getFourth_area_list();

                        List<Map<String,Object>> maps = new ArrayList<>();
                        for (FourthAreaBean bean : secondClassList) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("id",bean.getFourth_area_id());
                            map.put("text",bean.getFourth_area_name());
                            maps.add(map);
                        }
                        sb.replace(0,sb.length(),"");
                        sb.append(first_name);
                        third_area_id = first_id;
                        popListViewData.setAdapter(new SimplePopupListAdapter(getContext(),maps));

                        break;
                    }
                }
            }
        });

        popListViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaEasyPopup.dismiss();
                Map<String,Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                fourth_area_id = (int) item.get("id");
                sb.append("/").append( item.get("text"));
                tv_tab1.setText(sb.toString());
                presenter.initData(hotel_business_class,1,10,keyword,hotel_type,price_id,getCurrCityId(),third_area_id,fourth_area_id);
            }
        });

        if (areaEasyPopup != null) {
            areaEasyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            areaEasyPopup.showAtAnchorView(linear_area, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_area_point.setRotation(180);
            menuIndex = 0;
        }
    }

    @Override
    public void loadHouseTypeDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(),maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_salary_point.setRotation(180);
        menuIndex = 1;
    }

    @Override
    public void loadPriceDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(),maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_more_point.setRotation(180);
        menuIndex = 2;
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
            case R.id.linear_area:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.linear_salary:
                presenter.loadHotelTypeData();
                break;
            case R.id.linear_more:
                presenter.loadPriceData();
                break;
        }
    }
}
