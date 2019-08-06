package com.windmillsteward.jukutech.activity.newpage.pay;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 优惠券
 */
public class YouhuiquanListFragment extends BaseInitFragment {
    public static final String TAG = "YouhuiquanListFragment";
    @Bind(R.id.iv_no_use)
    ImageView ivNoUse;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_youhuiquan_list;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("选择优惠券");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    private RecyclerViewAdapter adapter;
    private List<String> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(true);
        //加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, recyclerView);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<String> data) {
            super(R.layout.item_recyclerview_youhuiquan, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }


}
