package com.windmillsteward.jukutech.activity.newpage.smartlife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.newpage.common.model.CommonBannerModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonBannerPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.DingcanRecommendListModel;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CustomRoundAngleImageView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.view.PickerViewWrap;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 订餐酒店门票搜索
 */
public class DingcanSearchActivity extends BaseInitActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 1025;
    public static final int RESULT_CODE_SUCCESS = 250;
    public static final int WEB_RESULT_CODE_SUCCESS = 300;
    public static final String KEYWORD = "keyword";
    public static final String PEOPLE_NUM = "people_num";
    public static final String SELECT_TIME = "select_time";


    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.flyBanner)
    FlyBanner flyBanner;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_select)
    TextView tvSelect;
    @Bind(R.id.et_num)
    EditText etNum;
    @Bind(R.id.et_keyword)
    EditText etKeyword;
    @Bind(R.id.tv_check)
    TextView tvCheck;
    @Bind(R.id.tv_tuijian_tips)
    TextView tvTuijianTips;
    @Bind(R.id.tv_tuijian_more)
    TextView tvTuijianMore;


//    private FlyBanner flyBanner;
//    private TextView tv_title;
//    private TextView tv_select;
//    private TextView tv_check;
//    private TextView tv_tuijian_tips;
//    private TextView tv_tuijian_more;
//    private EditText et_num;
//    private EditText et_keyword;

    private RecyclerViewAdapter adapter;
    private List<DingcanRecommendListModel> list;

    private CommonBannerPresenter commonBannerPresenter;

    private int page;
    private int type;
    private String yuDingTime = "";

    public static void go(Activity activity, int type) {
        Intent intent = new Intent(activity, DingcanSearchActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void initView(View view) {
        tvSelect.setOnClickListener(this);
        tvCheck.setOnClickListener(this);
        tvTuijianMore.setOnClickListener(this);
        initAdapter();
//        initHeader();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dingcan_search;
    }

    @Override
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("type");
            setMainTitle("搜索");

            if (type == 1) {
                tvTitle.setText("餐厅预定");
                etNum.setHint("就餐人数");
                etKeyword.setHint("关键字/餐厅名");
                tvTuijianTips.setText("推荐餐厅");
                String currentDate_old = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm");
                yuDingTime = DateUtil.DateToStampTime(currentDate_old, "yyyy-MM-dd HH:mm");
                String  currentDate = DateUtil.getCurrentDate("yyyy年MM月dd日 HH:mm");
                tvSelect.setText(currentDate);
            } else if (type == 2) {
                tvTitle.setText("酒店预定");
                etNum.setHint("预定间数");
                etKeyword.setHint("关键字/酒店名");
                tvTuijianTips.setText("推荐酒店");
                String currentDate_old = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm");
                yuDingTime = DateUtil.DateToStampTime(currentDate_old, "yyyy-MM-dd");
                String currentDate = DateUtil.getCurrentDate("yyyy年MM月dd日");
                tvSelect.setText(currentDate);
            } else if (type == 3) {
                tvTitle.setText("门票预定");
                etNum.setHint("预定张数");
                etKeyword.setHint("关键字/景点名");
                tvTuijianTips.setText("推荐景点");
                String currentDate_old = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm");
                yuDingTime = DateUtil.DateToStampTime(currentDate_old, "yyyy-MM-dd");
                String currentDate = DateUtil.getCurrentDate("yyyy年MM月dd日");
                tvSelect.setText(currentDate);
            }
        }
        commonBannerPresenter = new CommonBannerPresenter(this);


        getBannerData();
        getData();
    }

    @Override
    protected void refreshPageData() {
        getBannerData();
        getData();
    }


