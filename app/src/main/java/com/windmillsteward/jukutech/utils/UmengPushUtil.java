package com.windmillsteward.jukutech.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.MessageListActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.map.ShowMapYingPinActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyWalletActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.smartlife.DingcanOrderDetailActivity;
import com.windmillsteward.jukutech.activity.newpage.smartlife.SmartLifeDetailsActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.CommonYiZhanResultFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.HasPublishedPositionFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BaomuInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYingPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoYingpinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.HasZhaopinPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiYingPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinPositionDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanUseInfoActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.ZichanRenzhenFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.HasLgTzgPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.JianliDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.YonggongDetailFragment;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseFragmentActivity;
import com.windmillsteward.jukutech.bean.MessageBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.manager.CommonActivityManager;

/**
 * 描述：友盟推送相关type跳转
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class UmengPushUtil {

    private Context context;
    private MessageBean.ListBean umengPushBean;

    public UmengPushUtil(Context context, MessageBean.ListBean bean) {
        this.context = context;
        this.umengPushBean = bean;
    }

    /**
     * 根据type，跳转到对应的activity，和推送一样
     */
    public void jumpRelativeActivityByType() {
        if (umengPushBean == null) {
            return;
        }
        Intent intent_jump = null;
        Bundle bundle = new Bundle();

        String relevance_id = umengPushBean.getRelevance_id();
        int relevance_id_new = umengPushBean.getRelevance_id_new();
        String type = umengPushBean.getType();
        int index_type = umengPushBean.getIndex_type();//智慧生活订餐酒店门票用到
        String publish_status = umengPushBean.getPublish_status();

        if (TextUtils.equals("1", type)) {
            //1.劳务中介-我要找人-支付完成后的推送。跳转已发布职位界面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_ZHONGJIE);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("2", type)) {
            //2、劳务中介-我要找人-匹配成功后的推送。跳转工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("recruitment_id", Integer.parseInt(relevance_id));
            bundle1.putInt("showAddress", 1);
            jumpFragment(HasLgTzgPublishedDetailsFragment.TAG, bundle1);
        } else if (TextUtils.equals("3", type)) {
            //3.劳务中介-我要找工作-支付完成后的推送。跳转已发布界面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_ZHONGJIE);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("4", type)) {
            //4.劳务中介-我要找工作-匹配成功后的推送。跳转匹配的工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
            jumpFragment(YonggongDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("5", type)) {
            //5.求职招聘-我要招聘-支付完成后的推送。跳转已发布职位界面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_ZHAOPIN);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("6", type)) {
            //6.求职招聘-我要招聘-匹配成功后的推送。跳转已匹配简历界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
            jumpFragment(QiuZhiZhaoPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("7", type)) {
            //7.求职招聘-我要求职-支付完成后的推送。跳转求职招聘-“我要求职”界面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_ZHAOPIN);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("8", type)) {
            //8.求职招聘-我要求职-匹配成功后的推送。跳转已匹配工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
            jumpFragment(QiuZhiYingPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("9", type)) {
            //9.姻缘服务-我要找对象-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_YINYUAN);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("10", type)) {
            //10.姻缘服务-我要找对象-匹配成功后的推送。跳转已匹配详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
            jumpFragment(YinyuanOrderDetailFragment.TAG, bundle1);
//
//
//            Intent intent = new Intent(context, YinyuanUseInfoActivity.class);
//            intent.putExtra("match_id", Integer.parseInt(relevance_id));
//            context.startActivity(intent);
        } else if (TextUtils.equals("11", type)) {
            //11.家教-我要找家教-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_JIAJIAO);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("12", type)) {
            //12.家教-我要找家教-匹配成功后的推送。跳转招聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", relevance_id_new);
            jumpFragment(JiajiaoZhaoPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("13", type)) {
            //13.家教-我要当家教-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_JIAJIAO);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("14", type)) {
            //14.家教-我要当家教-匹配成功后的推送。跳转应聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("when_tutor_id", relevance_id_new);
            jumpFragment(JiajiaoYingpinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("15", type)) {
            //15.家教-我要找家教-确认完成工作后的推送。
        } else if (TextUtils.equals("16", type)) {
            //16.家教-我要当家教-确认完成工作后的推送。
        } else if (TextUtils.equals("17", type)) {
            //17.保姆-我要找保姆-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_BAOMU);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("18", type)) {
            //18.保姆-我要找保姆-匹配成功后的推送。跳转招聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", relevance_id_new);
            bundle1.putInt("roleType", PageConfig.TYPE_BAOMU);
            jumpFragment(CommBmZhaoPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("19", type)) {
            //19.保姆-我要当保姆-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_BAOMU);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("20", type)) {
            //20.保姆-我要当保姆-匹配成功后的推送。跳转应聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", relevance_id_new);
            bundle1.putInt("roleType", PageConfig.TYPE_BAOMU);
            jumpFragment(CommBmYingPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("21", type)) {
            //21.保姆-我要找保姆-确认完成工作后的推送。
        } else if (TextUtils.equals("22", type)) {
            //22.保姆-我要当保姆-确认完成工作后的推送。
        } else if (TextUtils.equals("23", type)) {
            //23.月嫂-我要找月嫂-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_YUESAO);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("24", type)) {
            //24.月嫂-我要找月嫂-匹配成功后的推送。跳转招聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", relevance_id_new);
            bundle1.putInt("roleType", PageConfig.TYPE_YUESAO);
            jumpFragment(CommBmZhaoPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("25", type)) {
            //25.月嫂-我要当月嫂-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_YUESAO);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("26", type)) {
            //26.月嫂-我要当月嫂-匹配成功后的推送。跳转应聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", relevance_id_new);
            bundle1.putInt("roleType", PageConfig.TYPE_YUESAO);
            jumpFragment(CommBmYingPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("27", type)) {
            //27.月嫂-我要找月嫂-确认完成工作后的推送。
        } else if (TextUtils.equals("28", type)) {
            //28.月嫂-我要当月嫂-确认完成工作后的推送。
        } else if (TextUtils.equals("29", type)) {
            //29.育儿嫂-我要找育儿嫂-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_YUERSAO);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("30", type)) {
            //30.育儿嫂-我要找育儿嫂-匹配成功后的推送。跳转招聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", relevance_id_new);
            bundle1.putInt("roleType", PageConfig.TYPE_YUERSAO);
            jumpFragment(CommBmZhaoPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("31", type)) {
            //31.育儿嫂-我要当育儿嫂-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_YUERSAO);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("32", type)) {
            //32.育儿嫂-我要当育儿嫂-匹配成功后的推送。跳转应聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", relevance_id_new);
            bundle1.putInt("roleType", PageConfig.TYPE_YUERSAO);
            jumpFragment(CommBmYingPinOrderDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("33", type)) {
            //33.育儿嫂-我要找育儿嫂-确认完成工作后的推送。
        } else if (TextUtils.equals("34", type)) {
            //34.育儿嫂-我要当育儿嫂-确认完成工作后的推送。
        } else if (TextUtils.equals("35", type)) {
            //35.钟点工-我要找钟点工-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_ZHONGDIANGONG);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("36", type)) {
            //36.钟点工-我要找钟点工-匹配成功后的推送。跳转招聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("lookfor_bell_worker_id", Integer.parseInt(relevance_id));
            bundle1.putInt("showAddress", 1);
            jumpFragment(ZhongdgUsePersonFragment.TAG, bundle1);
        } else if (TextUtils.equals("37", type)) {
            //37.钟点工-我要当钟点工-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_ZHONGDIANGONG);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("38", type)) {
            //38.钟点工-我要当钟点工-匹配成功后的推送。跳转应聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("hour_matching_id", Integer.parseInt(relevance_id));
            jumpFragment(ZhongdgInfoDetailsFragment.TAG, bundle1);
        } else if (TextUtils.equals("39", type)) {
            //39.特种工-我要找特种工-支付完成后的推送。跳转已发布页面 √
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_TEZHONGGONG);
//            jumpFragment(HasPublishedPositionFragment.TAG, bundle1);
            jumpToOrderFragment();
        } else if (TextUtils.equals("40", type)) {
            //40.特种工-我要找特种工-匹配成功后的推送。跳转招聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("recruitment_id", Integer.parseInt(relevance_id));
            bundle1.putInt("roleType", PageConfig.TYPE_TEZHONGGONG);
            jumpFragment(HasLgTzgPublishedDetailsFragment.TAG, bundle1);
        } else if (TextUtils.equals("41", type)) {
            //41.特种工-我要当特种工-支付完成后的推送。跳转已发布页面
//            Bundle bundle1 = new Bundle();
//            bundle1.putInt("roleType", PageConfig.TYPE_TEZHONGGONG);
//            jumpFragment(CommonYiZhanResultFragment.TAG, bundle1);
        } else if (TextUtils.equals("42", type)) {
            //42.特种工-我要当特种工-匹配成功后的推送。跳转应聘方工作详情界面
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
            jumpFragment(YonggongDetailFragment.TAG, bundle1);
        } else if (TextUtils.equals("43", type)) {
            //43.订单超时未匹配到工作 跳转钱包 欢迎使用顺风车管家，你的工作未匹配成功，您的金额已全部退还
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, MyWalletActivity.class);
                context.startActivity(intent);
            }
        } else if (TextUtils.equals("44", type)) {
            //44.劳务中介-我要找人 - 完成工作（应聘方已完成工作） 跳转订单详情 您的订单已完成，请您点击订单确认支付 √
            Bundle bundle1 = new Bundle();
            bundle1.putInt("recruitment_id", Integer.parseInt(relevance_id));
            bundle1.putInt("roleType", PageConfig.TYPE_ZHONGJIE);
            bundle1.putInt("showAddress", 1);
            jumpFragment(HasLgTzgPublishedDetailsFragment.TAG, bundle1);
        } else if (TextUtils.equals("45", type)) {
            //45.劳务中介-我要找工作 - 确认支付（招聘方确认支付后） 跳转钱包 您的订单已完成，已收到工资1000元，点击钱包查看 √
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, MyWalletActivity.class);
                context.startActivity(intent);
            }
        } else if (TextUtils.equals("46", type)) {
            //46.智慧生活-抢单后推送给发布者。 跳转到需求订单详情界面

                Intent intent = new Intent(context, SmartLifeDetailsActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
                bundle1.putString("longitude", "");
                bundle1.putString("latitude", "");
                intent.putExtras(bundle1);
                context.startActivity(intent);

        } else if (TextUtils.equals("47", type)) {
            //47.智慧生活-发布人确认支付后，推送给抢单人。 跳转钱包
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, MyWalletActivity.class);
                context.startActivity(intent);
            }
        } else if (TextUtils.equals("48", type)) {
            //48.智慧生活-抢单人确认任务完成后，推送给发布人。 跳转需求订单详情界面
                Intent intent = new Intent(context, SmartLifeDetailsActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
                bundle1.putString("longitude", "");
                bundle1.putString("latitude", "");
                intent.putExtras(bundle1);
                context.startActivity(intent);
        } else if (TextUtils.equals("49", type)) {
            //49.钟点工-求职方确认完成工作-推送 跳转订单详情
            Bundle bundle1 = new Bundle();
            bundle1.putInt("lookfor_bell_worker_id", Integer.parseInt(relevance_id));
            bundle1.putInt("showAddress", 1);
            jumpFragment(ZhongdgUsePersonFragment.TAG, bundle1);
        } else if (TextUtils.equals("50", type)) {
            //50.钟点工-确认支付（招聘方确认支付后）-推送 跳转钱包 您的订单已完成，已收到工资1000元，点击钱包查看
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, MyWalletActivity.class);
                context.startActivity(intent);
            }
        }else if (TextUtils.equals("51", type)) {
            //51.招聘方-订单详情点击提醒抢单人共享地理位置， 跳转地图。 招聘方想查看你的地址位置，点击跳转共享位置。
            Intent intent = new Intent(context, ShowMapYingPinActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
            bundle1.putString("longitude", umengPushBean.getLongitude());
            bundle1.putString("latitude", umengPushBean.getLatitude());
            bundle1.putInt("type", umengPushBean.getModule_type());
            intent.putExtras(bundle1);
            context.startActivity(intent);
        }else if (TextUtils.equals("52", type)) {
            //52.企业认证审核不通过。 跳转填写审核资料界面
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, BusinessAuthenticationActivity.class);
                context.startActivity(intent);
            }
        }else if (TextUtils.equals("53", type)) {
            //53.个人认证审核不通过。 跳转填写审核资料界面
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, PersonalAuthenticationActivity.class);
                context.startActivity(intent);
            }
        }else if (TextUtils.equals("54", type)) {
            //54.招聘方订单超时未匹配到人员 跳转钱包 欢迎使用顺风车管家，你的工作未匹配成功，您的金额已全部退还
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, MyWalletActivity.class);
                context.startActivity(intent);
            }
        }else if (TextUtils.equals("55", type)) {
            //55.姻缘服务未匹配到对象超时退款  跳转钱包首页
            if (TextUtils.isEmpty(getAccessToken())) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, MyWalletActivity.class);
                context.startActivity(intent);
            }
        }else if (TextUtils.equals("56", type)) {
            //56.姻缘服务订单已完成 跳转我要找对象-系统匹配对象详情
                Intent intent = new Intent(context, YinyuanUseInfoActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("match_id", Integer.parseInt(relevance_id));
                intent.putExtras(bundle1);
                context.startActivity(intent);
        }else if (TextUtils.equals("57", type)) {
            //57.资产认证审核不通过。  跳转填写资产认证资料界面
            Bundle bundle1 = new Bundle();
            jumpFragment(ZichanRenzhenFragment.TAG, bundle1);
        }else if (TextUtils.equals("61", type) || TextUtils.equals("97", type) ||TextUtils.equals("98", type)
        || TextUtils.equals("101", type)|| TextUtils.equals("102", type)) {
            //61.接单后抢单人收到的通知
            //97.接单人取消订单，抢单人收到的通知
            //98.用户发单预定，商家收到订单通知
            //101 商家拒绝订单 用户收到推送
            //102 商家接单 用户收到推送
            Intent intent = new Intent(context, DingcanOrderDetailActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putInt("relate_id", Integer.parseInt(relevance_id));
            bundle1.putInt("type", index_type);
            intent.putExtras(bundle1);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


    /**
     * 跳转Fragment
     *
     * @param tag
     * @param bundle1
     */
    private void jumpFragment(String tag, Bundle bundle1) {
        Intent intent = new Intent(context, CommonActivityManager.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(BackFragmentActivity.TAG_FRAGMENT, tag);
        intent.putExtra(BaseFragmentActivity.ARGS, bundle1);
        context.startActivity(intent);
    }

    public void jumpToOrderFragment(){
        AppManager.getAppManager().onSaveActivity(MainActivity.class);
        MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
        if (activity!= null){
            activity.changeButtonStatus(2);
        }
    }


    public String getAccessToken() {
        return Hawk.get(Define.ACCESS_TOKEN, "");
    }
}
