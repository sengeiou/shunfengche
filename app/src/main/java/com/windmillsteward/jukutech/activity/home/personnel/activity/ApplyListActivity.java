package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.ApplyListAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SexPopupAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.ApplyListPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.listener.OnItemClickListener;
import com.windmillsteward.jukutech.bean.EmployResumeListBean;
import com.windmillsteward.jukutech.bean.SexBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.LimitHeightListView;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：应聘简历列表
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class ApplyListActivity extends BaseActivity implements ApplyListView, View.OnClickListener {

    private ImageView iv_look_point;
    private LinearLayout linear_look;
    private ImageView iv_screen_point;
    private LinearLayout linear_screen;
    private ImageView toolbar_iv_back;
    private TextView toolbar_tv_right;
    private View bottomView;
    private ApplyListPresenter presenter;
    private List<EmployResumeListBean.ListBean> list;
    private ApplyListAdapter adapter;
    private int pageSize;
    private int page;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private EasyPopup mCirclePop;
    private ListView popListView;
    private int menuIndex = -1;
    private int is_view = 0;
    private int sort_type = 0;
    private LinearLayout linear_root_select;
    private TextView tv_look;
    private TextView tv_screen;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applylist);
        initView();
        initToolbar();
        initListView();

        initPopup();

        presenter = new ApplyListPresenter(this);
        presenter.initData(getAccessToken(),is_view, sort_type);
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.view_popup_area_select, null);
        popListView = (LimitHeightListView) inflate.findViewById(R.id.listView);

        mCirclePop = new EasyPopup(this)
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
                            iv_look_point.setRotation(0);
                        } else if (menuIndex == 2) {
                            iv_screen_point.setRotation(0);
                        }
                    }
                })
                .createPopup();

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCirclePop.dismiss();
                if (menuIndex == 1) {
                    is_view = ((SexBean) parent.getAdapter().getItem(position)).getSex_id();
                    tv_look.setText(((SexBean) parent.getAdapter().getItem(position)).getSex_name());
                } else if (menuIndex == 2) {
                    sort_type = ((SexBean) parent.getAdapter().getItem(position)).getSex_id();
                    tv_screen.setText(((SexBean) parent.getAdapter().getItem(position)).getSex_name());
                }
                presenter.initData(getAccessToken(),is_view, sort_type);
            }
        });
    }


    private void initListView() {
        list = new ArrayList<>();
        adapter = new ApplyListAdapter(this, list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    EmployResumeListBean.ListBean bean = list.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt(ResumeDetailActivity.RESUME_ID, bean.getResume_id());
                    startAtvDonFinish(ResumeDetailActivity.class, bundle);
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page ++;

                }
            }
        },mRecyclerView);
        adapter.setEnableLoadMore(true);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),is_view, sort_type);
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("应聘简历");
    }

    private void initView() {
        iv_look_point = (ImageView) findViewById(R.id.iv_look_point);
        linear_look = (LinearLayout) findViewById(R.id.linear_look);
        iv_screen_point = (ImageView) findViewById(R.id.iv_screen_point);
        linear_screen = (LinearLayout) findViewById(R.id.linear_screen);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);

        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        linear_root_select = (LinearLayout) findViewById(R.id.linear_root_select);

        linear_look.setOnClickListener(this);
        linear_screen.setOnClickListener(this);
        tv_look = (TextView) findViewById(R.id.tv_look);
        tv_screen = (TextView) findViewById(R.id.tv_screen);
    }

    @Override
    public void initDataSuccess(EmployResumeListBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);

        checkEnd();
    }

    @Override
    public void refreshDataSuccess(EmployResumeListBean bean) {
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
    public void loadNextDataSuccess(EmployResumeListBean bean) {
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();

        checkEnd();
    }

    @Override
    public void loadNextError(String msg) {
        page--;
        showTips(msg, Toast.LENGTH_SHORT);
        adapter.loadMoreFail();
        checkEnd();
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
            case R.id.linear_look:
                List<SexBean> list = new ArrayList<>();
                SexBean bean = new SexBean();
                bean.setSex_name("全部");
                bean.setSex_id(0);
                list.add(bean);
                SexBean bean1 = new SexBean();
                bean1.setSex_name("未查看");
                bean1.setSex_id(1);
                list.add(bean1);
                SexBean bean2 = new SexBean();
                bean2.setSex_name("已查看");
                bean2.setSex_id(2);
                list.add(bean2);
                popListView.setAdapter(new SexPopupAdapter(this, list));

                mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
                iv_look_point.setRotation(180);
                menuIndex = 1;
                break;
            case R.id.linear_screen:
                List<SexBean> list1 = new ArrayList<>();
                SexBean bean3 = new SexBean();
                list1.clear();
                bean3.setSex_name("最新");
                bean3.setSex_id(0);
                list1.add(bean3);
                SexBean bean4 = new SexBean();
                bean4.setSex_name("最早");
                bean4.setSex_id(1);
                list1.add(bean4);
                popListView.setAdapter(new SexPopupAdapter(this, list1));

                mCirclePop.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mCirclePop.showAtAnchorView(linear_root_select, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
                iv_screen_point.setRotation(180);
                menuIndex = 2;
                break;
        }
    }
}
