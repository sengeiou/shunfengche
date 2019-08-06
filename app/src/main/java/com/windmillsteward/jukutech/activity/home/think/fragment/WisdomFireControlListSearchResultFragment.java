package com.windmillsteward.jukutech.activity.home.think.fragment;

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
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.activity.home.think.activity.WisdomFireControlDetailActivity;
import com.windmillsteward.jukutech.activity.home.think.adapter.IdeaThinkAdapterAdapter;
import com.windmillsteward.jukutech.activity.home.think.presenter.WisdomFireControlListPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.IdeaThinkListBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：智慧消防列表
 * 时间：2018年7月31日 23:59:26
 * 作者：lc
 */

public class WisdomFireControlListSearchResultFragment extends BaseFragment implements WisdomFireControlListView, View.OnClickListener, ModuleBannerView {

    private static final String KEYWORD = "KEYWORD";
    private static final String CURR_CLASS = "CURR_CLASS";
    private TextView tv_tab1;
    private ImageView iv_tab1;
    private LinearLayout linear_tab1;
    private TextView tv_tab2;
    private ImageView iv_tab2;
    private LinearLayout linear_tab2;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private FlyBanner fl_banner;

    private IdeaThinkAdapterAdapter adapter;
    private List<IdeaThinkListBean.ListBean> list;
    private int page, pageSize;
    private String keyword;
    private int third_area_id;
    private int second_class_id;

    private WisdomFireControlListPresenter presenter;
    private LimitHeightListView popListView;
    private EasyPopup easyPopup;
    private EasyPopup classEasyPopup;
    private int menuIndex;
    private LimitHeightListView popListViewMenu;
    public boolean needRefresh;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private ModuleBannerPresenter moduleBannerPresenter;

    public static WisdomFireControlListSearchResultFragment getInstance(String keyword, int curr_type) {
        WisdomFireControlListSearchResultFragment fragment = new WisdomFireControlListSearchResultFragment();
        Bundle args = new Bundle();
        args.putString(KEYWORD, keyword);
        args.putInt(CURR_CLASS, curr_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            keyword = arguments.getString(KEYWORD);
            second_class_id = arguments.getInt(CURR_CLASS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wisdom_fire_control_list, container, false);
        initView(view);
        initRecyclerView();
        initPopup();
        initClassPopup();
//        initFlynner();

        presenter = new WisdomFireControlListPresenter(this);
        presenter.initData(keyword, 1, 10, getCurrCityId(), third_area_id, second_class_id);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(keyword, 1, 10, getCurrCityId(), third_area_id, second_class_id);
            needRefresh = false;
        }
    }

    private void initClassPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_wisdom_class, null);
        popListViewMenu = ((LimitHeightListView) inflate.findViewById(R.id.listView_menu));
        classEasyPopup = new EasyPopup(getContext())
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
                        if (menuIndex == 1) {
                            iv_tab2.setRotation(0);
                        }
                    }
                })
                .createPopup();
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
                        }
                    }
                })
                .createPopup();

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                easyPopup.dismiss();
                if (menuIndex == 0) {
                    third_area_id = (int) ((Map<String, Object>) parent.getAdapter().getItem(position)).get("id");
                    tv_tab1.setText((String) ((Map<String, Object>) parent.getAdapter().getItem(position)).get("text"));
                }
                presenter.initData(keyword, 1, 10, getCurrCityId(), third_area_id, second_class_id);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new IdeaThinkAdapterAdapter(list);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(keyword, page, 10, getCurrCityId(), third_area_id, second_class_id);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // consumption_id
                int require_id = list.get(position).getConsumption_id();
                Bundle bundle = new Bundle();
                bundle.putInt(WisdomFireControlDetailActivity.REQUIRE_ID, require_id);
                startAtvDonFinish(WisdomFireControlDetailActivity.class, bundle);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(keyword, 1, 10, getCurrCityId(), third_area_id, second_class_id);
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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);

        linear_tab1.setOnClickListener(this);
        linear_tab2.setOnClickListener(this);
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
        layoutParams.height = 275 * layoutParams.width / 1010;
        fl_banner.setLayoutParams(layoutParams);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        fl_banner.setImages(images);
        moduleBannerPresenter = new ModuleBannerPresenter(getActivity(), this);
        moduleBannerPresenter.getBannerList(14);
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
                if (bannerList.size() == 0) {
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
    public void initDataSuccess(IdeaThinkListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);

        checkEnd();
    }

    @Override
    public void refreshDataSuccess(IdeaThinkListBean bean) {
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
    }

    @Override
    public void loadNextDataSuccess(IdeaThinkListBean bean) {
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
        if (easyPopup != null) {
            easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            easyPopup.showAtAnchorView(linear_tab1, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_tab1.setRotation(180);
            menuIndex = 0;
        }
    }

    @Override
    public void loadClassDataSuccess(final List<SpecialtyHomeRecommendBean> list) {
        if (list.isEmpty()) {
            return;
        }
        final List<Map<String, Object>> maps = new ArrayList<>();
        for (SpecialtyHomeRecommendBean bean : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", bean.getClass_id());
            map.put("text", bean.getName());
            maps.add(map);
        }
        final StringBuilder sb = new StringBuilder();
        popListViewMenu.setAdapter(new SimplePopupListAdapter(getContext(), maps));

        popListViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int first_id = (int) maps.get(position).get("id");
                for (int i = 0; i < list.size(); i++) {
                    int curr_id = (int) maps.get(i).get("id");
                    if (curr_id == first_id) {
                        classEasyPopup.dismiss();
                        Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                        second_class_id = (int) item.get("id");
                        sb.append(item.get("text"));
                        tv_tab2.setText(sb.toString());
                        presenter.initData(keyword, 1, 10, getCurrCityId(), third_area_id, second_class_id);
                        break;
                    }
                }
            }
        });


        if (classEasyPopup != null) {
            classEasyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            classEasyPopup.showAtAnchorView(linear_tab1, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_tab2.setRotation(180);
            menuIndex = 1;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_tab1:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.linear_tab2:
                presenter.loadClassData();
                break;
        }
    }
}
