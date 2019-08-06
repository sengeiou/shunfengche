package com.windmillsteward.jukutech.activity.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.EaseConstant;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonPayPresenter;
import com.windmillsteward.jukutech.activity.newpage.pay.RedPacketPayActivity;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.RedPacketPayModel;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.RegexChkUtil;
import com.windmillsteward.jukutech.utils.StateButton;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述：发红包
 * author:cyq
 * 2019/4/19
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */
public class SendRedPacketActivity extends BaseInitActivity {

    public static final String CHAT_TYPE = "chat_type";

    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.tv_show_money)
    TextView tvShowMoney;
    @Bind(R.id.et_num)
    EditText etNum;
    @Bind(R.id.et_desc)
    EditText etDesc;
    @Bind(R.id.btn_commit)
    StateButton btnCommit;
    @Bind(R.id.tv_money_tips)
    TextView tvMoneyTips;
    @Bind(R.id.lay_ll_number)
    LinearLayout layLlNumber;
    @Bind(R.id.tv_hb_type_tips)
    TextView tvHbTypeTips;
    @Bind(R.id.tv_hb_type)
    TextView tvHbType;

    private int chatType;
    private int redPacketType = 1;//红包类型 1拼手气2普通
    private double norHbDouble;//普通红包的单价

    @Override
    protected void initView(View view) {
        showContentView();
        setMainTitle("发红包");
        //先
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable edt) {

                tvShowMoney.setText(edt.toString());
                if (TextUtils.isEmpty(edt.toString())) {
                    return;
                }
                if (redPacketType == 2) {
                    calmEnter(etMoney.getText().toString(),etNum.getText().toString());
                }
                int posDot = edt.toString().indexOf(".");//只允许输入两位小数
                if (posDot <= 0) {
                    return;
                }
                if (edt.toString().length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }

            }
        });
        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable edt) {
                String num = edt.toString();
                if (TextUtils.isEmpty(num)) {
                    return;
                }
                if (redPacketType == 2) {
                    calmEnter(etMoney.getText().toString(),num);
                }
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_send_red_packet;
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            chatType = getIntent().getIntExtra(CHAT_TYPE, 0);
            if (chatType == EaseConstant.CHATTYPE_SINGLE) {
                layLlNumber.setVisibility(View.GONE);
                tvMoneyTips.setText("单个金额");
            } else {
                layLlNumber.setVisibility(View.VISIBLE);
                tvMoneyTips.setText("总金额");
                tvHbTypeTips.setText("当前为拼手气红包，");
                tvHbType.setText("改为普通红包");

            }
        }
    }

    @Override
    protected void refreshPageData() {

    }


    @OnClick({R.id.btn_commit, R.id.tv_hb_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                commit();
                break;
            case R.id.tv_hb_type:
                if (redPacketType == 1) {//随机红包切换到普通红包，先把之前的总价算出来，然后除以红包个数，总金额不变
                    tvHbTypeTips.setText("当前为普通红包，");
                    tvHbType.setText("改为拼手气红包");
                    tvMoneyTips.setText("单个金额");
                    redPacketType = 2;
                    if (TextUtils.isEmpty(etNum.getText().toString())) {
                        return;
                    }
                    if (TextUtils.isEmpty(etMoney.getText().toString())) {
                        return;
                    }
                    calmMoney();
                    etMoney.setText(norHbDouble + "");
                } else if (redPacketType == 2) {
                    tvHbTypeTips.setText("当前为拼手气红包，");
                    tvHbType.setText("改为普通红包");
                    tvMoneyTips.setText("总金额");
                    redPacketType = 1;
                    if (TextUtils.isEmpty(etNum.getText().toString())) {
                        return;
                    }
                    if (TextUtils.isEmpty(etMoney.getText().toString())) {
                        return;
                    }
                    calmMoney();
                    etMoney.setText(norHbDouble + "");
                }
                break;

        }
    }

    /**
     * 当为普通红包时，才需要进来，重新计算
     */
    private void calmEnter(String money,String num){
        if (!TextUtils.isEmpty(money) && !TextUtils.isEmpty(num)) {
            double money_double = Double.parseDouble(money);
            double num_double = Double.parseDouble(num);
            double vv = money_double*num_double;
            BigDecimal bg = new BigDecimal(vv);
            double finalMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            tvShowMoney.setText(finalMoney + "");
        }
    }

    /**
     * 切换普通红包和拼手气红包时，重新计算
     */
    private void calmMoney() {
        String num = etNum.getText().toString();
        String money = etMoney.getText().toString();
        String totalMoney = tvShowMoney.getText().toString();
        if (TextUtils.isEmpty(etNum.getText().toString())) {
            return;
        }
        if (TextUtils.isEmpty(etMoney.getText().toString())) {
            return;
        }
        double dou_num = 0;
        double dou_money = 0;
        double dou_totalMoney = 0;

        boolean isNumeric = RegexChkUtil.isNumeric(num);
        boolean isMoneyric = RegexChkUtil.isNumeric(money);
        boolean isTotalMoneyric = RegexChkUtil.isNumeric(totalMoney);
        if (isNumeric) {
            dou_num = Double.parseDouble(num);
        }
        if (isMoneyric) {
            dou_money = Double.parseDouble(money);
        }
        if (isTotalMoneyric) {
            dou_totalMoney = Double.parseDouble(totalMoney);
        }
        double f = 0;
        if (redPacketType == 1) {//拼手气
            BigDecimal bg = new BigDecimal(dou_totalMoney);
            norHbDouble = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            tvShowMoney.setText(norHbDouble + "");
        } else if (redPacketType == 2) {//普通
            f = dou_totalMoney / dou_num;
            BigDecimal bg = new BigDecimal(f);
            norHbDouble = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double v = norHbDouble * dou_num;
            BigDecimal bg1 = new BigDecimal(v);
            double finalMoney = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            tvShowMoney.setText(finalMoney + "");
        }
    }


    private void commit() {
        String money = etMoney.getText().toString();
        if (TextUtils.isEmpty(money)) {
            showTips("请输入红包金额");
            return;
        }


        String num = etNum.getText().toString();
        if (chatType == EaseConstant.CHATTYPE_SINGLE) {
            num = "1";
            if (Double.parseDouble(money) > 200) {
                showTips("红包金额不能超过200");
                return;
            }
            ;
        } else {
            if (TextUtils.isEmpty(num)) {
                showTips("请输入红包个数");
                return;
            }
        }
        String input_desc = etDesc.getText().toString();
        if (TextUtils.isEmpty(input_desc)) {
            input_desc = "恭喜发财，大吉大利";
        }
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_SEND_RED_PACKET)
                .addParams("money", money)
                .addParams("packet_desc", input_desc)
                .addParams("type", redPacketType + "")
                .addParams("num", num)
                .setCallBackData(new BaseNewNetModelimpl<RedPacketPayModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<RedPacketPayModel> respnse, String source) {
                        dismiss();
                        RedPacketPayModel resultModel = respnse.getData();
                        if (resultModel != null) {
                            RedPacketPayActivity.go(SendRedPacketActivity.this,
                                    CommonPayPresenter.TYPE_FA_HONGBAO, resultModel.getRelate_id(),
                                    resultModel.getOrder_price() + "",
                                    resultModel.getOrder_name(), resultModel.getPacket_desc(), resultModel);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<RedPacketPayModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RedPacketPayActivity.REQUEST_CODE && resultCode == RedPacketPayActivity.RESULT_CODE_SUCCESS) {
            if (data != null) {
//                int packet_id = data.getIntExtra(EaseConstant.RED_PACKET_ID, 0);
//                String packet_desc = data.getStringExtra(EaseConstant.PACKET_DESC);
                RedPacketPayModel resultModel = (RedPacketPayModel) data.getSerializableExtra(EaseConstant.RED_PACKET_RESULT_MODEL);
                Intent intent = new Intent();
                if (resultModel != null) {
                    intent.putExtra(EaseConstant.RED_PACKET_RESULT_MODEL, resultModel);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

}
