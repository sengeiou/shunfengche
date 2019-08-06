package com.windmillsteward.jukutech.activity.home.personnel.fragment;

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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.ModuleBannerView;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.ModuleBannerPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.EduPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.PositionAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SalaryPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.WorkPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.PositionListPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.listener.OnItemClickListener;
import com.windmillsteward.jukutech.bean.HomePositionClassBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PositionListBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：搜索用-职位列表
 * 时间：2018/1/8
 * 作者：cyq
 */

public class SearchPositionFragment extends BaseFragment implements View.OnClickListener, PositionView, ModuleBannerView {

    public static final String KEY = "KEY";
    public static final String CURR_CLASS = "CURR_CLASS";
    private EasyPopup mCirclePop;
    private PositionAdapter adapter;
    private EasyPopup morePop;
    private ListView popListView;
    private MyGridView gv_work_years, gv_edu_years;
    private List<PositionListBean.ListBean> list;
    private PositionListPresenter presenter;
    private int menuIndex;
    private ImageView iv_area_point;
    private LinearLayout linear_area;
    private ImageView iv_salary_point;
    private LinearLayout linear_salary;
    private ImageView iv_more_point;
    private LinearLayout linear_more;
    private LinearLayout linear_root_select;
    private String keyword;
    private TextView tv_tab1;
    private TextView tv_tab2;
    private TextView tv_tab3;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private FlyBanner fl_banner;
    private int page;
    private int pageSize;
    private int areaId, salaryId, workId, eduId;
    private int job_class_id = 0;
    public boolean needRefresh;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private ModuleBannerPresenter moduleBannerPresenter;


    /**
     * 获取职位列表的fragment的实例
     *
     * @param keyword keyword 搜索关键字
     * @return PositionFragment
     */
    public static SearchPositionFragment getInstance(String keyword, int job_class_id) {
        SearchPositionFragment fragment = new SearchPositionFragment();
        Bundle b = new Bundle();
        b.putString(KEY, keyword);
        b.putInt(CURR_CLASS, job_class_id);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString(KEY, "搜索");
            job_class_id = bundle.getInt(CURR_CLASS, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_position, container, false);

        initView(view);
        initListView();
        initPopMenu();
        initMorePopup();


        presenter = new PositionListPresenter(getContext(), this);
        presenter.initData(keyword, getCurrCityId(), areaId, salaryId, workId, eduId, job_class_id);
        initFlynner();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(keyword, getCurrCityId(), areaId, salaryId, workId, eduId, job_class_id);
            needRefresh = false;
        }
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    /**
     * 实例化控件
     */
    private void initView(View view) {

        iv_area_point = (ImageView) view.findViewById(R.id.iv_area_point);
        linear_area = (LinearLayout) view.findViewById(R.id.linear_area);
        iv_salary_point = (ImageView) view.findViewById(R.id.iv_salary_point);
        linear_salary = (LinearLayout) view.findViewById(R.id.linear_salary);
        iv_more_point = (ImageView) view.findViewById(R.id.iv_more_point);
        linear_more = (LinearLayout) view.findViewById(R.id.linear_more);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);


        tv_tab1.setText("区域");
        tv_tab2.setText("薪资");
        tv_tab3.setText("更多");

