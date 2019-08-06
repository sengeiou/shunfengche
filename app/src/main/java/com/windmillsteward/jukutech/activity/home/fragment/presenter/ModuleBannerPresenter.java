package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.FinancingDetailActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.LoanDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.BuyCarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarDetailActivity;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyDetailActivity;
import com.windmillsteward.jukutech.activity.home.fragment.ModuleBannerView;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceDetailActivity;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.LegalExpertActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.ResumeDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailActivity;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkDetailActivity;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
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
 * 描述：模块的轮播图
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ModuleBannerPresenter extends BaseNetModelImpl {

    private final int BANNER = 1;
    private ModuleBannerView view;
    private Context context;

    public ModuleBannerPresenter(Context context, ModuleBannerView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 请求轮播图数据
     * @param banner_position  1APP首页顶部，2APP首页管家推荐，3APP旅游模块，4APP汽车模块，5APP法律模块， 6，PC名优特产首页，
     *                         7PC首页管家推荐，8PC旅游模块，9PC汽车模块，10PC法律模块，11PC首页顶部，12App特产首页，
     *                         13.App人才驿站模块，14.App思想智库，15.App智慧家庭，16.App房屋租售，17.App大健康，18.资本管理
     *                         20.劳务中介 21.智慧消防
     */
    public void getBannerList(int banner_position) {
        DataLoader dataLoader = new DataLoader(this, BANNER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("banner_position", banner_position);
        httpInfo.setUrl(APIS.URL_BANNER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }



    public void bannerClick(SliderPictureInfo info) {
        if (info == null) {
            return;
        }
        int jump_id = info.getJump_id();//跳转id
        String jump_type = info.getJump_type();//1.跳转的url地址，2.跳转内部

        //跳转内部的某个模块(1.商品页面,2.旅游，3.人才驿站-简历，4.人才驿站-职位，5.思想智库，6.智慧生活，7.房屋出售-卖房，
        // 8.车辆买卖-买车，9.车辆买卖-卖车，11.大健康，12.法律专家，13.资本管理-理财,
        // 14.酒店，15.房屋出售-买房，16.房屋出售-出租，17.房屋出售-求租，18.资本管理-贷款)
        String jump_code = info.getJump_code();

        String href_url = info.getHref_url();//外部url地址

        if (TextUtils.equals(jump_type, "1")) {//外部
            Bundle bundle = new Bundle();
            Intent intent = new Intent();
            bundle.putString(Define.INTENT_DATA,href_url);
            intent.putExtras(bundle);
            intent.setClass(context,CommonWebActivity.class);
            context.startActivity(intent, bundle);
        } else if (TextUtils.equals(jump_type, "2")) {//内部
            Bundle bundle = new Bundle();
            Intent intent = new Intent();
            switch (jump_code) {
                case "1"://商品详情
                    bundle.putInt(Define.INTENT_DATA,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,SpecialtyDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "2"://旅游详情
                    bundle.putInt(TravelDetailActivity.DETAIL_ID,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,TravelDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "3"://简历详情
                    bundle.putInt(ResumeDetailActivity.RESUME_ID,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,ResumeDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "4"://职位详情
                    bundle.putInt(PositionDetailActivity.DETAIL_ID,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,PositionDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "5"://思想智库详情
                    bundle.putInt(IdeaThinkDetailActivity.REQUIRE_ID,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,IdeaThinkDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "6"://智慧生活详情
                    bundle.putInt(IntelligentFamilyDetailActivity.DETAIL_ID,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,IntelligentFamilyDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "7"://卖房详情  require_type类型：1.卖房，2买房，3.出租，4.求租'HouseDetailActivity
                    bundle.putInt(HouseDetailActivity.DETAIL_ID,jump_id);
                    bundle.putInt(HouseDetailActivity.CLASS_TYPE,1);
                    intent.putExtras(bundle);
                    intent.setClass(context,HouseDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "8"://买车详情 BuyCarDetailActivity
                    bundle.putInt(BuyCarDetailActivity.DETAIL_ID,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,BuyCarDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "9"://卖车详情 CarDetailActivity Define.INTENT_DATA
                    bundle.putInt(Define.INTENT_DATA,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,CarDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "10"://租车详情暂时不跳转

                    break;
                case "11"://大健康详情
                    bundle.putInt(Define.INTENT_DATA,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,InsuranceDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "12"://法律专家 LegalExpertActivity
                    intent.setClass(context,LegalExpertActivity.class);
                    context.startActivity(intent);
                    break;
                case "13"://理财
                    bundle.putInt(Define.INTENT_DATA,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,FinancingDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "14"://酒店
                     Calendar c = Calendar.getInstance();
                    int currYear = c.get(Calendar.YEAR);
                    int currMonth = c.get(Calendar.MONTH) + 1;
                    int currDay = c.get(Calendar.DAY_OF_MONTH);
                    c.add(Calendar.DATE,1);
                    int nextYear = c.get(Calendar.YEAR);
                    int nextMonth = c.get(Calendar.MONTH) + 1;
                    int nextDay = c.get(Calendar.DAY_OF_MONTH);

                    String startTime = DateUtil.DateToStampTime(String.valueOf(currYear) + "-" + String.valueOf(currMonth) + "-" + String.valueOf(currDay), "yyyy-MM-dd");
                    String endTime = DateUtil.DateToStampTime(String.valueOf(nextYear) + "-" + String.valueOf(nextMonth) + "-" + String.valueOf(nextDay), "yyyy-MM-dd");

                    bundle.putInt(HotelAndHouseDetailActivity.HOTEL_ID,jump_id);
                    bundle.putString(HotelAndHouseListActivity.START_TIME,startTime);
                    bundle.putString(HotelAndHouseListActivity.END_TIME,endTime);
                    bundle.putInt(HotelAndHouseListActivity.DAY_NUM,1);
                    intent.putExtras(bundle);
                    intent.setClass(context,HotelAndHouseDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "15"://买房
                    bundle.putInt(HouseDetailActivity.DETAIL_ID,jump_id);
                    bundle.putInt(HouseDetailActivity.CLASS_TYPE,2);
                    intent.putExtras(bundle);
                    intent.setClass(context,HouseDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "16"://出租
                    bundle.putInt(HouseDetailActivity.DETAIL_ID,jump_id);
                    bundle.putInt(HouseDetailActivity.CLASS_TYPE,3);
                    intent.putExtras(bundle);
                    intent.setClass(context,HouseDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "17"://求租
                    bundle.putInt(HouseDetailActivity.DETAIL_ID,jump_id);
                    bundle.putInt(HouseDetailActivity.CLASS_TYPE,4);
                    intent.putExtras(bundle);
                    intent.setClass(context,HouseDetailActivity.class);
                    context.startActivity(intent);
                    break;
                case "18"://贷款
                    bundle.putInt(Define.INTENT_DATA,jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context,LoanDetailActivity.class);
                    context.startActivity(intent);
                    break;


            }
        }
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case BANNER:
                type = new TypeReference<BaseResultInfo<List<SliderPictureInfo>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case BANNER:
                List<SliderPictureInfo> topList = (List<SliderPictureInfo>) result.getData();
                view.getBannerListSuccess(topList);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case BANNER:
                view.getBannerListFailed(code, msg);
                break;
        }
    }
}
