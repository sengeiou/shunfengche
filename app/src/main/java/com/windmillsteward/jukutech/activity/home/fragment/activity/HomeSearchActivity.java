package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HomeSearchPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.databasebean.HotSearchKeyBean;
import com.windmillsteward.jukutech.customview.ZFlowLayout;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.litepal.crud.DataSupport;

import java.util.List;
import java.util.Map;

/**
 * 描述：首页搜索页面
 * author:cyq
 * 2018-03-30
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeSearchActivity extends BaseActivity implements View.OnClickListener, HomeSearchView, TextWatcher {

    private TextView tv_select;
    private EditText et_searchHint;
    private TextView tv_search;
    private TextView tv_clear;
    private LinearLayout lay_ll_hot_search;
    private ZFlowLayout lay_flow_hot_search;

    private HomeSearchPresenter presenter;

    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        initView();
    }

    private void initView() {
        tv_select = (TextView) findViewById(R.id.tv_select);
        et_searchHint = (EditText) findViewById(R.id.et_searchHint);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        lay_ll_hot_search = (LinearLayout) findViewById(R.id.lay_ll_hot_search);
        lay_flow_hot_search = (ZFlowLayout) findViewById(R.id.lay_flow_hot_search);

        tv_select.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
        et_searchHint.addTextChangedListener(this);
    }

    private void initData() {
        if (presenter == null) {
            presenter = new HomeSearchPresenter(this);
        }

        //只搜索后八条
        List<HotSearchKeyBean> limitList = DataSupport.where().order("id desc").limit(8).find(HotSearchKeyBean.class);
        for (int i = 0; i < limitList.size() - 1; i++) {
            for (int j = limitList.size() - 1; j > i; j--) {
                if (limitList.get(j).getKeyword().equals(limitList.get(i).getKeyword())) {
                    limitList.remove(j);
                }
            }
        }
        if (limitList.size() != 0) {
            lay_ll_hot_search.setVisibility(View.VISIBLE);
            initLabel(limitList);
        }else{
            lay_ll_hot_search.setVisibility(View.GONE);
        }
    }


    /**
     * 加载标签数据
     *
     * @param list
     */
    private void initLabel(final List<HotSearchKeyBean> list) {
        lay_flow_hot_search.removeAllViews();
        int wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT;
        final ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(wrapContent, wrapContent);
        layoutParams.setMargins(30, 30, 10, 10);// 设置边距
        for (int i = 0; i < list.size(); i++) {
            final TextView textView = new TextView(this);
            textView.setTag(i);
            textView.setTextSize(13);
            textView.setText(list.get(i).getKeyword());
            textView.setPadding(24, 11, 24, 11);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.shape_retangle_circle_r3_bg_white);
            lay_flow_hot_search.addView(textView, layoutParams);
            // 标签点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) textView.getTag();
                    //搜索
                    SearchModuleActivity.go(HomeSearchActivity.this, type, list.get(position).getKeyword());
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select:
                presenter.loadSelectTypeDta();
                break;
            case R.id.tv_clear:
                DataSupport.deleteAll(HotSearchKeyBean.class);
                lay_flow_hot_search.removeAllViews();
                List<HotSearchKeyBean> limitList = DataSupport.where().order("id desc").limit(8).find(HotSearchKeyBean.class);
                if (limitList.size() != 0) {
                    lay_ll_hot_search.setVisibility(View.VISIBLE);
                }else{
                    lay_ll_hot_search.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_search:
                String key = et_searchHint.getText().toString();
                if (key.length() > 0) {
                    //先把本次搜索的关键字存入数据库
                    HotSearchKeyBean bean = new HotSearchKeyBean();
                    bean.setKeyword(key);
                    bean.save();
                    //搜索
                    SearchModuleActivity.go(HomeSearchActivity.this, type, key);
                } else {
                    //取消
                    finish();
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String key = editable.toString();
        if (key.length() > 0) {
            tv_search.setText("搜索");
        } else {
            tv_search.setText("取消");
        }
    }

    @Override
    public void getHistorySearchListDataSuccess() {

    }

    @Override
    public void getHistorySearchListDataFailed() {

    }

    @Override
    public void getSelectTypeDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this, maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
                    @Override
                    public void onSelect(int id, int pos, String text) {
                        HomeSearchActivity.this.type = id;
                        tv_select.setText(text);
                    }
                })
                .show();
    }
}
