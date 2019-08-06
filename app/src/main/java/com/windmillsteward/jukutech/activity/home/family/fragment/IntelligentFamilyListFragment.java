package com.windmillsteward.jukutech.activity.home.family.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyDetailActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.PublishRequireNewActivity;
import com.windmillsteward.jukutech.activity.home.family.adapter.HeaderRecyclerViewAdapter;
import com.windmillsteward.jukutech.activity.home.family.adapter.IntelligentFamilyAdapter;
import com.windmillsteward.jukutech.activity.home.family.presenter.IntelligentFamilyListPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.activity.newpage.smartlife.DingCanServiceHomeActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
import com.windmillsteward.jukutech.bean.RequireClassBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：智慧家庭列表
 * 时间：2018/1/17
 * 作者：xjh
 */

public class IntelligentFamilyListFragment extends BaseFragment implements IntelligentFamilyListView, BDLocationListener, View.OnClickListener {

    private static final String KEYWORD = "KEYWORD";
    private static final String CURR_CLASS = "CURR_CLASS";
    private static final String CURR_NAME = "CURR_NAME";
    private EasyPopup mCirclePop;
    private LinearLayout linear_root_select;
    private LimitHeightListView listView;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private List<IntelligentFamilyBean.ListBean> list;
    private List<RequireClassBean> listHeader;
    private IntelligentFamilyAdapter adapter;
    private IntelligentFamilyListPresenter presenter;
    private int page;
    private int pageSize;
    public boolean needRefresh;

    //头部View
    private TextView tv_tab1;
    private ImageView iv_area_point;
    private LinearLayout linear_area;
    private TextView tv_tab2;
    private ImageView iv_salary_point;
    private LinearLayout linear_salary;
    private TextView tv_tab3;
    private ImageView iv_more_point;
    private ImageView iv_tab4;
    private LinearLayout linear_more;
    private TextView tv_tab4, tv_text_only;
    private LinearLayout linear_tab4, linear_container_text_only, linear_container_tab4;
    private RecyclerView mHeaderRecyclerView;
    private TextView tv_quick_order;

    private HeaderRecyclerViewAdapter headerAdapter;

    // 关键词
    private String keyword = "";
    // 需求分类id
    private int curr_class;
    // 区域分类
    private int third_area_id;
    // 时间排序
    private int time_sort;
    // 价格排序
    private int filter_type;
    // 经度
    private String longitude;
    // 维度
    private String latitude;
    // 当前选择的是哪个分类：0地区分类，1时间排序，2价格排序
    private int corrSelect = -1;
    // 百度地图定位
    private LocationClient mLocClient;

    private int currSelectPosition = -1;
    private String curr_name;
    private String district = "";
    private String city = "";
    private IntelligentFamilyBean.ListBean bean;//抢单成功后的实体，为了拿所抢单的需求id


    public static IntelligentFamilyListFragment getInstance(String keyword, int curr_class, String curr_name) {
        IntelligentFamilyListFragment fragment = new IntelligentFamilyListFragment();
        Bundle args = new Bundle();
        args.putString(KEYWORD, keyword);
        args.putInt(CURR_CLASS, curr_class);
        args.putString(CURR_NAME, curr_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString(KEYWORD, "");
            curr_class = bundle.getInt(CURR_CLASS);
            curr_name = bundle.getString(CURR_NAME);
            district =(String) Hawk.get(Define.CURR_CITY_THIRD_NAME);
            city = (String) Hawk.get(Define.CURR_CITY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_intelligentfamily, container, false);
        initView(view);
        initXListView();
        initPopup();

        presenter = new IntelligentFamilyListPresenter(this);



        //加载头部数据
        presenter.loadFamilyClassData();
        presenter.initData(keyword, getCurrCityId(), third_area_id, time_sort, filter_type, longitude, latitude, curr_class);

//        initLocation();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (needRefresh) {
            presenter.initData(keyword, getCurrCityId(), third_area_id, time_sort, filter_type, longitude, latitude, curr_class);
            presenter.loadFamilyClassData();
            needRefresh = false;
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocClient = new LocationClient(getContext());
        mLocClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(150); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }


    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null) {
            return;
        }
        district = location.getDistrict();
        city = location.getCity();
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        presenter.refreshData(keyword, getCurrCityId(), third_area_id, time_sort, filter_type, longitude, latitude, curr_class);

