package com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.model.MarriageMsgModel;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.AutoPollRecyclerView;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @date: on 2018/10/7
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 姻缘服务列表
 */
public class YinyuanInfoListFragment extends BaseInitFragment {
    @Bind(R.id.recyclerView)
    AutoPollRecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.layout_yinyuan_list;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        hidTitleView();
    }

    @Override
    protected void initData() {
        initAdapter();
        getData();
    }

    @Override
    protected void refreshPageData() {
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MARRIAGE_MESSAGE_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<MarriageMsgModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<MarriageMsgModel>> respnse, String source) {
                        dismiss();
                        showContentView();
                        if (respnse.getData() != null) {
                            list.clear();
                            list.addAll(respnse.getData());
                            adapter.notifyDataSetChanged();
                            recyclerView.start();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<MarriageMsgModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 外部调用
     */
    public void refresh() {
        refreshPageData();
    }

    private AutoPollAdapter adapter;
    private List<MarriageMsgModel> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new AutoPollAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    class AutoPollAdapter extends RecyclerView.Adapter<AutoPollAdapter.BaseViewHolder> {
        private final List<MarriageMsgModel> mData;

        public AutoPollAdapter(List<MarriageMsgModel> list) {
            this.mData = list;
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_yinyuan_list, parent, false);
            BaseViewHolder holder = new BaseViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            if(mData.size()!=0) {
                MarriageMsgModel data = mData.get(position % mData.size());
                holder.tv_right.setText(data.getAdd_time());
                holder.tv_left.setText(data.getMessage());
            }
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class BaseViewHolder extends RecyclerView.ViewHolder {
            TextView tv_left;
            TextView tv_right;

            public BaseViewHolder(View itemView) {
                super(itemView);
                tv_left = itemView.findViewById(R.id.tv_left);
                tv_right = itemView.findViewById(R.id.tv_right);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(recyclerView!=null){
            recyclerView.stop();
        }
    }
}