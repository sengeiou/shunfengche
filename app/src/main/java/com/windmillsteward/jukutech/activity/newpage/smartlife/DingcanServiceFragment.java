package com.windmillsteward.jukutech.activity.newpage.smartlife;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.newpage.model.DingcanServiceModel;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.popup.DingcanYudingPopurWindow;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DistanceUtils;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 订餐服务,酒店预定和门票预定内容
 */
public class DingcanServiceFragment extends BaseInitFragment implements View.OnClickListener {

    private static final String TYPE = "type";
    private static final String KEYWORD = "keyword";

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;
    @Bind(R.id.lay_ll_root)
    LinearLayout lay_ll_root;
    @Bind(R.id.tv_tab1)
    TextView tv_tab1;
    @Bind(R.id.iv_zhineng_point)
    ImageView iv_zhineng_point;
    @Bind(R.id.linear_zhineng)
    LinearLayout linear_zhineng;
    @Bind(R.id.tv_tab2)
    TextView tv_tab2;
    @Bind(R.id.iv_location_point)
    ImageView iv_location_point;
    @Bind(R.id.linear_location)
    LinearLayout linear_location;
    @Bind(R.id.tv_tab3)
    TextView tv_tab3;
    @Bind(R.id.iv_price_and_star_point)
    ImageView iv_price_and_star_point;
    @Bind(R.id.linear_price_and_star)
    LinearLayout linear_price_and_star;
    @Bind(R.id.view_line)
    View viewLine;
    @Bind(R.id.linear_root_select)
    LinearLayout linear_root_select;



    private LimitHeightListView listView;
    private EasyPopup mCirclePop;


    private RecyclerViewAdapter adapter;

    private List<DingcanServiceModel.ListBean> list;

    private int page = 1;
    private int sort = 1;//默认1.智能排序,2.价格由高到低, 3.价格由低到高
    private int corrSelect = -1;
    private int third_area_id = KV.get(Define.CURR_CITY_THIRD_ID);
    private int type;//1订餐2酒店3门票

    private String keyword;
    private List<ThirdAreaBean> thirdList;

    public static DingcanServiceFragment getInstance(int type) {
        DingcanServiceFragment fragment = new DingcanServiceFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_dingcan_service;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        linear_zhineng.setOnClickListener(this);
        linear_location.setOnClickListener(this);
        linear_price_and_star.setOnClickListener(this);
        hidTitleView();
        initAdapter();
//        initHeaderView();
        initPopup();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE);
            keyword = bundle.getString(KEYWORD);
        }

        getData();
    }

    public void search(String keyword) {
        this.keyword = keyword;
        getData();
    }


    private void getData() {
        String longlati = KV.get(Define.CURR_LONGLAT_ADDRESS);
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_DINGCAN_SERVICE_LIST)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("longitude", longlati.split(",")[0])
                .addParams("latitude", longlati.split(",")[1])
                .addParams("sort", sort + "")
                .addParams("keyword", keyword)
                .addParams("index_type", type + "")
                .addParams("third_area_id", third_area_id + "")
                .setCallBackData(new BaseNewNetModelimpl<DingcanServiceModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        commonRefresh.refreshComplete();
                        showErrorView();
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<DingcanServiceModel> respnse, String source) {
                        dismiss();
                        showContentView();
                        commonRefresh.refreshComplete();
                        DingcanServiceModel data = respnse.getData();
                        if (data.isFirstPage()) {
                            list.clear();
                        }
                        if (data.getList() != null) {
                            list.addAll(data.getList());
                        }
                        if (respnse.getData().isLastPage()) {
                            adapter.loadMoreEnd();
                        } else {
                            adapter.loadMoreComplete();
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<DingcanServiceModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        page = 1;
        getData();
    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        }, mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DingcanServiceModel.ListBean listBean = list.get(position);
                if (listBean.getService_id() != 0) {
                    DingcanWebActivity.go(getActivity(), type, listBean.getService_url(), listBean.getService_id(), listBean.getService_name());
                }
            }
        });

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });
    }