        mLocClient.unRegisterLocationListener(this);
    }

    private void initXListView() {
        list = new ArrayList<>();
        adapter = new IntelligentFamilyAdapter(getContext(), list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initHeaderView();
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page, keyword, getCurrCityId(), third_area_id, time_sort, filter_type, longitude, latitude, curr_class);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {
                    bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(IntelligentFamilyDetailActivity.DETAIL_ID, bean.getRequire_id());
                    startAtvDonFinish(IntelligentFamilyDetailActivity.class, bundle);
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                if (isLogin()) {
                    bean = list.get(position);
                    checkUserAuthen(new OnUserAuthenListener() {
                        @Override
                        public void isAuthen() {
                            //todo 接单要收费
                            presenter.getOrder(getAccessToken(), list.get(position).getRequire_id(), city + district, longitude, latitude);
                        }

                        @Override
                        public void isNotAuthen() {
                            final BaseDialog baseDialog = new BaseDialog(getActivity());
                            baseDialog.showThreeButton("认证信息", "请完善您的认证信息", "取消", "个人认证", "企业认证", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baseDialog.dismiss();
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baseDialog.dismiss();
                                    startAtvDonFinish(PersonalAuthenticationActivity.class);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    baseDialog.dismiss();
                                    startAtvDonFinish(BusinessAuthenticationActivity.class);
                                }
                            });
                        }
                    });

                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
            }
        });
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(keyword, getCurrCityId(), third_area_id, time_sort, filter_type, longitude, latitude, curr_class);
                presenter.loadFamilyClassData();
            }
        });
    }

    /**
     * 给RecyclerView添加头部View
     */
    private void initHeaderView() {
        View view = View.inflate(getActivity(), R.layout.header_recyclerview_intelligent_family_list, null);
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        iv_area_point = (ImageView) view.findViewById(R.id.iv_area_point);
        linear_area = (LinearLayout) view.findViewById(R.id.linear_area);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        iv_salary_point = (ImageView) view.findViewById(R.id.iv_salary_point);
        iv_tab4 = (ImageView) view.findViewById(R.id.iv_tab4);
        linear_salary = (LinearLayout) view.findViewById(R.id.linear_salary);
        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
        tv_tab4 = (TextView) view.findViewById(R.id.tv_tab4);
        linear_tab4 = (LinearLayout) view.findViewById(R.id.linear_tab4);
        linear_container_text_only = (LinearLayout) view.findViewById(R.id.linear_container_text_only);
        linear_container_tab4 = (LinearLayout) view.findViewById(R.id.linear_container_tab4);
        linear_container_tab4.setVisibility(View.VISIBLE);
        linear_root_select = view.findViewById(R.id.linear_root_select);

        tv_text_only = (TextView) view.findViewById(R.id.tv_text_only);
        iv_more_point = (ImageView) view.findViewById(R.id.iv_more_point);
        linear_more = (LinearLayout) view.findViewById(R.id.linear_more);
        mHeaderRecyclerView = view.findViewById(R.id.header_recyclerview);
        tv_quick_order = view.findViewById(R.id.tv_quick_order);
        tv_tab1.setText("区域");
        tv_tab2.setText("分类");
        tv_tab3.setText("发布时间");
        tv_tab4.setText("筛选");

        if (!TextUtils.isEmpty(curr_name)) {
            tv_tab2.setText(curr_name);
        }

        linear_tab4.setVisibility(View.VISIBLE);
        linear_container_tab4.setVisibility(View.VISIBLE);
        linear_area.setOnClickListener(this);
        linear_salary.setOnClickListener(this);
        linear_tab4.setOnClickListener(this);
        linear_more.setOnClickListener(this);
        adapter.setHeaderView(view);

        //绑定适配器
        listHeader = new ArrayList<>();
        headerAdapter = new HeaderRecyclerViewAdapter(listHeader);
        mHeaderRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mHeaderRecyclerView.setAdapter(headerAdapter);

        headerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currSelectPosition = position;
                // 发布时先判断是否认证
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
            }
        });

