package com.windmillsteward.jukutech.activity.home.specialty.fragment;

import android.content.Intent;
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
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.AreaPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyClassActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.SpecialtyListFragmentAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.presenter.SpecialtyListFragmentPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.SpecialtyListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class SpecialtyListFragment extends BaseFragment implements SpecialtyListFragmentView, View.OnClickListener {

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
    private LimitHeightListView popList;
    private EasyPopup easyPopup;

    private SpecialtyListFragmentPresenter presenter;

    private List<SpecialtyListBean.ListBean> list;
    private SpecialtyListFragmentAdapter adapter;

    private int corrSelect;
    private int second_area_id;
    private int third_area_id;
    private int commodity_first_type;
    private int commodity_second_type;
    private int commodity_third_type;
    private int sort_id;
    private String keyword;
    private int page;
    private int pageSize;
    private String class_text;

    public static SpecialtyListFragment getInstance(String keyword,int class_id,int class_id_parent,String class_text) {
        SpecialtyListFragment fragment = new SpecialtyListFragment();
        Bundle args = new Bundle();
        args.putString(Define.INTENT_DATA,keyword);
        args.putInt(Define.INTENT_DATA_TWO,class_id);
        args.putInt(Define.INTENT_DATA_THREE,class_id_parent);
        args.putString(Define.INTENT_DATA_FOUR,class_text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments!=null) {
            keyword = arguments.getString(Define.INTENT_DATA);
            commodity_first_type = arguments.getInt(Define.INTENT_DATA_TWO);
            commodity_second_type = arguments.getInt(Define.INTENT_DATA_THREE);
            class_text = arguments.getString(Define.INTENT_DATA_FOUR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialty_list, container, false);
        initView(view);
        initRecyclerView();
        initPopup();

        presenter = new SpecialtyListFragmentPresenter(this);
        presenter.initData(1,10,commodity_first_type,commodity_second_type,commodity_third_type,second_area_id,third_area_id,sort_id,keyword);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                commodity_first_type = extras.getInt(Define.INTENT_DATA);
                commodity_second_type = extras.getInt(Define.INTENT_DATA_TWO);
                commodity_third_type = extras.getInt(Define.INTENT_DATA_THREE);
                tv_tab2.setText(extras.getString(Define.INTENT_DATA_FOUR));
                presenter.initData(1,10,commodity_first_type,commodity_second_type,commodity_third_type,second_area_id,third_area_id,sort_id,keyword);
            }
        } else if (requestCode==101 && resultCode==200) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                second_area_id = extras.getInt(Define.INTENT_DATA);
                String city_name = extras.getString(Define.INTENT_DATA_TWO);
                third_area_id = extras.getInt(Define.INTENT_DATA_THREE);
                tv_tab1.setText(city_name+"/"+extras.getString(Define.INTENT_DATA_FOUR));
                presenter.initData(1,10,commodity_first_type,commodity_second_type,commodity_third_type,second_area_id,third_area_id,sort_id,keyword);
            }
        }
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
                            iv_area_point.setRotation(0);
                        } else if (corrSelect == 2) {
                            iv_more_point.setRotation(0);
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
                } else if (corrSelect == 2) {
                    Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    sort_id = (int) item.get("id");
                    tv_tab2.setText((String) item.get("text"));
                }
                presenter.initData(1,10,commodity_first_type,commodity_second_type,commodity_third_type,second_area_id,third_area_id,sort_id,keyword);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new SpecialtyListFragmentAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(page,10,commodity_first_type,commodity_second_type,commodity_third_type,second_area_id,third_area_id,sort_id,keyword);
                }
            }
        },mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA,list.get(position).getCommodity_id());
                    startAtvDonFinish(SpecialtyDetailActivity.class, bundle);
                }
            }
        });

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1,10,commodity_first_type,commodity_second_type,commodity_third_type,second_area_id,third_area_id,sort_id,keyword);
            }
        });
    }

    @Override
    public int registStartMode() {
        return singleTask;
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

        tv_tab1.setText("区域");
        tv_tab2.setText(TextUtils.isEmpty(class_text)?"类目":class_text);
        tv_tab3.setText("排序");

        linear_area.setOnClickListener(this);
        linear_salary.setOnClickListener(this);
        linear_more.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(SpecialtyListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(SpecialtyListBean bean) {
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
    public void loadNextDataSuccess(SpecialtyListBean bean) {
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
            iv_area_point.setRotation(180);
            corrSelect = 0;
        }
    }

    @Override
    public void loadSortDataSuccess(List<Map<String, Object>> maps) {
        popList.setAdapter(new SimpleListDialogAdapter(getContext(), maps));
        if (easyPopup != null) {
            easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            easyPopup.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
            iv_more_point.setRotation(180);
            corrSelect = 2;
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_area:
//                presenter.loadAreaData(second_area_id);
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA_TWO,101);
                bundle.putString(Define.INTENT_DATA,getCurrCityName());
                startAtvDonFinishForResult(SelectCityActivity.class, bundle,101);
                break;
            case R.id.linear_salary:
                startAtvDonFinishForResult(SpecialtyClassActivity.class,100);
                break;
            case R.id.linear_more:
                presenter.loadSortData();
                break;
        }
    }
}
