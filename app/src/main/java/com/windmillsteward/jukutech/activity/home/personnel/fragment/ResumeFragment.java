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
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.ModuleBannerView;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.ModuleBannerPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.ApplyListActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.MyPublishListActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.ResumeDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.EduPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.ResumeAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SalaryPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SexPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.WorkPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.ResumeListPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.listener.OnItemClickListener;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.ResumeListBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.SexBean;
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
 * 描述：简历列表
 * 时间：2018/1/8
 * 作者：xjh
 */

public class ResumeFragment extends BaseFragment implements ResumeView, View.OnClickListener,ModuleBannerView {

    public static final String KEY = "KEY";
    public static final String TYPE = "TYPE";
    public static final String CLASS_ID = "CLASS_ID";

    private ResumeListPresenter presenter;
    private ImageView iv_area_point;
    private LinearLayout linear_area;
    private ImageView iv_sex_point;
    private LinearLayout linear_sex;
    private ImageView iv_salary_point;
    private LinearLayout linear_salary;
    private ImageView iv_more_point;
    private LinearLayout linear_more;
    private LinearLayout linear_root_select;
    private LinearLayout linear_header;
    private List<ResumeListBean.ListBean> list;
    private ResumeAdapter adapter;
    private EasyPopup mCirclePop;
    private LimitHeightListView popListView;
    private EasyPopup morePopup;
    private MyGridView gv_work_years;
    private MyGridView gv_edu_years;

    private int page;
    private int pageSize;
    private int menuIndex;
    private int areaId, sexId, salaryId, workId, eduId;
    private View bottomView;
    private LinearLayout learn_my_publish;
    private LinearLayout learn_apply;
    private String keyword;
    private int type = 0;
    private LinearLayout linear_apply;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private FlyBanner fl_banner;
    private TextView tv_tab1;
    private TextView tv_tab2;
    private TextView tv_tab3;
    private TextView tv_tab4;
    public boolean needRefresh;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private ModuleBannerPresenter moduleBannerPresenter;



