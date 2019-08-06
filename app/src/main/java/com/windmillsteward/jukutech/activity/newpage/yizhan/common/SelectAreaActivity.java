package com.windmillsteward.jukutech.activity.newpage.yizhan.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 描述：发布地区页面
 * author:JK
 * 2019/2/26
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */
public class SelectAreaActivity extends BaseInitActivity {

    public static final int GET_CITY_REQUEST_CODE = 999;
    public static final String LAST_SECOND_ID = "last_second_id";
    public static final String LAST_SECOND_NAME = "last_second_name";
    public static final String LAST_THIRD_ID = "last_third_id";

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private List<ThirdAreaBean> listSelect;
    private int last_second_id;//上一次选的城市id
    private String last_second_name;//上一次选的城市名字
    private int last_third_id;//上一次选的区id

    public static void go(Activity activity,int second_area_id,int third_id,String second_area_name) {
        Intent intent = new Intent(activity, SelectAreaActivity.class);
        intent.putExtra(LAST_SECOND_ID,second_area_id);
        intent.putExtra(LAST_THIRD_ID,third_id);
        intent.putExtra(LAST_SECOND_NAME,second_area_name);
        activity.startActivityForResult(intent, GET_CITY_REQUEST_CODE);
    }

    public static void go(Fragment activity,int second_area_id,int third_id,String second_area_name) {
        Intent intent = new Intent(activity.getActivity(), SelectAreaActivity.class);
        intent.putExtra(LAST_SECOND_ID,second_area_id);
        intent.putExtra(LAST_THIRD_ID,third_id);
        intent.putExtra(LAST_SECOND_NAME,second_area_name);
        activity.startActivityForResult(intent, GET_CITY_REQUEST_CODE);
    }

    @Override
    protected void initView(View view) {
        setMainTitle("选择发布地区");
        showContentView();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview;
    }

    @Override
    protected void initData() {
        if (getIntent() != null){
            last_second_id = getIntent().getIntExtra(LAST_SECOND_ID, 0);
            last_second_name = getIntent().getStringExtra(LAST_SECOND_NAME);
            last_third_id = getIntent().getIntExtra(LAST_THIRD_ID, 0);
        }


        setMainTitleRightContent(TextUtils.isEmpty(last_second_name)?"":last_second_name);
        Drawable arrowDown = getResources().getDrawable(R.mipmap.icon_select_down);
        arrowDown.setBounds(0, 0, arrowDown.getMinimumWidth(), arrowDown.getMinimumHeight());
        getRightTv().setCompoundDrawables(null, null, arrowDown, null);
        getRightTv().setTextColor(getResources().getColor(R.color.black));
        getRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到选择城市页面
                startAtvDonFinishForResult(SelectCityForPublishActivity.class,SelectCityForPublishActivity.GET_CITY_REQUEST_CODE);
            }
        });

        listSelect = new ArrayList<>();
        adapter = new RecyclerViewAdapter(listSelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(false);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ThirdAreaBean thirdAreaBean = listSelect.get(position);
                Intent intent = new Intent();
                intent.putExtra("thirdId", thirdAreaBean.getThird_area_id());
                intent.putExtra("thirdName", thirdAreaBean.getThird_area_name());
                intent.putExtra("secondName", last_second_name);
                intent.putExtra("secondId", last_second_id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        getAreaData(last_second_id);
    }

    @Override
    protected void refreshPageData() {

    }

    class RecyclerViewAdapter extends BaseQuickAdapter<ThirdAreaBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<ThirdAreaBean> data) {
            super(R.layout.item_recycler_select_area, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ThirdAreaBean item) {
            helper.setText(R.id.tv_area, item.getThird_area_name());
            if (item.isSelect()){
                helper.getView(R.id.iv_select).setVisibility(View.VISIBLE);
            }else{
                helper.getView(R.id.iv_select).setVisibility(View.GONE);
            }
        }
    }

    //获取所选城市下的区列表
    private void getAreaData(int cityId) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_THIRD_AREA_LIST)
                .addParams("second_area_id", cityId + "")
                .setCallBackData(new BaseNewNetModelimpl<List<ThirdAreaBean>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse.getData() != null) {
                            listSelect.clear();
                            listSelect.addAll((List<ThirdAreaBean>) respnse.getData());
                            boolean isFirst = false;
                            for (ThirdAreaBean bean : listSelect){
                                int third_area_id = bean.getThird_area_id();
                                if (last_third_id == third_area_id){
                                    bean.setSelect(true);
                                    isFirst = true;
                                }else{
                                    bean.setSelect(false);
                                }
                            }
                            if (!isFirst){
                                if (listSelect != null && listSelect.size() >0){
                                    listSelect.get(0).setSelect(true);
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<ThirdAreaBean>>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectCityForPublishActivity.GET_CITY_REQUEST_CODE) {
            //地址
            if (data != null) {
                last_second_id = data.getIntExtra("city_id", 0);
                last_second_name = data.getStringExtra("city_name");
                getRightTv().setText(last_second_name);
                getAreaData(last_second_id);
            }
        }
    }
}
