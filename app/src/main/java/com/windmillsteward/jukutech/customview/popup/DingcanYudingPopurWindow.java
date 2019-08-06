package com.windmillsteward.jukutech.customview.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;
import com.windmillsteward.jukutech.utils.view.PickerViewWrap;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.qqtheme.framework.picker.DatePicker;

public class DingcanYudingPopurWindow extends PopupWindow implements View.OnClickListener {

    private Context context;

    private TextView tv_select;
    private TextView tv_yuding;
    private TextView tv_title;

    private EditText et_remark;
    private EditText et_num;
    private EditText et_contact_phone;
    private EditText et_contact_person;
    private ImageView iv_close;

    private String yuDingTime;
    private int type;
    private int service_id;

    private LinearLayout lay_ll_root;
    private LoadingDialog loadingDialog;
    private  DataCallBack dataCallBack;

    private List<Callback.Cancelable> cancelableList = new ArrayList<>();

    public DingcanYudingPopurWindow(int type, int service_id, Context context,DataCallBack dataCallBack) {
        super(context);
        this.type = type;
        this.service_id = service_id;
        this.context = context;
        this.dataCallBack = dataCallBack;

        loadingDialog = new LoadingDialog(context);
        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pop_dingcan_yuding, null);
        tv_title = view.findViewById(R.id.tv_title);
        tv_select = view.findViewById(R.id.tv_select);
        tv_yuding = view.findViewById(R.id.tv_yuding);
        et_num = view.findViewById(R.id.et_num);
        et_remark = view.findViewById(R.id.et_remark);
        et_contact_phone = view.findViewById(R.id.et_contact_phone);
        et_contact_person = view.findViewById(R.id.et_contact_person);
        iv_close = view.findViewById(R.id.iv_close);

        tv_yuding.setOnClickListener(this);
        tv_select.setOnClickListener(this);
        iv_close.setOnClickListener(this);

        if (type == 1) {
            String currentDate_old = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm");
            yuDingTime = DateUtil.DateToStampTime(currentDate_old, "yyyy-MM-dd HH:mm");
            String currentDate = DateUtil.getCurrentDate("yyyy年MM月dd日 HH:mm");
            tv_select.setText(currentDate);
        } else if (type == 2 || type == 3) {
            String currentDate_old = DateUtil.getCurrentDate("yyyy-MM-dd HH:mm");
            yuDingTime = DateUtil.DateToStampTime(currentDate_old, "yyyy-MM-dd");
            String currentDate = DateUtil.getCurrentDate("yyyy年MM月dd日");
            tv_select.setText(currentDate);
        }

        LoginSuccessInfo loginSuccessInfo = (LoginSuccessInfo) Hawk.get(Define.LOGIN_SUCCESS);
        if (loginSuccessInfo != null) {
            et_contact_person.setText(StringUtil.notEmptyBackValue(loginSuccessInfo.getNickname()));
        }
        String account = (String) Hawk.get("account");
        et_contact_phone.setText(StringUtil.notEmptyBackValue(account));

        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.85));
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                /**
                 * 取消所有的请求
                 */
                if (cancelableList != null) {
                    if (cancelableList.size() != 0) {
                        for (Callback.Cancelable cancelable : cancelableList) {
                            if (cancelable != null) {
                                if (!cancelable.isCancelled()) {
                                    cancelable.cancel();
                                }
                            }
                        }
                    }
                }
                backgroundAlpha((Activity) context, 1f);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void showAtMiddle(View view) {
        showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 10);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_yuding:
                submit();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_select:
                if (type == 1) {
                    new PickerViewWrap().showNianYueRiShiFenPicker((Activity) context, new DatePicker.OnYearMonthDayTimePickListener() {
                        @Override
                        public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                            tv_select.setText(s + "年" + s1 + "月" + s2 + "日 " + s3 + ":" + s4);
                            yuDingTime = DateUtil.DateToStampTime(s + "-" + s1 + "-" + s2 + " " + s3 + ":" + s4, "yyyy-MM-dd HH:mm");

                        }
                    }, 0);
                } else {
                    new PickerViewWrap().showDateFromTodayPicker((Activity) context, new DatePicker.OnYearMonthDayTimePickListener() {
                        @Override
                        public void onDateTimePicked(String s, String s1, String s2, String s3, String s4) {
                            tv_select.setText(s + "年" + s1 + "月" + s2 + "日");
                            yuDingTime = DateUtil.DateToStampTime(s + "-" + s1 + "-" + s2, "yyyy-MM-dd");
//                        workDate = s + "-" + s1 + "-" + s2;
                        }
                    }, 0);
                }
                break;
            default:
                break;
        }
    }

    private void submit() {

        if (TextUtils.isEmpty(yuDingTime)) {
            Toast.makeText(context, "请选择日期", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_num.getText().toString())) {
            Toast.makeText(context, "请输入预定人数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_contact_person.getText().toString())) {
            Toast.makeText(context, "请输入联系人", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_contact_phone.getText().toString())) {
            Toast.makeText(context, "请输入联系电话", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingDialog.showLoading("");
        //请求预定接口
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_DINGCAN_SERVICE_YUDING)
                .addParams("service_id", service_id + "")
                .addParams("booking_time", yuDingTime + "")
                .addParams("people_num", et_num.getText().toString().trim() + "")
                .addParams("contact_person", et_contact_person.getText().toString().trim())
                .addParams("contact_tel", et_contact_phone.getText().toString().trim())
                .addParams("remark", et_remark.getText().toString().trim() + "")
                .setCallBackData(new BaseNewNetModelimpl<String>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        loadingDialog.dismiss();
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<String> respnse, String source) {
                        loadingDialog.dismiss();
                        dismiss();
                        Toast.makeText(context, "预定成功", Toast.LENGTH_SHORT).show();
                        dataCallBack.yuDingSuccess();
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<String>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 添加请求到队列
     */
    public void addCall(Callback.Cancelable cancelable) {
        cancelableList.add(cancelable);
    }

   public interface DataCallBack{
        void yuDingSuccess();
    }

}
