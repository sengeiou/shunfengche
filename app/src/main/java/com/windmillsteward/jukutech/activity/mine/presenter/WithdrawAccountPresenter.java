package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.WithdrawAccountView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.WithdrawBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：提现账户列表实现类
 * 时间：2018/3/6
 * 作者：cyq
 */
public class WithdrawAccountPresenter extends BaseNetModelImpl {

    private static final int WITHDRAW_LIST = 1;

    private WithdrawAccountView view;

    public WithdrawAccountPresenter(WithdrawAccountView view) {
        this.view = view;
    }

    /**
     * 申请提现
     */
    public void getWithdrawAccountList(String access_token) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, WITHDRAW_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_WITHDRAW_ACCOUNT_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }



    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case WITHDRAW_LIST:
                type = new TypeReference<BaseResultInfo<List<WithdrawBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case WITHDRAW_LIST:
                view.dismiss();
                List<WithdrawBean> list = (List<WithdrawBean>) result.getData();
                view.getWithdrawAccountListSuccess(list);
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case WITHDRAW_LIST:
                view.dismiss();
                view.getWithdrawAccountListFailed(msg);
                break;

        }
    }
}
