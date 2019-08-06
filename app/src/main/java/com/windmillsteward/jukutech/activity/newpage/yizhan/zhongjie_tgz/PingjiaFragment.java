package com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BaomuInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgUsePersonFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.event.NotifyPageRefresh;
import com.windmillsteward.jukutech.interfaces.APIS;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 评价
 */
public class PingjiaFragment extends BaseInitFragment {
    public static final String TAG = "PingjiaFragment";
    @Bind(R.id.iv_01)
    ImageView iv01;
    @Bind(R.id.iv_02)
    ImageView iv02;
    @Bind(R.id.iv_03)
    ImageView iv03;
    @Bind(R.id.iv_04)
    ImageView iv04;
    @Bind(R.id.iv_05)
    ImageView iv05;
    @Bind(R.id.iv_001)
    ImageView iv001;
    @Bind(R.id.iv_002)
    ImageView iv002;
    @Bind(R.id.iv_003)
    ImageView iv003;
    @Bind(R.id.iv_004)
    ImageView iv004;
    @Bind(R.id.iv_005)
    ImageView iv005;
    @Bind(R.id.et_desc)
    EditText etDesc;
    @Bind(R.id.tv_commit)
    TextView tvCommit;

    private int service_evaluation, skill_evaluation;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_pingjia;
    }

    public static PingjiaFragment newInstance(int roleType, int relate_id) {
        Bundle args = new Bundle();
        args.putInt("relate_id", relate_id);
        args.putInt("roleType", roleType);
        PingjiaFragment fragment = new PingjiaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("评价");
//        ((BackFragmentActivity) getActivity()).addFragment(PingjiaFragment.newInstance(PageConfig.TYPE_BAOMU, getArguments()
//                .getInt("require_id")), true, true);

        showContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    @OnClick({R.id.iv_01, R.id.iv_02, R.id.iv_03, R.id.iv_04, R.id.iv_05, R.id.iv_001, R.id.iv_002, R.id.iv_003, R.id.iv_004, R.id.iv_005, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_01:
                clickServiceStars(1);
                break;
            case R.id.iv_02:
                clickServiceStars(2);
                break;
            case R.id.iv_03:
                clickServiceStars(3);
                break;
            case R.id.iv_04:
                clickServiceStars(4);
                break;
            case R.id.iv_05:
                clickServiceStars(5);
                break;
            case R.id.iv_001:
                clickSkillStars(1);
                break;
            case R.id.iv_002:
                clickSkillStars(2);
                break;
            case R.id.iv_003:
                clickSkillStars(3);
                break;
            case R.id.iv_004:
                clickSkillStars(4);
                break;
            case R.id.iv_005:
                clickSkillStars(5);
                break;
            case R.id.tv_commit:
                String content = etDesc.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    showTips("请输入内容");
                    return;
                }
                evaluation(getArguments().getInt("relate_id") + "", service_evaluation, skill_evaluation, content);
                break;
        }
    }

    private void clickServiceStars(int position) {
        service_evaluation = position;
        iv01.setImageResource(R.mipmap.starts_unselect);
        iv01.setImageResource(R.mipmap.starts_unselect);
        iv02.setImageResource(R.mipmap.starts_unselect);
        iv03.setImageResource(R.mipmap.starts_unselect);
        iv04.setImageResource(R.mipmap.starts_unselect);
        iv05.setImageResource(R.mipmap.starts_unselect);

        if (position == 1) {
            iv01.setImageResource(R.mipmap.starts_selected);
            iv01.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 2) {
            iv01.setImageResource(R.mipmap.starts_selected);
            iv02.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 3) {
            iv01.setImageResource(R.mipmap.starts_selected);
            iv02.setImageResource(R.mipmap.starts_selected);
            iv03.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 4) {
            iv01.setImageResource(R.mipmap.starts_selected);
            iv02.setImageResource(R.mipmap.starts_selected);
            iv03.setImageResource(R.mipmap.starts_selected);
            iv04.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 5) {
            iv01.setImageResource(R.mipmap.starts_selected);
            iv02.setImageResource(R.mipmap.starts_selected);
            iv03.setImageResource(R.mipmap.starts_selected);
            iv04.setImageResource(R.mipmap.starts_selected);
            iv05.setImageResource(R.mipmap.starts_selected);
        }
    }

    private void clickSkillStars(int position) {
        skill_evaluation = position;
        iv001.setImageResource(R.mipmap.starts_unselect);
        iv002.setImageResource(R.mipmap.starts_unselect);
        iv003.setImageResource(R.mipmap.starts_unselect);
        iv004.setImageResource(R.mipmap.starts_unselect);
        iv005.setImageResource(R.mipmap.starts_unselect);

        if (position == 1) {
            iv001.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 2) {
            iv001.setImageResource(R.mipmap.starts_selected);
            iv002.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 3) {
            iv001.setImageResource(R.mipmap.starts_selected);
            iv002.setImageResource(R.mipmap.starts_selected);
            iv003.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 4) {
            iv001.setImageResource(R.mipmap.starts_selected);
            iv002.setImageResource(R.mipmap.starts_selected);
            iv003.setImageResource(R.mipmap.starts_selected);
            iv004.setImageResource(R.mipmap.starts_selected);
        }
        if (position == 5) {
            iv001.setImageResource(R.mipmap.starts_selected);
            iv002.setImageResource(R.mipmap.starts_selected);
            iv003.setImageResource(R.mipmap.starts_selected);
            iv004.setImageResource(R.mipmap.starts_selected);
            iv005.setImageResource(R.mipmap.starts_selected);
        }
    }

    //评价
    private void evaluation(String relate_id, int service_evaluation, int skill_evaluation, String evaluation_text) {
        showDialog();
        final int roleType = getArguments().getInt("roleType");
        NetUtil netUtil = null;
        String key = "";
        if (roleType == PageConfig.TYPE_ZHONGJIE ||
                roleType == PageConfig.TYPE_TEZHONGGONG) {
            netUtil = new NetUtil().setUrl(APIS.URL_TALENT_EVALUATION);
            key = "relate_id";
        } else if (roleType == PageConfig.TYPE_BAOMU ||
                roleType == PageConfig.TYPE_YUESAO ||
                roleType == PageConfig.TYPE_YUERSAO) {
            netUtil = new NetUtil().setUrl(APIS.URL_TALENT_EVALUATION_BM);
            key = "require_id";
        } else if (roleType == PageConfig.TYPE_JIAJIAO) {
            netUtil = new NetUtil().setUrl(APIS.URL_TALENT_EVALUATION_JIAJIAO);
            key = "when_tutor_id";
        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
            netUtil = new NetUtil().setUrl(APIS.URL_TALENT_EVALUATION_ZDG);
            key = "hour_matching_id";
        }else if (roleType == PageConfig.TYPE_SMART) {
            netUtil = new NetUtil().setUrl(APIS.URL_SMART_EVALUATION);
            key = "require_id";
        }else if (roleType == PageConfig.TYPE_ZHAOPIN) {
            netUtil = new NetUtil().setUrl(APIS.URL_RESUME_EVALUATION);
            key = "job_resume_id";
        }
        addCall(netUtil
                .addParams(key, relate_id)
                .addParams("service_evaluation", service_evaluation + "")
                .addParams("skill_evaluation", skill_evaluation + "")
                .addParams("evaluation_text", evaluation_text)
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse.getCode() == 0) {
                            //评价成功
                            showTips("评价成功");

                            if (roleType == PageConfig.TYPE_ZHONGJIE ||
                                    roleType == PageConfig.TYPE_TEZHONGGONG) {
                                EventBus.getDefault().post(new NotifyPageRefresh(HasLgTzgPublishedDetailsFragment.TAG));
                            } else if (roleType == PageConfig.TYPE_BAOMU ||
                                    roleType == PageConfig.TYPE_YUESAO ||
                                    roleType == PageConfig.TYPE_YUERSAO) {
                                EventBus.getDefault().post(new NotifyPageRefresh(BaomuInfoDetailsFragment.TAG));
                            } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                                EventBus.getDefault().post(new NotifyPageRefresh(JiajiaoInfoDetailsFragment.TAG));
                            } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                                EventBus.getDefault().post(new NotifyPageRefresh(ZhongdgUsePersonFragment.TAG));
                            }

                            ((BackFragmentActivity) getHoldingActivity()).removeFragment();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }
}
