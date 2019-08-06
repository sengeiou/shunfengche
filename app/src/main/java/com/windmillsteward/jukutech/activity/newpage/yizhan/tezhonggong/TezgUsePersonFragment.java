package com.windmillsteward.jukutech.activity.newpage.yizhan.tezhonggong;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 特种工-用工明细
 */
public class TezgUsePersonFragment extends BaseInitFragment {
    public static final String TAG = "TezgUsePersonFragment";

    @Bind(R.id.tv_bianhao)
    TextView tvBianhao;
    @Bind(R.id.tv_bianti)
    TextView tvBianti;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_pipeishijian)
    TextView tvPipeishijian;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_finish)
    TextView tvFinish;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tezg_use_person;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("用工明细");
        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.tv_address, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                break;
            case R.id.tv_finish:
                break;
        }
    }
}