    /**
     * 获取简历列表的fragment的实例
     *
     * @param type    【type=0时是正常的简历列表，头部有我的发布入口；type=1时是搜索的简历界面，没有头部】
     * @param keyword keyword 搜索关键字
     * @param class_id class_id分类id
     * @return PositionFragment
     */
    public static ResumeFragment getInstance(int type, String keyword, int class_id) {
        ResumeFragment fragment = new ResumeFragment();
        Bundle b = new Bundle();
        b.putString(ResumeFragment.KEY, keyword);
        b.putInt(ResumeFragment.TYPE, type);
        b.putInt(ResumeFragment.CLASS_ID, class_id);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString(KEY, "");
            type = bundle.getInt(TYPE);
            eduId = bundle.getInt(CLASS_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resume, container, false);
        initView(view);

        initListView();
        initListener();

        initNormalPopup();
        initMorePopup();
        initFlynner();

        presenter = new ResumeListPresenter(getContext(), this);
        presenter.initData(keyword, sexId, getCurrCityId(), areaId, salaryId, workId, eduId);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(keyword, sexId, getCurrCityId(), areaId, salaryId, workId, eduId);
            needRefresh = false;
        }
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    private void initMorePopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popup_resume_more, null);

        morePopup = new EasyPopup(getContext())
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
        final String[] text = {"",""};
        gv_work_years = (MyGridView) inflate.findViewById(R.id.gv_work_years);
        gv_work_years.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                WorkPopupAdapter adapter = (WorkPopupAdapter) arg0.getAdapter();
                adapter.setSelect(pos);
                workId = ((MoreBean.WordYearListBean) adapter.getItem(pos)).getWork_year_id();
                text[0] =((MoreBean.WordYearListBean) adapter.getItem(pos)).getWork_year_show();
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
                morePopup.dismiss();
                presenter.initData(keyword, sexId, getCurrCityId(), areaId, salaryId, workId, eduId);
                tv_tab4.setText(text[0]+"/"+text[1]);
            }
        });
        inflate.findViewById(R.id.tv_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((WorkPopupAdapter) gv_work_years.getAdapter()).setSelect(0);
                ((EduPopupAdapter) gv_edu_years.getAdapter()).setSelect(0);
                eduId = 0;
                workId = 0;
                tv_tab4.setText("更多");
            }
        });
    }

    private void initNormalPopup() {
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
                            iv_sex_point.setRotation(0);
                        } else if (menuIndex == 2) {
                            iv_salary_point.setRotation(0);
                        }
                    }
                })
                .createPopup();
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                mCirclePop.dismiss();
                if (menuIndex == 0) {
                    areaId = ((ThirdAreaBean) arg0.getAdapter().getItem(pos)).getThird_area_id();
                    tv_tab1.setText(((ThirdAreaBean) arg0.getAdapter().getItem(pos)).getThird_area_name());
                } else if (menuIndex == 1) {
                    sexId = ((SexBean) arg0.getAdapter().getItem(pos)).getSex_id();
                    tv_tab2.setText(((SexBean) arg0.getAdapter().getItem(pos)).getSex_name());
                } else if (menuIndex == 2) {
                    salaryId = ((SalaryBean) arg0.getAdapter().getItem(pos)).getSalary_id();
                    tv_tab3.setText(((SalaryBean) arg0.getAdapter().getItem(pos)).getSalary_show());
                }

                presenter.initData(keyword, sexId, getCurrCityId(), areaId, salaryId, workId, eduId);
            }
        });
    }


    private void initView(View view) {
        iv_area_point = (ImageView) view.findViewById(R.id.iv_area_point);
        linear_area = (LinearLayout) view.findViewById(R.id.linear_area);
        iv_sex_point = (ImageView) view.findViewById(R.id.iv_sex_point);
        linear_sex = (LinearLayout) view.findViewById(R.id.linear_sex);
        iv_salary_point = (ImageView) view.findViewById(R.id.iv_salary_point);
        linear_salary = (LinearLayout) view.findViewById(R.id.linear_salary);
        iv_more_point = (ImageView) view.findViewById(R.id.iv_more_point);
        linear_more = (LinearLayout) view.findViewById(R.id.linear_more);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        linear_header = (LinearLayout) view.findViewById(R.id.linear_header);
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
        tv_tab4 = (TextView) view.findViewById(R.id.tv_tab4);

        learn_my_publish = (LinearLayout) view.findViewById(R.id.learn_my_publish);
        learn_my_publish.setOnClickListener(this);
        learn_apply = (LinearLayout) view.findViewById(R.id.linear_apply);
        learn_apply.setOnClickListener(this);

        if (type != 0) {
            linear_header.setVisibility(View.GONE);
        } else {
            linear_header.setVisibility(View.VISIBLE);
        }
        linear_apply = (LinearLayout) view.findViewById(R.id.linear_apply);
        linear_apply.setOnClickListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
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
        moduleBannerPresenter = new ModuleBannerPresenter(getActivity(),this);
        moduleBannerPresenter.getBannerList(13);
        adapter.addHeaderView(header);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new ResumeAdapter(getContext(), list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {
                    ResumeListBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(ResumeDetailActivity.RESUME_ID, bean.getResume_id());
                    startAtvDonFinish(ResumeDetailActivity.class, bundle);
                }

            }
        });
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page, keyword, getCurrCityId(), areaId, sexId, salaryId, workId, eduId);
                }
            }
        }, mRecyclerView);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(keyword, getCurrCityId(), areaId, sexId, salaryId, workId, eduId);
            }
        });
    }


    private void initListener() {
        linear_area.setOnClickListener(this);
        linear_salary.setOnClickListener(this);
        linear_sex.setOnClickListener(this);
        linear_more.setOnClickListener(this);
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

    /**
     * 初始化数据
     *
     * @param bean 数据
     */
    @Override
    public void initDataSuccess(ResumeListBean bean) {
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();

        list.clear();
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();

        checkEnd();
    }

    /**
     * 刷新数据
     */
    @Override
    public void refreshDataSuccess(ResumeListBean bean) {
        if (bean == null) {
            showTips("加载失败", Toast.LENGTH_SHORT);
            return;
        }
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();

        list.clear();
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    /**
     * 加载下一页数据
     */
    @Override
    public void loadNextDataSuccess(ResumeListBean bean) {
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();

        checkEnd();
    }

    /**
     * 加载下一页失败
     */
    @Override
    public void loadNextError(String msg) {
        page--;
        showTips(msg, Toast.LENGTH_SHORT);
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

    /**
     * 加载区域成功
     */
    @Override
    public void loadAreaDataSuccess(List<ThirdAreaBean> list) {
        popListView.setAdapter(new AreaPopupAdapter(getContext(), list));
        iv_area_point.setRotation(180);
        menuIndex = 0;
        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
    }

    /**
     * 加载性别数据成功
     */
    @Override
    public void loadSexDataSuccess(List<SexBean> list) {
        popListView.setAdapter(new SexPopupAdapter(getContext(), list));
        iv_sex_point.setRotation(180);
        menuIndex = 1;
        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
    }

    /**
     * 加载薪资数据成功
     */
    @Override
    public void loadSalaryDataSuccess(List<SalaryBean> list) {
        popListView.setAdapter(new SalaryPopupAdapter(getContext(), list));
        iv_salary_point.setRotation(180);
        menuIndex = 2;
        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
    }

    /**
     * 加载更多数据成功
     */
    @Override
    public void loadMoreDataSuccess(MoreBean bean) {
        List<MoreBean.WordYearListBean> word_year_list = bean.getWord_year_list();
        if (word_year_list!=null) {
            MoreBean.WordYearListBean element = new MoreBean.WordYearListBean();
            element.setWork_year_id(0);
            element.setWork_year_show("不限");
            word_year_list.add(0, element);
        }
        gv_work_years.setAdapter(new WorkPopupAdapter(getContext(), word_year_list));
        List<MoreBean.EducationListBean> education_list = bean.getEducation_list();
        if (education_list!=null) {
            MoreBean.EducationListBean element = new MoreBean.EducationListBean();
            element.setEducation_background_id(0);
            element.setEducation_name("不限");
            education_list.add(0, element);
        }
        gv_edu_years.setAdapter(new EduPopupAdapter(getContext(), education_list));
        workId = 0;
        eduId = 0;
        iv_more_point.setRotation(180);
        menuIndex = 3;
        morePopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        morePopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_area:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.linear_sex:
                presenter.loadSexData();
                break;
            case R.id.linear_salary:
                presenter.loadSalaryData();
                break;
            case R.id.linear_more:
                presenter.loadMoreData();
                break;
            case R.id.learn_my_publish:
                if (isLogin()) {
                    startAtvDonFinish(MyPublishListActivity.class);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_apply:
                if (isLogin()) {
                    startAtvDonFinish(ApplyListActivity.class);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            default:
                break;
        }
    }

}
