package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.MessageListAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.MessageListPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.MessageBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.utils.UmengPushUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：消息列表
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MessageListActivity extends BaseActivity implements MessageListView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private MessageListAdapter adapter;

    private MessageListPresenter presenter;

    private List<MessageBean.ListBean> list;

    private int page;
    private int pageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("系统消息");
    }

    private void initData() {
        list = new ArrayList<>();

        adapter = new MessageListAdapter(this, list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(), page);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size()) {
                    MessageBean.ListBean bean = list.get(position);
                    String msg_type = bean.getMsg_type();
                    String relevance_id = bean.getRelevance_id();
                    String type = bean.getType();
                    String msg_id = bean.getMsg_id();
                    if ("1".equals(msg_type)){
                        MessageListDetailActivity.go(MessageListActivity.this,msg_id);
                    }else if ("2".equals(msg_type)){
                        UmengPushUtil util = new UmengPushUtil(MessageListActivity.this,bean);
                        util.jumpRelativeActivityByType();
                    }
                }
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken());
            }
        });

        if (presenter == null) {
            presenter = new MessageListPresenter(this);
        }
        presenter.initData(getAccessToken());
    }


    @Override
    public void initDataSuccess(MessageBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(MessageBean bean) {
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
    public void loadNextDataSuccess(MessageBean bean) {
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

    private void checkEnd() {
        if (page >= pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }
}
