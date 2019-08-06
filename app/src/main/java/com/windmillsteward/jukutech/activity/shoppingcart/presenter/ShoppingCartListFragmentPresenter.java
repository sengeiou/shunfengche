package com.windmillsteward.jukutech.activity.shoppingcart.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.specialty.fragment.GoodsListFragmentView;
import com.windmillsteward.jukutech.activity.shoppingcart.fragment.ShoppingCartListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.EditShoppingCartListBean;
import com.windmillsteward.jukutech.bean.GoodsListBean;
import com.windmillsteward.jukutech.bean.ShoppingCarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class ShoppingCartListFragmentPresenter extends BaseNetModelImpl {
    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private final int EDIT_SHOPPING=4;
    private final int DELETE_SHOPPING=5;
    private final int DELETE_SHOPPING_NUM=6;

    private ShoppingCartListFragmentView view;

    public ShoppingCartListFragmentPresenter(ShoppingCartListFragmentView view) {
        this.view = view;
    }


    /**
     * 初始化数据
     * @param page 页数
     * @param page_count 数量
     */
    public void initData(int page,int page_count,String access_token){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_SHOPPING_CART_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(int page,int page_count,String access_token){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_SHOPPING_CART_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(int page,int page_count,String access_token){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_SHOPPING_CART_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 编辑购物车
     * @param access_token
     * @param cart_id
     * @param list
     */
    public void editShopping(String access_token,int cart_id,List<EditShoppingCartListBean> list){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, EDIT_SHOPPING);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("cart_id", cart_id);
        map.put("commodity_list", JSON.toJSONString(list));
        httpInfo.setUrl(APIS.URL_EDIT_SHOPPING_CART);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 编辑购物车
     * @param access_token
     * @param cart_id
     * @param list
     */
    public void editShoppingNum(String access_token,int cart_id,List<EditShoppingCartListBean> list){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DELETE_SHOPPING_NUM);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("cart_id", cart_id);
        map.put("commodity_list", JSON.toJSONString(list));
        httpInfo.setUrl(APIS.URL_EDIT_SHOPPING_CART);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 删除
     * @param access_token
     * @param cart_id
     * @param list
     */
    public void deleteShopping(String access_token,int cart_id,List<EditShoppingCartListBean> list){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DELETE_SHOPPING);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("cart_id", cart_id);
        map.put("commodity_list", JSON.toJSONString(list));
        httpInfo.setUrl(APIS.URL_EDIT_SHOPPING_CART);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void addBeforeAddOrder(String access_token,int cart_id,List<EditShoppingCartListBean> list,int address_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DELETE_SHOPPING);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("cart_id", cart_id);
        map.put("commodity_list", JSON.toJSONString(list));
        httpInfo.setUrl(APIS.URL_BEFORE_ADD_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<ShoppingCarListBean>>() {
                }.getType();
                break;
            case DELETE_SHOPPING_NUM:
            case DELETE_SHOPPING:
            case EDIT_SHOPPING:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {

        view.dismiss();
        switch (action) {
            case INIT_DATA:
                ShoppingCarListBean initData = (ShoppingCarListBean) result.getData();
                if (initData!=null) {
                    view.initDataSuccess(initData);
                }
                break;
            case REFRESH_DATA:
                ShoppingCarListBean refreshData = (ShoppingCarListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                ShoppingCarListBean nextData = (ShoppingCarListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextFailure();
                }
                break;
            case EDIT_SHOPPING:
                view.editShoppingCartSuccess();
                break;
            case DELETE_SHOPPING:
                view.deleteShoppingCartSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
        switch (action) {
            case INIT_DATA:
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextFailure();
                break;
        }
    }
}
