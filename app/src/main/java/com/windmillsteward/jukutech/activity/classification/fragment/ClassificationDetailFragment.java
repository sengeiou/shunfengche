package com.windmillsteward.jukutech.activity.classification.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.classification.adapter.MyGridViewAdapter;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.ClassificationModel;
import com.windmillsteward.jukutech.activity.newpage.model.HasPublicRectuitmentModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForCommBaomu;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForJiajiaoModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForLgAndTzg;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForYinyuanModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhaopinModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhongdgModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class ClassificationDetailFragment extends BaseInitFragment {

    private static final String TYPE = "TYPE";
    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private ArrayList<ClassificationModel.ListBean> list;
    private RecyclerViewAdapter adapter;
    private int type;

    private HasPublicRectuitmentModel hasPublicRectuitmentModel;//右边上一次发布实体
    private LastPublicForLgAndTzg lastPublicForLgAndTzg;//劳务工-我要找工作
    private LastPublicForZhaopinModel lastPublicForZhaopinModel;//求职-我要求职
    private LastPublicForYinyuanModel lastPublicForYinyuanModel;//姻缘
    private LastPublicForJiajiaoModel lastPublicForJiajiaoModel;//家教-我要找家教
    private LastPublicForCommBaomu lastPublicForCommBaomu;//保姆月嫂育儿嫂--我要找工作
    private LastPublicForZhongdgModel lastPublicForZhongdgModel;//钟点工--我要找工作

    /**
     * @param type 0-10 分别是首页那10 个模块
     * @return
     */
    public static ClassificationDetailFragment getInstance(int type, ArrayList<ClassificationModel.ListBean> listBeans) {
        ClassificationDetailFragment fragment = new ClassificationDetailFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        args.putParcelableArrayList("bean", listBeans);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_class_detail;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        hidTitleView();
        showContentView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt(TYPE);
            list = arguments.getParcelableArrayList("bean");
        }
        initAdapter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

    public void initAdapter() {
        adapter = new RecyclerViewAdapter(list);
        adapter.setOnItemClickListener(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(false);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<ClassificationModel.ListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<ClassificationModel.ListBean> data) {
            super(R.layout.view_mygrvideview, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ClassificationModel.ListBean item) {
            MyGridView myGridView = helper.getView(R.id.myGridView);
            helper.setText(R.id.tv_class, item.getName());
            if (type == 1){
                myGridView.setNumColumns(3);
            }else if (type== 2){
                myGridView.setNumColumns(3);
            }else if (type == 0){
                myGridView.setNumColumns(2);
            }
            myGridView.setAdapter(new MyGridViewAdapter(getActivity(), item.getClass_list(), type));

            myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ClassificationModel.ListBean.ClassListBean item = (ClassificationModel.ListBean.ClassListBean) parent.getAdapter().getItem(position);
                    if (type == 1) {
                        //智慧生活
                        Intent intent = new Intent(getActivity(), IntelligentFamilyListActivity.class);
                        intent.putExtra(IntelligentFamilyListActivity.CURR_CLASS, item.getRequire_class_id());
                        intent.putExtra(IntelligentFamilyListActivity.CURR_NAME, item.getClass_name());
                        getActivity().startActivity(intent);
                    } else if (type == 2) {
                        //房屋信息
                        HouseRentingActivity.go(getActivity(),"",item.getHouse_type_id(),item.getHouse_type_name());
                    } else if (type == 0) {
                        int types = item.getType();
                        if (types == 1) {
                            //劳务中介 我要找工作
                            authenAndJump(PageConfig.TYPE_ZHONGJIE,0);
                        } else if (types == 2) {
                            //劳务中介 我要找人
                            authenAndJump(PageConfig.TYPE_ZHONGJIE,1);
                        } else if (types == 3) {
                            //求职招聘 我要求职
                            authenAndJump(PageConfig.TYPE_ZHAOPIN,0);
                        } else if (types == 4) {
                            //求职招聘 我要找人
                            authenAndJump(PageConfig.TYPE_ZHAOPIN,1);
                        } else if (types == 5) {
                            //姻缘服务
                            authenAndJump(PageConfig.TYPE_YINYUAN,0);
                        } else if (types == 6) {
                            //家教 我要当家教
                            authenAndJump(PageConfig.TYPE_JIAJIAO,0);
                        } else if (types == 7) {
                            //家教 我要找家教
                            authenAndJump(PageConfig.TYPE_JIAJIAO,1);
                        } else if (types == 8) {
                            //我要当保姆
                            authenAndJump(PageConfig.TYPE_BAOMU,0);
                        } else if (types == 9) {
                            //我要找保姆
                            authenAndJump(PageConfig.TYPE_JIAJIAO,1);
                        } else if (types == 10) {
                            //我要当月嫂
                            authenAndJump(PageConfig.TYPE_YUESAO,0);
                        } else if (types == 11) {
                            //我要找月嫂
                            authenAndJump(PageConfig.TYPE_YUESAO,1);
                        } else if (types == 12) {
                            //我要当育儿嫂
                            authenAndJump(PageConfig.TYPE_YUERSAO,0);
                        } else if (types == 13) {
                            //我要找育儿嫂
                            authenAndJump(PageConfig.TYPE_YUERSAO,1);
                        } else if (types == 14) {
                            //钟点工 我要当钟点工
                            authenAndJump(PageConfig.TYPE_ZHONGDIANGONG,0);
                        } else if (types == 15) {
                            //钟点工 我要找钟点工
                            authenAndJump(PageConfig.TYPE_ZHONGDIANGONG,1);
                        } else if (types == 16) {
                            //特种工 我要找工作
                            authenAndJump(PageConfig.TYPE_TEZHONGGONG,0);
                        } else if (types == 17) {
                            //特种工 我要找特种工
                            authenAndJump(PageConfig.TYPE_TEZHONGGONG,1);
                        }
                    }
                }
            });
        }
    }

    //人才驿站，房屋信息，智慧生活分类跳转，需先判断是否认证，然后判断是否发布过
    //没有发布过,add_or_edit_type传0，发布过传true
    //没有发布过,isOnly传false，发不过传true
    public void authenAndJump(final int roleType, final int showPage) {
        if (TextUtils.isEmpty(getAccessToken())) {
            startAtvDonFinish(LoginActivity.class);
        } else {
//            getHoldingActivity().checkUserAuthen(new OnUserAuthenListener() {
//                @Override
//                public void isAuthen() {
//                    Intent intent;
//                    switch (roleType) {
//                        case PageConfig.TYPE_ZHONGJIE:
//                            openLwzjTzgLeft(roleType);
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_ZHONGJIE);
//                            if (lastPublicForLgAndTzg == null || lastPublicForLgAndTzg.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_ZHAOPIN:
//                            openQzzp();
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_ZHAOPIN);
//                            if (lastPublicForZhaopinModel == null || lastPublicForZhaopinModel.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_YINYUAN:
//                            openYyfw();
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_YINYUAN);
//                            if (lastPublicForYinyuanModel == null || lastPublicForYinyuanModel.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP,1);
//                            }
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_JIAJIAO:
//                            openJiajiao();
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_JIAJIAO);
//                            if (lastPublicForJiajiaoModel == null || lastPublicForJiajiaoModel.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_BAOMU:
//                            openBaomuYsYes(roleType);
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_BAOMU);
//                            if (lastPublicForCommBaomu == null || lastPublicForCommBaomu.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_YUESAO:
//                            openBaomuYsYes(roleType);
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_YUESAO);
//                            if (lastPublicForCommBaomu == null || lastPublicForCommBaomu.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_YUERSAO:
//                            openBaomuYsYes(roleType);
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_YUERSAO);
//                            if (lastPublicForCommBaomu == null || lastPublicForCommBaomu.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_ZHONGDIANGONG:
//                            openZhongdiangong();
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_ZHONGDIANGONG);
//                            if (lastPublicForZhongdgModel == null || lastPublicForZhongdgModel.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                        case PageConfig.TYPE_TEZHONGGONG:
//                            openLwzjTzgLeft(roleType);
//                            commRight(roleType);
//                            intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
//                            intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, PageConfig.TYPE_TEZHONGGONG);
//                            if (lastPublicForLgAndTzg == null || lastPublicForLgAndTzg.getIs_posted() == 0) {//没有发布过,add_or_edit_type传0
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 0);
//                            } else {
//                                intent.putExtra(HomeCommonPublishActivity.IS_ZP_OR_YP, 1);
//                            }
//                            intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE,showPage);
//                            startActivity(intent);
//                            break;
//                    }
//
//                }
//
//                @Override
//                public void isNotAuthen() {
//                    final BaseDialog baseDialog = new BaseDialog(getActivity());
//                    baseDialog.showThreeButton("认证信息", "请完善您的认证信息", "取消", "个人认证", "企业认证", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            baseDialog.dismiss();
//                        }
//                    }, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            baseDialog.dismiss();
//                            startAtvDonFinish(PersonalAuthenticationActivity.class);
//                        }
//                    }, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            baseDialog.dismiss();
//                            startAtvDonFinish(BusinessAuthenticationActivity.class);
//                        }
//                    });
//                }
//            });
        }

    }

    //通用右边的验证
    private void commRight(final int type) {
        showDialog();
        //type 1.劳务中介-我要找人 ，2.求职招聘-我要招聘 3.姻缘服务-资产认证，4.家教-我要找家教 5.保姆-我要找保姆 6.月嫂-我要找月嫂 * 7.育儿嫂-我要找育儿嫂 8.钟点工-我要找钟点工 9.我要找特种工
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_IS_RECRUITMENT)
                .addParams("type", (type + 1) + "")
                .setCallBackData(new BaseNewNetModelimpl<HasPublicRectuitmentModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasPublicRectuitmentModel> respnse, String source) {
                        dismiss();
                        if (respnse != null) {
                            hasPublicRectuitmentModel = respnse.getData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasPublicRectuitmentModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    //劳务中介 特种工 我要找工作
    private void openLwzjTzgLeft(final int roleType) {
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_LABOR_LAST_APPLY_DATA)
                .addParams("type", "" + (roleType == PageConfig.TYPE_ZHONGJIE ? 1 : 2))
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForLgAndTzg>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForLgAndTzg> respnse, String source) {
                        dismiss();
                        if (respnse != null) {
                            lastPublicForLgAndTzg = respnse.getData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForLgAndTzg>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    //求职招聘 我要求职
    private void openQzzp() {
        showDialog();
        //求职招聘
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_JOB_WANTED)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForZhaopinModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForZhaopinModel> respnse, String source) {
                        dismiss();
                        if (respnse != null) {
                            lastPublicForZhaopinModel = respnse.getData();

                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForZhaopinModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //姻缘服务
    private void openYyfw() {
        //姻缘
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_LOOKING_FOR_OBJECTS)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForYinyuanModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForYinyuanModel> respnse, String source) {
                        if (respnse != null) {
                            lastPublicForYinyuanModel = respnse.getData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForYinyuanModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //家教 我要当家教
    private void openJiajiao() {
        showDialog();
        // /家教
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_QUERY_LAST_REQUIRE_JIAJIAO)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForJiajiaoModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForJiajiaoModel> respnse, String source) {
                        dismiss();
                        if (respnse != null) {
                            lastPublicForJiajiaoModel = respnse.getData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForJiajiaoModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //钟点工 我要当钟点工
    private void openZhongdiangong() {
        showDialog();
        //钟点工
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_APPLICATION_INFO)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForZhongdgModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForZhongdgModel> respnse, String source) {
                        dismiss();
                        if (respnse != null) {
                            lastPublicForZhongdgModel = respnse.getData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForZhongdgModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //我要当保姆 月嫂 育儿嫂
    private void openBaomuYsYes(final int type) {
        //保姆 月嫂 育儿嫂
        int require_type = 0;
        switch (type) {
            case PageConfig.TYPE_BAOMU:
                require_type = 1;
                break;
            case PageConfig.TYPE_YUESAO:
                require_type = 2;
                break;
            case PageConfig.TYPE_YUERSAO:
                require_type = 3;
                break;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_QUERY_LAST_REQUIRE)
                .addParams("require_type", "" + require_type)
                .setCallBackData(new BaseNewNetModelimpl<LastPublicForCommBaomu>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<LastPublicForCommBaomu> respnse, String source) {
                        if (respnse != null) {
                            lastPublicForCommBaomu = respnse.getData();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<LastPublicForCommBaomu>>() {
                        }.getType();
                    }
                }).buildPost());
    }

}
