package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz;


import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.JianliDetailsModel;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 简历详情
 */
public class JianliDetailsFragment extends BaseInitFragment {
    public static final String TAG = "JianliDetailsFragment";
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;
    @Bind(R.id.tv_neirong)
    TextView tvNeirong;
    @Bind(R.id.tv_diqu)
    TextView tvDiqu;
    @Bind(R.id.tv_riqi)
    TextView tvRiqi;
    @Bind(R.id.tv_xinzi)
    TextView tvXinzi;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_pipei)
    TextView tvPipei;
    @Bind(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jianli_details;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("简历详情");

        if (getArguments() != null) {
            if (getArguments().getInt("roleType") == PageConfig.TYPE_TEZHONGGONG) {
                tvPipei.setVisibility(View.GONE);
            }
        }
    }

    public static JianliDetailsFragment newInstance(int relate_id, int roleType) {
        Bundle args = new Bundle();
        args.putInt("relate_id", relate_id);
        args.putInt("roleType", roleType);
        JianliDetailsFragment fragment = new JianliDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_RESUME_DETAIL)
                .addParams("relate_id", getArguments().getInt("relate_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<JianliDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<JianliDetailsModel> respnse, String source) {
                        dismiss();
                        showContentView();
                        final JianliDetailsModel data = respnse.getData();
                        if (data != null) {
                            GlideUtil.show(getActivity(), data.getUser_avatar_url(), ivAvatar);
                            tvTime.setText("更新时间：" + data.getAdd_time());
                            tvName.setText(data.getName() + "    " + (data.getSex() == 1 ? "男" : "女") + "|" + data.getAge() + "岁");
                            tvPhone.setText("联系电话：" + data.getContact_tel());
                            StringBuilder result = new StringBuilder();
                            for (String s : data.getWork_type_ids()) {
                                result.append(s + "、");
                            }
                            String result_str =  result.toString();
                            if (!TextUtils.isEmpty(result_str)) {
                                tvNeirong.setText(result_str.substring(0,result_str.length()-1));
                            }
                            tvDiqu.setText(data.getWork_third_area_name());
                            tvRiqi.setText(data.getWork_date());
                            tvXinzi.setText(data.getSalary_type() + "");
                            tvDesc.setText(data.getSelf_intro());

                            tvPipei.setText(new SpanUtils().append("匹配度：")
                                    .append(data.getMatch_value() + "%")
                                    .setForegroundColor(Color.parseColor("#fdbe44"))
                                    .create());

                            ivPhone.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                                    if (checkPermission(permissions)) {
                                        PhoneUtils.dial(data.getContact_tel());
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<JianliDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick(R.id.iv_phone)
    public void onViewClicked() {
    }

}
