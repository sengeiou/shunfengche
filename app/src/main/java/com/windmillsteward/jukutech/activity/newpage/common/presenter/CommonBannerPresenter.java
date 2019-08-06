package com.windmillsteward.jukutech.activity.newpage.common.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListNewActivity;
import com.windmillsteward.jukutech.activity.newpage.common.model.CommonBannerModel;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @date: on 2018/10/9
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class CommonBannerPresenter {
    /**
     * banner图位置：1.app首页顶部 2.管家推荐 3.首页底部banner 4.人才驿站首页 5.劳务中介首页 6.求职招聘 7.姻缘服务 8.家教 9.保姆 10.月嫂 11.育儿嫂 12.钟点工 13.特种工 14.房屋信息
     * banner图位置：1.app首页顶部 2.管家推荐 3.首页底部banner 4.人才驿站首页 5.劳务中介首页 6.求职招聘 7.姻缘服务 8.家教 9.保姆 10.月嫂 11.育儿嫂 12.钟点工 13.特种工 14.房屋信息
     */
    public static final int TYPE_HOME_TOP = 1;
    public static final int TYPE_GUANJIATUIJIAN = 2;
    public static final int TYPE_HOME_BOTTOM = 3;
    public static final int TYPE_RENCAIIZHAN = 4;
    public static final int TYPE_LAOWUZHONGJIE = 5;
    public static final int TYPE_QIUZHIZHAOPIN = 6;
    public static final int TYPE_YINYUANFUWU = 7;
    public static final int TYPE_JIAJIAO = 8;
    public static final int TYPE_BAOMU = 9;
    public static final int TYPE_YUESAO = 10;
    public static final int TYPE_YUERSAO = 11;
    public static final int TYPE_ZHONGDIANGONG = 12;
    public static final int TYPE_TEZHONGGONG = 13;
    public static final int TYPE_FANGWUXINXI = 14;

    private Context context;

    public CommonBannerPresenter(Context context) {
        this.context = context;
    }

    /**
     * 根据位置获取banner信息
     *
     * @param banner_position
     * @return
     */
    public Callback.Cancelable loadBannerData(int banner_position, final DataCallBack callBack) {

        return new NetUtil().setCallBackData(new BaseNewNetModelimpl<List<CommonBannerModel>>() {
            @Override
            protected void onFail(int type, String msg) {
                if (callBack != null)
                    callBack.onFail(type, msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<List<CommonBannerModel>> respnse, String source) {
                if (callBack != null) {
                    callBack.onSucess(code, respnse, source);
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<List<CommonBannerModel>>>() {
                }.getType();
            }
        }).addParams("banner_position", banner_position + "")
                .setUrl(APIS.URL_BANNER_LIST)
                .buildPost();
    }

    public interface DataCallBack {
        void onFail(int type, String msg);

        void onSucess(int code, BaseResultInfo<List<CommonBannerModel>> respnse, String source);
    }

    /**
     * 处理Banner
     *
     * @param flyBanner
     */
    public void handlerBanner(FlyBanner flyBanner, final BaseResultInfo<List<CommonBannerModel>> respnse) {
        if (respnse.getData() != null) {
            List<String> urls = new ArrayList<>();
            for (CommonBannerModel commonBannerModel : respnse.getData()) {
                urls.add(commonBannerModel.getPic_url());
            }
            flyBanner.setImagesUrl(urls);
            flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //0.不跳转 1.跳转的url地址，2.APP跳转内部
                    if (respnse.getData().get(position).getJump_type() == 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Define.INTENT_DATA, respnse.getData().get(position).getHref_url());
                        Intent intent = new Intent(context, CommonWebActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else if (respnse.getData().get(position).getJump_type() == 2) {
                        //跳转内部
                        int jump_code = respnse.getData().get(position).getJump_code();
                        Intent intent;
                        if (jump_code == 1) {//人才驿站
                            intent = new Intent(context, TalentInnListNewActivity.class);
                            context.startActivity(intent);
                        } else if (jump_code == 2) {//智慧生活
                            intent = new Intent(context, IntelligentFamilyListActivity.class);
                            context.startActivity(intent);
                        } else if (jump_code == 3) {//房屋
                            intent = new Intent(context, HouseRentingActivity.class);
                            context.startActivity(intent);
                        }
                    }
                }
            });
        }
    }
}
