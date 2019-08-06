package com.windmillsteward.jukutech.activity.home.houselease.fragment;

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
import com.windmillsteward.jukutech.activity.newpage.common.model.CommonBannerModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonBannerPresenter;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.houselease.adapter.BuyHouseAdapter;
import com.windmillsteward.jukutech.activity.home.houselease.presenter.BuyHouseListPresenter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.HouseSealListBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：
 * 时间：2018/1/17/017
 * 作者：xjh
 */
public class BuyHouseFragment extends BaseFragment implements BuyHouseView, View.OnClickListener {

    private static final String TYPE = "TYPE";
    private static final String KEYWORD = "KEYWORD";
    private static final String CURR_CLASS = "CURR_CLASS";
    private static final String CURR_NAME = "CURR_NAME";


    private List<HouseSealListBean.ListBean> list;
    private BuyHouseAdapter adapter;
    private BuyHouseListPresenter presenter;
    private int page;
    private int pageSize;
    private int house_type_id; // 分类
    private int third_area_id;  // 区id
    private int house_area_id;  // 面积id
    private int ren_type_number;  // 户型id
    private int time_type;  // 时间id
    private String param_name;  // 搜索关键词
    private LimitHeightListView popListView;
    private TextView tv_right;
    private TextView tv_left;
    private EasyPopup easyPopup;
    private EasyPopup morePopup;
    private int menuIndex;
    //headerView
    private TextView tv_tab1;
    private ImageView iv_tab1;
    private LinearLayout linear_tab1;
    private TextView tv_tab2;
    private ImageView iv_tab2;
    private LinearLayout linear_tab2;
    private TextView tv_tab3;
    private ImageView iv_tab3;
    private LinearLayout linear_tab3;
    private TextView tv_tab4;
    private ImageView iv_tab4;
    private LinearLayout linear_tab4;
    private LinearLayout linear_root_select;
    private FlyBanner flyBanner;
    private ImageView ivPlay;

    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private TagFlowLayout tagFlowLayout_area;
    //    private TagFlowLayout tagFlowLayout_type;
    private TagFlowLayout tagFlowLayout_huxing;
    private TextView tv_reset;
    private TextView tv_sure;
    private TextView tv_filtrate;
    private TagAdapter<HouseMoreBean.HouseAreaListBean> houseAreaAdapter;
    private int houseAreaAdapter_index = -1, houseTypeAdapter_index = -1, rentTypeAdapter_index = -1;
    //    private TagAdapter<HouseMoreBean.HouseTypeListBean> houseTypeAdapter;
    private TagAdapter<HouseMoreBean.RentTypeListBean> rentTypeAdapter;

    private HouseMoreBean moreBean;
    public boolean needRefresh;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private CommonBannerPresenter commonBannerPresenter;

    private String curr_name;

    /**
     * 实例化
     *
     * @param type      2.买房卖房 1租房出租
     * @param keyword   搜索关键词
     * @param curr_id   分类id
     * @param curr_name
     */
    public static BuyHouseFragment getInstance(int type, String keyword, int curr_id, String curr_name) {
        BuyHouseFragment fragment = new BuyHouseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        bundle.putString(KEYWORD, keyword);
        bundle.putString(CURR_NAME, curr_name);
        bundle.putInt(CURR_CLASS, curr_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString(KEYWORD);
            house_type_id = bundle.getInt(CURR_CLASS);
            curr_name = bundle.getString(CURR_NAME);
        }
    }

    private String title = "", price_sorting="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyhouse, container, false);

        initView(view);
        initListView();
        initPopup();
        initMorePopup();
        presenter = new BuyHouseListPresenter(this);
        commonBannerPresenter = new CommonBannerPresenter(getActivity());
        presenter.initData(1, 10, getCurrCityId(), third_area_id, title, time_type + "", house_area_id, house_type_id, ren_type_number, price_sorting);
        initFlynner();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(1, 10, getCurrCityId(), third_area_id, title, time_type + "", house_area_id, house_type_id, ren_type_number, price_sorting);
            needRefresh = false;
        }
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    private void initMorePopup() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_house, null);
        tagFlowLayout_area = (TagFlowLayout) view.findViewById(R.id.tagFlowLayout_area);