//    private void initHeaderView() {
//        View view = View.inflate(getActivity(), R.layout.header_recyclerview_dingcan_list, null);
//        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
//
//        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
//        iv_zhineng_point = (ImageView) view.findViewById(R.id.iv_zhineng_point);
//        linear_zhineng = (LinearLayout) view.findViewById(R.id.linear_zhineng);
//
//        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
//        iv_location_point = (ImageView) view.findViewById(R.id.iv_location_point);
//        linear_location = (LinearLayout) view.findViewById(R.id.linear_location);
//
//        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
//        iv_price_and_star_point = (ImageView) view.findViewById(R.id.iv_price_and_star_point);
//        linear_price_and_star = (LinearLayout) view.findViewById(R.id.linear_price_and_star);
//
//        linear_zhineng.setOnClickListener(this);
//        linear_location.setOnClickListener(this);
//        linear_price_and_star.setOnClickListener(this);
//
//        adapter.addHeaderView(view);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_zhineng:
                sort = 1;
                tv_tab1.setTextColor(getActivity().getResources().getColor(R.color.common_color));
                tv_tab3.setText("价格");
                refreshPageData();
                break;
            case R.id.linear_location:
                corrSelect = 1;
                if (thirdList == null) {
                    getAreaData(getCurrCityId());
                } else {
                    if (mCirclePop != null) {
                        AreaPopupAdapter adapter = new AreaPopupAdapter(getContext(), thirdList);
                        listView.setAdapter(adapter);
                        mCirclePop.getPopupWindow().setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
                        iv_location_point.setRotation(180);
                    }
                }
                break;
            case R.id.linear_price_and_star:
                corrSelect = 2;
                //筛选：【1：智能排序，2：价格从高到低，3：价格从低到高】
                List<Map<String, Object>> list1 = new ArrayList<>();
                String[] strings1 = new String[]{"价格从高到低", "价格从低到高"};
                for (int i = 0 ; i <strings1.length;i++){
                    Map<String, Object> map0 = new HashMap<>();
                    map0.put("id", i+2);
                    map0.put("text", strings1[i]);
                    list1.add(map0);
                }
                loadShaixuanDataSuccess(list1);
                break;

        }
    }

    private void getAreaData(int second_area_id) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_THIRD_AREA_LIST)
                .addParams("second_area_id", second_area_id + "")
                .setCallBackData(new BaseNewNetModelimpl<List<ThirdAreaBean>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<ThirdAreaBean>> respnse, String source) {
                        dismiss();
                        if (respnse.getData() != null) {
                            thirdList = respnse.getData();
                            ThirdAreaBean areaBean = new ThirdAreaBean();
                            areaBean.setThird_area_id(0);
                            areaBean.setThird_area_name("全部");
                            thirdList.add(0, areaBean);
                            AreaPopupAdapter adapter = new AreaPopupAdapter(getContext(), thirdList);
                            listView.setAdapter(adapter);
                            if (mCirclePop != null) {
                                mCirclePop.getPopupWindow().setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                                mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                                mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
                                iv_location_point.setRotation(180);
                            }
                        }


                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<ThirdAreaBean>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        listView = (LimitHeightListView) inflate.findViewById(R.id.listView);
        LinearLayout lay_ll_top = (LinearLayout) inflate.findViewById(R.id.lay_ll_top);
        TextView tv_left = (TextView) inflate.findViewById(R.id.tv_left);
        lay_ll_top.setVisibility(View.GONE);
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
                        if (corrSelect == 0) {  // 智能排序

                        } else if (corrSelect == 1) {  // 区域
                            iv_location_point.setRotation(0);
                        } else if (corrSelect == 2) {  // 价格
                            iv_price_and_star_point.setRotation(0);
                        }
                    }
                })
                .createPopup();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCirclePop.dismiss();
                if (corrSelect == 0) {  // 智能排序

                } else if (corrSelect == 1) {  // 区域
                    ThirdAreaBean areaBean = (ThirdAreaBean) parent.getAdapter().getItem(position);
                    third_area_id = areaBean.getThird_area_id();
                    tv_tab2.setText(areaBean.getThird_area_name());
                    tv_tab1.setTextColor(getActivity().getResources().getColor(R.color.text_color_black));
                } else if (corrSelect == 2) {  // 价格
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    sort = (int) item.get("id");
                    tv_tab3.setText((String) item.get("text"));
                    tv_tab1.setTextColor(getActivity().getResources().getColor(R.color.text_color_black));
                }
                refreshPageData();
            }
        });
    }

    public void loadShaixuanDataSuccess(List<Map<String, Object>> list) {
        listView.setAdapter(new SimpleListDialogAdapter(getContext(), list));
        if (mCirclePop != null) {
            mCirclePop.getPopupWindow().setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_price_and_star_point.setRotation(180);

        }
    }


    private void clickYuding(final int service_id) {

        DingcanYudingPopurWindow dingcanYudingPopurWindow = new DingcanYudingPopurWindow(type, service_id, getActivity(), new DingcanYudingPopurWindow.DataCallBack() {
            @Override
            public void yuDingSuccess() {
                getActivity().finish();
                MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
                if (activity != null){
                    activity.changeButtonStatus(2);
                }
            }
        });
        dingcanYudingPopurWindow.showAtMiddle(lay_ll_root);

    }

    class RecyclerViewAdapter extends BaseQuickAdapter<DingcanServiceModel.ListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<DingcanServiceModel.ListBean> data) {
            super(R.layout.item_recycler_dingcan_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final DingcanServiceModel.ListBean item) {
            GlideUtil.show(getActivity(), item.getPic_url(), (ImageView) helper.getView(R.id.iv_pic));
            helper.setText(R.id.tv_title, item.getService_name())
                    .setText(R.id.tv_evaluation_point, item.getScore())
                    .setText(R.id.tv_evaluation_num, item.getReview() + "点评")
                    .setText(R.id.tv_renjun, "人均:" + item.getPrice() + "元")
                    .setText(R.id.tv_distance, "距离我" + DistanceUtils.getFormatDistance(item.getJuli()));
            ImageView iv[] = new ImageView[5];
            iv[0] = (ImageView) helper.getView(R.id.iv_star1);
            iv[1] = (ImageView) helper.getView(R.id.iv_star2);
            iv[2] = (ImageView) helper.getView(R.id.iv_star3);
            iv[3] = (ImageView) helper.getView(R.id.iv_star4);
            iv[4] = (ImageView) helper.getView(R.id.iv_star5);
            showStar(item.getStar(), iv, (LinearLayout) helper.getView(R.id.lay_ll_star));
            helper.getView(R.id.tv_yuding).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickYuding(item.getService_id());
                }
            });
            FlowLayout fl_content = (FlowLayout) helper.getView(R.id.fl_content);
            fl_content.removeAllViews();
            List<String> attributeList = item.getAttributeList();
            if (attributeList != null) {
                for (int i = 0; i < attributeList.size(); i++) {
                    TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.type_text_shape, fl_content, false);
                    view.setText(attributeList.get(i));
                    fl_content.addView(view);
                }
            }
        }
    }

    private void showStar(int num, ImageView iv[], LinearLayout lay_ll_star) {
        lay_ll_star.setVisibility(View.VISIBLE);
        if (num == 1) {
            iv[0].setVisibility(View.VISIBLE);
            iv[1].setVisibility(View.GONE);
            iv[2].setVisibility(View.GONE);
            iv[3].setVisibility(View.GONE);
            iv[4].setVisibility(View.GONE);
        } else if (num == 2) {
            iv[0].setVisibility(View.VISIBLE);
            iv[1].setVisibility(View.VISIBLE);
            iv[2].setVisibility(View.GONE);
            iv[3].setVisibility(View.GONE);
            iv[4].setVisibility(View.GONE);
        } else if (num == 3) {
            iv[0].setVisibility(View.VISIBLE);
            iv[1].setVisibility(View.VISIBLE);
            iv[2].setVisibility(View.VISIBLE);
            iv[3].setVisibility(View.GONE);
            iv[4].setVisibility(View.GONE);
        } else if (num == 4) {
            iv[0].setVisibility(View.VISIBLE);
            iv[1].setVisibility(View.VISIBLE);
            iv[2].setVisibility(View.VISIBLE);
            iv[3].setVisibility(View.VISIBLE);
            iv[4].setVisibility(View.GONE);
        } else if (num == 5) {
            iv[0].setVisibility(View.VISIBLE);
            iv[1].setVisibility(View.VISIBLE);
            iv[2].setVisibility(View.VISIBLE);
            iv[3].setVisibility(View.VISIBLE);
            iv[4].setVisibility(View.VISIBLE);
        } else {
            lay_ll_star.setVisibility(View.GONE);
        }

    }


}
