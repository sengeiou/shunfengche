package com.windmillsteward.jukutech.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.model.TuiguangModel;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.TimerCountUtil;

import java.lang.reflect.Type;

public class PhoneCodeDialog implements View.OnClickListener {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private EditText et_phone;
    private EditText et_code;
    private TextView tv_code;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private DataCallBack dataCallBack;

    public PhoneCodeDialog(Context context, DataCallBack dataCallBack) {
        this.context = context;
        this.dataCallBack = dataCallBack;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public PhoneCodeDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_phone_code, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        et_phone = (EditText) view.findViewById(R.id.et_phone);
        et_code = (EditText) view.findViewById(R.id.et_code);
        tv_code = (TextView) view.findViewById(R.id.tv_code);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        img_line = (ImageView) view.findViewById(R.id.img_line);

        btn_neg.setOnClickListener(this);
        btn_pos.setOnClickListener(this);
        tv_code.setOnClickListener(this);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.IosAlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public PhoneCodeDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                String phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                final LoadingDialog loadingDialog = new LoadingDialog(context);
                loadingDialog.showLoading("");
                new NetUtil().addParams("mobile_phone", phone)
                        .setUrl(APIS.URL_GET_CHANGE_PHONE_CODE).setCallBackData(new BaseNewNetModelimpl<String>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        if (respnse.getData() != null) {
                            if (loadingDialog != null) {
                                loadingDialog.dismiss();
                            }
                            TimerCountUtil timerCountUtil = new TimerCountUtil(30000, 1000, tv_code);
                            timerCountUtil.setTimeSurListner(new TimerCountUtil.TimerChangeListner() {
                                @Override
                                public void onChange(long minis) {
                                    tv_code.setText("剩余" + minis / 1000 + "秒");
                                    tv_code.setTextColor(Color.BLACK);
                                }

                                @Override
                                public void onChangeFinish(String state) {
                                    tv_code.setText("重新获取");
                                    tv_code.setTextColor(Color.GRAY);
                                }
                            });
                            timerCountUtil.start();
                            Toast.makeText(context, "验证码已发送,请注意查收", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<String>>() {
                        }.getType();
                    }
                }).buildPost();
                break;
            case R.id.btn_neg:
                String mobile_phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(mobile_phone)) {
                    Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = et_code.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(context, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                final LoadingDialog loadingDialog2 = new LoadingDialog(context);
                loadingDialog2.showLoading("");
                new NetUtil().addParams("mobile_phone", mobile_phone)
                        .addParams("verify_code", code)
                        .setUrl(APIS.URL_CHANGE_PHONE).setCallBackData(new BaseNewNetModelimpl<String>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        if (loadingDialog2 != null) {
                            loadingDialog2.dismiss();
                        }
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        if (respnse.getData() != null) {
                            if (loadingDialog2 != null) {
                                loadingDialog2.dismiss();
                            }
                            dataCallBack.verifyCodeSuccess(et_phone.getText().toString());
                            dialog.dismiss();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<String>>() {
                        }.getType();
                    }
                }).buildPost();
                break;
            case R.id.btn_pos:
                dialog.dismiss();
                break;
        }
    }

    private void setLayout() {

    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public interface DataCallBack {
        void verifyCodeSuccess(String new_phone);
    }
}