//        tagFlowLayout_type = (TagFlowLayout) view.findViewById(R.id.tagFlowLayout_type);
        tagFlowLayout_huxing = (TagFlowLayout) view.findViewById(R.id.tagFlowLayout_huxing);
        tv_reset = (TextView) view.findViewById(R.id.tv_reset);
        tv_sure = (TextView) view.findViewById(R.id.tv_sure);
        morePopup = new EasyPopup(getContext())
                .setContentView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
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
                        iv_tab4.setRotation(0);
                    }
                })
                .createPopup();

        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseAreaAdapter.setSelectedList();
//                houseTypeAdapter.setSelectedList();
                rentTypeAdapter.setSelectedList();
                houseAreaAdapter.notifyDataChanged();
//                houseTypeAdapter.notifyDataChanged();
                rentTypeAdapter.notifyDataChanged();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morePopup.dismiss();
                if (moreBean == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                Set<Integer> selectedArea = tagFlowLayout_area.getSelectedList();
//                Set<Integer> selectedType = tagFlowLayout_type.getSelectedList();
                Set<Integer> selectedHuxing = tagFlowLayout_huxing.getSelectedList();
                if (!selectedArea.isEmpty()) {
                    for (Integer integer : selectedArea) {
                        List<HouseMoreBean.HouseAreaListBean> house_area_list = moreBean.getHouse_area_list();
                        if (house_area_list != null && house_area_list.size() > 0) {
                            if (integer < house_area_list.size() && integer >= 0) {
                                house_area_id = house_area_list.get(integer).getHouse_area_id();
                                houseAreaAdapter_index = integer;
                                sb.append(house_area_list.get(integer).getHouse_area_name());
                            }
                        } else {
                            house_area_id = 0;
                            houseAreaAdapter_index = -1;
                        }
                    }
                } else {
                    house_area_id = 0;
                    houseAreaAdapter_index = -1;
                }
//                if (!selectedType.isEmpty()) {
//                    for (Integer integer : selectedType) {
//                        List<HouseMoreBean.HouseTypeListBean> house_type_list = moreBean.getHouse_type_list();
//                        if (house_type_list != null && house_type_list.size() > 0) {
//                            if (integer < house_type_list.size() && integer >= 0) {
//                                house_type_id = house_type_list.get(integer).getHouse_type_id();
//                                houseTypeAdapter_index = integer;
//                                sb.append(house_type_list.get(integer).getHouse_type_name());
//                            }
//                        } else {
//                            house_type_id = 0;
//                            houseTypeAdapter_index = -1;
//                        }
//                    }
//                } else {
//                    house_type_id = 0;
//                    houseTypeAdapter_index = -1;
//                }

                if (!selectedHuxing.isEmpty()) {
                    for (Integer integer : selectedHuxing) {
                        List<HouseMoreBean.RentTypeListBean> rent_type_list = moreBean.getRent_type_list();
                        if (rent_type_list != null && rent_type_list.size() > 0) {
                            if (integer < rent_type_list.size() && integer >= 0) {
                                ren_type_number = rent_type_list.get(integer).getRent_type_id();
                                rentTypeAdapter_index = integer;
                                sb.append(rent_type_list.get(integer).getRent_type_name());
                            }
                        } else {
                            ren_type_number = 0;
                            rentTypeAdapter_index = -1;
                        }
                    }
                } else {
                    ren_type_number = 0;
                    rentTypeAdapter_index = -1;
                }
//                tv_filtrate.setText(sb.toString());

                presenter.initData(1, 10, getCurrCityId(), third_area_id, title, time_type + "", house_area_id, house_type_id, ren_type_number, price_sorting);
            }
        });
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        popListView = (LimitHeightListView) inflate.findViewById(R.id.listView);
        tv_left = (TextView) inflate.findViewById(R.id.tv_left);
        tv_right = (TextView) inflate.findViewById(R.id.tv_right);

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
                            iv_tab1.setRotation(0);
                        } else if (menuIndex == 1) {
                            iv_tab2.setRotation(0);
                        } else if (menuIndex == 2) {
                            iv_tab3.setRotation(0);
                        } else if (menuIndex == 3) {
                            iv_tab4.setRotation(0);
                        }
                    }
                })
                .createPopup();

        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyPopup.dismiss();
            }
        });
        tv_right.setVisibility(View.INVISIBLE);
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyPopup.dismiss();
            }
        });
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                easyPopup.dismiss();
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                if (menuIndex == 0) {
                    //区域
                    tv_tab1.setText((String) item.get("text"));
                    third_area_id = (int) item.get("id");
                } else if (menuIndex == 1) {
                    //分类
                    tv_tab2.setText((String) item.get("text"));
                    house_type_id = (int) item.get("id");
                } else if (menuIndex == 2) {
                    //发布时间
                    tv_tab3.setText((String) item.get("text"));
                    time_type = (int) item.get("id");
                } else if (menuIndex == 3) {
                    //价格
                    tv_tab4.setText((String) item.get("text"));
                    price_sorting = item.get("id").toString();
                }

                presenter.initData(1, 10, getCurrCityId(), third_area_id, title, time_type + "", house_area_id, house_type_id, ren_type_number, price_sorting);
            }
        });
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new BuyHouseAdapter(list);
        initHeaderView();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HouseSealListBean.ListBean bean = list.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt(HouseDetailActivity.DETAIL_ID, bean.getHouse_id());
