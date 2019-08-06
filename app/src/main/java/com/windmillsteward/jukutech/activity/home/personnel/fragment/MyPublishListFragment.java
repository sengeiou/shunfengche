package com.windmillsteward.jukutech.activity.home.personnel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.activity.MyPositionDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.MyResumeDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.ResumeDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.MyPublishAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.MyPublishListPresenter;
import com.windmillsteward.jukutech.activity.mine.activity.EditPositionDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditResumeDetailActivity;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.listener.OnItemClickListener;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：我发布的简历列表
 * 时间：2018/1/16
 * 作者：xjh
 */

public class MyPublishListFragment extends BaseFragment implements MyPublishListView {

    public static final String TYPE = "TYPE";
    private static final int REQUEST_CODE_DELETE = 100;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private List<MyPublishBean.ListBean> list;
    private MyPublishAdapter adapter;
    private MyPublishListPresenter presenter;
    private int page, pageSize;
    private int type = 1;


    /**
     * 获取我的发布列表的fragment的实例
     *
     * @param type type【1：职位，2：简历】
     * @return MyPublishListFragment
     */
    public static MyPublishListFragment getInstance(int type) {
        MyPublishListFragment fragment = new MyPublishListFragment();
        Bundle b = new Bundle();
        b.putInt(MyPublishListFragment.TYPE, type);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.base_listview, container, false);
        initView(view);
        initListView();
        presenter = new MyPublishListPresenter(getContext(), this);
        presenter.initData(getAccessToken(),type);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_DELETE && resultCode == MyPositionDetailActivity.DELETE_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                int position = extras.getInt(MyPositionDetailActivity.POSITION);
                this.list.remove(position);
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode==REQUEST_CODE_DELETE && resultCode == MyResumeDetailActivity.DELETE_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                int position = extras.getInt(MyPositionDetailActivity.POSITION);
                this.list.remove(position);
                adapter.notifyDataSetChanged();
            }
        }

        if (requestCode==REQUEST_CODE_DELETE && resultCode == EditPositionDetailActivity.DELETE_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                int position = extras.getInt(EditPositionDetailActivity.POSITION);
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        }

    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new MyPublishAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position<list.size()) {
                    if (type == 1) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(EditPositionDetailActivity.DETAIL_ID, list.get(position).getId());
                        bundle.putInt(EditPositionDetailActivity.POSITION, position);
                        startAtvDonFinishForResult(EditPositionDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt(EditResumeDetailActivity.RESUME_ID, list.get(position).getId());
                        bundle.putInt(EditResumeDetailActivity.POSITION, position);
                        startAtvDonFinishForResult(EditResumeDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                    }
                }

            }
        });
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page, type);
                }
            }
        },mRecyclerView);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),type);
            }
        });
    }


    /**
     * 初始化数据成功
     */
    @Override
    public void initDataSuccess(MyPublishBean bean) {
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        list.clear();
        list.addAll(bean.getList());
        adapter.setNewData(list);
        checkEnd();
    }

    /**
     * 刷新数据成功
     * 如果为空刷新失败
     */
    @Override
    public void refreshDataSuccess(MyPublishBean bean) {
        if (bean == null) {
            return;
        }

        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        list.clear();
        list.addAll(bean.getList());
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
    public void loadNextDataSuccess(MyPublishBean bean) {
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
        adapter.loadMoreComplete();
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
        if (page>=pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }
}
