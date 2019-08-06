package com.windmillsteward.jukutech.activity.mine.presenter;


import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.DeleteWithDrawView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;


/**
 * 删除提现账号实现类
 *
 * @author: cyq
 * @data: 2018/3/6
 * Created by  2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class DeleteWithDrawPresenter extends BaseNetModelImpl  {

    private DeleteWithDrawView deleteWithDrawView;
    private final int DELETE_ACCOUNT = 1;


    public DeleteWithDrawPresenter(DeleteWithDrawView deleteWithDrawView) {
        this.deleteWithDrawView = deleteWithDrawView;
    }



    public void deleteAccount(int account_id,String access_token) {
        DataLoader dataLoader = new DataLoader(this, DELETE_ACCOUNT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("account_id", account_id);
        map.put("access_token", access_token);
        StringUtil.dataSignatureProcess(map);
        httpInfo.setUrl(APIS.URL_DELETE_ACCOUNT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
        deleteWithDrawView.showDialog("");
    }


    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        deleteWithDrawView.dismiss();
        switch (action) {
            case DELETE_ACCOUNT:
                deleteWithDrawView.deleteSuccess();
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        deleteWithDrawView.dismiss();
        deleteWithDrawView.deleteFailed(msg);
    }

    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case DELETE_ACCOUNT:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }
}
