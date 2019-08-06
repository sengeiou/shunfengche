package com.windmillsteward.jukutech.activity.newpage.me;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.utils.EaseUserUtils;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.AboutUsActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyCollectActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyCouponActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyInfoActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyWalletActivity;
import com.windmillsteward.jukutech.activity.mine.activity.ServiceCenterActivity;
import com.windmillsteward.jukutech.activity.mine.activity.SystemSettingActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.FubuQiuzhiZhaopinFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuzhiJianliFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.LwTzWorkListDetailFragment;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.UserInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.zhouwei.blurlibrary.EasyBlur;

import org.xutils.x;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseInitFragment {
    public static final String TAG = "MeFragment";
    @Bind(R.id.iv_avatar_bg)
    ImageView ivAvatarBg;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_level)
    TextView tvLevel;
    @Bind(R.id.ll_login)
    LinearLayout llLogin;
    @Bind(R.id.tv_qiandao)
    TextView tvQiandao;
    @Bind(R.id.tv_shoucang)
    TextView tvShoucang;
    @Bind(R.id.tv_qianbao)
    TextView tvQianbao;
    @Bind(R.id.tv_youhuiquan)
    TextView tvYouhuiquan;
    @Bind(R.id.tv_fuwuzhongxin)
    TextView tvFuwuzhongxin;
    @Bind(R.id.tv_shezhi)
    TextView tvShezhi;
    @Bind(R.id.tv_guanyu)
    TextView tvGuanyu;
    @Bind(R.id.tv_jiangli)
    TextView tvJiangli;
    @Bind(R.id.tv_duihuan)
    TextView tvDuiHuan;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        showContentView();
        hidTitleView();
        hidBackTv();
        hidLineDivider();
    }

    @Override
    public void onResume() {
        super.onResume();
        String access_token = (String) Hawk.get(Define.ACCESS_TOKEN);
        if (TextUtils.isEmpty(access_token)) {
            tvNickname.setText("登录");
            tvLevel.setVisibility(View.GONE);
            ivAvatar.setImageResource(R.mipmap.icon_head_n);
        } else {
            //请求用户数据
            tvLevel.setVisibility(View.VISIBLE);
            getData();
        }
    }

    @Override
    protected void initData() {
    }

    public void getData() {
        String access_token = (String) Hawk.get(Define.ACCESS_TOKEN);
        if (TextUtils.isEmpty(access_token)) {
            return;
        }
        addCall(new NetUtil().setUrl(APIS.URL_GET_MY_INFO)
                .setCallBackData(new BaseNewNetModelimpl<UserInfo>() {
                    @Override
                    protected void onFail(int type, final String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<UserInfo> respnse, String source) {
                        if (respnse.getData() != null) {
                            UserInfo userInfo = respnse.getData();
                            if (userInfo == null) {
                                return;
                            }
                            KV.put(Define.USER_INFO, userInfo);
                            String nickname = userInfo.getNickname();
                            tvNickname.setText(TextUtils.isEmpty(nickname) ? "" : nickname);
                            String user_avatar_url = userInfo.getUser_avatar_url();
                            int sex = userInfo.getSex();

                            Glide.with(getActivity()).asBitmap()
                                    .apply(GlideUtil.defaultOptions(R.mipmap.icon_default_pic, R.mipmap.icon_head_n))
                                    .load(user_avatar_url)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                            if (resource != null) {
                                                Bitmap blur = EasyBlur.with(getActivity()).bitmap(resource) //要模糊的图片
                                                        .radius(10)//模糊半径
                                                        .scale(4)//指定模糊前缩小的倍数
                                                        .policy(EasyBlur.BlurPolicy.FAST_BLUR)//使用fastBlur
                                                        .blur();

                                                ivAvatarBg.setImageBitmap(blur);
                                            }
                                        }
                                    });
                            if (sex == 1) {
                                x.image().bind(ivAvatar, user_avatar_url, ImageUtils.defaultBoyHeadOptions());
                            } else if (sex == 2) {
                                x.image().bind(ivAvatar, user_avatar_url, ImageUtils.defaultGirlHeadOptions());
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeReference<BaseResultInfo<UserInfo>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {

    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    @OnClick({R.id.ll_login, R.id.tv_qiandao, R.id.tv_shoucang, R.id.tv_qianbao, R.id.tv_youhuiquan, R.id.tv_fuwuzhongxin, R.id.tv_shezhi, R.id.tv_guanyu, R.id.tv_jiangli, R.id.tv_duihuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_login:
                String access_token = (String) Hawk.get(Define.ACCESS_TOKEN);
                if (TextUtils.isEmpty(access_token)) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    //跳到个人中心
                    startAtvDonFinish(MyInfoActivity.class);
                }
                break;
            case R.id.tv_qiandao:
                showTips("正在开发中...");
                break;
            case R.id.tv_shoucang:
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyCollectActivity.class);
                }
                break;
            case R.id.tv_qianbao:
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyWalletActivity.class);
                }
                break;
            case R.id.tv_youhuiquan:
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MyCouponActivity.class);
                }
                break;
            case R.id.tv_fuwuzhongxin:
                startAtvDonFinish(ServiceCenterActivity.class);
                break;
            case R.id.tv_shezhi:
                startAtvDonFinish(SystemSettingActivity.class);
                break;
            case R.id.tv_guanyu:
                startAtvDonFinish(AboutUsActivity.class);
                break;
            case R.id.tv_jiangli:
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startManagerActivity(CommonActivityManager.class, TuiguangFragment.TAG);
                }
                break;
            case R.id.tv_duihuan:
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(DuiJiangWebActivity.class);
//                    startAtvDonFinish(DuiJiangWeb2Activity.class);
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
