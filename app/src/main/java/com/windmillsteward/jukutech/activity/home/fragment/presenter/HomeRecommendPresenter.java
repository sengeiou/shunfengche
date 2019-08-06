package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.CapitalManagerActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.FinancingDetailActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.LoanDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarServiceActivity;
import com.windmillsteward.jukutech.activity.home.fragment.HomeRecommendView;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceDetailActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceListActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseHomeActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.StayAndTravelHomeActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailActivity;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.RecommendGridBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：首页推荐
 * author:cyq
 * 2018-06-07
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeRecommendPresenter extends BaseNetModelImpl {

    private final int HOME_RECOMMEND = 1;
    private final int GUESS_YOU_LIKE = 2;
    private HomeRecommendView view;
    private Context context;

    public HomeRecommendPresenter(Context context, HomeRecommendView view) {
        this.view = view;
        this.context = context;
    }

    public void getHomeRecommendList() {
        DataLoader dataLoader = new DataLoader(this, HOME_RECOMMEND);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_HOME_RECOMMEND_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void getGuessYouLikeList() {
        DataLoader dataLoader = new DataLoader(this, GUESS_YOU_LIKE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_GUESS_YOU_LIKE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 推荐详情点击
     *
     * @param data
     */
    public void recommendClick(RecommendGridBean.ListBean data) {
        //详情跳转类型：【1：房屋租售模块-卖房，2：房屋租售模块-买房，3：房屋租售模块-出租，4：房屋租售模块-求租，
        // 5：旅游，6：酒店，7：车辆买卖-卖车，8：大健康，9：名优特产，10：资本管理-理财，11：资本管理-贷款】
        int type = data.getType();
        int relate_id = data.getRelate_id();
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        switch (type) {
            case 1:
                bundle.putInt(HouseDetailActivity.DETAIL_ID, relate_id);
                bundle.putInt(HouseDetailActivity.CLASS_TYPE, 1);
                intent.putExtras(bundle);
                intent.setClass(context, HouseDetailActivity.class);
                context.startActivity(intent);
                break;
            case 2:
                bundle.putInt(HouseDetailActivity.DETAIL_ID, relate_id);
                bundle.putInt(HouseDetailActivity.CLASS_TYPE, 2);
                intent.putExtras(bundle);
                intent.setClass(context, HouseDetailActivity.class);
                context.startActivity(intent);
                break;
            case 3:
                bundle.putInt(HouseDetailActivity.DETAIL_ID, relate_id);
                bundle.putInt(HouseDetailActivity.CLASS_TYPE, 3);
                intent.putExtras(bundle);
                intent.setClass(context, HouseDetailActivity.class);
                context.startActivity(intent);
                break;
            case 4:
                bundle.putInt(HouseDetailActivity.DETAIL_ID, relate_id);
                bundle.putInt(HouseDetailActivity.CLASS_TYPE, 4);
                intent.putExtras(bundle);
                intent.setClass(context, HouseDetailActivity.class);
                context.startActivity(intent);
                break;
            case 5:
                bundle.putInt(TravelDetailActivity.DETAIL_ID, relate_id);
                intent.putExtras(bundle);
                intent.setClass(context, TravelDetailActivity.class);
                context.startActivity(intent);
                break;
            case 6:
                Calendar c = Calendar.getInstance();
                int currYear = c.get(Calendar.YEAR);
                int currMonth = c.get(Calendar.MONTH) + 1;
                int currDay = c.get(Calendar.DAY_OF_MONTH);
                c.add(Calendar.DATE, 1);
                int nextYear = c.get(Calendar.YEAR);
                int nextMonth = c.get(Calendar.MONTH) + 1;
                int nextDay = c.get(Calendar.DAY_OF_MONTH);

                String startTime = DateUtil.DateToStampTime(String.valueOf(currYear) + "-" + String.valueOf(currMonth) + "-" + String.valueOf(currDay), "yyyy-MM-dd");
                String endTime = DateUtil.DateToStampTime(String.valueOf(nextYear) + "-" + String.valueOf(nextMonth) + "-" + String.valueOf(nextDay), "yyyy-MM-dd");

                bundle.putInt(HotelAndHouseDetailActivity.HOTEL_ID, relate_id);
                bundle.putString(HotelAndHouseListActivity.START_TIME, startTime);
                bundle.putString(HotelAndHouseListActivity.END_TIME, endTime);
                bundle.putInt(HotelAndHouseListActivity.DAY_NUM, 1);
                intent.putExtras(bundle);
                intent.setClass(context, HotelAndHouseDetailActivity.class);
                context.startActivity(intent);
                break;
            case 7:
                bundle.putInt(Define.INTENT_DATA, relate_id);
                intent.putExtras(bundle);
                intent.setClass(context, CarDetailActivity.class);
                context.startActivity(intent);
                break;
            case 8:
                bundle.putInt(Define.INTENT_DATA, relate_id);
                intent.putExtras(bundle);
                intent.setClass(context, InsuranceDetailActivity.class);
                context.startActivity(intent);
                break;
            case 9:
                bundle.putInt(Define.INTENT_DATA, relate_id);
                intent.putExtras(bundle);
                intent.setClass(context, SpecialtyDetailActivity.class);
                context.startActivity(intent);
                break;
            case 10:
                bundle.putInt(Define.INTENT_DATA, relate_id);
                intent.putExtras(bundle);
                intent.setClass(context, FinancingDetailActivity.class);
                context.startActivity(intent);
                break;
            case 11:
                bundle.putInt(Define.INTENT_DATA, relate_id);
                intent.putExtras(bundle);
                intent.setClass(context, LoanDetailActivity.class);
                context.startActivity(intent);
                break;
        }
    }

    /**
     * 推荐更多点击--跳到列表
     *
     * @param data
     */
    public void recommendMoreClick(RecommendGridBean data) {
        //模块类型：【1：房屋租售，2：旅游，3：酒店，4：车辆买卖-卖车，5：大健康，6：名优特产，7：资本管理】
        int type = data.getModule_type();
        Intent intent = new Intent();
        switch (type) {
            case 1:
                intent.setClass(context, HouseRentingActivity.class);
                context.startActivity(intent);
                break;
            case 2:
                intent.setClass(context, StayAndTravelHomeActivity.class);
                context.startActivity(intent);
                break;
            case 3:
                intent.setClass(context, HotelAndHouseHomeActivity.class);
                context.startActivity(intent);
                break;
            case 4:
                intent.setClass(context, CarServiceActivity.class);
                context.startActivity(intent);
                break;
            case 5:
                intent.setClass(context, InsuranceListActivity.class);
                context.startActivity(intent);
                break;
            case 6:
                intent.setClass(context, SpecialtyListActivity.class);
                context.startActivity(intent);
                break;
            case 7:
                intent.setClass(context, CapitalManagerActivity.class);
                context.startActivity(intent);
                break;
        }

    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case HOME_RECOMMEND:
                type = new TypeReference<BaseResultInfo<List<RecommendGridBean>>>() {
                }.getType();
                break;
            case GUESS_YOU_LIKE:
                type = new TypeReference<BaseResultInfo<GuessYouLikeBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case HOME_RECOMMEND:
                List<RecommendGridBean> bean = (List<RecommendGridBean>) result.getData();
                view.getHomeRecommendDataSuccess(bean);
                break;
            case GUESS_YOU_LIKE:
                GuessYouLikeBean data = (GuessYouLikeBean) result.getData();
                view.getGuessYouLikeListSuccess(data);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case HOME_RECOMMEND:
                view.getHomeRecommendDataFailed(code, msg);
                break;
            case GUESS_YOU_LIKE:
                view.getGuessYouLikeListFailed(code, msg);
                break;
        }
    }
}
