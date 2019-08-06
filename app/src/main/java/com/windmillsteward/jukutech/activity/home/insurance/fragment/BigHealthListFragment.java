package com.windmillsteward.jukutech.activity.home.insurance.fragment;

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

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.ModuleBannerView;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.ModuleBannerPresenter;
import com.windmillsteward.jukutech.activity.home.insurance.activity.BigHealthDetailActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceDetailActivity;
import com.windmillsteward.jukutech.activity.home.insurance.adapter.BigHealthListFragmentAdapter;
import com.windmillsteward.jukutech.activity.home.insurance.adapter.InsuranceListFragmentAdapter;
import com.windmillsteward.jukutech.activity.home.insurance.presenter.BigHealthListFragmentPresenter;
import com.windmillsteward.jukutech.activity.home.insurance.presenter.InsuranceListFragmentPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.BigHealthListBean;
import com.windmillsteward.jukutech.bean.InsuranceListBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
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
 * 描述：大健康
 * 时间：2018年8月2日
 * 作者：lc
 */
public class BigHealthListFragment extends BaseFragment implements BigHealthListFragmentView, View.OnClickListener,ModuleBannerView {

    private static final String KEYWORD = "KEYWORD";
    private static final String CURR_CLASS = "CURR_CLASS";

    private TextView tv_tab1;
    private ImageView iv_tab1;
    private LinearLayout linear_tab1;
    private LinearLayout linear_root_select;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private FlyBanner fl_banner;

    private LimitHeightListView popList;
    private EasyPopup easyPopup;

    // 关键词
    private String keyword = "";
    // 分类id
    private int curr_class;
    // 区域分类
    private int third_area_id;
    // 险种分类id
    private int insurance_type;
    // 分页
    private int page,pageSize;
    // 分类选项
    private int corrSelect;

    private List<BigHealthListBean.ListBean> list;
    private BigHealthListFragmentAdapter adapter;

    private BigHealthListFragmentPresenter presenter;
    public boolean needRefresh;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private ModuleBannerPresenter moduleBannerPresenter;

    /**
     * 获取实例
     *
     * @param keyword    搜索关键词
     * @param curr_class 分类id
     */
    public static BigHealthListFragment getInstance(String keyword, int curr_class) {
        BigHealthListFragment fragment = new BigHealthListFragment();
        Bundle args = new Bundle();
        args.putString(KEYWORD, keyword);
        args.putInt(CURR_CLASS, curr_class);
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
        }
        insurance_type = curr_class;
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_big_health_list, container, false);
        initView(view);
        initTabView();
        initRecyclerView();
        initPopup();
        initFlynner();
        presenter = new BigHealthListFragmentPresenter(this);
        presenter.initData(1,10,keyword,getCurrCityId(),third_area_id);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(1,10,keyword,getCurrCityId(),third_area_id);
            needRefresh = false;
        }
    }

    private void initTabView() {
        tv_tab1.setText("区域");
        linear_tab1.setOnClickListener(this);
    }


    private void initView(View view) {
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        iv_tab1 = (ImageView) view.findViewById(R.id.iv_tab1);
        linear_tab1 = (LinearLayout) view.findViewById(R.id.linear_tab1);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    /**
     * 初始化PopupWindow
     */
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
                .setDimView(mRecyclerView)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if (corrSelect == 0) {
                            iv_tab1.setRotation(0);
                        } else if (corrSelect == 1) {
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
                } else if (corrSelect == 1) {
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    insurance_type = (int) item.get("id");
                }
                presenter.initData(1,10,keyword,getCurrCityId(),third_area_id);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new BigHealthListFragmentAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(page,10,keyword,getCurrCityId(),third_area_id);
                }
            }
        },mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA,list.get(position).getHealth_id());
                    startAtvDonFinish(BigHealthDetailActivity.class, bundle);
                }
            }
        });

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1,10,keyword,getCurrCityId(),third_area_id);
            }
        });
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
        layoutParams.height  =  275*layoutParams.width/1010;
        fl_banner.setLayoutParams(layoutParams);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        fl_banner.setImages(images);
        moduleBannerPresenter = new ModuleBannerPresenter(getActivity(),this);
        moduleBannerPresenter.getBannerList(17);
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
    public void initDataSuccess(BigHealthListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(BigHealthListBean bean) {
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
    public void loadNextDataSuccess(BigHealthListBean bean) {
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
        popList.setAdapter(adapter);
        if (easyPopup != null) {
            easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_tab1.setRotation(180);
        }
    }

    private void checkEnd() {
        if (page>=pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_tab1:
                presenter.loadAreaData(getCurrCityId());
                corrSelect = 0;
                break;
        }
    }
}
