package com.windmillsteward.jukutech.activity.home.think.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.SimpleListDialogAdapter;
import com.windmillsteward.jukutech.activity.home.think.presenter.PublishWisdomFireControlPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.IdeaThinkDetailBean;
import com.windmillsteward.jukutech.bean.PublishIdeaThinkResultBean;
import com.windmillsteward.jukutech.bean.TestItemsListBean;
import com.windmillsteward.jukutech.customview.TestItemsListDialog;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.customview.dialog.SimpleListDialog;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：智慧消防发布需求
 * 时间：2018/2/5
 * 作者：xjh
 */

public class PublishWisdomFireControlListActivity extends BaseActivity implements PublishWisdomFireControlView,View.OnClickListener {

    private static final int REQUEST_CLASS_CODE = 100;
    public static final String TYPE = "TYPE";
    public static final String DATA = "DATA";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_class;
    private EditText et_title;
    private EditText et_area;// 面积
    private EditText et_site;// 地址
    private EditText et_floor;// 楼层
    private TextView tv_test_item;// 检测项目
    private EditText et_desc;
    private EditText et_contacts;
    private EditText et_phone;
    private TextView tv_publish_area;
    private TextView tv_publish;

    private PublishWisdomFireControlPresenter presenter;

    private int second_class_id;// 分类
    private int third_area_id;// 发布地区
    private int type;
    private IdeaThinkDetailBean bean;
    private Set<Integer> benefit_ids=new ArraySet<>();  // 福利id集合
    private Set<Integer> benefit_pos=new ArraySet<>();  // 福利id集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_wisdom_fire_control);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(TYPE);
            bean = (IdeaThinkDetailBean) extras.getSerializable(DATA);
        }

        initView();
        initToolbar();
        presenter = new PublishWisdomFireControlPresenter(this);

        if (type!=0 && bean!=null) {
            initData();
        }
    }

    private void initData() {
        tv_class.setText(bean.getSecond_class_name());
        second_class_id = bean.getSecond_class_id();
        et_title.setText(bean.getTitle());
//        et_price.setText(bean.getPrice());
        et_desc.setText(bean.getDescription());
        et_contacts.setText(bean.getContact_person());
        et_phone.setText(bean.getContact_tel());
        tv_publish_area.setText(bean.getThird_area_name());
        third_area_id = bean.getThird_area_id();
        tv_publish.setText("保存");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CLASS_CODE && resultCode == ChoiceIdeaThinkClassActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                second_class_id = extras.getInt(ChoiceIdeaThinkClassActivity.RESULT_DATA_ID);
                tv_class.setText(extras.getString(ChoiceIdeaThinkClassActivity.RESULT_DATA_NAME));
            }
        }
//        else if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
////            presenter.publish(getAccessToken(),first_class_id,second_class_id,et_title.getText().toString().trim(),et_price.getText().toString().trim()
////                    ,et_desc.getText().toString().trim(),et_contacts.getText().toString().trim(),et_phone.getText().toString().trim(),getCurrCityId(),third_area_id);
//        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("发布需求");
        mImmersionBar.keyboardEnable(true).init();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_class = (TextView) findViewById(R.id.tv_class);
        et_title = (EditText) findViewById(R.id.et_title);
        et_area = (EditText) findViewById(R.id.et_area);
        et_site = (EditText) findViewById(R.id.et_site);
        et_floor = (EditText) findViewById(R.id.et_floor);
        tv_test_item = (TextView) findViewById(R.id.tv_test_item);
        et_desc = (EditText) findViewById(R.id.et_desc);
        et_contacts = (EditText) findViewById(R.id.et_contacts);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_publish_area = (TextView) findViewById(R.id.tv_publish_area);
        tv_publish = (TextView) findViewById(R.id.tv_publish);

        tv_class.setOnClickListener(this);
        tv_publish_area.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
        tv_test_item.setOnClickListener(this);
    }

    private void submit() {
        if (second_class_id==0) {
            showTips("请选择分类",0);
            return;
        }
        String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showTips("请输入标题",0);
            return;
        }

        String area = et_area.getText().toString().trim();
        if (TextUtils.isEmpty(area)) {
            showTips("面积不能为空",0);
            return;
        }
        String site = et_site.getText().toString().trim();
        if (TextUtils.isEmpty(site)) {
            showTips("地址不能为空",0);
            return;
        }
