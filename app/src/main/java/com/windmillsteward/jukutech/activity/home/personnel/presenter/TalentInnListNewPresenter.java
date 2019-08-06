package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListNewView;
import com.windmillsteward.jukutech.activity.home.personnel.model.TalentInnListClassicModel;
import com.windmillsteward.jukutech.activity.home.personnel.model.TalentInnListModel;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class TalentInnListNewPresenter extends BasePresenter {

    private TalentInnListNewView view;

    public TalentInnListNewPresenter(TalentInnListNewView view) {
        this.view = view;
    }

    /**
     * 获取头部数据
     */
    public void getHeaderViewData() {
        new NetUtil().setUrl(APIS.URL_TALENT_HOME_INDEX_CLASS)
                .setCalls(getCalls())
                .setCallBackData(new BaseNewNetModelimpl<List<TalentInnListClassicModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        view.dismiss();
                        view.showErrorView();
                        view.showTips(AppConstant.ERROR_MSG, 1);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<TalentInnListClassicModel>> respnse, String source) {
                        view.dismiss();
                        view.showContentView();
                        view.loadHeaderViewData(respnse.getData());
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<TalentInnListClassicModel>>>() {
                        }.getType();
                    }
                })
                .buildPost();
    }


    /**
     * 获取列表数据
     *
     * @param page
     */
    public void getListData(int page) {
        new NetUtil().setUrl(APIS.URL_TALENT_GET_INDEX_RECOMMENDS)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID,0)+"")
                .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID,0)+"")
                .setCallBackData(new BaseNewNetModelimpl<TalentInnListModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        view.dismiss();
                        view.loadFail();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<TalentInnListModel> respnse, String source) {
                        view.dismiss();
                        view.showContentView();
                        if (respnse.getData() != null) {
                            view.loadListData(respnse.getData());
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<TalentInnListModel>>() {
                        }.getType();
                    }
                }).buildPost();

    }
}
