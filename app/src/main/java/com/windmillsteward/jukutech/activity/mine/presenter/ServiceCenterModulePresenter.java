package com.windmillsteward.jukutech.activity.mine.presenter;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：服务中心功能模块数据
 * author:cyq
 * 2018-03-12
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ServiceCenterModulePresenter {



    public ServiceCenterModulePresenter() {
    }

    public List<PublicSelectInfo> creatFunctionsPart() {

        PublicSelectInfo a = new PublicSelectInfo();
        a.setId(1);
        a.setName("人才驿站");
        a.setResource_id(R.mipmap.icon_personnel);

        PublicSelectInfo b = new PublicSelectInfo();
        b.setId(2);
        b.setName("智慧生活");
        b.setResource_id(R.mipmap.icon_wisdom);

        PublicSelectInfo c = new PublicSelectInfo();
        c.setId(3);
        c.setName("房屋信息");
        c.setResource_id(R.mipmap.icon_family);


        List<PublicSelectInfo> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);

        return list;
    }
}