//                bundle.putInt(HouseDetailActivity.CLASS_TYPE, bean.getRequire_type());
                startAtvDonFinish(HouseDetailActivity.class, bundle);
            }
        });
        adapter.setLoadMoreEndText("——到底了——");
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page, 10, getCurrCityId(), third_area_id, title, time_type + "", house_area_id, house_type_id, ren_type_number, price_sorting);
                }
            }
        }, mRecyclerView);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1, 10, getCurrCityId(), third_area_id, title, time_type + "", house_area_id, house_type_id, ren_type_number, price_sorting);
            }
        });
    }

    /**
     * 头部布局
     */
    private void initHeaderView() {
        View view = View.inflate(getActivity(), R.layout.header_recyclerview_buy_house, null);
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        iv_tab1 = (ImageView) view.findViewById(R.id.iv_tab1);
        linear_tab1 = (LinearLayout) view.findViewById(R.id.linear_tab1);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        iv_tab2 = (ImageView) view.findViewById(R.id.iv_tab2);
        linear_tab2 = (LinearLayout) view.findViewById(R.id.linear_tab2);
        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
        iv_tab3 = (ImageView) view.findViewById(R.id.iv_tab3);
        linear_tab3 = (LinearLayout) view.findViewById(R.id.linear_tab3);
        tv_tab4 = (TextView) view.findViewById(R.id.tv_tab4);
        tv_filtrate = (TextView) view.findViewById(R.id.tv_filtrate);
        iv_tab4 = (ImageView) view.findViewById(R.id.iv_tab4);
        linear_tab4 = (LinearLayout) view.findViewById(R.id.linear_tab4);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        flyBanner = view.findViewById(R.id.flyBanner);
        ivPlay = view.findViewById(R.id.iv_play);

        tv_tab1.setText("区域");
        tv_tab2.setText("分类");
        tv_tab3.setText("发布时间");
        tv_tab4.setText("价格");

        if (!TextUtils.isEmpty(curr_name)) {
            tv_tab2.setText(curr_name);
        }

        linear_tab1.setOnClickListener(this);
        linear_tab2.setOnClickListener(this);
        linear_tab3.setOnClickListener(this);
        linear_tab4.setOnClickListener(this);
        tv_filtrate.setOnClickListener(this);

        adapter.setHeaderView(view);
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    /**
     * 初始化轮播图
     */
    private void initFlynner() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        flyBanner.setImages(images);
        ViewWrap.handlerFlyBanner(flyBanner, 670, 360);
        commonBannerPresenter.loadBannerData(CommonBannerPresenter.TYPE_FANGWUXINXI, new CommonBannerPresenter.DataCallBack() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
            }

            @Override
            public void onSucess(int code, BaseResultInfo<List<CommonBannerModel>> respnse, String source) {
                dismiss();
                commonBannerPresenter.handlerBanner(flyBanner, respnse);
            }
        });
    }

    @Override
    public void initDataSuccess(HouseSealListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(HouseSealListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void loadNextDataSuccess(HouseSealListBean bean) {
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
    public void loadAreaDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab1.setRotation(180);
        menuIndex = 0;
    }

    @Override
    public void loadClassDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab2.setRotation(180);
        menuIndex = 1;
    }

    @Override
    public void loadPriceDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab4.setRotation(180);
        menuIndex = 3;
    }

    @Override
    public void loadTimeDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab3.setRotation(180);
        menuIndex = 2;
    }

    @Override
    public void loadMoreDataSuccess(HouseMoreBean bean) {
        moreBean = bean;
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        List<HouseMoreBean.HouseAreaListBean> areaList = bean.getHouse_area_list();
        if (areaList != null) {
            houseAreaAdapter = new TagAdapter<HouseMoreBean.HouseAreaListBean>(areaList) {
                @Override
                public View getView(FlowLayout parent, int position, HouseMoreBean.HouseAreaListBean bean) {
                    TextView view = (TextView) inflater.inflate(R.layout.item_buyhouse_popup_more, parent, false);
                    view.setText(bean.getHouse_area_name());

                    return view;
                }
            };
            if (houseAreaAdapter_index == -1) {
                houseAreaAdapter.setSelectedList();
            } else {
                houseAreaAdapter.setSelectedList(houseAreaAdapter_index);
            }
            tagFlowLayout_area.setAdapter(houseAreaAdapter);
        }

//        List<HouseMoreBean.HouseTypeListBean> house_type_list = bean.getHouse_type_list();
//        if (house_type_list != null) {
//            houseTypeAdapter = new TagAdapter<HouseMoreBean.HouseTypeListBean>(house_type_list) {
//                @Override
//                public View getView(FlowLayout parent, int position, HouseMoreBean.HouseTypeListBean bean) {
//                    TextView view = (TextView) inflater.inflate(R.layout.item_buyhouse_popup_more, parent, false);
//                    view.setText(bean.getHouse_type_name());
//
//                    return view;
//                }
//            };
//            if (houseTypeAdapter_index == -1) {
//                houseTypeAdapter.setSelectedList();
//            } else {
//                houseTypeAdapter.setSelectedList(houseTypeAdapter_index);
//            }
//            tagFlowLayout_type.setAdapter(houseTypeAdapter);
//        }

        List<HouseMoreBean.RentTypeListBean> rent_type_list = bean.getRent_type_list();
        if (rent_type_list != null) {
            rentTypeAdapter = new TagAdapter<HouseMoreBean.RentTypeListBean>(rent_type_list) {
                @Override
                public View getView(FlowLayout parent, int position, HouseMoreBean.RentTypeListBean bean) {
                    TextView view = (TextView) inflater.inflate(R.layout.item_buyhouse_popup_more, parent, false);
                    view.setText(bean.getRent_type_name());

                    return view;
                }
            };
            if (rentTypeAdapter_index == -1) {
                rentTypeAdapter.setSelectedList();
            } else {
                rentTypeAdapter.setSelectedList(rentTypeAdapter_index);
            }
            tagFlowLayout_huxing.setAdapter(rentTypeAdapter);
        }

        morePopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        morePopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
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
            case R.id.linear_tab1:
                //区域
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.linear_tab2:
                //分类
                addCall(presenter.loadClassData());
                break;
            case R.id.linear_tab3:
                //发布时间
                presenter.loadTimeData();
                break;
            case R.id.linear_tab4:
                //价格
                // presenter.loadMoreData(type);
                presenter.loadPriceData();
                break;
            case R.id.tv_filtrate:
                presenter.loadMoreData();
                break;
        }
    }
}
