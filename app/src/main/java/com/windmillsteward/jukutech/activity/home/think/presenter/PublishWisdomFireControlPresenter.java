package com.windmillsteward.jukutech.activity.home.think.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.think.activity.PublishWisdomFireControlView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.TestItemsListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：
 * 时间：2018/2/5/005
 * 作者：xjh
 */
public class PublishWisdomFireControlPresenter extends BaseNetModelImpl {


    private static final int AREA_LIST = 1;
    private static final int PUBLISH = 2;
    private static final int EDIT = 3;
    private static final int IS_CHARGE = 4;
    private static final int TEST_ITEMS_LIST = 5;

    private PublishWisdomFireControlView view;

    public PublishWisdomFireControlPresenter(PublishWisdomFireControlView view) {
        this.view = view;
    }

    public void loadPublishAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 智慧消防发布
     * @param access_token
     * @param second_area_id 市id
     * @param third_area_id 区id
     * @param consumption_type 智慧消防类型id
     * @param title 标题
     * @param floor_area 面积
     * @param address 地址
     * @param check_items 检测项目
     * @param description 需求说明
     * @param contact_tel 联系电话
     * @param contact_person 联系人
     * @param floor_num 楼层(选填)
     */
    public void publish(String access_token, String second_area_id, String third_area_id, String consumption_type
            , String title, String floor_area, String address, Set<Integer> check_items , String description
            , String contact_tel, String contact_person, String floor_num){

        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("consumption_type", consumption_type);
        map.put("title", title);
        map.put("floor_area", floor_area);
        map.put("address", address);
        map.put("check_items", JSON.toJSONString(check_items));
        map.put("description", description);
        map.put("contact_tel", contact_tel);
        map.put("contact_person", contact_person);
        map.put("floor_num", floor_num);
        httpInfo.setUrl(APIS.URL_ADD_SMART_FIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void edit(int require_id,String access_token,int first_class_id,int second_class_id,String title,String price,String description,String contact_person,
                        String contact_tel,int second_area_id,int third_area_id){

        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        map.put("first_class_id", first_class_id);
        map.put("second_class_id", second_class_id);
        map.put("title", title);
        map.put("price", price);
        map.put("description", description);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_EDIT_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断是否收费
     */
    public void isCharge(String access_token,int relate_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 120);
        map.put("relate_id", relate_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 检测项目列表
     */
    public void loadTestItemsList(){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, TEST_ITEMS_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_TEST_ITEMS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case PUBLISH:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
            case EDIT:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case TEST_ITEMS_LIST:
                type = new TypeReference<BaseResultInfo<List<TestItemsListBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : areaBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadPublishAreaDataSuccess(maps);
                }
                break;
            case PUBLISH:
                view.dismiss();
                view.publishSuccess(null);
                view.showTips("发布成功",0);
//                PublishIdeaThinkResultBean resultData = (PublishIdeaThinkResultBean) result.getData();
//                if (resultData!=null) {
//                    view.publishSuccess(resultData);
//                    view.showTips("发布成功",0);
//                } else {
//                    view.dismiss();
//                }
                break;
            case EDIT:
                view.dismiss();
                view.publishSuccess(null);
                view.showTips("修改成功",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.isCharge(((ChargeResultBean) result.getData()));
                break;
            case TEST_ITEMS_LIST:
                view.dismiss();
                List<TestItemsListBean> testitemslistbean = (List<TestItemsListBean>) result.getData();
                view.loadTestItemsListSuccess(testitemslistbean);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case AREA_LIST:
                view.dismiss();
                break;
            case PUBLISH:
                view.dismiss();
                view.showTips("发布失败",0);
                break;
            case EDIT:
                view.dismiss();
                view.showTips("修改失败",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case TEST_ITEMS_LIST:
                view.dismiss();
                break;
        }
    }
}
