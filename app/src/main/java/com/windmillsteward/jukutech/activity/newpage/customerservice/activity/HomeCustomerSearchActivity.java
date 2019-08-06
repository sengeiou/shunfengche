package com.windmillsteward.jukutech.activity.newpage.customerservice.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.HomeCustomerModel;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DistanceUtils;
import com.windmillsteward.jukutech.utils.MapNaviUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class HomeCustomerSearchActivity extends BaseInitActivity {
    @Bind(R.id.iv_backs)
    ImageView ivBacks;
    @Bind(R.id.ed_search)
    EditText edSearch;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;

    @Override
    protected void initView(View view) {
        hidTitleView();
        showContentView();

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    list.clear();
                    adapter.loadMoreEnd();
                    adapter.notifyDataSetChanged();
                } else
                    search(s.toString());
            }
        });

        initAdapter();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home_customer_search;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {
        if (!TextUtils.isEmpty(edSearch.getText().toString()))
            search(edSearch.getText().toString());
        else {
            list.clear();
            showContentView();
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.iv_backs, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_backs:
                finish();
                break;
            case R.id.tv_cancel:
                if (tvCancel.getText().toString().equals("取消"))
                    finish();
                else {
                    if (!TextUtils.isEmpty(edSearch.getText().toString()))
                        search(edSearch.getText().toString());
                }
                break;
        }
    }

    //搜索
    private void search(String key) {
        final String longlati = KV.get(Define.CURR_LONGLAT_ADDRESS);
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_CONVENIEN_SEARCH)
                .addParams("name", key)
                .addParams("longitude", longlati.split(",")[0])
                .addParams("latitude", longlati.split(",")[1])
                .setCallBackData(new BaseNewNetModelimpl<List<HomeCustomerModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<HomeCustomerModel>> respnse, String source) {
                        dismiss();
                        showContentView();
                        if(respnse.getData()!=null){
                            list.clear();
                            list.addAll(respnse.getData());
                            adapter.loadMoreEnd();
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<HomeCustomerModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private RecyclerViewAdapter adapter;
    private List<HomeCustomerModel> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeCustomerModel bean = list.get(position);
                if (bean != null){
                    if (!TextUtils.isEmpty(bean.getDesc_url())){
                        Bundle bundle = new Bundle();
                        bundle.putString(Define.INTENT_DATA, bean.getDesc_url());
                        startAtvDonFinish(CommonWebActivity.class, bundle);
                    }
                }
            }
        });
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<HomeCustomerModel, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HomeCustomerModel> data) {
            super(R.layout.item_recycler_home_customer_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomeCustomerModel item) {
            helper.setText(R.id.tv_title, item.getService_name())
                    .setText(R.id.tv_phone, item.getContact_tel())
                    .setText(R.id.tv_distance, DistanceUtils.getFormatDistance(item.getDistance()));

            Glide.with(HomeCustomerSearchActivity.this)
                    .load(item.getPic_url()).into((CircleImageView) helper.getView(R.id.iv_avatar));
            helper.getView(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapNaviUtils.showDaoHangDialog(HomeCustomerSearchActivity.this, Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), item.getService_address());

                }
            });
            helper.getView(R.id.tv_phone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapNaviUtils.showDaoHangDialog(HomeCustomerSearchActivity.this, Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), item.getService_address());

                }
            });

            helper.getView(R.id.tv_distance).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapNaviUtils.showDaoHangDialog(HomeCustomerSearchActivity.this,Double.parseDouble(item.getLongitude()),Double.parseDouble(item.getLatitude()),item.getService_address());

                }
            });

            helper.getView(R.id.iv_play).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                    if (checkPermission(permissions)) {
                        PhoneUtils.dial(item.getContact_tel());
                    }
                }
            });
        }
    }

}
