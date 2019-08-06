package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.QuickIndexCarAdapter;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.QuickIndexCarPresenter;
import com.windmillsteward.jukutech.activity.home.commons.quickindex.QuickIndexCityAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.customview.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述：一定要先初始化数据再显示
 * 时间：2018/1/26
 * 作者：xjh
 */

public class QuickIndexCarActivity extends BaseActivity implements QuickIndexCarActivityView {
    public static final int RESULT_CODE = 200;
    public static final String ID = "ID";
    public static final String TEXT = "TEXT";
    private ArrayList<CarClassListBean> datas;
    private int preIndex = -1;
    private Handler mHandler = new Handler();

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ListView list;
    private QuickIndexBar bar;
    private TextView tv_index_layout;
    private QuickIndexCarAdapter adapter;
    private QuickIndexCarPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_index);

        initView();
        initToolbar();
        datas = new ArrayList<>();
        presenter = new QuickIndexCarPresenter(this);
        presenter.loadAreaData();
    }

    private void initListView() {
        adapter = new QuickIndexCarAdapter(this, datas);
        list.setAdapter(adapter);
        // 设置选中监听
        list.setOnItemClickListener(new QuickIndexContactsItemListener());

        // 设置滚动监听
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (preIndex != firstVisibleItem) {
                    // 更新索引
                    int index = datas.get(firstVisibleItem).getPinyin().charAt(0) - 'A';
                    bar.setCurrentSelectedIndex(index);
                    preIndex = firstVisibleItem;
                }
            }
        });

        bar.setOnLetterUpdateListener(new QuickIndexBar.OnLetterUpdateListener() {

            @Override
            public void onUpdate(String letter) {
                int selectedPosition = -1;
                for (int i = 0; i < datas.size(); i++) {
                    if (letter.equals(datas.get(i).getPinyin().charAt(0) + "")) {
                        selectedPosition = i;
                        break;
                    }
                }
                list.setSelection(selectedPosition);
                // 在索引层中显示索引
                showIndexLayout(letter);
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("");
    }

    /* 在索引层中显示索引 */
    private void showIndexLayout(String letter) {
        tv_index_layout.setVisibility(View.VISIBLE);
        tv_index_layout.setText(letter);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_index_layout.setVisibility(View.INVISIBLE);
            }
        }, 2000);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        list = (ListView) findViewById(R.id.list);
        bar = (QuickIndexBar) findViewById(R.id.bar);
        tv_index_layout = (TextView) findViewById(R.id.tv_index_layout);
    }

    @Override
    public void initDataSuccess(List<CarClassListBean> list) {
        datas.clear();
        datas.addAll(list);
        Collections.sort(datas);
        initListView();
    }


    private class QuickIndexContactsItemListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (datas != null && datas.size() > 0) {
                Intent data = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(ID, datas.get(position).getBrand_id());
                bundle.putString(TEXT, datas.get(position).getBrand_name());
                data.putExtras(bundle);
                setResult(RESULT_CODE, data);
                finish();
            }
        }
    }
}
