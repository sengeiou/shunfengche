package com.windmillsteward.jukutech.manager;

import android.content.Intent;
import android.os.Bundle;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.me.TuiguangFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BmYsYesRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BmYsYesWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiaJiaoRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiaJiaoWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiRenCaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.CommHasPublicFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.CommonYiZhanResultFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.HasMatchedResumeFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.HasPublishedPositionFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.TouSuFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BaomuInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYingPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsUesAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmYsYesUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.CommBmZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.HasBmYsYesPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.HasJiajiaoPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoYingpinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.FubuQiuzhiZhaopinFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.HasZhaopinPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiYingPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiZhaoPinOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuzhiJianliFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinPositionDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.tezhonggong.TezgAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.tezhonggong.TezgCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.YinyuanOrderDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yinyuan.ZichanRenzhenFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yuersao.YuersaoAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yuersao.YuersaoCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yuesao.YuesaoAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.yuesao.YuesaoCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZdgRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZdgWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgAddUserInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgCreateRecruitFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgHasMatchedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.HasLgTzgPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.JianliDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.LwTzRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.LwTzWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.PingjiaFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.YonggongDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.ZjTzgAddWorkInfoFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.ZjTzgFabuZhaopinFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;

/**
 * Created by cretin on 2018/4/2.
 * 这里放置一些公用的页面
 */

