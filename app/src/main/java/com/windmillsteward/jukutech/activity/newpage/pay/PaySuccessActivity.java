package com.windmillsteward.jukutech.activity.newpage.pay;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListNewActivity;
import com.windmillsteward.jukutech.activity.newpage.newpublish.WorkAndRencaiInfoActivity;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.StringUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class PaySuccessActivity extends BaseInitActivity {

    @Bind(R.id.iv_pay)
    ImageView ivPay;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_back_btn)
    TextView tvBackBtn;
    private int type;
    private int relate_id;
    private String longitude;
    private String latitude;

    @Override
    protected void initView(View view) {
        setMainTitle("支付结果");
        showContentView();

        tvTime.setVisibility(View.VISIBLE);
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        relate_id = intent.getIntExtra("relate_id", 0);
        String longLat = KV.get(Define.CURR_LONGLAT_ADDRESS, "");
        if (!StringUtil.isEmpty(longLat)) {
            longitude = longLat.split(",")[0];
            latitude = longLat.split(",")[1];
        }
        TextView leftBackView = getLeftBackView();
        leftBackView.setVisibility(View.VISIBLE);
        leftBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToOrderFragment();
            }
        });
    }

    private void backToOrderFragment(){
        MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
        if (activity != null){
            activity.changeButtonStatus(2);
        }
        TalentInnListNewActivity activity1 = (TalentInnListNewActivity) AppManager.getAppManager().getActivity(TalentInnListNewActivity.class);
        if (activity1 != null){
            activity1.finish();
        }
        WorkAndRencaiInfoActivity activity2 = (WorkAndRencaiInfoActivity) AppManager.getAppManager().getActivity(WorkAndRencaiInfoActivity.class);
        if (activity2 != null){
            activity2.finish();
        }
        finish();
    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick(R.id.tv_back_btn)
    public void onViewClicked() {
        backToOrderFragment();
    }

    private CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            tvTime.setText(new SpanUtils().append(value + "s")
                    .setForegroundColor(ResUtils.getCommRed())
                    .append(" 后自动关闭页面")
                    .create());
        }

        @Override
        public void onFinish() {
            backToOrderFragment();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
