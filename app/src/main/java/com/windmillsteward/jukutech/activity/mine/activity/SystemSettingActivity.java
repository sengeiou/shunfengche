package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.CheckVersionUpdatePresenter;
import com.windmillsteward.jukutech.activity.CheckVersionUpdateView;
import com.windmillsteward.jukutech.activity.login.activity.ForgetPasswordActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.bean.CheckVersionUpdateBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CacheUtil;
import com.windmillsteward.jukutech.utils.CheckVersionUtil;

/**
 * 描述：系统设置
 * author:cyq
 * 2018-03-13
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SystemSettingActivity extends BaseActivity implements View.OnClickListener, CheckVersionUpdateView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_cache;
    private LinearLayout lay_ll_clear_cache;
    private LinearLayout lay_ll_forget_password;
    private LinearLayout lay_ll_update_version;
    private ImageView iv_ring;
    private ImageView iv_shake;

    private BaseDialog clearDialog;

    private boolean isRing = Hawk.get(Define.MSG_RING,false);
    private boolean isShake = Hawk.get(Define.MSG_SHAKE,false);

    private CheckVersionUpdatePresenter checkVersionUpdatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        initView();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_cache = (TextView) findViewById(R.id.tv_cache);
        lay_ll_clear_cache = (LinearLayout) findViewById(R.id.lay_ll_clear_cache);
        lay_ll_forget_password = (LinearLayout) findViewById(R.id.lay_ll_forget_password);
        lay_ll_update_version = (LinearLayout) findViewById(R.id.lay_ll_update_version);
        iv_ring = (ImageView) findViewById(R.id.iv_ring);
        iv_shake = (ImageView) findViewById(R.id.iv_shake);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("系统设置");

        lay_ll_clear_cache.setOnClickListener(this);
        lay_ll_forget_password.setOnClickListener(this);
        lay_ll_update_version.setOnClickListener(this);

        iv_ring.setOnClickListener(this);
        iv_shake.setOnClickListener(this);

        try {
            String totalCacheSize = CacheUtil.getTotalCacheSize(this);
            tv_cache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isRing){
            iv_ring.setImageResource(R.mipmap.icon_slipon);
        }else{
            iv_ring.setImageResource(R.mipmap.icon_slipoff);
        }
        if (isShake){
            iv_shake.setImageResource(R.mipmap.icon_slipon);
        }else{
            iv_shake.setImageResource(R.mipmap.icon_slipoff);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_ll_clear_cache:
                if (clearDialog == null) {
                    clearDialog = new BaseDialog(this);
                }
                clearDialog.showTwoButton("提示", "是否清除缓存", "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clearDialog.dismiss();
                        CacheUtil.clearAllCache(SystemSettingActivity.this);
                        showTips("清除成功", 1);
                        tv_cache.setText("0");
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clearDialog.dismiss();
                    }
                });
                break;
            case R.id.lay_ll_forget_password:
                startAtvDonFinish(ForgetPasswordActivity.class);
                break;
            case R.id.lay_ll_update_version:
                if (checkVersionUpdatePresenter == null) {
                    checkVersionUpdatePresenter = new CheckVersionUpdatePresenter(this);
                }
                checkVersionUpdatePresenter.checkUpdate();
                break;
            case R.id.iv_shake:
                if (isShake){
                    isShake = false;
                    showTips("消息震动已关闭");
                    iv_shake.setImageResource(R.mipmap.icon_slipoff);
                }else{
                    isShake = true;
                    showTips("消息震动已开启");
                    iv_shake.setImageResource(R.mipmap.icon_slipon);
                }
                Hawk.put(Define.MSG_SHAKE,isShake);
                break;
            case R.id.iv_ring:
                if (isRing){
                    isRing = false;
                    showTips("消息响铃已关闭");
                    iv_ring.setImageResource(R.mipmap.icon_slipoff);
                }else{
                    isRing = true;
                    showTips("消息响铃已开启");
                    iv_ring.setImageResource(R.mipmap.icon_slipon);
                }
                Hawk.put(Define.MSG_RING,isRing);
                break;

        }
    }

    @Override
    public void getCheckVersionDataSuccess(CheckVersionUpdateBean bean) {
        if (bean == null) {
            return;
        }
        CheckVersionUtil.checkVersionUpdate(this, bean, true);
    }

    @Override
    public void getCheckVersionDataFailed(int code, String msg) {
        showTips(msg, 1);
    }
}
