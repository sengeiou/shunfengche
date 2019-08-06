package com.windmillsteward.jukutech.activity.classification.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.classification.adapter.ClassificationMenuAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.activity.HomeSearchActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.MessageListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.ScanActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.newpage.model.ClassificationModel;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.ClassificationMenuBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.windmillsteward.jukutech.activity.home.fragment.HomeFragmentThree.msg_count;

/**
 * 描述：
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class ClassificationFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private FrameLayout fl_content;

    private int currPosition = -1;
    private ClassificationModel data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_iffication, container, false);
        initView(view);
        initData();
        return view;
    }

    //设置数据
    private void initData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_INDEX_CLASS_LIST)
                .setCallBackData(new BaseNewNetModelimpl<ClassificationModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ClassificationModel> respnse, String source) {
                        if (respnse.getData() != null) {
                            data = respnse.getData();
                            getChildFragmentManager().beginTransaction().replace(R.id.fl_content, ClassificationDetailFragment.getInstance(0, data.getWork())).commit();

                            final List<ClassificationMenuBean> menus = new ArrayList<>();
                            String[] array = getActivity().getResources().getStringArray(R.array.classificationMenus);
                            for (String s : array) {
                                ClassificationMenuBean bean = new ClassificationMenuBean();
                                bean.setText(s);
                                bean.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_color_black));
                                bean.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_f3f4f6));
                                menus.add(bean);
                            }

                            for (int i = 0; i < menus.size(); i++) {
                                if (i == 0) {
                                    menus.get(0).setTextColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                                    menus.get(0).setLineColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                                    menus.get(0).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_white));
                                }
                            }
                            ClassificationMenuAdapter adapter = new ClassificationMenuAdapter(menus);
                            mRecyclerView.setAdapter(adapter);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            mRecyclerView.setHasFixedSize(true);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    if (currPosition == position) {
                                        return;
                                    }
                                    currPosition = position;
                                    for (int i = 0; i < menus.size(); i++) {
                                        if (i == position) {
                                            menus.get(i).setTextColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                                            menus.get(i).setLineColor(ContextCompat.getColor(getContext(), R.color.text_blue));
                                            menus.get(i).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_white));
                                        } else {
                                            menus.get(i).setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_black));
                                            menus.get(i).setLineColor(ContextCompat.getColor(getContext(), R.color.color_white));
                                            menus.get(i).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_f3f4f6));
                                        }
                                    }
                                    if (position == 0) {
                                        getChildFragmentManager().beginTransaction().replace(R.id.fl_content, ClassificationDetailFragment.getInstance(position, data.getWork())).commit();
                                    } else if (position == 1) {
                                        getChildFragmentManager().beginTransaction().replace(R.id.fl_content, ClassificationDetailFragment.getInstance(position, data.getSmart_home())).commit();
                                    } else if (position == 2) {
                                        getChildFragmentManager().beginTransaction().replace(R.id.fl_content, ClassificationDetailFragment.getInstance(position, data.getHouse())).commit();
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ClassificationModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }
}
