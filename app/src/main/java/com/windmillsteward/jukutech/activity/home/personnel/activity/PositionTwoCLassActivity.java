package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.PositionTwoCLassActivityLeftAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.PositionTwoCLassActivityRightAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.model.GongzhongModel;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTypeModel;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.BaseImageView;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.StringUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：多选职位
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public class PositionTwoCLassActivity extends BaseActivity {
    public static final int RESULT_CODE = 222;
    public static final int REQUEST_CODE = 1026;
    public static final String JOB_CLASS_ONE_ID = "job_class_one_id";
    public static final String JOB_CLASS_TWO_ID = "job_class_two_id";
    public static final String JOB_CLASS_TWO_NAME = "job_class_two_name";
    public static final String JOB_CLASS_TWO_DESC = "job_class_two_desc";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView et_search;
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private RecyclerView select_recyclerview;
    private Button btn_confirm;
    private LinearLayout lay_ll_select;


    private List<WorkTypeModel> list_left;
    private List<WorkTypeModel.ListBean> list_right;
    private PositionTwoCLassActivityLeftAdapter leftAdapter;
    private PositionTwoCLassActivityRightAdapter rightAdapter;
    private RecyclerViewAdapter selectAdapter;

    private int leftPosition = 0;
    private List<Integer> leftList;//保存所选一级职位id
    private List<WorkTypeModel.ListBean> selectRightList;//保存所选二级职位id
    private int total;

    String job_class_one_id;
    String job_class_two_id;
    private boolean isFirst = true;//第一个要显示
    List<String> jobClssOneIdList;
    List<String> jobClssTwoIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_class_two);
        initView();
        initToolbar();
        initRecyclerView();

        jobClssOneIdList = new ArrayList<>();
        jobClssTwoIdList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            job_class_one_id = extras.getString(JOB_CLASS_ONE_ID, "");
            job_class_two_id = extras.getString(JOB_CLASS_TWO_ID, "");
            if (StringUtil.isAllNotEmpty(job_class_one_id, job_class_two_id)) {
                jobClssOneIdList = Arrays.asList(job_class_one_id.split(","));
                jobClssTwoIdList = Arrays.asList(job_class_two_id.split(","));
            }
        }

        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_JOB_CLASS_LIST_COMMON)
                        .setCallBackData(new BaseNewNetModelimpl<List<WorkTypeModel>>() {
                            @Override
                            protected void onFail(int type, String msg) {
                                showErrorView();
                                showTips(msg);
                            }

                            @Override
                            protected void onSuccess(int code, BaseResultInfo<List<WorkTypeModel>> respnse, String source) {
                                if (respnse.getData() != null) {
                                    list_left.clear();
                                    list_right.clear();
                                    List<WorkTypeModel> data = respnse.getData();
                                    list_left.addAll(data);
                                    if (StringUtil.isAllNotEmpty(job_class_one_id, job_class_two_id)) {
                                        for (int i = 0; i < list_left.size(); i++) {
                                            int job_class_id_one = list_left.get(i).getJob_class_id_one();
                                            List<WorkTypeModel.ListBean> list = list_left.get(i).getList();

                                            if (jobClssOneIdList != null) {//
                                                if (jobClssOneIdList.contains(job_class_id_one + "")) {
                                                    list_left.get(i).setSelect(true);
                                                    if (isFirst) {
                                                        WorkTypeModel workTypeModel = list_left.get(i);
                                                        if (workTypeModel.getList() != null) {
                                                            list_right.addAll(workTypeModel.getList());
                                                            isFirst = false;
                                                        }
                                                    }
                                                }

                                                for (int k = 0; k < list.size(); k++) {
                                                    int job_class_id_two = list.get(k).getJob_class_id_two();
                                                    if (jobClssTwoIdList != null) {
                                                        if (jobClssTwoIdList.contains(job_class_id_two + "")) {
                                                            list.get(k).setSelect(true);
                                                            selectRightList.add(list.get(k));
                                                            total++;
                                                        }
                                                    }
                                                    boolean select = list.get(k).isSelect();
                                                    if (select) {
                                                        list_left.get(i).setHaveOneSelect(true);//图标变成已勾选
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    leftAdapter.notifyDataSetChanged();
                                    rightAdapter.notifyDataSetChanged();
                                    selectAdapter.notifyDataSetChanged();
                                    showSelect();

//                            if (list_left.size() > 0) {
//                                WorkTypeModel positionClassBean = list_left.get(0);
//                                positionClassBean.setSelect(true);
//                                if (positionClassBean.getList() != null)
//                                    list_right.addAll(positionClassBean.getList());
//                            }
//                                    leftAdapter.notifyDataSetChanged();
//                                    rightAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            protected Type getType() {
                                return new TypeToken<BaseResultInfo<List<WorkTypeModel>>>() {
                                }.getType();
                            }
                        }).buildPost()
        );
    }

    private void showSelect() {
        if (total >= 1) {
            btn_confirm.setVisibility(View.VISIBLE);
            lay_ll_select.setVisibility(View.VISIBLE);
        } else {
            btn_confirm.setVisibility(View.GONE);
            lay_ll_select.setVisibility(View.GONE);
        }
        btn_confirm.setText("确认" + total + "/3");
    }

    private void initRecyclerView() {
        leftList = new ArrayList<>();
        selectRightList = new ArrayList<>();
        list_left = new ArrayList<>();
        list_right = new ArrayList<>();
        leftAdapter = new PositionTwoCLassActivityLeftAdapter(list_left);
        rightAdapter = new PositionTwoCLassActivityRightAdapter(list_right);
        selectAdapter = new RecyclerViewAdapter(selectRightList);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        select_recyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerView1.setAdapter(leftAdapter);
        mRecyclerView2.setAdapter(rightAdapter);
        select_recyclerview.setAdapter(selectAdapter);
        leftAdapter.removeEmptyView();
        rightAdapter.removeEmptyView();
        selectAdapter.removeEmptyView();


        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                leftPosition = position;
                list_right.clear();
                for (WorkTypeModel workTypeModel : list_left) {
                    workTypeModel.setSelect(false);
                }
                WorkTypeModel bean = list_left.get(position);
                bean.setSelect(true);
                for (int i = 0; i < list_left.size(); i++) {
                    WorkTypeModel workTypeModel = list_left.get(i);
                    List<WorkTypeModel.ListBean> chilidList = workTypeModel.getList();
                    for (int k = 0; k < chilidList.size(); k++) {
                        boolean select = chilidList.get(k).isSelect();
                        if (select) {
                            workTypeModel.setHaveOneSelect(true);//图标变成已勾选
                        }
                    }
                }
                List<WorkTypeModel.ListBean> list = bean.getList();
                if (list != null)
                    list_right.addAll(list);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }
        });
        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                boolean isRepeat = false;

                WorkTypeModel.ListBean classBean = list_right.get(position);
                WorkTypeModel left = list_left.get(leftPosition);

                int job_class_id_two = classBean.getJob_class_id_two();
                for (WorkTypeModel.ListBean selectListBean : selectRightList) {
                    int job_class_id_two1 = selectListBean.getJob_class_id_two();
                    if (job_class_id_two == job_class_id_two1) {
                        isRepeat = true;
                    }
                }
                if (!isRepeat) {
                    if (total >= 3) {
                        showTips("所选职位不能超过3个");
                        return;
                    }
                }
                if (classBean.isSelect()) {//如果已经选中，则变成未选中，而且需要判断所选二级里还有没有已选中的，如果有一级不会变成未选中
                    classBean.setSelect(false);
                    selectRightList.remove(classBean);
                    boolean isHaveOneSelect = false;
                    total--;
                    for (WorkTypeModel.ListBean data : list_right) {
                        boolean select = data.isSelect();
                        if (select) {
                            isHaveOneSelect = true;
                            break;
                        }
                    }
                    if (isHaveOneSelect) {
                        left.setSelect(true);
                        left.setHaveOneSelect(true);
                    } else {
                        left.setSelect(true);
                        left.setHaveOneSelect(false);
                    }
                } else {//如果未选中，则变成已选中
                    classBean.setSelect(true);
                    left.setSelect(true);
                    selectRightList.add(classBean);
                    total++;
                }

                //点击二级的时候，选中一个以上，一级都要变成已选状态
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
                selectAdapter.notifyDataSetChanged();

                showSelect();
                selectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                    }
                });

            }
        });
    }


    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("选择期望职位");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        et_search = (TextView) findViewById(R.id.et_search);
        mRecyclerView1 = (RecyclerView) findViewById(R.id.mRecyclerView1);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.mRecyclerView2);
        select_recyclerview = (RecyclerView) findViewById(R.id.select_recyclerview);
        lay_ll_select = (LinearLayout) findViewById(R.id.lay_ll_select);
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer leftsb = new StringBuffer();
                for (int i = 0; i < list_left.size(); i++) {
                    boolean select = list_left.get(i).isSelect();
                    int job_class_id_one = list_left.get(i).getJob_class_id_one();
                    if (select) {
                        leftsb.append(job_class_id_one+",");
                    }
                }
                StringBuffer selectSb = new StringBuffer();
                StringBuffer selectNameSb = new StringBuffer();
                StringBuffer selectDescSb = new StringBuffer();
                for (int i = 0; i < selectRightList.size(); i++) {
                    boolean select = selectRightList.get(i).isSelect();
                    int job_class_id_two = selectRightList.get(i).getJob_class_id_two();
                    String job_class_id_two_name = selectRightList.get(i).getJob_class_id_two_name();
                    String description = selectRightList.get(i).getDescription();
                    if (select) {
                            selectSb.append(job_class_id_two+",");
                            selectNameSb.append(job_class_id_two_name+"/");
                            selectDescSb.append(description+"\n");

                    }
                }
                String left = leftsb.toString();
                String selectId = selectSb.toString();
                String selectName = selectNameSb.toString();
                String selectDesc = selectDescSb.toString();
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putString(JOB_CLASS_ONE_ID, left.substring(0,left.length()-1));
                extras.putString(JOB_CLASS_TWO_ID, selectId.substring(0,selectId.length()-1));
                extras.putString(JOB_CLASS_TWO_NAME, selectName.substring(0,selectName.length()-1));
                extras.putString(JOB_CLASS_TWO_DESC, selectDesc);
                data.putExtras(extras);
                setResult(RESULT_CODE, data);
                finish();

            }
        });
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<WorkTypeModel.ListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<WorkTypeModel.ListBean> data) {
            super(R.layout.item_recycler_select_position, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final WorkTypeModel.ListBean item) {
            helper.setText(R.id.tv_name, item.getJob_class_id_two_name());
            helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setSelect(false);
                    selectRightList.remove(helper.getAdapterPosition());

                    for (int i = 0; i < list_left.size(); i++) {
                        WorkTypeModel workTypeModel = list_left.get(i);
                        List<WorkTypeModel.ListBean> chilidList = workTypeModel.getList();
                        boolean isHaveOneSelect = false;
                        for (int k = 0; k < chilidList.size(); k++) {
                            boolean select = chilidList.get(k).isSelect();
                            if (select) {
                                isHaveOneSelect = true;
                                break;
                            }
                        }
                        if (isHaveOneSelect){
                            workTypeModel.setHaveOneSelect(true);
                        }else{
                            workTypeModel.setHaveOneSelect(false);
                        }
                    }
                    total--;
                    showSelect();
                    notifyDataSetChanged();
                    leftAdapter.notifyDataSetChanged();
                    rightAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
