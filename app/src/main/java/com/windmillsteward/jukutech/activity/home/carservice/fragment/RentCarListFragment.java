package com.windmillsteward.jukutech.activity.home.carservice.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.bigkoo.pickerview.TimePickerView;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.PublishFinancingActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.PublishLoanActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarTypeSelectActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PoiIndexListActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.RentCarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.RentCarListFragmentAdapter;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.RentCarListFragmentPresenter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.RentCarListBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：
 * 时间：2018/3/25/025
 * 作者：xjh
 */
public class RentCarListFragment extends BaseFragment implements View.OnClickListener,RentCarListFragmentView {

    private FrameLayout fl_toolbar;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private LinearLayout linear_content;
    private ImageView iv_sot;
    private TextView tv_departure_place;
    private TextView tv_destination_place;
    private TextView tv_go_off;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private ImageView iv_title_index;
    private LinearLayout linear_title;
    // popup弹窗
    private EasyPopup easyPopup;
    // 内容
    private LimitHeightListView listView;

    private int type=1;
    // 出发地经度
    private String departure_longitude;
    // 出发地唯独
    private String departure_latitude;
    private String departure_place;
    // 目的地经度
    private String destination_longitude;
    // 目的地维度
    private String destination_latitude;
    private String destination_place;
    // 出发时间  10位数秒
    private int go_off;

    private RentCarListFragmentPresenter presenter;

    private List<RentCarListBean.ListBean> list;
    private RentCarListFragmentAdapter adapter;
    private int page,pageSize;

    private int class_id;
    private int curr_position;
    public boolean needRefresh;
    private boolean isCall;

    public static RentCarListFragment getInstance(int class_id) {
        RentCarListFragment fragment = new RentCarListFragment();
        Bundle args = new Bundle();
        args.putInt(Define.INTENT_DATA,class_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments!=null) {
            class_id = arguments.getInt(Define.INTENT_DATA,1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rentcar_list, container, false);
        initView(view);
        initToolbar();
        initRecyclerView();
        initPopup();
        presenter = new RentCarListFragmentPresenter(this);
        presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 200) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                tv_departure_place.setText(extras.getString(Define.INTENT_DATA));
                departure_place = extras.getString(Define.INTENT_DATA);
                LatLng latLng = extras.getParcelable(Define.INTENT_DATA_TWO);
                if (latLng!=null) {
                    departure_longitude = String.valueOf(latLng.longitude);
                    departure_latitude = String.valueOf(latLng.latitude);
                    presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
                }
            }
        } else if (requestCode==102 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                tv_destination_place.setText(extras.getString(Define.INTENT_DATA));
                destination_place = extras.getString(Define.INTENT_DATA);
                LatLng latLng = extras.getParcelable(Define.INTENT_DATA_TWO);
                if (latLng!=null) {
                    destination_longitude = String.valueOf(latLng.longitude);
                    destination_latitude = String.valueOf(latLng.latitude);
                    presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
                }
            }
        }

        if (TextUtils.isEmpty(tv_departure_place.getText().toString().trim()) || TextUtils.isEmpty(tv_destination_place.getText().toString().trim())) {
            iv_sot.setImageResource(R.mipmap.icon_sot_n);
            iv_sot.setTag(0);
        } else {
            iv_sot.setImageResource(R.mipmap.icon_sot);
            iv_sot.setTag(1);
        }