        linear_area.setOnClickListener(this);
        linear_salary.setOnClickListener(this);
        linear_more.setOnClickListener(this);

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
        moduleBannerPresenter.getBannerList(13);
        adapter.addHeaderView(header);

    }

    /**
     * 初始化列表
     */
    private void initListView() {
        list = new ArrayList<>();
        adapter = new PositionAdapter(getContext(), list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {
                    PositionListBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(PositionDetailActivity.DETAIL_ID, bean.getJob_id());
                    intent.putExtras(bundle);
                    startAtvDonFinish(PositionDetailActivity.class, bundle);
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page, keyword, getCurrCityId(), areaId, salaryId, workId, eduId, job_class_id);
                }
            }
        }, mRecyclerView);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(keyword, getCurrCityId(), areaId, salaryId, workId, eduId, job_class_id);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_area:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.linear_salary:
                presenter.loadSalaryData();
                break;
            case R.id.linear_more:
                presenter.loadMoreData();
                break;
        }
    }


    /**
     * 初始化弹窗
     */
    private void initPopMenu() {

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        popListView = (LimitHeightListView) inflate.findViewById(R.id.listView);

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
                        if (menuIndex == 0) {
                            iv_area_point.setRotation(0);
                        } else if (menuIndex == 1) {
                            iv_salary_point.setRotation(0);
                        }
                    }
                })
                .createPopup();

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCirclePop.dismiss();
                if (menuIndex == 0) {
                    areaId = ((ThirdAreaBean) parent.getAdapter().getItem(position)).getThird_area_id();
                    tv_tab1.setText(((ThirdAreaBean) parent.getAdapter().getItem(position)).getThird_area_name());
                    presenter.initData(keyword, getCurrCityId(), areaId, salaryId, workId, eduId, job_class_id);
                } else if (menuIndex == 1) {
                    salaryId = ((SalaryBean) parent.getAdapter().getItem(position)).getSalary_id();
                    tv_tab2.setText(((SalaryBean) parent.getAdapter().getItem(position)).getSalary_show());
                    presenter.initData(keyword, getCurrCityId(), areaId, salaryId, workId, eduId, job_class_id);
                }
            }
        });
    }

    private void initMorePopup() {

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popup_resume_more, null);
        morePop = new EasyPopup(getContext())
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
                        iv_more_point.setRotation(0);
                    }
                })
                .createPopup();
        gv_work_years = (MyGridView) inflate.findViewById(R.id.gv_work_years);
        final String[] text = {"", ""};
        gv_work_years.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                WorkPopupAdapter adapter = (WorkPopupAdapter) arg0.getAdapter();
                adapter.setSelect(pos);
                workId = ((MoreBean.WordYearListBean) adapter.getItem(pos)).getWork_year_id();
                text[0] = ((MoreBean.WordYearListBean) adapter.getItem(pos)).getWork_year_show();
            }
        });
        gv_edu_years = (MyGridView) inflate.findViewById(R.id.gv_edu_years);
        gv_edu_years.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                EduPopupAdapter adapter = (EduPopupAdapter) arg0.getAdapter();
                adapter.setSelect(pos);
                eduId = ((MoreBean.EducationListBean) adapter.getItem(pos)).getEducation_background_id();
                text[1] = ((MoreBean.EducationListBean) adapter.getItem(pos)).getEducation_name();
            }
        });
        inflate.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morePop.dismiss();
                presenter.initData(keyword, getCurrCityId(), areaId, salaryId, workId, eduId, job_class_id);
                tv_tab3.setText(text[0] + "/" + text[1]);
            }
        });
        inflate.findViewById(R.id.tv_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((WorkPopupAdapter) gv_work_years.getAdapter()).setSelect(-1);
                ((EduPopupAdapter) gv_edu_years.getAdapter()).setSelect(-1);
                eduId = 0;
                workId = 0;
                tv_tab3.setText("更多");
            }
        });
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

    /**
     * 出事啊胡数据成功
     */
    @Override
    public void initDataSuccess(PositionListBean bean) {
        this.list.clear();
        List<PositionListBean.ListBean> list = bean.getList();
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();

        this.list.addAll(list);
        adapter.setNewData(list);

        checkEnd();
    }

    /**
     * 加载地区数据成功
     */
    @Override
    public void loadAreaDataSuccess(List<ThirdAreaBean> areaBeans) {
        if (mCirclePop != null) {
            popListView.setAdapter(new AreaPopupAdapter(getContext(), areaBeans));
            menuIndex = 0;
            mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_area_point.setRotation(180);
        }
    }

    /**
     * 加载薪资数据成功
     */
    @Override
    public void loadSalaryDataSuccess(List<SalaryBean> list) {
        if (mCirclePop != null) {
            popListView.setAdapter(new SalaryPopupAdapter(getContext(), list));
            menuIndex = 1;
            mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_salary_point.setRotation(180);
        }
    }

    /**
     * 加载更多分类成功
     */
    @Override
    public void loadMoreDataSuccess(MoreBean bean) {
        List<MoreBean.WordYearListBean> word_year_list = bean.getWord_year_list();
        if (word_year_list != null) {
            MoreBean.WordYearListBean element = new MoreBean.WordYearListBean();
            element.setWork_year_id(0);
            element.setWork_year_show("不限");
            word_year_list.add(0, element);
        }
        gv_work_years.setAdapter(new WorkPopupAdapter(getContext(), word_year_list));
        List<MoreBean.EducationListBean> education_list = bean.getEducation_list();
        if (education_list != null) {
            MoreBean.EducationListBean element = new MoreBean.EducationListBean();
            element.setEducation_background_id(0);
            element.setEducation_name("不限");
            education_list.add(0, element);
        }
        gv_edu_years.setAdapter(new EduPopupAdapter(getContext(), education_list));
        workId = 0;
        eduId = 0;
        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        morePop.showAsDropDown(linear_root_select, 0, 0);
        iv_more_point.setRotation(180);
        menuIndex = 2;
    }

    /**
     * 刷新数据成功，失败返回null
     */
    @Override
    public void refreshDataSuccess(PositionListBean bean) {

        this.list.clear();
        List<PositionListBean.ListBean> list = bean.getList();
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();

        this.list.addAll(list);
        adapter.setNewData(list);
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    /**
     * 加载下一页成功
     */
    @Override
    public void loadNextDataSuccess(PositionListBean bean) {

        this.list.addAll(bean.getList());
        adapter.notifyDataSetChanged();

        checkEnd();
    }

    /**
     * 加载下一页失败
     */
    @Override
    public void loadNextDataError() {
        page--;
        adapter.loadMoreFail();
        checkEnd();
    }


    private void checkEnd() {
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void loadPositionClassSuccess(List<HomePositionClassBean> classBeanList) {

    }

    @Override
    public void loadPositionClassFailed(int code, String msg) {

    }


}
