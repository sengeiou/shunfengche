package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.baidumap.BaiduMapActivity;
import com.windmillsteward.jukutech.activity.home.commons.dataselect.DateSelectActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.HotelAndHouseDetailAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.HotelAndHouseDetailPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.HotelAndHouseDetailBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 描述：酒店和房源的详情
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class HotelAndHouseDetailActivity extends BaseActivity implements HotelAndHouseDetailView, View.OnClickListener {


    public static final String HOTEL_ID="HOTEL_ID";
    private static int REQUEST_CODE_SELECT_DATE = 100;
    private RecyclerView mRecyclerView;
    private HotelAndHouseDetailAdapter adapter;
    private HotelAndHouseDetailBean bean;
    private FlyBanner flyBanner;
    private LinearLayout linear_bottom;
    private TextView tv_hotel_name;
    private TextView tv_house_type;
    private TextView tv_address;
    private LinearLayout linear_address;
    private LinearLayout linear_join_hotel_time;
    private TextView tv_time;
    private TextView tv_days;
    private ImageView iv_back,iv_collect;
    private List<HotelAndHouseDetailBean.RoomListBean> room_list;

    private HotelAndHouseDetailPresenter presenter;
    private int hotel_id;
    private int position;
    private boolean collect_type;
    private String startTime;
    private String endTime;
    private int days=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelandhouse_detail);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            hotel_id = extras.getInt(HOTEL_ID);
            position = extras.getInt(Define.POSITION);
            startTime = extras.getString(HotelAndHouseListActivity.START_TIME);
            endTime = extras.getString(HotelAndHouseListActivity.END_TIME);
            days = extras.getInt(HotelAndHouseListActivity.DAY_NUM,1);
        }
        initView();
        initRecyclerView();
        initToolbar();
        initFlyBanner();

        presenter = new HotelAndHouseDetailPresenter(this);
        presenter.initData(getAccessToken(),hotel_id,startTime,endTime);
    }

    private void initToolbar() {
        iv_back.setOnClickListener(this);
        iv_collect.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_DATE && resultCode == DateSelectActivity.RERSULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                startTime = extras.getString(DateSelectActivity.START);
                endTime = extras.getString(DateSelectActivity.END);
                days = extras.getInt(DateSelectActivity.DAY_NUMBER);
                tv_time.setText(DateUtil.StampTimeToDate(startTime,"MM月dd日")+"-"+DateUtil.StampTimeToDate(endTime,"MM月dd日"));
                tv_days.setText(" 共" + days + "晚");
                presenter.initData(getAccessToken(),hotel_id,startTime,endTime);
            }
        }
    }

    private void initRecyclerView() {
        room_list = new ArrayList<>();
        adapter = new HotelAndHouseDetailAdapter(this,room_list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addHead();
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_reserve:
                        if (isLogin()) {
                            HotelAndHouseDetailBean.RoomListBean roomListBean = room_list.get(position);
                            int remain_room_num = roomListBean.getRemain_room_num();
                            if (remain_room_num<=0) {
                                showTips("房间已定完",0);
                                return;
                            }
                            // 预定
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(HotelAndHouseReserveActivity.DATA,HotelAndHouseDetailActivity.this.bean);
                            bundle.putString(HotelAndHouseReserveActivity.START,startTime);
                            bundle.putString(HotelAndHouseReserveActivity.END,endTime);
                            bundle.putInt(HotelAndHouseReserveActivity.DAY_NUMBER,days);
                            bundle.putInt(HotelAndHouseReserveActivity.POSITION,position);
                            startAtvDonFinish(HotelAndHouseReserveActivity.class,bundle);
                        } else {
                            startAtvDonFinish(LoginActivity.class);
                        }
                        break;
                }
            }
        });
        adapter.removeEmptyView();
    }

    private void addHead() {
        View header = LayoutInflater.from(this).inflate(R.layout.header_hotelandhouse_detail, (ViewGroup) mRecyclerView.getParent(), false);
        flyBanner = (FlyBanner) header.findViewById(R.id.flyBanner);
        linear_bottom = (LinearLayout) header.findViewById(R.id.linear_bottom);
        tv_hotel_name = (TextView) header.findViewById(R.id.tv_hotel_name);
        tv_house_type = (TextView) header.findViewById(R.id.tv_house_type);
        tv_address = (TextView) header.findViewById(R.id.tv_address);
        linear_address = (LinearLayout) header.findViewById(R.id.linear_address);
        linear_join_hotel_time = (LinearLayout) header.findViewById(R.id.linear_join_hotel_time);
        tv_time = (TextView) header.findViewById(R.id.tv_time);
        tv_days = (TextView) header.findViewById(R.id.tv_days);

        adapter.addHeaderView(header);

        linear_address.setOnClickListener(this);
        linear_join_hotel_time.setOnClickListener(this);
        linear_bottom.setOnClickListener(this);

        if (TextUtils.isEmpty(startTime)) {
            Calendar c = Calendar.getInstance();
            int currYear = c.get(Calendar.YEAR);
            int currMonth = c.get(Calendar.MONTH) + 1;
            int currDay = c.get(Calendar.DAY_OF_MONTH);
            c.add(Calendar.DATE,1);
            int nextYear = c.get(Calendar.YEAR);
            int nextMonth = c.get(Calendar.MONTH) + 1;
            int nextDay = c.get(Calendar.DAY_OF_MONTH);
            tv_time.setText(currMonth+"月"+currDay+"日"+"-"+currMonth+"月"+(nextDay)+"日");
            tv_days.setText(" 共" + 1 + "晚");
            startTime = DateUtil.DateToStampTime(String.valueOf(currYear) + "-" + String.valueOf(currMonth) + "-" + String.valueOf(currDay), "yyyy-MM-dd");
            endTime = DateUtil.DateToStampTime(String.valueOf(nextYear) + "-" + String.valueOf(nextMonth) + "-" + String.valueOf(nextDay), "yyyy-MM-dd");
        } else {
            tv_time.setText(DateUtil.StampTimeToDate(startTime,"MM月dd日")+"-"+DateUtil.StampTimeToDate(endTime,"MM月dd日"));
            tv_days.setText(" 共" + days + "晚");
        }

        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height =  575*layoutParams.width/1080;
        flyBanner.setLayoutParams(layoutParams);

    }

    private void initFlyBanner() {
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bean!=null) {
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) bean.getPic_urls());
                        bundle.putInt(PhotoViewActivity.CURR_POSITION,position);
                        startAtvDonFinish(PhotoViewActivity.class,bundle);
                }
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_collect = (ImageView) findViewById(R.id.iv_collection);
    }

    @Override
    public void initDataSuccess(HotelAndHouseDetailBean bean) {
        this.bean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls != null && pic_urls.size() > 0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_address.setText(bean.getArea_name());
        List<HotelAndHouseDetailBean.RoomListBean> room_list = bean.getRoom_list();
        if (room_list!=null && room_list.size()>0) {
            this.room_list.clear();
            this.room_list.addAll(room_list);
            adapter.setNewData(this.room_list);
        }
        int is_collect = bean.getIs_collect();
        if (is_collect==0){
            iv_collect.setImageResource(R.mipmap.icon_collect02);
        }else {
            collect_type = true;
            iv_collect.setImageResource(R.mipmap.icon_collect02_yes);
        }
        tv_hotel_name.setText(bean.getHotel_name());
        tv_house_type.setText(bean.getHotel_type_name()+" " + bean.getOpening_date()+"年装修 >");
    }

    @Override
    public void collectionSuccess() {
        iv_collect.setImageResource(R.mipmap.icon_collect02_yes);
        collect_type = true;
    }

    @Override
    public void cancelCollectionSuccess() {
        iv_collect.setImageResource(R.mipmap.icon_collect02);
        collect_type = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.POSITION,collect_type?-1:position);
                data.putExtras(extras);
                setResult(200, data);
                finish();
                break;
            case R.id.linear_bottom:
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(HotelAndHouseFacilitiesActivity.DETAIL,bean);
                    startAtvDonFinish(HotelAndHouseFacilitiesActivity.class,bundle);
                }
                break;
            case R.id.linear_address:
                // 跳转到地图
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(BaiduMapActivity.LATITUDE,bean.getLatitude());
                    bundle.putString(BaiduMapActivity.LONGITUDE,bean.getLongitude());
                    bundle.putString(BaiduMapActivity.HOTEL_NAME,bean.getHotel_name());
                    startAtvDonFinish(BaiduMapActivity.class,bundle);
                }
                break;
            case R.id.linear_join_hotel_time:
                Bundle bundle = new Bundle();
                bundle.putString(DateSelectActivity.START,startTime);
                bundle.putString(DateSelectActivity.END,endTime);
                startAtvDonFinishForResult(DateSelectActivity.class, REQUEST_CODE_SELECT_DATE,bundle);
                break;
            case R.id.iv_collection:
                if (isLogin()) {
                    if (bean!=null)
                        if (collect_type) {
                            presenter.cancelCollect(getAccessToken(),hotel_id);
                        } else {
                            presenter.collect(getAccessToken(),hotel_id);
                        }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            Intent data = new Intent();
            Bundle extras = new Bundle();
            extras.putInt(Define.POSITION,(collect_type)?-1:position);
            data.putExtras(extras);
            setResult(200, data);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