//    private void initHeader() {
//        View view = View.inflate(this, R.layout.header_recyclerview_dingcan_search, null);
//
//        flyBanner = (FlyBanner) view.findViewById(R.id.flyBanner);
//        tv_title = (TextView) view.findViewById(R.id.tv_title);
//        tv_select = (TextView) view.findViewById(R.id.tv_select);
//        tv_check = (TextView) view.findViewById(R.id.tv_check);
//        tv_tuijian_tips = (TextView) view.findViewById(R.id.tv_tuijian_tips);
//        tv_tuijian_more = (TextView) view.findViewById(R.id.tv_tuijian_more);
//        et_num = (EditText) view.findViewById(R.id.et_num);
//        et_keyword = (EditText) view.findViewById(R.id.et_keyword);
//
//        flyBanner.setPoinstPosition(FlyBanner.RIGHT);
//        flyBanner.setPointPadding(0, 0, GraphicUtil.dp2px(this, 10), GraphicUtil.dp2px(this, 25));
//
//        if (type == 1) {
//            tv_title.setText("餐厅预定");
//            et_num.setHint("就餐人数");
//            et_keyword.setHint("关键字/餐厅名");
//            tv_tuijian_tips.setText("推荐餐厅");
//        } else if (type == 2) {
//            tv_title.setText("酒店预定");
//            et_num.setHint("预定间数");
//            et_keyword.setHint("关键字/酒店名");
//            tv_tuijian_tips.setText("推荐酒店");
//        } else if (type == 3) {
//            tv_title.setText("门票预定");
//            et_num.setHint("预定张数");
//            et_keyword.setHint("关键字/景点名");
//            tv_tuijian_tips.setText("推荐景点");
//        }
//        tv_select.setOnClickListener(this);
//        tv_check.setOnClickListener(this);
//        tv_tuijian_more.setOnClickListener(this);
//
//        adapter.addHeaderView(view);
//    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        adapter.setEnableLoadMore(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(10));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DingcanRecommendListModel model = list.get(position);
                if (model != null) {
                    DingcanWebActivity.go(DingcanSearchActivity.this, type, model.getService_url(), model.getService_id(), model.getService_name());
                }
            }
        });

    }


    private void getBannerData() {
        int banner_type = type;
        //获取banner信息
        commonBannerPresenter.loadBannerData(banner_type, new CommonBannerPresenter.DataCallBack() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
            }

            @Override
            public void onSucess(int code, BaseResultInfo<List<CommonBannerModel>> respnse, String source) {
                if (respnse != null && respnse.getData() != null) {
                    List<String> pics = new ArrayList<>();
                    List<String> urls = new ArrayList<>();
                    for (CommonBannerModel commonBannerModel : respnse.getData()) {
                        pics.add(commonBannerModel.getPic_url());
                        urls.add(commonBannerModel.getHref_url());
                    }
                    ViewWrap.setUpFlyBanner(DingcanSearchActivity.this, pics, urls, flyBanner);
                }
            }
        });
    }

    private void getData() {
        addCall(new NetUtil().setUrl(APIS.URL_DINGCAN_RECOMMEND_LIST)
                .addParams("index_type", type + "")
                .setCallBackData(new BaseNewNetModelimpl<List<DingcanRecommendListModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<DingcanRecommendListModel>> respnse, String source) {
                        dismiss();
                        showContentView();
                        if (respnse.getData() != null) {
                            list.clear();
                            list.addAll(respnse.getData());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<DingcanRecommendListModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select:
                if (type == 1) {
                    new PickerViewWrap().showNianYueRiShiFenPicker(this, new DatePicker.OnYearMonthDayTimePickListener() {
                        @Override
                        public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                            tvSelect.setText(s + "年" + s1 + "月" + s2 + "日 " + s3 + ":" + s4);
                            yuDingTime = DateUtil.DateToStampTime(s + "-" + s1 + "-" + s2 + " " + s3 + ":" + s4, "yyyy-MM-dd HH:mm");

                        }
                    }, 0);
                } else {
                    new PickerViewWrap().showDateFromTodayPicker(this, new DatePicker.OnYearMonthDayTimePickListener() {
                        @Override
                        public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                            tvSelect.setText(s + "年" + s1 + "月" + s2 + "日");
                            yuDingTime = DateUtil.DateToStampTime(s + "-" + s1 + "-" + s2, "yyyy-MM-dd");
//                        workDate = s + "-" + s1 + "-" + s2;
                        }
                    }, 0);
                }
                break;
            case R.id.tv_check:
                submit();
                break;
            case R.id.tv_tuijian_more:

                break;
        }
    }

    private void submit() {
//        if (TextUtils.isEmpty(yuDingTime)) {
//            Toast.makeText(this, "请选择日期", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(etNum.getText().toString())) {
//            Toast.makeText(this, "请输入预定人数", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (TextUtils.isEmpty(etKeyword.getText().toString())) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(KEYWORD, etKeyword.getText().toString());
        intent.putExtra(PEOPLE_NUM, etNum.getText().toString());
        intent.putExtra(SELECT_TIME, yuDingTime);
        setResult(RESULT_CODE_SUCCESS, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DingcanWebActivity.REQUEST_CODE && resultCode == DingcanWebActivity.RESULT_CODE_SUCCESS) {

                MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
                if (activity != null) {
                    activity.changeButtonStatus(2);
                }
                setResult(WEB_RESULT_CODE_SUCCESS, intent);
                finish();

        }
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<DingcanRecommendListModel, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<DingcanRecommendListModel> data) {
            super(R.layout.item_recycler_dingcan_recommend_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final DingcanRecommendListModel item) {
//            ViewWrap.handlerImageView();
            GlideUtil.show(DingcanSearchActivity.this, item.getPic_url(), (CustomRoundAngleImageView) helper.getView(R.id.iv_pic));
            helper.setText(R.id.tv_price, "¥人均 " + item.getPrice() + "元")
                    .setText(R.id.tv_title, item.getService_name());
            FlowLayout fl_content = (FlowLayout) helper.getView(R.id.fl_content);
            fl_content.removeAllViews();
            List<String> attributeList = item.getAttributeList();
            if (attributeList != null) {
                for (int i = 0; i < attributeList.size(); i++) {
                    TextView view = (TextView) LayoutInflater.from(DingcanSearchActivity.this).inflate(R.layout.type_text_shape, fl_content, false);
                    view.setText(attributeList.get(i));
                    fl_content.addView(view);
                }
            }
        }
    }
}
