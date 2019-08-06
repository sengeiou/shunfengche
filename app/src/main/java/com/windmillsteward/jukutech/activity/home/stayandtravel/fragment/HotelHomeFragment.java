package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.dataselect.DateSelectActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.HotelAndHouseAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.HotelAndHouseHomeAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.HotelHomePresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.HotelAndHouseBean;
import com.windmillsteward.jukutech.bean.HotelAndHouseHomeBean;
import com.windmillsteward.jukutech.bean.HotelTypeBean;
import com.windmillsteward.jukutech.bean.PriceBean;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * 描述：
 * 时间：2018/1/30
 * 作者：xjh
 */

public class HotelHomeFragment extends BaseFragment implements HotelHomeView, View.OnClickListener {

    private static final String ID = "ID";
    private static final int REQUEST_CODE = 100;

    private RecyclerView mRecyclerView;
    private HotelAndHouseHomeAdapter adapter;
    private List<HotelAndHouseHomeBean> list;
    private TextView tv_location;
    private TextView tv_curr_location;
    private TextView tv_date;
    private EditText et_keyword;
    private TextView tv_price;
    private TextView tv_search;

    private int hotel_business_class;
    private int hotel_type;
    private String hotel_type_name;
    private int price_id;
    private String price_id_name;
    private int third_area_id;

    private HotelHomePresenter presenter;
    private TextView tv_hotel_type;
    private LinearLayout linear_hotel_type;
    private LinearLayout linear_search_keyword;
    private LinearLayout linear_price;

    private List<PriceBean> priceBeans;

    private String startTime;
    private String endTime;
    private int days=1;

    private LocationClient mLocationClient;

    /**
     * 获取fragment实例
     *
     * @param hotel_business_class 分类id 1是酒店，2是房源
     * @return fragment
     */
    public static HotelHomeFragment getInstance(int hotel_business_class) {
        HotelHomeFragment fragment = new HotelHomeFragment();
        Bundle args = new Bundle();
        args.putInt(ID, hotel_business_class);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            hotel_business_class = bundle.getInt(ID, 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hotelhome, container, false);
        initView(view);

        initRecyclerView();
        initLocation();
        presenter = new HotelHomePresenter(this);

        return view;
    }

