package com.windmillsteward.jukutech.activity.newpage.common.presenter;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoFeeModel;
import com.windmillsteward.jukutech.activity.newpage.common.model.PayInfoModel;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;

import org.xutils.common.Callback;

import java.lang.reflect.Type;

/**
 * @date: on 2018/10/9
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class CommonPayPresenter extends BasePresenter {
    /**
     * 1.用户注册，2.智慧生活悬赏金费用订单 3.智慧生活信息费用，4.房屋模块联系 5.劳务中介-发布劳务工信息费用 *
     * 6.劳务中介-发布招聘劳务工信息费 7.特种工- 发布特种工信息费用 8.特种工 - 发布招聘特种工信息费用
     * 9.发布我要当保姆信息费用， * 10.发布我要找保姆信息费用，11.发布我要当月嫂信息费用
     * 12.发布我要找月嫂信息费用 13.发布我当育儿嫂信息费用 14.发布我找育儿嫂信息费用
     * 15.发布我要当家教信息费用，16.发布我要找家教费用，17.我要找保姆支付工程尾款 18.我要找家教支付工程尾款
     * 19.姻缘服务-发布我要找对象信息费用 20.姻缘服务-个人资产认证费用 21.发布我要求职信息费 22.发布我要招聘信息费
     *  23.发布钟点工信息费 24.发布招聘钟点工信息费用,25聊天发红包,26智慧生活广告发布27智慧生活订餐酒店门票发布
     */
    public static final int TYPE_REGISTER = 1;
    public static final int TYPE_ZHIHUI_XUANSHANGJIN = 2;
    public static final int TYPE_ZHIHUI_SHENGHUOXINXI = 3;
    public static final int TYPE_FANGWU = 4;
    public static final int TYPE_LAOGONG_XINXI = 5;
    public static final int TYPE_LAOGONG_ZHAOPIN = 6;
    public static final int TYPE_TEZHONG_XINXI = 7;
    public static final int TYPE_TEZHONG_ZHAOPIN = 8;
    public static final int TYPE_BAOMU_XINXI = 9;
    public static final int TYPE_BAOMU_ZHAOPIN = 10;
    public static final int TYPE_YUESAO_XINXI = 11;
    public static final int TYPE_YUESAO_ZHAOPIN = 12;
    public static final int TYPE_YUER_XINXI = 13;
    public static final int TYPE_YUER_ZHAOPIN = 14;
    public static final int TYPE_JIAJIAO_XINXI = 15;
    public static final int TYPE_JIAJIAO_ZHAOPIN = 16;
    public static final int TYPE_BAOMU_WEIKUAN = 17;
    public static final int TYPE_JIAJIAO_WEIKUAN = 18;
    public static final int TYPE_YINYUAN_DUIXIANG = 19;
    public static final int TYPE_YINYUAN_ZICHAN = 20;
    public static final int TYPE_QIUZHI = 21;
    public static final int TYPE_ZHAOPIN = 22;
    public static final int TYPE_ZHONGDIANGONG = 23;
    public static final int TYPE_ZHONGDIANGONG_ZHAOPIN = 24;
    public static final int TYPE_FA_HONGBAO = 25;
    public static final int TYPE_SMART_PUBLIC_AD = 26;
    public static final int TYPE_SMART_DINGCAN = 27;


    /**
     * 3.智慧生活信息费用 4.房屋租售联系信息费用 5.劳务中介-发布劳务工信息费用 6.劳务中介-发布招聘劳务工信息费
     * 7.特种工- 发布特种工信息费用 8.特种工 - 发布招聘特种工信息费用
     * <p>
     * 3.智慧生活信息费用 4.房屋租售联系信息费用 5.劳务中介-发布我要找工作信息费 6.劳务中介-发布招聘劳务工其它工种信息费，
     * * 7.特种工- 发布特种工信息费用，8.特种工 - 发布招聘特种工信息费用，9.发布我要当保姆信息费用，10.发布我要找保姆信息费用，
     * * 11.发布我要当月嫂信息费用 12.发布我要找月嫂信息费用 13.发布我当育儿嫂信息费用 14.发布我找育儿嫂信息费用
     * * 15.发布我要当家教信息费用 16.发布我要找家教信息费用 18.发布我要找对象信息费用 19.认证个人资产信息费用
     * 20.发布钟点工信息费用  21.发布我要求职信息费 22.发布我要招聘信息费23.发布招聘钟点工信息费用
     */
    public static final int FEE_TYPE_ZHIHUI = 3;
    public static final int FEE_TYPE_FANGWU = 4;
    public static final int FEE_TYPE_LAOWU_XINXI = 5;
    public static final int FEE_TYPE_LAOWU_ZHAOPIN = 6;
    public static final int FEE_TYPE_TEZHONG_XINXI = 7;
    public static final int FEE_TYPE_TEZHONG_ZHAOPIN = 8;
    public static final int FEE_TYPE_BAOMU_XINXI = 9;
    public static final int FEE_TYPE_BAOMU_ZHAOPIN = 10;
    public static final int FEE_TYPE_YUESAO_XINXI = 11;
    public static final int FEE_TYPE_YUESAO_ZHAOPIN = 12;
    public static final int FEE_TYPE_YUERSAO_XINXI = 13;
    public static final int FEE_TYPE_YUERSAO_ZHAOPIN = 14;
    public static final int FEE_TYPE_JIAJIAO_XINXI = 15;
    public static final int FEE_TYPE_JIAJIAO_ZHAOPIN = 16;
    public static final int FEE_TYPE_DUIXIANG_ZHAOPIN = 18;
    public static final int FEE_TYPE_GERENZHICHAN = 19;
    public static final int FEE_TYPE_ZHONGDIANGONG = 20;
    public static final int FEE_TYPE_QIUZHI_XINXI = 21;
    public static final int FEE_TYPE_QIUZHI_ZHAOPIN = 22;
    public static final int FEE_TYPE_ZHONGDIANGONG_ZHAOPIN = 23;

    private Context context;

    public CommonPayPresenter(Context context) {
        this.context = context;
    }

    /**
     * 支付类型
     */
    public enum PAY_TYPE {
        ALI_PAY, WECHAT, CARD
    }

    /**
     * 根据类型获取信息
     *
     * @param type
     * @param payType   支付类型 1.支付宝  2.微信  3.钱包
     * @param relate_id 关联id
     * @param callBack
     * @return
     */
    public Callback.Cancelable loadPayInfoData(int type, PAY_TYPE payType, int relate_id,int coupon_id, final DataCallBack callBack) {
        int pay_type = -1;
        switch (payType) {
            case CARD:
                pay_type = 3;
                break;
            case WECHAT:
                pay_type = 2;
                break;
            case ALI_PAY:
                pay_type = 1;
                break;
        }
        return new NetUtil().setCallBackData(new BaseNewNetModelimpl<PayInfoModel>() {
            @Override
            protected void onFail(int type, String msg) {
                if (callBack != null)
                    callBack.onFail(type, msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<PayInfoModel> respnse, String source) {
                if (callBack != null) {
                    callBack.onSucess(code, respnse, source);
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<PayInfoModel>>() {
                }.getType();
            }
        }).addParams("pay_type", pay_type + "")
                .addParams("type", type + "")
                .addParams("relate_id", relate_id + "")
                .addParams("receive_id", coupon_id + "")
                .setUrl(APIS.URL_CONFIRM_PAY)
                .buildPost();
    }

    /**
     * 根据类型获取信息
     *
     * @param type
     * @param payType   支付类型 1.支付宝  2.微信  3.钱包
     * @param relate_id 关联id
     * @param callBack
     * @return
     */
    public Callback.Cancelable walletPay(int type, PAY_TYPE payType, int relate_id,int coupon_id, final DataCallBack callBack) {
        int pay_type = -1;
        switch (payType) {
            case CARD:
                pay_type = 3;
                break;
            case WECHAT:
                pay_type = 2;
                break;
            case ALI_PAY:
                pay_type = 1;
                break;
        }
        return new NetUtil().setCallBackData(new BaseNewNetModelimpl<String>() {
            @Override
            protected void onFail(int type, String msg) {
                if (callBack != null)
                    callBack.onFail(type, msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<String> respnse, String source) {
                if (callBack != null) {
                    callBack.onSucess(code, respnse, source);
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<String>>() {
                }.getType();
            }
        }).addParams("pay_type", pay_type + "")
                .addParams("type", type + "")
                .addParams("relate_id", relate_id + "")
                .addParams("receive_id", coupon_id + "")
                .setUrl(APIS.URL_CONFIRM_PAY)
                .buildPost();
    }

    /**
     * 根据类型获取信息
     *
     * @param price
     * @param payType   支付类型 1.支付宝  2.微信
     * @param callBack
     * @return
     */
    public Callback.Cancelable walletRecharge(String price, PAY_TYPE payType, final DataCallBack callBack) {
        int pay_type = -1;
        switch (payType) {
            case WECHAT:
                pay_type = 2;
                break;
            case ALI_PAY:
                pay_type = 1;
                break;
        }
        return new NetUtil().setCallBackData(new BaseNewNetModelimpl<PayInfoModel>() {
            @Override
            protected void onFail(int type, String msg) {
                if (callBack != null)
                    callBack.onFail(type, msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<PayInfoModel> respnse, String source) {
                if (callBack != null) {
                    callBack.onSucess(code, respnse, source);
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<PayInfoModel>>() {
                }.getType();
            }
        }).addParams("pay_type", pay_type + "")
                .addParams("price", price )
                .setUrl(APIS.URL_WALLET_RECHARGE)
                .buildPost();
    }

    /**
     * 查询信息费
     *
     * @param type
     * @param callBack
     * @return
     */
    public Callback.Cancelable loadPayInfoFeeData(int type, final DataCallBack callBack) {
        return new NetUtil().setCallBackData(new BaseNewNetModelimpl<PayInfoFeeModel>() {
            @Override
            protected void onFail(int type, String msg) {
                if (callBack != null)
                    callBack.onFail(type, msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<PayInfoFeeModel> respnse, String source) {
                if (callBack != null) {
                    callBack.onSucess(code, respnse, source);
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<PayInfoFeeModel>>() {
                }.getType();
            }
        }).addParams("type", type + "")
                .setUrl(APIS.URL_INFO_FEE)
                .buildPost();
    }

    /**
     * 查询房屋信息费
     *
     * @param type
     * @param relate_id 房屋id
     * @param callBack
     * @return
     */
    public Callback.Cancelable loadHousePayInfoFeeData(int type,int relate_id, final DataCallBack callBack) {
        return new NetUtil().setCallBackData(new BaseNewNetModelimpl<PayInfoFeeModel>() {
            @Override
            protected void onFail(int type, String msg) {
                if (callBack != null)
                    callBack.onFail(type, msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<PayInfoFeeModel> respnse, String source) {
                if (callBack != null) {
                    callBack.onSucess(code, respnse, source);
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<PayInfoFeeModel>>() {
                }.getType();
            }
        }).addParams("type", type + "").addParams("relate_id",relate_id+"")
                .setUrl(APIS.URL_INFO_FEE)
                .buildPost();
    }


    public interface DataCallBack<T> {
        void onFail(int type, String msg);

        void onSucess(int code, BaseResultInfo<T> respnse, String source);
    }
}
