package com.windmillsteward.jukutech.activity.newpage.me;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.StateButton;

import java.lang.reflect.Type;

import butterknife.Bind;

/**
 * 描述：兑换奖品页面
 * author:cyq
 * 2019/4/18
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */
public class DuiJiangActivity extends BaseInitActivity {

    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.btn_commit)
    StateButton btnCommit;

    @Override
    protected void initView(View view) {
        showContentView();
        setMainTitle("兑换奖品");
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_duijiang;
    }

    @Override
    protected void initData() {
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
    }

    private void commit() {
        String content = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            showTips("请输入兑奖码");
            return;
        }

        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_COMMIT_DUIHUAN_CODE)
                .addParams("code", etPwd.getText().toString().trim())
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        showTips("提交成功");
                        etPwd.setText("");
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void refreshPageData() {

    }


}