//        tv_quick_order.setOnClickListener(this);
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        listView = (LimitHeightListView) inflate.findViewById(R.id.listView);

        mCirclePop = new EasyPopup(getContext())
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
                        if (corrSelect == 0) {  // 地区分类
                            iv_area_point.setRotation(0);
                        } else if (corrSelect == 1) {  // 分类
                            iv_salary_point.setRotation(0);
                        } else if (corrSelect == 2) {  // 发布时间
                            iv_more_point.setRotation(0);
                        } else if (corrSelect == 3) {  // 筛选
                            iv_tab4.setRotation(0);
                        }
                    }
                })
                .createPopup();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCirclePop.dismiss();
                if (corrSelect == 0) {  // 地区分类
                    ThirdAreaBean areaBean = (ThirdAreaBean) parent.getAdapter().getItem(position);
                    third_area_id = areaBean.getThird_area_id();
                    tv_tab1.setText(areaBean.getThird_area_name());
                } else if (corrSelect == 1) {  // 类型分类
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    curr_class = (int) item.get("id");
                    tv_tab2.setText((String) item.get("text"));
                } else if (corrSelect == 2) {  // 发布时间分类
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    time_sort = (int) item.get("id");
                    tv_tab3.setText((String) item.get("text"));
                } else if (corrSelect == 3) {  // 筛选分类
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    filter_type = (int) item.get("id");
                    tv_tab4.setText((String) item.get("text"));
                }
                presenter.initData(keyword, getCurrCityId(), third_area_id, time_sort, filter_type, longitude, latitude, curr_class);
            }
        });
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    @Override
    public void initDataSuccess(IntelligentFamilyBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(IntelligentFamilyBean bean) {
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
    public void loadNextDataSuccess(IntelligentFamilyBean bean) {
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void loadNextFailure() {
        page--;
        adapter.loadMoreFail();
        checkEnd();
    }

    @Override
    public void loadAreaDataSuccess(List<ThirdAreaBean> list) {
        ThirdAreaBean areaBean = new ThirdAreaBean();
        areaBean.setThird_area_id(0);
        areaBean.setThird_area_name("全部");
        list.add(0, areaBean);
        AreaPopupAdapter adapter = new AreaPopupAdapter(getContext(), list);
        listView.setAdapter(adapter);
        if (mCirclePop != null) {
            mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_area_point.setRotation(180);
        }
    }

    @Override
    public void loadPushTimeDataSuccess(List<Map<String, Object>> list) {
        listView.setAdapter(new SimpleListDialogAdapter(getContext(), list));
        if (mCirclePop != null) {
            mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_salary_point.setRotation(180);
        }
    }

    @Override
    public void loadPriceDataSuccess(List<Map<String, Object>> list) {
        listView.setAdapter(new SimpleListDialogAdapter(getContext(), list));
        if (mCirclePop != null) {
            mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_more_point.setRotation(180);
        }
    }

    public void loadShaixuanDataSuccess(List<Map<String, Object>> list) {
        listView.setAdapter(new SimpleListDialogAdapter(getContext(), list));
        if (mCirclePop != null) {
            mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_tab4.setRotation(180);
        }
    }

    @Override
    public void getOrderSuccess(final PublicResultModel resultModel, final String msg) {
        final BaseDialog baseDialog = new BaseDialog(getActivity());
        baseDialog.showTwoButton("提示", "是否花取"+resultModel.getOrder_price()+"元,查看并接取此用户订单", "确定", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
                //TODO 抢单成功要去收银台页面
                NewPayActivity.go(IntelligentFamilyListFragment.this,
                        CommonPayPresenter.TYPE_ZHIHUI_SHENGHUOXINXI, resultModel.getRelate_id(),
                        resultModel.getOrder_price() + "",
                        resultModel.getOrder_name(), NewPayActivity.CAN_USE_COUPON,msg);
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
            }
        });
        presenter.refreshData(keyword, getCurrCityId(), third_area_id, time_sort, filter_type, longitude, latitude, curr_class);


//        SmartLifeDetailsActivity.go(getActivity(), bean.getRequire_id(), longitude, latitude);
    }

    @Override
    public void loadRequireClassListSuccess(List<RequireClassBean> list) {
        listHeader.clear();
        listHeader.addAll(list);
        headerAdapter.notifyDataSetChanged();
        headerAdapter.setEnableLoadMore(false);
    }

    @Override
    public void loadRequireClassListFail() {
        showTips("数据加载失败");
    }

    /**
     * 判断是否认证
     *
     * @param bean bean.getIs_authen()==1 已经认证过，0未认证
     */
    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean != null) {
            if (bean.getIs_authen() == 1) {
                //已认证
//                startAtvDonFinish(PublishRequireActivity.class);
                if (currSelectPosition != -1) {
                    if (listHeader.get(currSelectPosition).getIndex_type() == 1){//订餐
                        DingCanServiceHomeActivity.go(getActivity(),DingCanServiceHomeActivity.DINGCAN);
                    }else if (listHeader.get(currSelectPosition).getIndex_type() == 2){//酒店
                        DingCanServiceHomeActivity.go(getActivity(),DingCanServiceHomeActivity.HOTEL);
                    }else if (listHeader.get(currSelectPosition).getIndex_type() == 3){//门票
                        DingCanServiceHomeActivity.go(getActivity(),DingCanServiceHomeActivity.MENPIAO);
                    }else {
                        Bundle bundle = new Bundle();
                        bundle.putString("name", listHeader.get(currSelectPosition).getClass_name());
                        bundle.putInt("id", listHeader.get(currSelectPosition).getRequire_class_id());
                        bundle.putString("price", listHeader.get(currSelectPosition).getPrice());
                        bundle.putString("info_fee", listHeader.get(currSelectPosition).getInfo_fee());
                        bundle.putInt("two_address", listHeader.get(currSelectPosition).getTwo_address());
                        bundle.putInt("is_ad", listHeader.get(currSelectPosition).getIs_ad());
                        startAtvDonFinishForResult(PublishRequireNewActivity.class, bundle, NewPayActivity.REQUEST_CODE);
                        needRefresh = true;
                    }
                }
            } else if (bean.getIs_authen() == 0) {
                final BaseDialog baseDialog = new BaseDialog(getActivity());
                baseDialog.showThreeButton("认证信息", "请完善您的认证信息", "取消", "个人认证", "企业认证", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        startAtvDonFinish(PersonalAuthenticationActivity.class);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        startAtvDonFinish(BusinessAuthenticationActivity.class);
                    }
                });
            } else if (bean.getIs_authen() == 2) {
                //正在审核中
                showTips("客服正在审核认证信息，审核完成后方可操作！", 1);
            }
        }
    }

    private void checkEnd() {
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewPayActivity.REQUEST_CODE && resultCode == NewPayActivity.RESULT_CODE_SUCCESS) {
            getActivity().finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_area:
                presenter.loadAreaData(getCurrCityId());
                corrSelect = 0;
                break;
            case R.id.linear_salary:
                corrSelect = 1;
                if (listHeader != null) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    Map<String, Object> map0 = new HashMap<>();
                    map0.put("id", 0);
                    map0.put("text", "全部");
                    list.add(map0);
                    for (RequireClassBean requireClassBean : listHeader) {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("id", requireClassBean.getRequire_class_id());
                        map1.put("text", requireClassBean.getClass_name());
                        list.add(map1);
                    }
                    loadPushTimeDataSuccess(list);
                }
                break;
            case R.id.linear_more:
                corrSelect = 2;
                //发布时间排序：【0：全部，默认最新时间，1：1小时内，2：1天内，3：7天内，4：一个月内】
                List<Map<String, Object>> list = new ArrayList<>();
                String[] strings = new String[]{"全部", "1小时内", "1天内", "7天内", "一个月内"};
                int i = 0;
                for (String string : strings) {
                    Map<String, Object> map0 = new HashMap<>();
                    map0.put("id", i++);
                    map0.put("text", string);
                    list.add(map0);
                }
                loadPriceDataSuccess(list);
                break;
            case R.id.linear_tab4:
                corrSelect = 3;
                //筛选：【0：默认，1：距离最近，:2：悬赏金最高】
                List<Map<String, Object>> list1 = new ArrayList<>();
                String[] strings1 = new String[]{"全部", "距离最近", "悬赏金最高"};
                int j = 0;
                for (String string : strings1) {
                    Map<String, Object> map0 = new HashMap<>();
                    map0.put("id", j++);
                    map0.put("text", string);
                    list1.add(map0);
                }
                loadShaixuanDataSuccess(list1);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocClient != null) {
            mLocClient.stop();
        }
    }
}
