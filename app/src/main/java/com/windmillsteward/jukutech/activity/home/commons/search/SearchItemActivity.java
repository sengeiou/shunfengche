package com.windmillsteward.jukutech.activity.home.commons.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.SearchHistoryBean;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：搜索记录界面
 * 时间：2018/1/15
 * 作者：xjh
 */

public class SearchItemActivity extends BaseActivity implements SearchItemView, View.OnClickListener {

    public static final String TYPE = "TYPE";
    public static final String CLASS_ID = "CLASS_ID";
    private int type;
    private int class_id;
    private EditText et_search;
    private TextView tv_cancel;
    private TextView tv_clear;
    private FlowLayout fl_content;

    private List<SearchHistoryBean> list = new ArrayList<>();
    private LayoutInflater inflater;
    private SearchItemPresenter presenter;

    /**
     * 跳转到该界面
     *
     * @param type type=0：职位搜索；1：简历搜索；2：；3：智慧家庭 4:智慧生活-订餐服务
     *             10：旅游全部 11：周边游 12：跟团游 13：自由行 14：一日游 15：酒店 16: 家庭房源
     *             20: 思想智库
     *             30: 房屋租售-买房 31：租房
     *             40：车辆买卖-车辆列表
     *             50：大健康--保险
     *             60: 特产
     *             70: 资本管理-理财 71: 贷款
     *             80: 劳务中心
     *             81: 消防
     *             82: 大健康--大健康
     * @param class_id 分类id
     */
    public static void go(Context context, int type,int class_id) {
        Intent intent = new Intent(context, SearchItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(SearchItemActivity.TYPE, type);
        bundle.putInt(SearchItemActivity.CLASS_ID, class_id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchitem);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(TYPE);
            class_id = extras.getInt(CLASS_ID);
        }

        initView();
        initListener();
        presenter = new SearchItemPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadHistory(type);
    }

    private void initListener() {
        tv_cancel.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        fl_content = (FlowLayout) findViewById(R.id.fl_content);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_search.getText().toString().trim().length() == 0) {
                    tv_cancel.setText("取消");
                } else {
                    tv_cancel.setText("搜索");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void submit() {
        String search = et_search.getText().toString().trim();
        if (!TextUtils.isEmpty(search)) {
            Bundle bundle = new Bundle();
            bundle.putInt(SearchResultActivity.TYPE, type);
            bundle.putInt(SearchResultActivity.CLASS_ID, class_id);
            bundle.putString(SearchResultActivity.KEY, search);
            startAtvDonFinish(SearchResultActivity.class, bundle);
            SearchHistoryBean bean = new SearchHistoryBean();
            bean.setKey(search);
            list.add(bean);
            Hawk.put(Define.SEARCH_HISTORY + type, list);
        } else {
            finish();
        }
    }

    @Override
    public void onLoadHistory(List<SearchHistoryBean> list) {
        fl_content.removeAllViews();
        this.list.clear();
        if (list != null) {
            this.list.addAll(list);
            for (final SearchHistoryBean bean : list) {
                TextView view = (TextView) inflater.inflate(R.layout.type_searchhistory, fl_content, false);
                view.setText(bean.getKey());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(SearchResultActivity.TYPE, type);
                        bundle.putString(SearchResultActivity.KEY, bean.getKey());
                        bundle.putInt(SearchResultActivity.CLASS_ID, class_id);
                        startAtvDonFinish(SearchResultActivity.class, bundle);
                    }
                });
                fl_content.addView(view);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                submit();
                break;
            case R.id.tv_clear:
                Hawk.delete(Define.SEARCH_HISTORY + type);
                presenter.loadHistory(type);
                break;
        }
    }
}
