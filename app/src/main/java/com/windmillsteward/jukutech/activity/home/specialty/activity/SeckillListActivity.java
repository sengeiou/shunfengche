package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.SeckillListAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.SeckillListPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.SeckillListBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CountDownUtils;
import com.windmillsteward.jukutech.utils.LogUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：秒杀列表
 * author:lc
 * 2018-07-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SeckillListActivity extends BaseActivity implements View.OnClickListener, SeckillListView, CountDownUtils.OnCountDownUpdateListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private LinearLayout ll_content;
    private TextView tv_count_down;
    private TextView tv_about_of_start;
    private TextView tv_time_content;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private SeckillListPresenter presenter;
    private int onClickLocation = 0;// 点击的位置
    private CountDownUtils countDownUtils;
    private long leftTime;
    private boolean timeOut;
    private int page;
    private int pageSize;

    private List<SeckillListBean.CListBean.ListBean> list;
    private SeckillListAdapter alertsAdapter;

    private boolean is_current_list_seckill_start;// 当前列表是否开始秒杀 true开始秒杀  false还没开始秒杀

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seckill_list);
        initView();
        initData();
    }

    private void initView() {
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_count_down = (TextView) findViewById(R.id.tv_count_down);
        tv_about_of_start = (TextView) findViewById(R.id.tv_about_of_start);
        tv_time_content = (TextView) findViewById(R.id.tv_time_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) findViewById(R.id.common_refresh);

        toolbar_iv_title.setText("秒杀");
        handleBackEvent(toolbar_iv_back);

    }

    private void initData() {
        list = new ArrayList<>();
        alertsAdapter = new SeckillListAdapter(this, list);
        alertsAdapter.setEnableLoadMore(true);

        alertsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pageSize) {
                    page++;
                    presenter.loadNextData(page);
                }
            }
        }, mRecyclerView);
        alertsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < list.size() && is_current_list_seckill_start) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA, list.get(position).getCommodity_id());
                    startAtvDonFinish(SeckillDetailActivity.class, bundle);
                }
            }
        });
        alertsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData();
            }
        });

        if (presenter == null) {
            presenter = new SeckillListPresenter(this);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(alertsAdapter);
        presenter.initData();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    private void startCountDown(int count) {
        if (countDownUtils == null) {
            countDownUtils = new CountDownUtils(count, 1, 1);
            countDownUtils.setOnCountDownUpdateListener(this);
        }
        countDownUtils.startCountDown();
    }

    private void checkEnd() {
        if (page >= pageSize) {
            alertsAdapter.loadMoreEnd();
        } else {
            alertsAdapter.loadMoreComplete();
        }
    }

    @Override
    public void getSeckillListSuccess(List<SeckillListBean> seckillListBean) {
    }

    @Override
    public void getSeckillFailed(int code, String msg) {
    }


//    private int a = 0;

    @Override
    public void getSeckillInitDataSuccess(List<SeckillListBean> seckillListBean) {
        list.clear();
        list.addAll(seckillListBean.get(onClickLocation).getC_list().getList());
        page = seckillListBean.get(onClickLocation).getC_list().getPageNumber();
        pageSize = seckillListBean.get(onClickLocation).getC_list().getTotalPage();
        alertsAdapter.notifyDataSetChanged();
        checkEnd();


        long currentTimesTamp = TimeUtils.getCurrentTimesTamp();
//        if (a == 0) {
//            a++;
//            currentTimesTamp = 1531227595;
//        } else if (a == 1) {
//            a++;
//            currentTimesTamp = 1531231195;
//        } else {
//            currentTimesTamp = 1531234795;
//        }


        ll_content.removeAllViews();
        for (int i = 0; seckillListBean.size() > i; i++) {
            SeckillListBean seckillListBean1 = seckillListBean.get(i);
            View inflate = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_seckill_scorl, ll_content, false);
            final LinearLayout ll_content_other = (LinearLayout) inflate.findViewById(R.id.ll_content);
            ll_content_other.setTag(i);
            TextView tv_time = (TextView) inflate.findViewById(R.id.tv_time);
            TextView tv_content = (TextView) inflate.findViewById(R.id.tv_content);

            long start_time = seckillListBean1.getStart_time();
            long end_time = seckillListBean1.getEnd_time();
//            long currentTimesTamp = TimeUtils.getCurrentTimesTamp();
            if (currentTimesTamp < start_time) {// 如果当前时间小于开始时间就是 即将开始 否则就是 正在抢购
                tv_content.setText("即将开始");
                if (i == onClickLocation) {
                    startCountDown((int) ((start_time - currentTimesTamp)));
                    is_current_list_seckill_start = false;
                    ll_content_other.setBackgroundColor(getResources().getColor(R.color.color_white));
                    tv_time.setText(seckillListBean1.getTime_str());
                    tv_time.setTextColor(getResources().getColor(R.color.color_text_999));
                    tv_content.setTextColor(getResources().getColor(R.color.color_text_999));
                } else {
                    ll_content_other.setBackgroundColor(getResources().getColor(R.color.color_bg_f0f0));
                    tv_time.setText(seckillListBean1.getTime_str());
                    tv_time.setTextColor(getResources().getColor(R.color.color_text_999));
                    tv_content.setTextColor(getResources().getColor(R.color.color_text_999));
                }

            } else {
                tv_content.setText("正在抢购");
                if (i == onClickLocation) {
                    startCountDown((int) ((end_time - currentTimesTamp)));
                    is_current_list_seckill_start = true;
                    ll_content_other.setBackgroundColor(getResources().getColor(R.color.color_white));
                    tv_time.setText(seckillListBean1.getTime_str());
                    tv_time.setTextColor(getResources().getColor(R.color.color_text_94b));
                    tv_content.setTextColor(getResources().getColor(R.color.color_text_94b));
                } else {
                    ll_content_other.setBackgroundColor(getResources().getColor(R.color.color_bg_f0f0));
                    tv_time.setText(seckillListBean1.getTime_str());
                    tv_time.setTextColor(getResources().getColor(R.color.color_text_94b));
                    tv_content.setTextColor(getResources().getColor(R.color.color_text_94b));
                }
            }
            ll_content_other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) ll_content_other.getTag();
                    if (onClickLocation != tag) {
                        if (countDownUtils != null) {
                            countDownUtils.stopCountDown();
                            countDownUtils = null;
                        }
                        onClickLocation = tag;
                        presenter.initData();
                    }
                }
            });
            ll_content.addView(inflate);
        }

        if (is_current_list_seckill_start) {
            tv_about_of_start.setText("正在抢购");
            tv_time_content.setText("剩余时间");
            LogUtil.e("正在抢购");
        } else {
            tv_about_of_start.setText("即将开始");
            tv_time_content.setText("距开始倒计时");
            LogUtil.e("即将开始");
        }
    }

    @Override
    public void getSeckillRefreshDataSuccess(List<SeckillListBean> seckillListBean) {
        list.clear();
        list.addAll(seckillListBean.get(onClickLocation).getC_list().getList());
        page = seckillListBean.get(onClickLocation).getC_list().getPageNumber();
        pageSize = seckillListBean.get(onClickLocation).getC_list().getTotalPage();
        alertsAdapter.notifyDataSetChanged();
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void getSeckillRefreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void getSeckillLoadNextDataSuccess(List<SeckillListBean> seckillListBean) {
        list.addAll(seckillListBean.get(onClickLocation).getC_list().getList());
        alertsAdapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void getSeckillLoadNextFailure() {
        page--;
        alertsAdapter.loadMoreFail();
        checkEnd();
    }

    @Override
    public void countDownUpdate(final boolean isEnd, final int number) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_count_down.setText(String.format(Locale.getDefault(), TimeUtils.getLeftTime(number)));
                if (is_current_list_seckill_start) {
                    alertsAdapter.refreshState(is_current_list_seckill_start);
                    if (isEnd) {
                        countDownUtils.stopCountDown();
                        countDownUtils = null;
                        timeOut = true;
                        presenter.initData();
                        onClickLocation = 0;
                    }
                } else {
                    alertsAdapter.refreshState(is_current_list_seckill_start);
                    if (isEnd) {
                        countDownUtils.stopCountDown();
                        countDownUtils = null;
                        timeOut = true;
                        presenter.initData();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownUtils != null) {
            countDownUtils.stopCountDown();
            countDownUtils = null;
        }
    }
}