public class CommonActivityManager extends BackFragmentActivity<Bundle> {
    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }

    @Override
    protected BaseInitFragment getFirstFragment() {
        BaseInitFragment fragment = null;
        if (ZjTzgAddWorkInfoFragment.TAG.equals(tag_fragment)) {
            fragment = ZjTzgAddWorkInfoFragment.newInstance(args.getInt("qz_id"),args.getInt("type"), args.getInt("roleType"), false);
        } else if (ZjTzgFabuZhaopinFragment.TAG.equals(tag_fragment)) {
            fragment = ZjTzgFabuZhaopinFragment.newInstance(args.getInt("qz_id"),args.getInt("type"), args.getInt("roleType"), false);
        } else if (QiuzhiJianliFragment.TAG.equals(tag_fragment)) {
            fragment = new QiuzhiJianliFragment();
        } else if (FubuQiuzhiZhaopinFragment.TAG.equals(tag_fragment)) {
            fragment = FubuQiuzhiZhaopinFragment.newInstance(args.getInt("qz_id"),args.getInt("type"),args.getInt("roleType"), false);
        } else if (ZichanRenzhenFragment.TAG.equals(tag_fragment)) {
            fragment = ZichanRenzhenFragment.newInstance(false);
        } else if (JiajiaoAddUserInfoFragment.TAG.equals(tag_fragment)) {
            fragment = JiajiaoAddUserInfoFragment.newInstance(args.getInt("qz_id"),args.getInt("type"), args.getInt("roleType"),  false);
        } else if (JiajiaoCreateRecruitFragment.TAG.equals(tag_fragment)) {
            fragment = JiajiaoCreateRecruitFragment.newInstance(args.getInt("qz_id"), args.getInt("type"),args.getInt("roleType"), false);
        } else if (CommBmYsUesAddUserInfoFragment.TAG.equals(tag_fragment)) {
            fragment = CommBmYsUesAddUserInfoFragment.newInstance(args.getInt("qz_id"),args.getInt("type"), args.getInt("roleType"),  false);
        } else if (CommBmYsYesCreateRecruitFragment.TAG.equals(tag_fragment)) {
            fragment = CommBmYsYesCreateRecruitFragment.newInstance( args.getInt("qz_id"),args.getInt("type"),args.getInt("roleType"), false);
        } else if (YuesaoAddUserInfoFragment.TAG.equals(tag_fragment)) {
            fragment = new YuesaoAddUserInfoFragment();
        } else if (YuesaoCreateRecruitFragment.TAG.equals(tag_fragment)) {
            fragment = new YuesaoCreateRecruitFragment();
        } else if (YuersaoAddUserInfoFragment.TAG.equals(tag_fragment)) {
            fragment = new YuersaoAddUserInfoFragment();
        } else if (YuersaoCreateRecruitFragment.TAG.equals(tag_fragment)) {
            fragment = new YuersaoCreateRecruitFragment();
        } else if (ZhongdgAddUserInfoFragment.TAG.equals(tag_fragment)) {
            fragment = ZhongdgAddUserInfoFragment.newInstance(args.getInt("qz_id"),args.getInt("type"), args.getInt("roleType"), false);
        } else if (ZhongdgCreateRecruitFragment.TAG.equals(tag_fragment)) {
            fragment = ZhongdgCreateRecruitFragment.newInstance(args.getInt("qz_id"),args.getInt("type"),args.getInt("roleType"), false);
        } else if (TezgCreateRecruitFragment.TAG.equals(tag_fragment)) {
            fragment = new TezgCreateRecruitFragment();
        } else if (ZhaopinInfoDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = ZhaopinInfoDetailsFragment.newInstance(args.getInt("job_resume_id"));
        } else if (TezgAddUserInfoFragment.TAG.equals(tag_fragment)) {
            fragment = new TezgAddUserInfoFragment();
        } else if (CommonYiZhanResultFragment.TAG.equals(tag_fragment)) {
            fragment = CommonYiZhanResultFragment.newInstance(args.getInt("roleType"));
        } else if (CommHasPublicFragment.TAG.equals(tag_fragment)) {
            fragment = CommHasPublicFragment.newInstance(args.getInt("type"), args.getInt("num"));
        } else if (HasLgTzgPublishedDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = HasLgTzgPublishedDetailsFragment.newInstance(args.getInt("recruitment_id"), args.getInt("roleType"), args.getInt("showAddress"));
        } else if (HasBmYsYesPublishedDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = HasBmYsYesPublishedDetailsFragment.newInstance(args.getInt("recruitment_id"), args.getInt("roleType"), args.getInt("showAddress"));
        } else if (HasJiajiaoPublishedDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = HasJiajiaoPublishedDetailsFragment.newInstance(args.getInt("look_for_tutor_id"), args.getInt("showAddress"));
        } else if (HasZhaopinPublishedDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = HasZhaopinPublishedDetailsFragment.newInstance(args.getInt("job_id"), args.getInt("showAddress"));
        } else if (ZhongdgUsePersonFragment.TAG.equals(tag_fragment)) {
            fragment = ZhongdgUsePersonFragment.newInstance(args.getInt("lookfor_bell_worker_id"), args.getInt("showAddress"));
        } else if (YonggongDetailFragment.TAG.equals(tag_fragment)) {
            fragment = YonggongDetailFragment.newInstance(args.getInt("relate_id"));
        } else if (ZhaopinPositionDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = ZhaopinPositionDetailsFragment.newInstance(args.getInt("job_resume_id"));
        } else if (JiajiaoUsePersonFragment.TAG.equals(tag_fragment)) {
            fragment = JiajiaoUsePersonFragment.newInstance(args.getInt("look_for_tutor_id"));
        } else if (CommBmYsYesUsePersonFragment.TAG.equals(tag_fragment)) {
            fragment = CommBmYsYesUsePersonFragment.newInstance(0, args.getInt("recruitment_id"));
        } else if (ZhongdgInfoDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = ZhongdgInfoDetailsFragment.newInstance(args.getInt("hour_matching_id"));
        } else if (HasPublishedPositionFragment.TAG.equals(tag_fragment)) {
            fragment = HasPublishedPositionFragment.newInstance(args.getInt("roleType"));
        } else if (HasMatchedResumeFragment.TAG.equals(tag_fragment)) {
            fragment = HasMatchedResumeFragment.newInstance(args.getInt("roleType"));
        } else if (JianliDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = JianliDetailsFragment.newInstance(args.getInt("relate_id"), args.getInt("roleType"));
        } else if (JiajiaoInfoDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = JiajiaoInfoDetailsFragment.newInstance(args.getInt("when_tutor_id"));
        } else if (BaomuInfoDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = BaomuInfoDetailsFragment.newInstance(args.getInt("require_id"), args.getInt("roleType"));
        } else if (ZhongdgHasMatchedDetailsFragment.TAG.equals(tag_fragment)) {
            fragment = ZhongdgHasMatchedDetailsFragment.newInstance(args.getInt("hour_matching_id"));
        } else if (TuiguangFragment.TAG.equals(tag_fragment)) {
            fragment = new TuiguangFragment();
        } else if (TouSuFragment.TAG.equals(tag_fragment)) {
            fragment = TouSuFragment.newInstance(args.getInt("roleType"), args.getInt("relate_id"));
        }else if (PingjiaFragment.TAG.equals(tag_fragment)) {
            fragment = PingjiaFragment.newInstance(args.getInt("roleType"), args.getInt("relate_id"));
        }else if (QiuZhiYingPinOrderDetailFragment.TAG.equals(tag_fragment)) {
            fragment = QiuZhiYingPinOrderDetailFragment.newInstance(args.getInt("relate_id"));
        }else if (JiajiaoYingpinOrderDetailFragment.TAG.equals(tag_fragment)) {
            fragment = JiajiaoYingpinOrderDetailFragment.newInstance(args.getInt("when_tutor_id"));
        }else if (CommBmYingPinOrderDetailFragment.TAG.equals(tag_fragment)) {
            fragment = CommBmYingPinOrderDetailFragment.newInstance(args.getInt("relate_id"),args.getInt("roleType"));
        }else if (YinyuanOrderDetailFragment.TAG.equals(tag_fragment)) {
            fragment = YinyuanOrderDetailFragment.newInstance(args.getInt("relate_id"));
        }else if (QiuZhiZhaoPinOrderDetailFragment.TAG.equals(tag_fragment)) {
            fragment = QiuZhiZhaoPinOrderDetailFragment.newInstance(args.getInt("relate_id"));
        }else if (JiajiaoZhaoPinOrderDetailFragment.TAG.equals(tag_fragment)) {
            fragment = JiajiaoZhaoPinOrderDetailFragment.newInstance(args.getInt("relate_id"));
        }else if (CommBmZhaoPinOrderDetailFragment.TAG.equals(tag_fragment)) {
            fragment = CommBmZhaoPinOrderDetailFragment.newInstance(args.getInt("relate_id"),args.getInt("roleType"));
        }else if (QiuZhiWorkListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = QiuZhiWorkListDetailFragment.newInstance(args.getInt("job_id"));
        }else if (QiuZhiRenCaiListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = QiuZhiRenCaiListDetailFragment.newInstance(args.getInt("resume_id"));
        }else if (LwTzWorkListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = LwTzWorkListDetailFragment.newInstance(args.getInt("recruitment_id"),args.getInt("roleType"));
        }else if (LwTzRencaiListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = LwTzRencaiListDetailFragment.newInstance(args.getInt("info_id"),args.getInt("roleType"));
        }else if (JiaJiaoWorkListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = JiaJiaoWorkListDetailFragment.newInstance(args.getInt("look_for_tutor_id"));
        }else if (JiaJiaoRencaiListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = JiaJiaoRencaiListDetailFragment.newInstance(args.getInt("when_tutor_id"));
        }else if (BmYsYesRencaiListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = BmYsYesRencaiListDetailFragment.newInstance(args.getInt("require_id"),args.getInt("roleType"));
        }else if (BmYsYesWorkListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = BmYsYesWorkListDetailFragment.newInstance(args.getInt("recruitment_id"),args.getInt("roleType"));
        }else if (ZdgWorkListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = ZdgWorkListDetailFragment.newInstance(args.getInt("lookfor_bell_worker_id"));
        }else if (ZdgRencaiListDetailFragment.TAG.equals(tag_fragment)) {
            fragment = ZdgRencaiListDetailFragment.newInstance(args.getInt("when_bell_worker_id"));
        }
        return fragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}