//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = true;
//            presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
            needRefresh = false;
        }
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        listView = (LimitHeightListView) inflate.findViewById(R.id.listView);

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
                .setDimView(linear_content)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        iv_title_index.setRotation(0);
                    }
                })
                .createPopup();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                easyPopup.dismiss();
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                type = (int) item.get("id");
                toolbar_iv_title.setText((String) item.get("text"));
                departure_longitude = "";
                departure_latitude = "";
                departure_place = "";
                destination_longitude = "";
                destination_latitude = "";
                destination_place = "";
                go_off = 0;

                presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new RentCarListFragmentAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(type,page,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {
                    RentCarListBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA, bean.getCar_rent_id());
                    startAtvDonFinish(RentCarDetailActivity.class, bundle);
                }
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_call:
                        curr_position = position;
                        if (type==1) {
                            presenter.isCharge(getAccessToken(),106,list.get(position).getCar_rent_id());
                        } else if (type==2) {
                            presenter.isCharge(getAccessToken(),107,list.get(position).getCar_rent_id());
                        }
                        break;
                }
            }
        });

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
            }
        });
    }

    private void initToolbar() {
        toolbar_iv_back.setOnClickListener(this);
        toolbar_iv_title.setText((class_id==1)?"车主信息":"乘客信息");
        linear_title.setOnClickListener(this);
        type = class_id;
    }

    private void initView(View view) {
        fl_toolbar = (FrameLayout) view.findViewById(R.id.fl_toolbar);
        toolbar_iv_back = (ImageView) view.findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) view.findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) view.findViewById(R.id.toolbar_tv_right);
        linear_content = (LinearLayout) view.findViewById(R.id.linear_content);
        iv_sot = (ImageView) view.findViewById(R.id.iv_sot);
        tv_departure_place = (TextView) view.findViewById(R.id.tv_departure_place);
        tv_destination_place = (TextView) view.findViewById(R.id.tv_destination_place);
        tv_go_off = (TextView) view.findViewById(R.id.tv_go_off);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
        iv_title_index = (ImageView) view.findViewById(R.id.iv_title_index);
        linear_title = (LinearLayout) view.findViewById(R.id.linear_title);
        tv_departure_place.setOnClickListener(this);
        tv_destination_place.setOnClickListener(this);
        tv_go_off.setOnClickListener(this);
        toolbar_tv_right.setOnClickListener(this);
        iv_sot.setOnClickListener(this);
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv_back:
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.finish();
                }
                break;
            case R.id.linear_title:
                List<Map<String,Object>> maps = new ArrayList<>();
                // 【1：车主信息，2：乘客信息】
                Map<String,Object> map1 = new HashMap<>();
                map1.put("id",1);
                map1.put("text","车主信息");
                maps.add(map1);
                Map<String,Object> map2 = new HashMap<>();
                map2.put("id",2);
                map2.put("text","乘客信息");
                maps.add(map2);
                listView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
                easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                easyPopup.showAtAnchorView(fl_toolbar, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
                iv_title_index.setRotation(180);
                break;
            case R.id.toolbar_tv_right:
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.tv_departure_place:
                Intent intent = new Intent(getContext(), PoiIndexListActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.tv_destination_place:
                Intent intent1 = new Intent(getContext(), PoiIndexListActivity.class);
                startActivityForResult(intent1, 102);
                break;
            case R.id.iv_sot:
                if (iv_sot.getTag()==null || ((int) iv_sot.getTag())==0) {
                    return;
                }
                String _departure_longitude = departure_longitude;
                String _departure_latitude = departure_latitude;
                String _departure_place = departure_place;
                String _destination_longitude = destination_longitude;
                String _destination_latitude = destination_latitude;
                String _destination_place = destination_place;
                departure_longitude = _destination_longitude;
                departure_latitude = _destination_latitude;
                departure_place = _destination_place;
                destination_longitude = _departure_longitude;
                destination_latitude = _departure_latitude;
                destination_place = _departure_place;
                tv_departure_place.setText(departure_place);
                tv_destination_place.setText(destination_place);
                presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
                break;
            case R.id.tv_go_off:
                TimePickerView compulsory = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        go_off = (int) (date.getTime()/1000);
                        String dd = DateUtil.getYY_MM_DD(date, "MM-dd HH:mm");
                        tv_go_off.setText(dd);
                        presenter.initData(type,1,departure_longitude,departure_latitude,destination_longitude,destination_latitude,go_off);
                    }
                })
                        .setType(new boolean[]{false, true, true, true, true, false})
                        .build();
                compulsory.setDate(Calendar.getInstance());
                compulsory.show();
                break;
        }
    }

    @Override
    public void initDataSuccess(RentCarListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();

        if (isCall) {
            isCall = false;
            if (list.size()-1<curr_position) {
                return;
            }
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + list.get(curr_position).getContact_tel());
            intent.setData(uri);
            startActivity(intent);
        }
    }

    @Override
    public void refreshDataSuccess(RentCarListBean bean) {
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
    public void loadNextDataSuccess(RentCarListBean bean) {
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

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + list.get(curr_position).getContact_tel());
            intent.setData(uri);
            startActivity(intent);
        } else {
            new AlertDialog(getContext()).builder()
                    .setTitle("提示")
                    .setMsg("拨打电话需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (type==1) { // 车主
                            } else if (type==2) { // 乘客
                            }
                        }
                    })
                    .show();
        }
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean.getIs_authen()==1) {
             Bundle bundle = new Bundle();
             bundle.putInt(CarTypeSelectActivity.TYPE,2);
             startAtvDonFinish(CarTypeSelectActivity.class, bundle);
             needRefresh = true;
        } else if (bean.getIs_authen()==0){
            startAtvDonFinish(PersonalAuthenticationActivity.class);
        } else if (bean.getIs_authen()==2) {
            showTips("客服正在审核认证信息，审核完成后可发布信息",1);
        }
    }

    private void checkEnd() {
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }
}
