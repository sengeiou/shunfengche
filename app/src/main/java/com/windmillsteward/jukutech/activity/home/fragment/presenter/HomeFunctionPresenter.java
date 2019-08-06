package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import android.content.Context;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.HomeBean;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：功能模块数据
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeFunctionPresenter {

    private Context context;

    public HomeFunctionPresenter(Context context) {
        this.context = context;
    }

    public List<PublicSelectInfo> creatFunctionsPart() {

        PublicSelectInfo a = new PublicSelectInfo();
        a.setId(1);
        a.setName("人才驿站");
        a.setResource_id(R.mipmap.icon_personnel);

        PublicSelectInfo b = new PublicSelectInfo();
        b.setId(2);
        b.setName("思想智库");
        b.setResource_id(R.mipmap.icon_wisdom);

        PublicSelectInfo c = new PublicSelectInfo();
        c.setId(3);
        c.setName("智慧生活");
        c.setResource_id(R.mipmap.icon_family);

        PublicSelectInfo d = new PublicSelectInfo();
        d.setId(4);
        d.setName("房屋租售");
        d.setResource_id(R.mipmap.icon_lease);

        PublicSelectInfo e = new PublicSelectInfo();
        e.setId(5);
        e.setName("住宿旅行");
        e.setResource_id(R.mipmap.icon_tour);

        PublicSelectInfo f = new PublicSelectInfo();
        f.setId(6);
        f.setName("汽车服务");
        f.setResource_id(R.mipmap.icon_carsale);

        PublicSelectInfo g = new PublicSelectInfo();
        g.setId(7);
        g.setName("大健康");
        g.setResource_id(R.mipmap.icon_secure);

        PublicSelectInfo h = new PublicSelectInfo();
        h.setId(8);
        h.setName("名优特产");
        h.setResource_id(R.mipmap.icon_gift);

        PublicSelectInfo i = new PublicSelectInfo();
        i.setId(9);
        i.setName("资本管理");
        i.setResource_id(R.mipmap.icon_capital);

        PublicSelectInfo j = new PublicSelectInfo();
        j.setId(10);
        j.setName("法律专家");
        j.setResource_id(R.mipmap.icon_law);

        List<PublicSelectInfo> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        list.add(h);
        list.add(i);
        list.add(j);
        return list;
    }

    public List<HomeBean> homeRecyclerViewData(){
        List<HomeBean> list = new ArrayList<>();

        HomeBean a = new HomeBean();
        a.setViewType("top_banner");

        HomeBean b = new HomeBean();
        b.setViewType("module_grid");

        HomeBean c = new HomeBean();
        c.setViewType("marquee");

        HomeBean d = new HomeBean();
        d.setViewType("middle_banner");


        HomeBean f = new HomeBean();
        f.setViewType("recommend_grid");

        HomeBean g = new HomeBean();
        g.setViewType("guess_you_like");

        HomeBean h = new HomeBean();
        h.setViewType("bottom");

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(f);
        list.add(g);
        list.add(h);

        return list;
    }
}
