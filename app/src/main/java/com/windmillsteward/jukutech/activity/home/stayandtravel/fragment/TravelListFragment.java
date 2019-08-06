package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.TravelListAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.TravelListPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.listener.OnItemClickListener;
import com.windmillsteward.jukutech.bean.TravelListBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：
 * 时间：2018/1/30
 * 作者：xjh
 */

public class TravelListFragment extends BaseFragment implements TravelListView, View.OnClickListener {

    public static final String TYPE = "CLASS_TYPE";
    public static final String KEYWORD = "KEYWORD";

    private TextView tv_tab1;
    private ImageView iv_area_point;
    private LinearLayout linear_area;
    private TextView tv_tab2;
    private ImageView iv_salary_point;
    private LinearLayout linear_salary;
    private TextView tv_tab3;
    private ImageView iv_more_point;
    private LinearLayout linear_more;
    private LinearLayout linear_root_select;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private LimitHeightListView popListView;
    private EasyPopup mCirclePop;

    private List<TravelListBean.ListBean> list;
    private TravelListAdapter adapter;
    private TravelListPresenter presenter;

    private int travel_class_id;
    private String keyword;
    private int third_area_id;
    private int travel_area_id;
    private int price_id;

    private int page;
    private int pageSize;
    private int menuIndex;
    public boolean needRefresh;
    /**
     * 发车到此
     *
     * @param type type 0：全部；44：周边游；45：跟团游；46：自由行；47：一日游
     */
    public static TravelListFragment getInstance(int type, String keyword) {
        TravelListFragment fragment = new TravelListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        bundle.putString(KEYWORD, keyword);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getArguments();
        if (extras!=null) {
            travel_class_id = extras.getInt(TYPE);
            keyword = extras.getString(KEYWORD,"");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travellist, container, false);

        initView(view);
        initPopup();
        initRecyclerView();

        presenter = new TravelListPresenter(this);
        presenter.initData(1,10,travel_class_id,keyword,getCurrCityId(),third_area_id,price_id,travel_area_id);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(1,10,travel_class_id,keyword,getCurrCityId(),third_area_id,price_id,travel_area_id);
            needRefresh = false;
        }
    }

    private void initView(View view) {
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        iv_area_point = (ImageView) view.findViewById(R.id.iv_area_point);
        linear_area = (LinearLayout) view.findViewById(R.id.linear_area);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        iv_salary_point = (ImageView) view.findViewById(R.id.iv_salary_point);
        linear_salary = (LinearLayout) view.findViewById(R.id.linear_salary);
        tv_tab3 = (TextView) view.findViewById(R.id.tv_tab3);
        iv_more_point = (ImageView) view.findViewById(R.id.iv_more_point);
        linear_more = (LinearLayout) view.findViewById(R.id.linear_more);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);

        tv_tab1.setText("发布地区");
        tv_tab2.setText("旅游地区");
        tv_tab3.setText("价格");

        linear_area.setOnClickListener(this);
        linear_salary.setOnClickListener(this);
        linear_more.setOnClickListener(this);
    }

    private void initPopup() {
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
                .setDimView(common_refresh)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if (menuIndex == 1) {
                            iv_area_point.setRotation(0);
                        } else if (menuIndex == 2) {
                            iv_salary_point.setRotation(0);
                        } else if (menuIndex == 3) {
                            iv_more_point.setRotation(0);
                        }
                    }
                })
                .createPopup();

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCirclePop.dismiss();
                Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                int _id = (int) item.get("id");
                String text = (String) item.get("text");
                if (menuIndex == 1) {
                    third_area_id = _id;
                    tv_tab1.setText(text);
                } else if (menuIndex == 2) {
                    travel_area_id = _id;
                    tv_tab2.setText(text);
                } else if (menuIndex == 3) {
                    price_id = _id;
                    tv_tab3.setText(text);
                }
                presenter.initData(1,10,travel_class_id,keyword,getCurrCityId(),third_area_id,price_id,travel_area_id);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new TravelListAdapter(getContext(),list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setAdapter(adapter);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1,10,travel_class_id,keyword,getCurrCityId(),third_area_id,price_id,travel_area_id);
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                if (position<list.size()) {
                    TravelListBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(TravelDetailActivity.DETAIL_ID,bean.getTravel_id());
                    startAtvDonFinish(TravelDetailActivity.class,bundle);
                }
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page ++;
                    presenter.loadNextData(page,10,travel_class_id,keyword,getCurrCityId(),third_area_id,price_id,travel_area_id);
                }
            }
        },mRecyclerView);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void initDataSuccess(TravelListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);

        checkEnd();
    }

    @Override
    public void refreshDataSuccess(TravelListBean bean) {
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
    public void loadNextDataSuccess(TravelListBean bean) {
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        adapter.loadMoreComplete();

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

        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_area_point.setRotation(180);
        menuIndex = 1;
    }

    @Override
    public void loadTravelAreaDataSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));

        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_salary_point.setRotation(180);
        menuIndex = 2;
    }

    @Override
    public void loadTravelPriceSuccess(List<Map<String, Object>> maps) {
        popListView.setAdapter(new SimplePopupListAdapter(getContext(), maps));

        mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_more_point.setRotation(180);
        menuIndex = 3;
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
            case R.id.linear_search:  // 0：全部；44：周边游；45：跟团游；46：自由行；47：一日游
                switch (travel_class_id) {
                    case 0:
                        SearchItemActivity.go(getContext(),10,0);
                        break;
                    case 44:
                        SearchItemActivity.go(getContext(),11,0);
                        break;
                    case 45:
                        SearchItemActivity.go(getContext(),12,0);
                        break;
                    case 46:
                        SearchItemActivity.go(getContext(),13,0);
                        break;
                    case 47:
                        SearchItemActivity.go(getContext(),14,0);
                        break;
                }
                break;
            case R.id.linear_area:
                presenter.loadAreaData(getCurrCityId());
                break;
            case R.id.linear_salary:
                presenter.loadTravelAreaData();
                break;
            case R.id.linear_more:
                presenter.loadTravelPriceData();
                break;
        }
    }
}
