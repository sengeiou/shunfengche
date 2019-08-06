package com.windmillsteward.jukutech.activity.chat.activity;

import android.view.View;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.PwdEditTextTwo;
import com.windmillsteward.jukutech.customview.PwdKeyboardView;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.lang.reflect.Type;

import butterknife.Bind;

/**
 * 描述：面对面建群
 * author:cyq
 * 2019/4/17
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */
public class Face2FaceCreateGroupActivity extends BaseInitActivity implements PwdKeyboardView.OnKeyListener {



    @Bind(R.id.et_pwd)
    PwdEditTextTwo et_pwd;
    @Bind(R.id.key_board)
    PwdKeyboardView keyBoard;

    private String result = "";//输入结果

    @Override
    protected void initView(View view) {
        showContentView();
        setMainTitle("面对面建群");
        keyBoard.setOnKeyListener(this);

        et_pwd.setOnInputFinishListener(new PwdEditTextTwo.OnInputFinishListener() {
            @Override
            public void onInputFinish(String password) {
                showDialog();
                if (password.length() != 4){
                    return;
                }
                addCall(new NetUtil().setUrl(APIS.URL_ADD_GROUP)
                        .addParams("group_sn",password)
                        .setCallBackData(new BaseNewNetModelimpl<String>() {
                            @Override
                            protected void onFail(int type, final String msg) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showTips(msg);
                                        dismiss();
                                    }
                                });

                            }

                            @Override
                            protected void onSuccess(int code, BaseResultInfo<String> respnse, String source) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dismiss();
                                        showTips("成功");
                                        setResult(RESULT_OK);
                                        finish();
                                    }
                                });
                            }

                            @Override
                            protected Type getType() {
                                return new TypeReference<BaseResultInfo<String>>() {
                                }.getType();
                            }
                        }).buildPost());
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_face_to_face_create_group;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshPageData() {

    }

//    @Override
//    public void onComplete(String input_result) {
//        this.result = input_result;
//        if (result!=null){
//            return;
//        }
//        showDialog();
//        Toast.makeText(Face2FaceCreateGroupActivity.this, "input complete : " + result +"\n"+"长度为："+ result.length(), Toast.LENGTH_SHORT).show();
//        addCall(new NetUtil().setUrl(APIS.URL_ADD_GROUP)
//                .addParams("group_sn",input_result)
//                .setCallBackData(new BaseNewNetModelimpl<String>() {
//                    @Override
//                    protected void onFail(int type, final String msg) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                showTips(msg);
//                                dismiss();
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    protected void onSuccess(int code, BaseResultInfo<String> respnse, String source) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                dismiss();
//                                showTips("加入群聊成功");
//                                setResult(RESULT_OK);
//                                finish();
//                            }
//                        });
//                    }
//
//                    @Override
//                    protected Type getType() {
//                        return new TypeReference<BaseResultInfo<String>>() {
//                        }.getType();
//                    }
//                }).buildPost());
//    }

    @Override
    public void onInput(String text) {
        et_pwd.append(text);
    }

    @Override
    public void onDelete() {
        String content1 = et_pwd.getText().toString();
        if (content1.length() > 0) {
            et_pwd.setText(content1.substring(0, content1.length() - 1));
        }
    }
}
