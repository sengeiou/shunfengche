package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.SingleSelectAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.DeleteWithDrawPresenter;
import com.windmillsteward.jukutech.activity.mine.presenter.WithdrawAccountPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;
import com.windmillsteward.jukutech.bean.WithdrawBean;
import com.windmillsteward.jukutech.customview.MyListView;
import com.windmillsteward.jukutech.customview.dialog.SelectTwoDialog;
import com.windmillsteward.jukutech.utils.StateButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：转出方式管理
 * author:cyq
 * 2018/3/6
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ManageWithDrawActivity extends BaseActivity implements View.OnClickListener, DeleteWithDrawView,
        WithdrawAccountView, SelectTwoDialog.DialogListner, SingleSelectAdapter.DataCallBack {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private MyListView lv_content;
    private StateButton btn_add;
    private StateButton btn_add_bank;

    private DeleteWithDrawPresenter deleteWithDrawPresenter;
    private WithdrawAccountPresenter withdrawAccountPresenter;

    private SingleSelectAdapter singleSelectAdapter;

    private SelectTwoDialog selectTwoDialog;

    private int account_id;//提现id
    private ArrayList<PublicSelectInfo> publicSelectList = new ArrayList<>();//提现列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_withdraw);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        btn_add = (StateButton) findViewById(R.id.btn_add);
        btn_add_bank = (StateButton) findViewById(R.id.btn_add_bank);
        lv_content = (MyListView) findViewById(R.id.lv_content);

        toolbar_iv_title.setText("管理提现账户");
        handleBackEvent(toolbar_iv_back);

        btn_add.setOnClickListener(this);
        btn_add_bank.setOnClickListener(this);
    }

    private void initData() {
        singleSelectAdapter = new SingleSelectAdapter(this, new ArrayList<PublicSelectInfo>(), this);
        lv_content.setAdapter(singleSelectAdapter);

        selectTwoDialog = new SelectTwoDialog(this, 1, this, "", "解绑账户", "取消");

        deleteWithDrawPresenter = new DeleteWithDrawPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        withdrawAccountPresenter = new WithdrawAccountPresenter(this);
        withdrawAccountPresenter.getWithdrawAccountList(getAccessToken());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                startAtvDonFinish(AddWithdrawAccountActivity.class);
                break;
            case R.id.btn_add_bank:
                startAtvDonFinish(AddBankAccountActivity.class);
                break;
        }
    }

    @Override
    public void selectDialogIndex(int action, int positon) {
        if (positon == 2) {
            deleteWithDrawPresenter.deleteAccount(account_id, getAccessToken());
            selectTwoDialog.dismiss();
        } else {
            selectTwoDialog.dismiss();
        }
    }

    @Override
    public void deleteSuccess() {
        showTips("解绑成功", 1);
        withdrawAccountPresenter.getWithdrawAccountList(getAccessToken());//解绑完成后，重新刷新列表
    }

    @Override
    public void getWithdrawAccountListSuccess(List<WithdrawBean> list) {
        if (list == null) {
            return;
        }
        publicSelectList.clear();
        for (WithdrawBean listBean : list) {
            PublicSelectInfo info = new PublicSelectInfo();
            info.setId(listBean.getAccount_id());
            info.setName(listBean.getAccount());
            info.setType(listBean.getType());
            publicSelectList.add(info);
        }
        singleSelectAdapter.refreshList(publicSelectList);
    }

    @Override
    public void deleteFailed(String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getWithdrawAccountListFailed(String msg) {
        showTips(msg, 1);
    }

    @Override
    public void onItemClick(int tagId, int id, int type, String values2) {
        this.account_id = id;
        selectTwoDialog.showAtBottom();
        selectTwoDialog.hideOneButton();
    }
}
