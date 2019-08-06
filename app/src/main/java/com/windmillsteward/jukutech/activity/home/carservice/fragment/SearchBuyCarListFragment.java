package com.windmillsteward.jukutech.activity.home.carservice.fragment;

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
import com.windmillsteward.jukutech.activity.home.carservice.activity.BuyCarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.BuyCarListAdapter;
import com.windmillsteward.jukutech.activity.home.family.presenter.BuyCarListFragmentPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.SimplePopupListAdapter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.BuyCarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
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
 * 时间：2018/3/25/025
 * 作者：xjh
 */
public class SearchBuyCarListFragment extends BaseFragment implements BuyCarListFragmentView, View.OnClickListener {

    public static final String KEYWORD = "KEYWORD";
    private TextView tv_tab1;
    private ImageView iv_tab1;
    private LinearLayout linear_tab1;
    private TextView tv_tab2;
    private ImageView iv_tab2;
    private LinearLayout linear_tab2;
    private LinearLayout linear_root_select;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    // popup弹窗
    private EasyPopup easyPopup;
    // 内容
    private LimitHeightListView listView;
    // 选择的是第几个分类
    private int currSelect;
    // 地区id
    private int third_area_id;
    // 价格id
    private int price_id;
    // 关键词
    private String keyword;
    // 分页
    private int page;
    // 总页数
    private int pageSize;

    private BuyCarListFragmentPresenter presenter;
    private BuyCarListAdapter adapter;
    private List<BuyCarListBean.ListBean> list;

    public static SearchBuyCarListFragment getInstance(String keyword) {
        SearchBuyCarListFragment fragment = new SearchBuyCarListFragment();
        Bundle args = new Bundle();
        args.putString(KEYWORD, keyword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyword = bundle.getString(KEYWORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_buycar_list, container, false);
        initView(view);
        initTabBar();
        initPopup();
        initRecyclerView();
        presenter = new BuyCarListFragmentPresenter(this);
        presenter.initData(keyword,getCurrCityId(), third_area_id, price_id);
        return view;
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new BuyCarListAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page, keyword, getCurrCityId(),third_area_id, price_id);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {
                    BuyCarListBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(BuyCarDetailActivity.DETAIL_ID, bean.getBuy_car_id());
                    startAtvDonFinish(BuyCarDetailActivity.class, bundle);
                }
            }
        });

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(keyword, getCurrCityId(),third_area_id, price_id);
            }
        });
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_popup_area_select, null);
        listView = (LimitHeightListView) inflate.findViewById(R.id.listView);

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
                        if (currSelect == 0) {  // 地区分类
                            iv_tab1.setRotation(0);
                        } else if (currSelect == 1) {  // 价格分类
                            iv_tab2.setRotation(0);
                        }
                    }
                })
                .createPopup();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                easyPopup.dismiss();
                if (currSelect == 0) {  // 地区分类
                    ThirdAreaBean areaBean = (ThirdAreaBean) parent.getAdapter().getItem(position);
                    third_area_id = areaBean.getThird_area_id();
                    tv_tab1.setText(areaBean.getThird_area_name());
                } else if (currSelect == 1) {  // 价格分类
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    price_id = (int) item.get("id");
                    tv_tab2.setText((String) item.get("text"));
                }
                presenter.initData(keyword,getCurrCityId(), third_area_id, price_id);
            }
        });
    }

    private void initTabBar() {
        tv_tab1.setText("区域");
        tv_tab2.setText("价格");
        linear_tab1.setOnClickListener(this);
        linear_tab2.setOnClickListener(this);
    }

    private void initView(View view) {
        tv_tab1 = (TextView) view.findViewById(R.id.tv_tab1);
        iv_tab1 = (ImageView) view.findViewById(R.id.iv_tab1);
        linear_tab1 = (LinearLayout) view.findViewById(R.id.linear_tab1);
        tv_tab2 = (TextView) view.findViewById(R.id.tv_tab2);
        iv_tab2 = (ImageView) view.findViewById(R.id.iv_tab2);
        linear_tab2 = (LinearLayout) view.findViewById(R.id.linear_tab2);
        linear_root_select = (LinearLayout) view.findViewById(R.id.linear_root_select);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    @Override
    public void initDataSuccess(BuyCarListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(BuyCarListBean bean) {
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
    public void loadNextDataSuccess(BuyCarListBean bean) {
        list.addAll(bean.getList());
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
    public void loadAreaDataSuccess(List<ThirdAreaBean> list) {
        ThirdAreaBean areaBean = new ThirdAreaBean();
        areaBean.setThird_area_id(0);
        areaBean.setThird_area_name("全部");
        list.add(0, areaBean);
        AreaPopupAdapter adapter = new AreaPopupAdapter(getContext(), list);
        listView.setAdapter(adapter);
        if (easyPopup != null) {
            easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_tab1.setRotation(180);
        }
    }

    @Override
    public void loadPriceDataSuccess(List<Map<String, Object>> maps) {
        listView.setAdapter(new SimplePopupListAdapter(getContext(), maps));
        easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
        iv_tab2.setRotation(180);
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
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
                currSelect = 0;
                break;
            case R.id.linear_tab2:
                presenter.loadCarPriceListData();
                currSelect = 1;
                break;
        }
    }
}
