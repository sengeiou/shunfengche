package com.windmillsteward.jukutech.activity.home.think.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.ModuleBannerView;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.ModuleBannerPresenter;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.MyMenuViewPageAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.SpecialtyMenuGridViewAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkDetailActivity;
import com.windmillsteward.jukutech.activity.home.think.adapter.IdeaThinkAdapter;
import com.windmillsteward.jukutech.activity.home.think.adapter.IdeaThinkMenuGridViewAdapter;
import com.windmillsteward.jukutech.activity.home.think.presenter.IdeaThinkListPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBeanTwo;
import com.windmillsteward.jukutech.bean.IdeaThinkListBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
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
 * 描述：思想智库
 * 时间：2018/2/5
 * 作者：xjh
 */

public class IdeaThinkListFragment extends BaseFragment implements IdeaThinkListView, View.OnClickListener,ModuleBannerView {

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

    private IdeaThinkAdapter adapter;
    private List<IdeaThinkListBean.ListBean> list;
    private int page,pageSize;
    private String keyword;
    private int third_area_id;
    private int second_class_id;

    private IdeaThinkListPresenter presenter;
    private LimitHeightListView popListView;
    private EasyPopup easyPopup;
    private EasyPopup classEasyPopup;
    private int menuIndex;
    private LimitHeightListView popListViewMenu,popListViewData;
    public boolean needRefresh;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private ModuleBannerPresenter moduleBannerPresenter;

