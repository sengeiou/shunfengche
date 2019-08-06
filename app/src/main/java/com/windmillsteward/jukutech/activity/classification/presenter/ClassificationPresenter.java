package com.windmillsteward.jukutech.activity.classification.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.classification.fragment.ClassificationView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ClassificationMenuBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class ClassificationPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;

    ClassificationView view;

    public ClassificationPresenter(ClassificationView view) {
        this.view = view;
    }

    public void initMenuData(Context context){
        List<ClassificationMenuBean> menus = new ArrayList<>();
        String[] array = context.getResources().getStringArray(R.array.classificationMenus);
        for (String s : array) {
            ClassificationMenuBean bean = new ClassificationMenuBean();
            bean.setText(s);
            bean.setTextColor(ContextCompat.getColor(context,R.color.text_color_black));
            bean.setBackgroundColor(ContextCompat.getColor(context,R.color.color_f3f4f6));
            menus.add(bean);
        }
        view.initMenuDataSuccess(menus);
    }


    @Override
    protected Type getDataType(int action) {

        return null;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {

    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {

    }
}
