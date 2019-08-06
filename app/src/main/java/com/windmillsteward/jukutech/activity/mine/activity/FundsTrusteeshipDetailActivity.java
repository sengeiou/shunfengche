package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.ConsultationRecordAdapter;
import com.windmillsteward.jukutech.activity.mine.adapter.OnePictureAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.FundsTrusteeshipDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipDetailBean;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：资金托管详情
 * author:cyq
 * 2018-03-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class FundsTrusteeshipDetailActivity extends BaseActivity implements FundsTrusteeshipDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView tv_order_status;
    private TextView tv_trusteeship_title;
    private TextView tv_trusteeship_module_name;
    private TextView tv_trusteeship_module_id;
    private TextView tv_trusteeship_money;
    private TextView tv_trusteeship_date;
    private TextView tv_trusteeship_mobile_phone;
    private TextView tv_trusteeship_contact;
    private LinearLayout lay_ll_informer_info;
    private TextView tv_informer_against_id;
    private TextView tv_apply_time;
    private TextView tv_report_number;
    private TextView tv_initiator;
    private LinearLayout lay_ll_informer_against_info;
    private TextView tv_description_tag;
    private TextView tv_description;
    private MyGridView gv_content;
    private LinearLayout lay_ll_one_status;
    private TextView tv_one_status;
    private TextView tv_two_status_left;
    private TextView tv_two_status_right;
    private LinearLayout lay_ll_two_status;
    private LinearLayout lay_ll_service_tel;
    private LinearLayout lay_ll_pic;
    private LinearLayout lay_ll_consultation_record;
    private TextView tv_service_tel;
    private NestedScrollView sv_content;
    private RecyclerView recycleView_content;

    private FundsTrusteeshipDetailPresenter presenter;

    private OnePictureAdapter onePictureAdapter;
    private ConsultationRecordAdapter recordAdapter;

    private int trusteeship_id;//托管id
    private String status_name = "";

    private List<FundsTrusteeshipDetailBean.ListBean> list = new ArrayList<>();


    /**
     * 跳转到资金托管详情页面
     *
     * @param context
     * @param trusteeship_id 托管id
     */
    public static void go(Context context, int trusteeship_id) {
        Intent intent = new Intent(context, FundsTrusteeshipDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(DETAIL_ID, trusteeship_id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funds_trusteeship_detail);
        initView();
        initData();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        tv_order_status = (TextView) findViewById(R.id.tv_order_status);
        tv_trusteeship_title = (TextView) findViewById(R.id.tv_trusteeship_title);
        tv_trusteeship_module_name = (TextView) findViewById(R.id.tv_trusteeship_module_name);
        tv_trusteeship_module_id = (TextView) findViewById(R.id.tv_trusteeship_module_id);
        tv_trusteeship_money = (TextView) findViewById(R.id.tv_trusteeship_money);
        tv_trusteeship_date = (TextView) findViewById(R.id.tv_trusteeship_date);
        tv_trusteeship_mobile_phone = (TextView) findViewById(R.id.tv_trusteeship_mobile_phone);
        tv_trusteeship_contact = (TextView) findViewById(R.id.tv_trusteeship_contact);
        lay_ll_informer_info = (LinearLayout) findViewById(R.id.lay_ll_informer_info);
        tv_informer_against_id = (TextView) findViewById(R.id.tv_informer_against_id);
        tv_apply_time = (TextView) findViewById(R.id.tv_apply_time);
        tv_report_number = (TextView) findViewById(R.id.tv_report_number);
        tv_initiator = (TextView) findViewById(R.id.tv_initiator);
        lay_ll_informer_against_info = (LinearLayout) findViewById(R.id.lay_ll_informer_against_info);
        tv_description_tag = (TextView) findViewById(R.id.tv_description_tag);
        tv_description = (TextView) findViewById(R.id.tv_description);
        gv_content = (MyGridView) findViewById(R.id.gv_content);
        lay_ll_one_status = (LinearLayout) findViewById(R.id.lay_ll_one_status);
        tv_one_status = (TextView) findViewById(R.id.tv_one_status);
        tv_two_status_left = (TextView) findViewById(R.id.tv_two_status_left);
        tv_two_status_right = (TextView) findViewById(R.id.tv_two_status_right);
        lay_ll_two_status = (LinearLayout) findViewById(R.id.lay_ll_two_status);
        lay_ll_service_tel = (LinearLayout) findViewById(R.id.lay_ll_service_tel);
        lay_ll_pic = (LinearLayout) findViewById(R.id.lay_ll_pic);
        lay_ll_consultation_record = (LinearLayout) findViewById(R.id.lay_ll_consultation_record);
        tv_service_tel = (TextView) findViewById(R.id.tv_service_tel);
        sv_content = (NestedScrollView) findViewById(R.id.sv_content);
        recycleView_content = (RecyclerView) findViewById(R.id.recycleView_content);

        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("托管详情");

        lay_ll_one_status.setOnClickListener(this);
        tv_one_status.setOnClickListener(this);
        tv_two_status_left.setOnClickListener(this);
        tv_two_status_right.setOnClickListener(this);

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            trusteeship_id = bundle.getInt(DETAIL_ID);
        }

        onePictureAdapter = new OnePictureAdapter(this, new ArrayList<String>());
        gv_content.setAdapter(onePictureAdapter);


        presenter = new FundsTrusteeshipDetailPresenter(this);
        presenter.getDetail(getAccessToken(), trusteeship_id);

        recycleView_content.setLayoutManager(new LinearLayoutManager(this));
        recordAdapter = new ConsultationRecordAdapter(this,list);
        recycleView_content.setAdapter(recordAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_ll_one_status:
            case R.id.tv_one_status:
                if (status_name.equals("审核中")) {
                    //审核中-取消申请
                    presenter.cancelTrusteeship(getAccessToken(), trusteeship_id);
                } else if (status_name.equals("提交申请")) {
                    //提交申请-撤回举报
                    presenter.returnTrusteeship(getAccessToken(), trusteeship_id);
                }
                break;
            case R.id.tv_two_status_left:
                if (status_name.equals("托管中")) {
                    //托管中-举报
                    ReportTrusteeshipActivity.go(this, trusteeship_id, tv_two_status_left.getText().toString());
                } else if (status_name.equals("已拒绝")) {
                    //已拒绝-撤回举报
                    presenter.returnTrusteeship(getAccessToken(), trusteeship_id);
                } else if (status_name.equals("待处理")) {
                    //待处理-同意退款
                    presenter.agreeRefund(getAccessToken(), trusteeship_id);
                } else if (status_name.equals("举证协商")) {
                    //举证协商-同意退款
                    presenter.agreeRefund(getAccessToken(), trusteeship_id);
                }
                break;
            case R.id.tv_two_status_right:
                if (status_name.equals("托管中")) {
                    //托管中-取消托管
                    presenter.cancelTrusteeship(getAccessToken(), trusteeship_id);
                } else if (status_name.equals("已拒绝")) {
                    //已拒绝-举证
                    ReportTrusteeshipActivity.go(this, trusteeship_id, tv_two_status_right.getText().toString());
                } else if (status_name.equals("待处理")) {
                    //待处理-拒绝退款
                    presenter.refuseRefund(getAccessToken(), trusteeship_id);
                } else if (status_name.equals("举证协商")) {
                    //举证协商-举证
                    ReportTrusteeshipActivity.go(this, trusteeship_id, tv_two_status_right.getText().toString());
                }
                break;
        }
    }

    /**
     * //待审核，托管中，已完成，已取消
     *
     * @param data
     */
    private void userShowData(FundsTrusteeshipDetailBean data) {
        //显示举报人信息，隐藏被举报人资料
        lay_ll_informer_info.setVisibility(View.VISIBLE);
        lay_ll_informer_against_info.setVisibility(View.GONE);

        //托管ID
        String trusteeship_id = data.getTrusteeship_id();
        tv_trusteeship_module_id.setText(TextUtils.isEmpty(trusteeship_id) ? "" : trusteeship_id);
        //托管模块
        String trusteeship_module_name = data.getTrusteeship_module_name();
        tv_trusteeship_module_name.setText(TextUtils.isEmpty(trusteeship_module_name) ? "" : trusteeship_module_name);
        //托管金额
        double trusteeship_money = data.getTrusteeship_money();
        tv_trusteeship_money.setText(trusteeship_money + "");
        //生效日期
        String start_valid_time = data.getStart_valid_time();
        String end_valid_time = data.getEnd_valid_time();
        tv_trusteeship_date.setText(start_valid_time + " 至 " + end_valid_time);
        //手机号码
        String mobile_no = data.getMobile_no();
        tv_trusteeship_mobile_phone.setText(TextUtils.isEmpty(mobile_no) ? "" : mobile_no);
        //联系人
        String contact_person = data.getContact_person();
        tv_trusteeship_contact.setText(TextUtils.isEmpty(contact_person) ? "" : contact_person);
        //描述
        String describe = data.getDescribe();
        tv_description_tag.setText("描述说明");
        tv_description.setText(TextUtils.isEmpty(describe) ? "" : describe);
        //凭证
        List<String> trusteeship_voucher = data.getTrusteeship_voucher();
        if (trusteeship_voucher != null) {
            onePictureAdapter.refreshData(trusteeship_voucher);
            lay_ll_pic.setVisibility(View.VISIBLE);
        }else{
            lay_ll_pic.setVisibility(View.GONE);
        }
        //协商记录
        List<FundsTrusteeshipDetailBean.ListBean> consultation_record = data.getConsultation_record();
        if (consultation_record != null){
            if (consultation_record.size()!=0){
                list.addAll(consultation_record);
                recordAdapter.notifyDataSetChanged();
                //显示协商记录
                lay_ll_consultation_record.setVisibility(View.VISIBLE);
            }
        }else{
            //隐藏协商记录
            lay_ll_consultation_record.setVisibility(View.GONE);
        }
    }

    /**
     * 提交申请，待处理，已拒绝，举证协商 状态对应的内容展示
     *
     * @param data
     */
    private void toUserShowData(FundsTrusteeshipDetailBean data) {
        //隐藏举报人信息，显示被举报人资料
        lay_ll_informer_info.setVisibility(View.GONE);
        lay_ll_informer_against_info.setVisibility(View.VISIBLE);
        //显示协商记录
        lay_ll_consultation_record.setVisibility(View.VISIBLE);
        //举报的托管ID
        String trusteeship_id = data.getTrusteeship_id();
        tv_informer_against_id.setText(TextUtils.isEmpty(trusteeship_id) ? "" : trusteeship_id);
        //申请时间
        long report_time = data.getReport_time();
        tv_apply_time.setText(DateUtil.StampTimeToDate(report_time + "", "yyyy-MM-dd HH:mm"));
        //举报编号
        String report_sn = data.getReport_sn();
        tv_report_number.setText(TextUtils.isEmpty(report_sn) ? "" : report_sn);
        //发起人
        String user_name = data.getReport_user_name();
        tv_initiator.setText(TextUtils.isEmpty(user_name) ? "" : user_name);
        //举报原因
        tv_description_tag.setText("举报原因");
        String consultation_reason = data.getConsultation_reason();
        tv_description.setText(TextUtils.isEmpty(consultation_reason) ? "" : consultation_reason);
        //举证图片
        List<String> report_voucher = data.getReport_voucher();
        if (report_voucher != null) {
            onePictureAdapter.refreshData(report_voucher);
            lay_ll_pic.setVisibility(View.VISIBLE);
        }
        //协商记录
        List<FundsTrusteeshipDetailBean.ListBean> consultation_record = data.getConsultation_record();
        if (consultation_record != null){
            if (consultation_record.size()!=0){
                list.addAll(consultation_record);
                recordAdapter.notifyDataSetChanged();
                //显示协商记录
                lay_ll_consultation_record.setVisibility(View.VISIBLE);
            }
        }else{
            //隐藏协商记录
            lay_ll_consultation_record.setVisibility(View.GONE);
        }
    }

    @Override
    public void getFundsTrusteeshipDetailSuccess(FundsTrusteeshipDetailBean data) {
        if (data == null) {
            return;
        }
        list.clear();
        //标题
        String trusteeship_title = data.getTrusteeship_title();
        tv_trusteeship_title.setText(TextUtils.isEmpty(trusteeship_title) ? "" : trusteeship_title);

        //客服电话
        String consumer_hotline = data.getConsumer_hotline();

        status_name = data.getStatus_name();
        tv_order_status.setText(TextUtils.isEmpty(status_name) ? "" : status_name);

        //托管状态 1待审核 2托管中 3提交申请/待处理(纠纷订单) 4已拒绝/举证协商(纠纷订单) 5已完成 6已取消 7已过期
        int trusteeship_status = data.getTrusteeship_status();

        int user_id = data.getUser_id();
        switch (status_name) {
            //待审核，托管中，已完成，已取消
            case "审核中":
                userShowData(data);
                lay_ll_one_status.setVisibility(View.VISIBLE);
                lay_ll_service_tel.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.GONE);
                tv_one_status.setText("取消申请");
                break;
            case "托管中":
                userShowData(data);
                lay_ll_one_status.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.VISIBLE);
                lay_ll_service_tel.setVisibility(View.GONE);
                tv_two_status_left.setText("举报");
                tv_two_status_right.setText("取消托管");
                if (user_id == (int)Hawk.get(Define.USER_ID,0)){
                    tv_two_status_right.setVisibility(View.VISIBLE);
                }else{
                    tv_two_status_right.setVisibility(View.GONE);
                }
                break;
            case "已完成":
                userShowData(data);
                lay_ll_service_tel.setVisibility(View.VISIBLE);
                lay_ll_one_status.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.GONE);
                tv_service_tel.setText(TextUtils.isEmpty(consumer_hotline) ? "" : "客服电话：" + consumer_hotline);
                break;
            case "已取消":
                userShowData(data);
                lay_ll_service_tel.setVisibility(View.VISIBLE);
                lay_ll_one_status.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.GONE);
                tv_service_tel.setText(TextUtils.isEmpty(consumer_hotline) ? "" : "客服电话：" + consumer_hotline);
                break;
            //提交申请，待处理，已拒绝，举证协商
            case "提交申请":
                toUserShowData(data);
                lay_ll_consultation_record.setVisibility(View.GONE);
                lay_ll_one_status.setVisibility(View.VISIBLE);
                lay_ll_service_tel.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.GONE);
                tv_one_status.setText("撤回举报");
                break;
            case "待处理":
                toUserShowData(data);
                lay_ll_consultation_record.setVisibility(View.GONE);
                lay_ll_one_status.setVisibility(View.GONE);
                lay_ll_service_tel.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.VISIBLE);
                tv_two_status_left.setText("同意退款");
                tv_two_status_right.setText("拒绝退款");
                break;
            case "已拒绝":
                toUserShowData(data);
                lay_ll_one_status.setVisibility(View.GONE);
                lay_ll_service_tel.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.VISIBLE);
                tv_two_status_left.setText("撤回举报");
                tv_two_status_right.setText("举证");
                break;
            case "举证协商":
                toUserShowData(data);
                lay_ll_one_status.setVisibility(View.GONE);
                lay_ll_service_tel.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.VISIBLE);
                tv_two_status_left.setText("同意退款");
                tv_two_status_right.setText("举证");
                break;
            case "已过期":
                userShowData(data);
                lay_ll_service_tel.setVisibility(View.VISIBLE);
                lay_ll_one_status.setVisibility(View.GONE);
                lay_ll_two_status.setVisibility(View.GONE);
                tv_service_tel.setText(TextUtils.isEmpty(consumer_hotline) ? "" : "客服电话：" + consumer_hotline);
                break;
        }

    }

    @Override
    public void operateSuccess() {
        showTips("操作成功", 1);
        finish();
    }

    @Override
    public void operateFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getFundsTrusteeshipDetailFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void uploadPicSuccess(List<String> pic_urls) {
        //用不上
    }

    @Override
    public void uploadPicFailed(int code, String msg) {
        //用不上
    }
}
