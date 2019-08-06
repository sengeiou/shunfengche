package com.windmillsteward.jukutech.activity.home.carservice.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.UsedCarHomeFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.bean.CarPriceListBean;
import com.windmillsteward.jukutech.bean.RecommendCarListBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.UsedCarHomeBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class UsedCarHomeFragmentPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int CAR_PRICE_DATA = 2;
    private static final int RECOMMEND_CAR_DATA= 3;
    private static final int TOP_BANNER = 4;
    private static final int IS_AUTHEN=5;

    private UsedCarHomeFragmentView view;
    private UsedCarHomeBean usedCarHomeBean;

    public UsedCarHomeFragmentPresenter(UsedCarHomeFragmentView view) {
        this.view = view;
        usedCarHomeBean = new UsedCarHomeBean();
    }

    public void initData(boolean isShow) {
        if (isShow) {
            view.showDialog("");
        }
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_CAR_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    private void loadBannerTopList(int banner_position) {
        DataLoader dataLoader = new DataLoader(this, TOP_BANNER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("banner_position", banner_position);
        httpInfo.setUrl(APIS.URL_BANNER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    private void loadCarPriceListData(){
        DataLoader dataLoader = new DataLoader(this, CAR_PRICE_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_CAR_PRICE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    private void loadRecommendCarListData(){
        DataLoader dataLoader = new DataLoader(this, RECOMMEND_CAR_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_RECOMMEND_CAR_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void getAuthenState(String access_token){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_AUTHEN);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_AUTHEN);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<CarClassListBean>>>() {
                }.getType();
                break;
            case TOP_BANNER:
                type = new TypeReference<BaseResultInfo<List<SliderPictureInfo>>>() {
                }.getType();
                break;
            case CAR_PRICE_DATA:
                type = new TypeReference<BaseResultInfo<List<CarPriceListBean>>>() {
                }.getType();
                break;
            case RECOMMEND_CAR_DATA:
                type = new TypeReference<BaseResultInfo<List<RecommendCarListBean>>>() {
                }.getType();
                break;
            case IS_AUTHEN:
                type = new TypeReference<BaseResultInfo<AuthenResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                List<CarClassListBean> carClassListBeans = (List<CarClassListBean>) result.getData();
                if (carClassListBeans!=null) {
                    usedCarHomeBean.setCarClassListBeans(carClassListBeans);
                } else {
                    usedCarHomeBean.setCarClassListBeans(new ArrayList<CarClassListBean>());
                }
                loadBannerTopList(4);
                break;
            case TOP_BANNER:
                List<SliderPictureInfo> topList = (List<SliderPictureInfo>) result.getData();
                if (topList!=null && topList.size()>0) {
                    usedCarHomeBean.setTopBanner(topList.get(0));
                } else {
                    usedCarHomeBean.setTopBanner(new SliderPictureInfo());
                }
                loadCarPriceListData();
                break;
            case CAR_PRICE_DATA:
                List<CarPriceListBean> carPriceListBeans = (List<CarPriceListBean>) result.getData();
                if (carPriceListBeans!=null) {
                    usedCarHomeBean.setCarPriceListBeans(carPriceListBeans);
                } else {
                    usedCarHomeBean.setCarPriceListBeans(new ArrayList<CarPriceListBean>());
                }
                loadRecommendCarListData();
                break;
            case RECOMMEND_CAR_DATA:
                view.dismiss();
                List<RecommendCarListBean> recommendCarListBeans = (List<RecommendCarListBean>) result.getData();
                if (recommendCarListBeans!=null) {
                    usedCarHomeBean.setRecommendRarListBeans(recommendCarListBeans);
                } else {
                    usedCarHomeBean.setRecommendRarListBeans(new ArrayList<RecommendCarListBean>());
                }
                view.initDataSuccess(usedCarHomeBean);
                break;
            case IS_AUTHEN:
                view.dismiss();
                AuthenResultBean data = (AuthenResultBean) result.getData();
                view.isAuthen(data);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,1);
    }
}
