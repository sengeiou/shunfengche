package com.windmillsteward.jukutech.activity.home.commons.search;

import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.SearchHistoryBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public class SearchItemPresenter extends BaseNetModelImpl {

    private Context context;
    private SearchItemView view;

    public SearchItemPresenter(Context context, SearchItemView view) {
        this.context = context;
        this.view = view;
    }

    public void loadHistory(int type){
        List<SearchHistoryBean> list = Hawk.get(Define.SEARCH_HISTORY+type, null);

        view.onLoadHistory(list);
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
