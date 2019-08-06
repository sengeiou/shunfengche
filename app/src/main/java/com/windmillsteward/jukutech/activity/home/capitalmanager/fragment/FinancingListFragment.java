package com.windmillsteward.jukutech.activity.home.capitalmanager.fragment;

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
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.FinancingDetailActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.adapter.FinancingListFragmentAdapter;
import com.windmillsteward.jukutech.activity.home.capitalmanager.presenter.FinancingListFragmentPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.ModuleBannerView;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.ModuleBannerPresenter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.CapitalListBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 理财
 * Created by Administrator on 2018/4/8 0008.
 */

public class FinancingListFragment extends BaseFragment implements FinancingListFragmentView, View.OnClickListener, ModuleBannerView {

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
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private LimitHeightListView popListView;
    private EasyPopup easyPopup;
    private FlyBanner fl_banner;

    private List<CapitalListBean.ListBean> list;
    private FinancingListFragmentAdapter adapter;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private ModuleBannerPresenter moduleBannerPresenter;

    private FinancingListFragmentPresenter presenter;
    // 选择的第几项
    private int menuIndex;
    // 地区
    private int third_area_id;
    // 利率排序
    private int sorting_type;
    // 产品类型
    private int product_type;
    // 期限
    private int deadline_id;
    private String keyword;
    private int page;
    private int pageSize;

    private String select_text;

    public boolean needRefresh;

    /**
     * 实例化
     *
     * @param keyword 搜索关键词
     * @param curr_id 分类id
     */
    public static FinancingListFragment getInstance(String keyword, int curr_id, String select_text) {
        FinancingListFragment fragment = new FinancingListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Define.INTENT_DATA, keyword);
        bundle.putInt(Define.INTENT_DATA_TWO, curr_id);
        bundle.putString(Define.INTENT_DATA_THREE, select_text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString(Define.INTENT_DATA);
            product_type = bundle.getInt(Define.INTENT_DATA_TWO);
            select_text = bundle.getString(Define.INTENT_DATA_THREE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_financing_list, container, false);
        initView(view);
        initRecyclerView();
        initPopup();
        initFlynner();
        presenter = new FinancingListFragmentPresenter(this);
        presenter.initData(1, 1, 10, getCurrCityId(), third_area_id, sorting_type, product_type, deadline_id, keyword);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (needRefresh) {
            presenter.initData(1, 1, 10, getCurrCityId(), third_area_id, sorting_type, product_type, deadline_id, keyword);
            needRefresh = false;
        }
    }

    @Override
    public int registStartMode() {
        return singleTask;
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

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                easyPopup.dismiss();
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                if (menuIndex == 0) {
                    tv_tab1.setText((String) item.get("text"));
                    third_area_id = (int) item.get("id");
                } else if (menuIndex == 1) {
                    tv_tab2.setText((String) item.get("text"));
                    sorting_type = (int) item.get("id");
                } else if (menuIndex == 2) {
                    tv_tab3.setText((String) item.get("text"));
                    product_type = (int) item.get("id");
                } else if (menuIndex == 3) {
                    tv_tab4.setText((String) item.get("text"));
                    deadline_id = (int) item.get("id");
                }
                presenter.initData(1, 1, 10, getCurrCityId(), third_area_id, sorting_type, product_type, deadline_id, keyword);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new FinancingListFragmentAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA, list.get(position).getCapital_id());
                startAtvDonFinish(FinancingDetailActivity.class, bundle);
            }
        });
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(1, page, 10, getCurrCityId(), third_area_id, sorting_type, product_type, deadline_id, keyword);
                }
            }
        }, mRecyclerView);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1, 1, 10, getCurrCityId(), third_area_id, sorting_type, product_type, deadline_id, keyword);
            }
        });
    }

    private void initView(View view) {
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
        iv_tab4 = (ImageView) view.findViewById(R.id.iv_tab4);
        linear_tab4 = (LinearLayout) view.findViewById(R.id.linear_tab4);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);

        tv_tab1.setText("区域");
        tv_tab2.setText("利率排序");
        tv_tab3.setText(TextUtils.isEmpty(select_text) ? "产品类型" : select_text);
        tv_tab4.setText("期限");

        linear_tab1.setOnClickListener(this);
        linear_tab2.setOnClickListener(this);
        linear_tab3.setOnClickListener(this);
        linear_tab4.setOnClickListener(this);
    }

    /**
     * 初始化轮播图
     */
    private void initFlynner() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header_module_banner, (ViewGroup) mRecyclerView.getParent(), false);
        fl_banner = (FlyBanner) header.findViewById(R.id.fl_banner);

        ViewGroup.LayoutParams layoutParams = fl_banner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(getActivity(), GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(getActivity(), 20);
        layoutParams.height =  275*layoutParams.width/1010;
        fl_banner.setLayoutParams(layoutParams);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        fl_banner.setImages(images);
        moduleBannerPresenter = new ModuleBannerPresenter(getActivity(), this);
        moduleBannerPresenter.getBannerList(18);
        adapter.addHeaderView(header);
    }

    @Override
    public void getBannerListSuccess(List<SliderPictureInfo> list) {
        bannerList.clear();
        List<String> picList = new ArrayList<>();
        this.bannerList = list;
        for (SliderPictureInfo info : list) {
            String pic_url = info.getPic_url();
            picList.add(pic_url);
        }
        if (list.size() > 0) {
            fl_banner.setImagesUrl(picList);
        }

        fl_banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bannerList.size() == 0){
                    return;
                }
                SliderPictureInfo sliderPictureInfo = bannerList.get(position);
                moduleBannerPresenter.bannerClick(sliderPictureInfo);
            }
        });

    }

    @Override
    public void getBannerListFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void initDataSuccess(CapitalListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(CapitalListBean bean) {
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
    public void loadNextDataSuccess(CapitalListBean bean) {
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
    public void loadSortingDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab2.setRotation(180);
        menuIndex = 1;
    }

    @Override
    public void loadProductTypeDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab3.setRotation(180);
        menuIndex = 2;
    }

    @Override
    public void loadDeadlineDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab4.setRotation(180);
        menuIndex = 3;
    }

    private void checkEnd() {
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_tab1:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.linear_tab2:
                presenter.loadSortingData();
                break;
            case R.id.linear_tab3:
                presenter.loadProductTypeData();
                break;
            case R.id.linear_tab4:
                presenter.loadDeadlineData();
                break;
        }
    }
}