//        String test_item = tv_test_item.getText().toString().trim();
//        if (TextUtils.isEmpty(test_item)) {
//            showTips("检测项目不能为空",0);
//            return;
//        }
        String desc = et_desc.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            showTips("描述不能为空",0);
            return;
        }

        String contacts = et_contacts.getText().toString().trim();
        if (TextUtils.isEmpty(contacts)) {
            showTips("请输入联系人",0);
            return;
        }

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("请输入联系电话",0);
            return;
        }

        if (third_area_id==0) {
            showTips("请选择发布地区",0);
            return;
        }


        if (type==0) {
            presenter.isCharge(getAccessToken(),0);
        } else {
            if (bean!=null) {
//                presenter.edit(bean.getRequire_id(),getAccessToken(),first_class_id,second_class_id,title,price,desc,contacts,phone,getCurrCityId(),third_area_id);
            }
        }
    }

    @Override
    public void loadPublishAreaDataSuccess(List<Map<String, Object>> maps) {
        new SimpleListDialog(this).builder().setAdapter(new SimpleListDialogAdapter(this,maps))
                .setSelectListener(new SimpleListDialog.OnSelectListener() {
            @Override
            public void onSelect(int id, int pos, String text) {
                third_area_id = id;
                tv_publish_area.setText(text);
            }
        }).show();
    }

    @Override
    public void publishSuccess(PublishIdeaThinkResultBean bean) {
        finish();
    }

    @Override
    public void isCharge(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            String title = et_title.getText().toString().trim();
            String area = et_area.getText().toString();
            String site = et_site.getText().toString();
            String desc = et_desc.getText().toString();
            String phone = et_phone.getText().toString();
            String contacts = et_contacts.getText().toString();
            presenter.publish(getAccessToken(),getCurrCityId() + "",third_area_id + ""
                    ,second_class_id + "",title,area,site,benefit_ids
                    ,desc,phone,contacts,et_floor.getText().toString().trim());
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("发布需求需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .show();
        }
    }

    @Override
    public void loadTestItemsListSuccess(final List<TestItemsListBean> testitemslistbean) {
        final LayoutInflater inflater = LayoutInflater.from(this);

        TagAdapter<TestItemsListBean> adapter = new TagAdapter<TestItemsListBean>(testitemslistbean) {
            @Override
            public View getView(FlowLayout parent, int position, TestItemsListBean bean) {
                TextView view = (TextView) inflater.inflate(R.layout.item_textview_mult_select, parent, false);
                view.setText(bean.getName());
                return view;
            }
        };
        adapter.setSelectedList(benefit_pos);
        new TestItemsListDialog(this).builder().setAdapter(adapter).setOnListener(new TestItemsListDialog.OnListener() {
            @Override
            public void onSelect(Set<Integer> ids) {
                benefit_pos = ids;
                if (ids!=null && ids.size()>0) {
                    StringBuilder sb = new StringBuilder();
                    for (Integer id : ids) {
                        int benefit_id = testitemslistbean.get(id).getClass_id();
                        benefit_ids.add(benefit_id);
                        sb.append(testitemslistbean.get(id).getName()).append(" ");
                    }
                    tv_test_item.setText(sb.toString());
                } else {
                    benefit_ids.clear();
                    tv_test_item.setText("暂无");
                }
            }
        }).show();
    }

    @Override
    public void onClick(View v) {
        SystemUtil.dismissKeyBorwd(this);
        switch (v.getId()) {
            case R.id.tv_class:
                startAtvDonFinishForResult(ChoiceWisdomFireControlActivity.class,REQUEST_CLASS_CODE);
                break;
            case R.id.tv_publish_area:
                presenter.loadPublishAreaData(getCurrCityId());
                break;
            case R.id.tv_publish:
                submit();
                break;
            case R.id.tv_test_item:
                presenter.loadTestItemsList();
                break;
        }
    }


}