    private ViewPager mViewpager;
    private LinearLayout lay_ll_points;
    private ImageView[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 5; //每页显示的最大的数量
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中

    public static IdeaThinkListFragment getInstance(String keyword,int curr_type) {
        IdeaThinkListFragment fragment = new IdeaThinkListFragment();
        Bundle args = new Bundle();
        args.putString(KEYWORD,keyword);
        args.putInt(CURR_CLASS,curr_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments!=null) {
            keyword = arguments.getString(KEYWORD);
            second_class_id = arguments.getInt(CURR_CLASS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ideathink, container, false);
        initView(view);
        initRecyclerView();
        initPopup();
        initClassPopup();
        initFlynner();

        presenter = new IdeaThinkListPresenter(this);
        presenter.initData(keyword,1,10,getCurrCityId(),third_area_id,second_class_id);
        presenter.loadClassDataTwo();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(keyword,1,10,getCurrCityId(),third_area_id,second_class_id);
            needRefresh = false;
        }
    }

    private void initClassPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_idea_class, null);
        popListViewMenu = ((LimitHeightListView) inflate.findViewById(R.id.listView_menu));
        popListViewData = ((LimitHeightListView) inflate.findViewById(R.id.listView_data));
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
                presenter.initData(keyword,1,10,getCurrCityId(),third_area_id,second_class_id);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new IdeaThinkAdapter(list);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(keyword,page,10,getCurrCityId(),third_area_id,second_class_id);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int require_id = list.get(position).getRequire_id();
                Bundle bundle = new Bundle();
                bundle.putInt(IdeaThinkDetailActivity.REQUIRE_ID,require_id);
                startAtvDonFinish(IdeaThinkDetailActivity.class,bundle);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(keyword,1,10,getCurrCityId(),third_area_id,second_class_id);
            }
        });
    }

    private void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);


    }


    public void setViewPageData(List<IdeaThinkClassBeanTwo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        lay_ll_points.removeAllViews();
        totalPage = (int) Math.ceil(list.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.idea_home_viewpage_gridview, null);
            gridView.setAdapter(new IdeaThinkMenuGridViewAdapter(getActivity(), list, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof SpecialtyClassBean) {
//                                            Toast.makeText(MyActivity.this, ((ProdctBean)obj).getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        mViewpager.setAdapter(new MyMenuViewPageAdapter(viewPagerList));

        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(getActivity());
            if (i == 0) {
                ivPoints[i].setImageResource(R.mipmap.icon_step_g);
            } else {
                ivPoints[i].setImageResource(R.mipmap.icon_step);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            lay_ll_points.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        mViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.mipmap.icon_step_g);
                    } else {
                        ivPoints[i].setImageResource(R.mipmap.icon_step);
                    }
                }
            }
        });

    }

    /**
     * 初始化轮播图
     */
    private void initFlynner() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.idea_think_list_header_banner, (ViewGroup) mRecyclerView.getParent(), false);
        fl_banner = (FlyBanner) header.findViewById(R.id.fl_banner);
        mViewpager = (ViewPager) header.findViewById(R.id.mViewpager);
        lay_ll_points = (LinearLayout) header.findViewById(R.id.lay_ll_points);
        tv_tab1 = (TextView) header.findViewById(R.id.tv_tab1);
        iv_tab1 = (ImageView) header.findViewById(R.id.iv_tab1);
        tv_tab2 = (TextView) header.findViewById(R.id.tv_tab2);
        iv_tab2 = (ImageView) header.findViewById(R.id.iv_tab2);
        linear_tab1 = (LinearLayout) header.findViewById(R.id.linear_tab1);
        linear_tab2 = (LinearLayout) header.findViewById(R.id.linear_tab2);
        linear_tab1.setOnClickListener(this);
        linear_tab2.setOnClickListener(this);

        ViewGroup.LayoutParams layoutParams = fl_banner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(getActivity(), GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(getActivity(), 20);
        layoutParams.height =  275*layoutParams.width/1010;
        fl_banner.setLayoutParams(layoutParams);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        fl_banner.setImages(images);
        moduleBannerPresenter = new ModuleBannerPresenter(getActivity(),this);
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
        page --;
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
    public void loadClassDataSuccess(final List<IdeaThinkClassBean> list) {
        if (list.isEmpty()) {
            return;
        }
        final List<Map<String,Object>> maps = new ArrayList<>();
        for (IdeaThinkClassBean bean : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",bean.getFirst_class_id());
            map.put("text",bean.getFirst_class_name());
            maps.add(map);
        }
        final StringBuilder sb = new StringBuilder();
        popListViewMenu.setAdapter(new SimplePopupListAdapter(getContext(),maps));

        List<IdeaThinkClassBean.SecondClassListBean> secondClassList = list.get(0).getSecond_class_list();
        sb.append(list.get(0).getFirst_class_name()).append("/");
        final List<Map<String,Object>> maps_data = new ArrayList<>();
        for (IdeaThinkClassBean.SecondClassListBean bean : secondClassList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",bean.getSecond_class_id());
            map.put("text",bean.getSecond_class_name());
            maps_data.add(map);
        }

        popListViewData.setAdapter(new SimplePopupListAdapter(getContext(),maps_data));

        popListViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int first_id = (int) maps.get(position).get("id");
                for (int i = 0; i < list.size(); i++) {
                    int curr_id = (int) maps.get(i).get("id");
                    if (curr_id == first_id) {
                        List<IdeaThinkClassBean.SecondClassListBean> secondClassList = list.get(i).getSecond_class_list();

                        List<Map<String,Object>> maps = new ArrayList<>();
                        for (IdeaThinkClassBean.SecondClassListBean bean : secondClassList) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("id",bean.getSecond_class_id());
                            map.put("text",bean.getSecond_class_name());
                            maps.add(map);
                        }
                        sb.replace(0,sb.length(),"");
                        sb.append(list.get(i).getFirst_class_name()).append("/");
                        popListViewData.setAdapter(new SimplePopupListAdapter(getContext(),maps));
                        break;
                    }
                }
            }
        });

        popListViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                classEasyPopup.dismiss();
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                second_class_id = (int) item.get("id");
                sb.append(item.get("text"));
                tv_tab2.setText(sb.toString());
                presenter.initData(keyword,1,10,getCurrCityId(),third_area_id,second_class_id);
            }
        });

        if (classEasyPopup != null) {
            classEasyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            classEasyPopup.showAtAnchorView(linear_tab1, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_tab2.setRotation(180);
            menuIndex = 1;
        }
    }

    @Override
    public void loadClassDataTwoSuccess(List<IdeaThinkClassBeanTwo> list) {
        setViewPageData(list);
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
                break;
            case R.id.linear_tab2:
                presenter.loadClassData();
                break;
        }
    }
}