    private void initLocation() {
        // 定位
        mLocationClient = new LocationClient(getContext());
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(150); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {

                if (bdLocation==null) {
                    return;
                }
                double longitude = bdLocation.getLongitude();
                double latitude = bdLocation.getLatitude();
                String addr = bdLocation.getAddrStr();    //获取详细地址信息
                String country = bdLocation.getCountry();    //获取国家
                String province = bdLocation.getProvince();    //获取省份
                String city = bdLocation.getCity();    //获取城市
                String district = bdLocation.getDistrict();    //获取区县
                String street = bdLocation.getStreet();    //获取街道信息
                tv_location.setText((district==null?"未知":district) + " " + (street==null?"":street));
                presenter.initData(hotel_business_class,String.valueOf(longitude),String.valueOf(latitude),getCurrCityId());
                mLocationClient.unRegisterLocationListener(this);
            }
        });

        mLocationClient.start();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new HotelAndHouseHomeAdapter(list);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    HotelAndHouseHomeBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(HotelAndHouseDetailActivity.HOTEL_ID,bean.getHotel_id());
                    bundle.putString(HotelAndHouseListActivity.START_TIME,startTime);
                    bundle.putString(HotelAndHouseListActivity.END_TIME,endTime);
                    bundle.putInt(HotelAndHouseListActivity.DAY_NUM,days);
                    startAtvDonFinish(HotelAndHouseDetailActivity.class,bundle);
                }
            }
        });
        initHeader();
        adapter.removeEmptyView();
    }

    private void initHeader() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_hotelhome, ((ViewGroup) mRecyclerView.getParent()), false);
        tv_location = (TextView) header.findViewById(R.id.tv_location);
        tv_curr_location = (TextView) header.findViewById(R.id.tv_curr_location);
        tv_date = (TextView) header.findViewById(R.id.tv_date);
        et_keyword = (EditText) header.findViewById(R.id.et_keyword);
        tv_price = (TextView) header.findViewById(R.id.tv_price);
        tv_search = (TextView) header.findViewById(R.id.tv_search);
        tv_hotel_type = (TextView) header.findViewById(R.id.tv_hotel_type);
        linear_hotel_type = (LinearLayout) header.findViewById(R.id.linear_hotel_type);
        linear_search_keyword = (LinearLayout) header.findViewById(R.id.linear_search_keyword);
        linear_price = (LinearLayout) header.findViewById(R.id.linear_price);

        if (hotel_business_class == 1) {
            linear_search_keyword.setVisibility(View.GONE);
            linear_hotel_type.setVisibility(View.VISIBLE);
        } else {
            linear_search_keyword.setVisibility(View.VISIBLE);
            linear_hotel_type.setVisibility(View.GONE);
        }

        tv_curr_location.setOnClickListener(this);
        tv_date.setOnClickListener(this);
        linear_hotel_type.setOnClickListener(this);
        linear_price.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        adapter.addHeaderView(header);

        Calendar c = Calendar.getInstance();
        int currYear = c.get(Calendar.YEAR);
        int currMonth = c.get(Calendar.MONTH) + 1;
        int currDay = c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DATE,1);
        int nextYear = c.get(Calendar.YEAR);
        int nextMonth = c.get(Calendar.MONTH) + 1;
        int nextDay = c.get(Calendar.DAY_OF_MONTH);

        tv_date.setText(currMonth+"月"+currDay+"日"+"-"+currMonth+"月"+(nextDay)+"日"+" 共" + 1 + "晚");
        startTime = DateUtil.DateToStampTime(String.valueOf(currYear) + "-" + String.valueOf(currMonth) + "-" + String.valueOf(currDay), "yyyy-MM-dd");
        endTime = DateUtil.DateToStampTime(String.valueOf(nextYear) + "-" + String.valueOf(nextMonth) + "-" + String.valueOf(nextDay), "yyyy-MM-dd");

        if (hotel_business_class==1) {
            tv_search.setText("查找酒店");
        } else {
            tv_search.setText("查找房源");
        }
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == DateSelectActivity.RERSULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                startTime = extras.getString(DateSelectActivity.START);
                endTime = extras.getString(DateSelectActivity.END);
                days = extras.getInt(DateSelectActivity.DAY_NUMBER);
                tv_date.setText(DateUtil.StampTimeToDate(startTime,"MM月dd日")+"-"+DateUtil.StampTimeToDate(endTime,"MM月dd日") + " 共"+days+"晚");
            }
        }

    }

    @Override
    public void initDataSuccess(List<HotelAndHouseHomeBean> beans) {
        list.clear();
        list.addAll(beans);
        adapter.setNewData(list);
        adapter.loadMoreEnd();
    }

    @Override
    public void loadPriceDataSuccess(List<PriceBean> list) {
        if (hotel_business_class==1) {
            priceBeans = list;
            presenter.loadHotelTypeData();
        } else if (hotel_business_class == 2){
            priceBeans = list;

            dismiss();
            final LayoutInflater inflater = LayoutInflater.from(getContext());
            final Dialog dialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_hotelhome_bottom_dialog, null);
            inflate.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            //初始化控件
            final TagFlowLayout tagFlowLayout_price = (TagFlowLayout) inflate.findViewById(R.id.tagFlowLayout_price);
            TextView tv_sure = (TextView) inflate.findViewById(R.id.tv_sure);
            TextView tv_reset = (TextView) inflate.findViewById(R.id.tv_reset);
            inflate.findViewById(R.id.tagFlowLayout_type).setVisibility(View.GONE);
            inflate.findViewById(R.id.tv_type).setVisibility(View.GONE);

            final TagAdapter<PriceBean> adapter = new TagAdapter<PriceBean>(list) {
                @Override
                public View getView(FlowLayout parent, int position, PriceBean priceBean) {
                    TextView view = (TextView) inflater.inflate(R.layout.item_buyhouse_popup_more, parent, false);
                    view.setText(priceBean.getPrice_name());
                    return view;
                }
            };
            if (price_id!=0) {
                for (int i = 0; i < priceBeans.size(); i++) {
                    if (price_id==priceBeans.get(i).getPrice_id()) {
                        adapter.setSelectedList(i);
                        break;
                    }
                }
            }
            tagFlowLayout_price.setAdapter(adapter);
            tv_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Set<Integer> selectedList = tagFlowLayout_price.getSelectedList();
                    if (selectedList!=null) {
                        for (Integer integer : selectedList) {
                            price_id = priceBeans.get(integer).getPrice_id();
                            String price_name = priceBeans.get(integer).getPrice_name();
                            price_id_name = price_name;
                            tv_price.setText(price_name);
                        }
                    }
                    dialog.dismiss();
                }
            });
            tv_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    price_id = 0;
                    price_id_name = "";
                    tv_price.setText("");
                    adapter.setSelectedList();
                }
            });

            //将布局设置给Dialog
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
            window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            dialog.show();
        }
    }

    @Override
    public void loadHouseTypeDataSuccess(final List<HotelTypeBean> list) {
        dismiss();

        final LayoutInflater inflater = LayoutInflater.from(getContext());
        final Dialog dialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_hotelhome_bottom_dialog, null);
        inflate.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //初始化控件
        final TagFlowLayout tagFlowLayout_price = (TagFlowLayout) inflate.findViewById(R.id.tagFlowLayout_price);
        TextView tv_sure = (TextView) inflate.findViewById(R.id.tv_sure);
        TextView tv_reset = (TextView) inflate.findViewById(R.id.tv_reset);
        final TagFlowLayout tagFlowLayout_type = (TagFlowLayout)inflate.findViewById(R.id.tagFlowLayout_type);

        final TagAdapter<PriceBean> adapter = new TagAdapter<PriceBean>(priceBeans) {
            @Override
            public View getView(FlowLayout parent, int position, PriceBean priceBean) {
                TextView view = (TextView) inflater.inflate(R.layout.item_buyhouse_popup_more, parent, false);
                view.setText(priceBean.getPrice_name());
                return view;
            }
        };
        if (price_id!=0) {
            for (int i = 0; i < priceBeans.size(); i++) {
                if (price_id==priceBeans.get(i).getPrice_id()) {
                    adapter.setSelectedList(i);
                    break;
                }
            }
        }
        tagFlowLayout_price.setAdapter(adapter);


        final TagAdapter<HotelTypeBean> adapter1 = new TagAdapter<HotelTypeBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, HotelTypeBean hotelTypeBean) {
                TextView view = (TextView) inflater.inflate(R.layout.item_buyhouse_popup_more, parent, false);
                view.setText(hotelTypeBean.getType_name());
                return view;
            }
        };
        if (hotel_type!=0) {
            for (int i = 0; i < list.size(); i++) {
                if (hotel_type==list.get(i).getHotel_type()) {
                    adapter1.setSelectedList(i);
                    break;
                }
            }
        }
        tagFlowLayout_type.setAdapter(adapter1);


        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Integer> selectedList = tagFlowLayout_price.getSelectedList();
                if (selectedList!=null) {
                    for (Integer integer : selectedList) {
                        price_id = priceBeans.get(integer).getPrice_id();
                        String price_name = priceBeans.get(integer).getPrice_name();
                        price_id_name = price_name;
                        tv_price.setText(price_name);
                    }
                }
                Set<Integer> selectedList1 = tagFlowLayout_type.getSelectedList();
                if (selectedList1!=null) {
                    for (Integer integer : selectedList1) {
                        hotel_type = list.get(integer).getHotel_type();
                        hotel_type_name = list.get(integer).getType_name();
                        tv_hotel_type.setText(list.get(integer).getType_name());
                    }
                }
                dialog.dismiss();
            }
        });
        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_id = 0;
                price_id_name = "";
                hotel_type = 0;
                hotel_type_name = "";
                tv_price.setText("");
                tv_hotel_type.setText("");
                adapter.setSelectedList();
                adapter1.setSelectedList();
            }
        });

        //将布局设置给Dialog
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_curr_location:  // 获取当前位置
                initLocation();
                break;
            case R.id.tv_date:  // 选择入住离店时间
                Bundle bundle = new Bundle();
                bundle.putString(DateSelectActivity.START,startTime);
                bundle.putString(DateSelectActivity.END,endTime);
                startAtvDonFinishForResult(DateSelectActivity.class, bundle,100);
                break;
            case R.id.linear_hotel_type:
                // 加载酒店价格
                presenter.loadHotelPriceData();
                break;
            case R.id.linear_price:
                presenter.loadHotelPriceData();
                break;
            case R.id.tv_search:
                HotelAndHouseListActivity.go(getContext(),hotel_business_class,et_keyword.getText().toString().trim(),hotel_type,hotel_type_name,price_id,price_id_name,third_area_id,startTime,endTime,days);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient!=null) {
            mLocationClient.stop();
        }
    }
}
