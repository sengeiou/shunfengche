package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.windmillsteward.jukutech.activity.home.fragment.activity.HomeSearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：首页搜索
 * author:cyq
 * 2018-03-30
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeSearchPresenter {

    private HomeSearchView view;
    //模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅行】
    //模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅行，6：车辆买卖模块，7：大健康，8：名优特产，9：资本管理】
    private String[] type_module = new String[]{"全部","智慧生活","房屋信息"};

    public HomeSearchPresenter(HomeSearchView view) {
        this.view = view;
    }

    public void loadSelectTypeDta(){
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < type_module.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i);
            map.put("text",type_module[i]);
            maps.add(map);
        }
        view.getSelectTypeDataSuccess(maps);
    }
}
