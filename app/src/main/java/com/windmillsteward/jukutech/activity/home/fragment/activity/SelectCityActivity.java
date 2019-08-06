package com.windmillsteward.jukutech.activity.home.fragment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.SelectCityAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.SelectCityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.AssortView;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：城市选择
 * author:cyq
 * 2018-03-02
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SelectCityActivity extends BaseActivity implements View.OnClickListener, SelectCityView, View.OnKeyListener {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_SELECT = 1;
    private EditText et_search_key;
    private ExpandableListView elv_city;
    private AssortView assort_member;
    private LinearLayout ll_select_qu;
    private ImageView iv_back;
    private ImageView iv_closed_jiantou;
    private LinearLayout ll_area;

    private SelectCityPresenter selectCityPresenter;

    private SelectCityAdapter selectCityAdapter;

    private List<CityBean> cityList = new ArrayList<>();

    private int type;

    private TextView mTv_looking;
    private TextView mTv_select_quxian;
    private android.support.v7.widget.RecyclerView mRecycler_01;
    private LinearLayout mLl_recent;
    private TextView mTv_recent;
    private android.support.v7.widget.RecyclerView mRecycler_02;
    private CityBean cityBean;//选择到的城市实体

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        if (getIntent() != null)
            type = getIntent().getIntExtra("type", TYPE_NORMAL);
        else {
            type = TYPE_NORMAL;
        }
        initView();
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            setResult(200, data);
            finish();
        }
    }

    private void initView() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_search_key = (EditText) findViewById(R.id.et_search_key);
        elv_city = (ExpandableListView) findViewById(R.id.elv_city);
        assort_member = (AssortView) findViewById(R.id.assort_member);

        if (type == TYPE_NORMAL) {
            initHeaderView();
            ll_select_qu.setOnClickListener(this);
            mLl_recent.setOnClickListener(this);
        }

        iv_back.setOnClickListener(this);
        et_search_key.setOnKeyListener(this);


        elv_city.setOnGroupClickListener(onGroupClickListener);
        elv_city.setOnChildClickListener(onChildClickListener);
        assort_member.setOnTouchAssortListener(getAssortListener(this));// 中间字母弹窗
    }

    private void initData() {
        selectCityAdapter = new SelectCityAdapter(this, new ArrayList<CityBean>());

        elv_city.setAdapter(selectCityAdapter);

        selectCityPresenter = new SelectCityPresenter(this);
        selectCityPresenter.getCityList(et_search_key.getText().toString());

        if (getCurrCityId() != 0) {
            if (type == TYPE_NORMAL) {
                loadAreaData(getCurrCityId());
                selectCityPresenter.getHotCityList(new BaseNewNetModelimpl<List<CityBean>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<CityBean>> respnse, String source) {
                        dismiss();
                        if (respnse.getData() != null)
                            getHotCityListSuccess(respnse.getData());
                    }

                    @Override
                    protected Type getType() {
                        return new TypeReference<BaseResultInfo<List<CityBean>>>() {
                        }.getType();
                    }
                });
            }
        }
    }

    private void initHeaderView() {
        View view = View.inflate(this, R.layout.header_search_city, null);
        mTv_looking = (TextView) view.findViewById(R.id.tv_looking);
        mTv_select_quxian = (TextView) view.findViewById(R.id.tv_select_quxian);
        mRecycler_01 = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.recycler_01);
        mLl_recent = (LinearLayout) view.findViewById(R.id.ll_recent);
        mTv_recent = (TextView) view.findViewById(R.id.tv_recent);
        iv_closed_jiantou = (ImageView) view.findViewById(R.id.iv_closed_jiantou);
        ll_select_qu = view.findViewById(R.id.ll_select_qu);
        ll_area = (LinearLayout) view.findViewById(R.id.ll_area);
        mRecycler_02 = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.recycler_02);
        ll_area.setVisibility(View.VISIBLE);

        initHotAdapter();
        initAreaAdapter();
        elv_city.addHeaderView(view);

        String current_city = getIntent().getStringExtra(Define.INTENT_DATA);

        cityBean = new CityBean();
        cityBean.setArea_name(current_city);
        cityBean.setArea_id(Hawk.get(Define.CURR_CITY_ID,0));

        //最近访问
        String area = KV.get(Define.CURR_CITY_THIRD_NAME);
        currId = KV.get(Define.CURR_CITY_THIRD_ID, 0);
        if (TextUtils.isEmpty(area)) {
            //取城市
            area = KV.get(Define.CURR_CITY_NAME);
            currId = KV.get(Define.CURR_CITY_ID, 0);
            if (TextUtils.isEmpty(area)) {
                area = current_city;
                currId = -1;
            }
        }
        currName = area;
        mTv_recent.setText(TextUtils.isEmpty(current_city) ? "定位失败" : current_city +"/"+ currName);
        mTv_looking.setText("您正在看：" + (TextUtils.isEmpty(current_city) ? "定位失败" : current_city));

    }

    private String currName;
    private int currId;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_select_qu:
                if (mRecycler_02.getVisibility() == View.VISIBLE) {
                    mRecycler_02.setVisibility(View.GONE);
                    iv_closed_jiantou.setRotation(180);
                } else {
                    mRecycler_02.setVisibility(View.VISIBLE);
                    iv_closed_jiantou.setRotation(0);
                }
                break;
            case R.id.ll_recent:
                if (cityList == null) {
                    showTips("请重新获取定位", 1);
                    return;
                }

                for (int i = 0; i < cityList.size(); i++) {
                    if (cityList.get(i).getArea_name().contains(mTv_recent.getText().toString())) {
                        int area_id = cityList.get(i).getArea_id();
                        String area_name = cityList.get(i).getArea_name();
                        if (type == TYPE_NORMAL) {
                            Hawk.put(Define.CURR_CITY_ID, area_id);
                            Hawk.put(Define.CURR_CITY_NAME, area_name);
//                            Hawk.put(Define.CURR_CITY_THIRD_ID, 0);
//                            Hawk.put(Define.CURR_CITY_THIRD_NAME, "");
                            selectCityAdapter.notifyDataSetChanged();
                            elv_city.setSelection(0);
                            loadAreaData(area_id);
                            mTv_looking.setText("您正在看：" + (TextUtils.isEmpty(Hawk.get(Define.CURR_CITY_NAME, "")) ? "定位失败" : Hawk.get(Define.CURR_CITY_NAME, "")));
                        } else {
                            Intent intent = getIntent();
                            intent.putExtra(Define.INTENT_DATA, area_name);
                            intent.putExtra("area_id", area_id);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                        break;
                    }
                }
                break;
        }
    }

    private void loadAreaData(int city_id) {
        selectCityPresenter.loadAreaData(city_id, new BaseNewNetModelimpl<List<ThirdAreaBean>>() {
            @Override
            protected void onFail(int type, String msg) {
                dismiss();
                showTips(msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<List<ThirdAreaBean>> respnse, String source) {
                dismiss();
                if (respnse.getData() != null) {
                    getCityAreaListSuccess(respnse.getData());
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
            }
        });
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
            SystemUtil.dismissKeyBorwd(this);
            String key = et_search_key.getText().toString();
            selectCityPresenter.getCityList(key);
        }
        return false;
    }

    /**
     * 右边字母导航栏监听器
     */
    private AssortView.OnTouchAssortListener getAssortListener(final Context ctx) {
        AssortView.OnTouchAssortListener assortListener = new AssortView.OnTouchAssortListener() {
            View layoutView = LayoutInflater.from(ctx).inflate(
                    R.layout.layout_dialog_alert_menu, null);
            TextView text = (TextView) layoutView.findViewById(R.id.content);
            PopupWindow popupWindow;

            @Override
            public void onTouchAssortUP() {
                if (popupWindow != null)
                    popupWindow.dismiss();
                popupWindow = null;
            }

            @Override
            public void onTouchAssortListener(String str) {
                int index = selectCityAdapter.getAssort().getMemberHashList()
                        .indexOfKey(str);
                if (index != -1) {
                    elv_city.setSelectedGroup(index);
                }
                if (popupWindow != null) {
                    text.setText(str);
                } else {
                    popupWindow = new PopupWindow(layoutView, 200, 200, false);
                    // 显示在Activity的根视图中心
                    popupWindow.showAtLocation(getWindow().getDecorView(),
                            Gravity.CENTER, 0, 0);
                }
                text.setText(str);
            }
        };
        return assortListener;
    }

    // 二级列表中的父级点击跳转
    ExpandableListView.OnGroupClickListener onGroupClickListener = new ExpandableListView.OnGroupClickListener() { // 点击父列表不做处理
        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
            return true;
        }
    };

    // 二级列表中的子级点击跳转,获取城市id，并且保存
    ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            cityBean = (CityBean) selectCityAdapter.getChild(groupPosition, childPosition);
            int area_id = cityBean.getArea_id();
            String area_name = cityBean.getArea_name();
            if (type == TYPE_NORMAL) {
//                Hawk.put(Define.CURR_CITY_ID, area_id);
//                Hawk.put(Define.CURR_CITY_NAME, area_name);
                selectCityAdapter.notifyDataSetChanged();
                elv_city.setSelection(0);
                mTv_looking.setText("您正在看：" + (TextUtils.isEmpty(cityBean.getArea_name()) ? "定位失败" : cityBean.getArea_name()));
                loadAreaData(area_id);
            } else {
                Intent intent = getIntent();
                intent.putExtra(Define.INTENT_DATA, area_name);
                intent.putExtra("area_id", area_id);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
    };

    @Override
    public void getCityListSuccess(List<CityBean> list) {
        if (list == null) {
            return;
        }
        this.cityList = list;
        selectCityAdapter.setList(list);
        // 展开所有
        for (int i = 0, length = selectCityAdapter.getGroupCount(); i < length; i++) {
            elv_city.expandGroup(i);
        }
    }

    public void getHotCityListSuccess(List<CityBean> list) {
        listHot.clear();
        listHot.addAll(list);
        adapterHot.notifyDataSetChanged();

    }


    public void getCityAreaListSuccess(List<ThirdAreaBean> list) {
        listArea.clear();
        listArea.addAll(list);
        adapterArea.notifyDataSetChanged();


    }

    @Override
    public void getCityListFailed(int code, String msg) {
        showTips(msg, 1);
    }


    private HotRecyclerViewAdapter adapterHot;
    private List<CityBean> listHot;

    public void initHotAdapter() {
        listHot = new ArrayList<>();
        adapterHot = new HotRecyclerViewAdapter(listHot);
        mRecycler_01.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycler_01.setAdapter(adapterHot);
        mRecycler_01.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        mRecycler_01.setHasFixedSize(true);

        //事件监听
        adapterHot.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                cityBean = listHot.get(position);

                if (type == TYPE_NORMAL) {
//                    Hawk.put(Define.CURR_CITY_ID, cityBean.getArea_id());
//                    Hawk.put(Define.CURR_CITY_NAME, cityBean.getArea_name());
//                    Hawk.put(Define.CURR_CITY_THIRD_ID, 0);
//                    Hawk.put(Define.CURR_CITY_THIRD_NAME, "");
                    selectCityAdapter.notifyDataSetChanged();
                    elv_city.setSelection(0);
                    mTv_looking.setText("您正在看：" + (TextUtils.isEmpty(cityBean.getArea_name()) ? "定位失败" : cityBean.getArea_name()));
                    loadAreaData(cityBean.getArea_id());
                } else {
                    Intent intent = getIntent();
                    intent.putExtra(Define.INTENT_DATA, cityBean.getArea_name());
                    intent.putExtra("area_id", cityBean.getArea_id());
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    class HotRecyclerViewAdapter extends BaseQuickAdapter<CityBean, BaseViewHolder> {

        public HotRecyclerViewAdapter(@Nullable List<CityBean> data) {
            super(R.layout.item_recycler_hot_city, data, false);
        }

        @Override
        protected void convert(BaseViewHolder helper, CityBean item) {
            helper.setText(R.id.tv_name, item.getArea_name());
        }
    }

    private RecyclerViewAdapter adapterArea;
    private List<ThirdAreaBean> listArea;

    public void initAreaAdapter() {
        listArea = new ArrayList<>();
        adapterArea = new RecyclerViewAdapter(listArea);
        mRecycler_02.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycler_02.setAdapter(adapterArea);
        mRecycler_02.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        mRecycler_02.setHasFixedSize(true);

        //事件监听
        adapterArea.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ThirdAreaBean thirdAreaBean = listArea.get(position);
                Intent intent = getIntent();
                intent.putExtra(Define.INTENT_DATA, thirdAreaBean.getThird_area_name());
                intent.putExtra("third_area_id", thirdAreaBean.getThird_area_id());
                setResult(RESULT_OK, intent);
                if (type == TYPE_NORMAL) {
                    //保存所选的城市id
                    Hawk.put(Define.CURR_CITY_THIRD_ID, thirdAreaBean.getThird_area_id());
                    Hawk.put(Define.CURR_CITY_THIRD_NAME, thirdAreaBean.getThird_area_name());
                    Hawk.put(Define.CURR_CITY_ID, cityBean.getArea_id());
                    Hawk.put(Define.CURR_CITY_NAME, cityBean.getArea_name());
                }
                finish();
            }
        });
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<ThirdAreaBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<ThirdAreaBean> data) {
            super(R.layout.item_recycler_area_city, data, false);
        }

        @Override
        protected void convert(BaseViewHolder helper, ThirdAreaBean item) {
            helper.setText(R.id.tv_name, item.getThird_area_name());
        }
    }
}
