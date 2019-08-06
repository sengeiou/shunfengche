package com.windmillsteward.jukutech.activity.home.specialty.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyHomeRecommendListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.HomeBean;
import com.windmillsteward.jukutech.bean.SeckillBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class SpecialtyHomeDataPresenter extends BaseNetModelImpl {

    private static final int RECOMMEND_INIT_DATA = 1;
    private static final int BANNER = 2;
    private static final int GUESS_YOU_LIKE = 3;
    private static final int SPECIALTY_LIST = 4;
    private static final int SECKILL_LIST = 5;

    private SpecialtyHomeRecommendListView view;
    private Context context;

    public SpecialtyHomeDataPresenter(Context context, SpecialtyHomeRecommendListView view) {
        this.view = view;
        this.context = context;
    }


    /**
     * 初始化数据
     */
    public void initData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, RECOMMEND_INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_SPECIALTY_HOME_RECOMMEND_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 轮播图
     *
     * @param banner_position
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


    /**
     * recyclerview分层数据
     *
     * @return
     */
    public List<HomeBean> SpecialtyHomeRecyclerViewData() {
        List<HomeBean> list = new ArrayList<>();

        HomeBean beanner = new HomeBean();
        beanner.setViewType(Define.BANNER);

        HomeBean classViewPage = new HomeBean();
        classViewPage.setViewType(Define.CLASS_VIEW_PAGE);

        HomeBean seckill = new HomeBean();
        seckill.setViewType(Define.SECKILL);

        HomeBean recommend_grid = new HomeBean();
        recommend_grid.setViewType(Define.RECOMMEND_GRID);

        HomeBean guessYouLike = new HomeBean();
        guessYouLike.setViewType(Define.GUESS_YOU_LIKE);

        HomeBean bottom = new HomeBean();
        bottom.setViewType(Define.BOTTOM);

        list.add(beanner);
        list.add(classViewPage);
        list.add(seckill);
        list.add(recommend_grid);
        list.add(guessYouLike);
        list.add(bottom);

        return list;
    }

    /**
     * 轮播图点击事件
     *
     * @param info
     */
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
            bundle.putString(Define.INTENT_DATA, href_url);
            intent.putExtras(bundle);
            intent.setClass(context, CommonWebActivity.class);
            context.startActivity(intent, bundle);
        } else if (TextUtils.equals(jump_type, "2")) {//内部
            Bundle bundle = new Bundle();
            Intent intent = new Intent();
            switch (jump_code) {
                case "1"://商品详情
                    bundle.putInt(Define.INTENT_DATA, jump_id);
                    intent.putExtras(bundle);
                    intent.setClass(context, SpecialtyDetailActivity.class);
                    context.startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 猜你喜欢
     */
    public void getGuessYouLikeList() {
        DataLoader dataLoader = new DataLoader(this, GUESS_YOU_LIKE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_GUESS_YOU_LIKE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 商品分类
     */
    public void loadSpcialtyClassData() {
        DataLoader dataLoader = new DataLoader(this, SPECIALTY_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("parent_id", 0);
        httpInfo.setUrl(APIS.URL_CATEGORY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载秒杀数据
     */
    public void loadSpecialtySeckillData() {
        DataLoader dataLoader = new DataLoader(this, SECKILL_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_SPECIALTY_HOME_SECKILL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case RECOMMEND_INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<SpecialtyHomeRecommendBean>>>() {
                }.getType();
                break;
            case BANNER:
                type = new TypeReference<BaseResultInfo<List<SliderPictureInfo>>>() {
                }.getType();
                break;
            case GUESS_YOU_LIKE:
                type = new TypeReference<BaseResultInfo<GuessYouLikeBean>>() {
                }.getType();
                break;
            case SPECIALTY_LIST:
                type = new TypeReference<BaseResultInfo<List<SpecialtyClassBean>>>() {
                }.getType();
                break;
            case SECKILL_LIST:
                type = new TypeReference<BaseResultInfo<SeckillBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case RECOMMEND_INIT_DATA:
                List<SpecialtyHomeRecommendBean> data = (List<SpecialtyHomeRecommendBean>) result.getData();
                if (data != null) {
                    view.initDataSuccess(data);
                }
                break;
            case BANNER:
                List<SliderPictureInfo> list = (List<SliderPictureInfo>) result.getData();
                if (list != null) {
                    view.getBannerListSuccess(list);
                }
                break;
            case GUESS_YOU_LIKE:
                GuessYouLikeBean bean = (GuessYouLikeBean) result.getData();
                if (bean != null) {
                    view.getGuessYouLikeListSuccess(bean);
                }
                break;
            case SPECIALTY_LIST:
                List<SpecialtyClassBean> beanList = (List<SpecialtyClassBean>) result.getData();
                if (beanList != null) {
                    view.initSpecialtyClassSuccess(beanList);
                }
                break;
            case SECKILL_LIST:
                SeckillBean seckillBean = (SeckillBean) result.getData();
                if (seckillBean != null) {
                    view.getSeckillDataSuccess(seckillBean);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case RECOMMEND_INIT_DATA:
                view.initDataFailed(code, msg);
                break;
            case BANNER:
                view.getBannerListFailed(code, msg);
                break;
            case GUESS_YOU_LIKE:
                view.getGuessYouLikeListFailed(code, msg);
                break;
            case SPECIALTY_LIST:
                view.initSpecialtyClassFailed(code, msg);
                break;
            case SECKILL_LIST:
                view.getSeckillDataFaild(code, msg);
                break;
        }
    }
}
