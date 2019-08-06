package com.windmillsteward.jukutech.activity.chat.activity;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.RedPacketDetailModel;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 聊天---红包详情
 */
public class RedPacketDetailActivity extends BaseInitActivity implements View.OnClickListener {

    public static final String PACKET_ID = "packet_id";


    TextView tvBack;
    CircleImageView ivHead;
    TextView tvName;
    TextView tvDesc;
    TextView tvLquQingkuang;
    RelativeLayout lay_rl_root;

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.tv_tips)
    TextView tvTips;

    int packet_id;//红包id

    private List<RedPacketDetailModel.ListBean> list;
    private  RecyclerViewAdapter adapter;

    @Override
    protected void initView(View view) {
        mImmersionBar.statusBarColor(R.color.bg_fb5952).statusBarDarkFont(false, 0.2f).fitsSystemWindows(true).keyboardEnable(true).init();
        showContentView();
        hidTitleView();
        initAdapter();
        initHeader();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_red_packet_detail;
    }

    private void initAdapter(){
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
    }

    private void initHeader(){
        View view = View.inflate(this, R.layout.header_red_packet_detail, null);
        tvLquQingkuang = (TextView) view.findViewById(R.id.tv_lqu_qingkuang);
        tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvBack = (TextView) view.findViewById(R.id.tv_back);
        ivHead = (CircleImageView) view.findViewById(R.id.iv_head);
        lay_rl_root = (RelativeLayout) view.findViewById(R.id.lay_rl_root);

        tvBack.setOnClickListener(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            lay_rl_root.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
//        }

        adapter.addHeaderView(view);
    }

    @Override
    protected void initData() {
        if (getIntent() != null){
            packet_id =  getIntent().getIntExtra(PACKET_ID,0);
        }
        getData();
    }

    private void getData(){
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_RED_PACKET_DETAIL)
                .addParams("packet_id",packet_id+"")
                .setCallBackData(new BaseNewNetModelimpl<RedPacketDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<RedPacketDetailModel> respnse, String source) {
                        dismiss();
                        RedPacketDetailModel data = respnse.getData();
                        if (data != null){
                            List<RedPacketDetailModel.ListBean> packetUsersList = data.getPacketUsers();
                            if (packetUsersList != null){
                                list.clear();
                                list.addAll(packetUsersList);
                                adapter.notifyDataSetChanged();
                            }
                                setData(data);
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeReference<BaseResultInfo<RedPacketDetailModel>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    @Override
    protected void refreshPageData() {
            getData();
    }

    private void setData(RedPacketDetailModel data){
        GlideUtil.show(this,data.getUser_avatar_url(),ivHead);
        tvName.setText(data.getNickname()+"的红包");
        tvDesc.setText(data.getPacket_desc());
        //已领取2/4个，共0.76/1.00元
        tvLquQingkuang.setText("已领取"+data.getGet_num()+"/"+data.getNum()+"个，共"+data.getGet_money()+"/"+data.getMoney()+"元");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<RedPacketDetailModel.ListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<RedPacketDetailModel.ListBean> data) {
            super(R.layout.item_red_packet_detail, data);
        }

        @Override
        protected void convert(BaseViewHolder helper,  RedPacketDetailModel.ListBean item) {
            CircleImageView iv_head = (CircleImageView) helper.getView(R.id.iv_head);
            GlideUtil.show(RedPacketDetailActivity.this,item.getUser_avatar_url(),iv_head);
            helper.setText(R.id.tv_name,item.getNickname())
                    .setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(item.getAdd_time() * 1000l))
                    .setText(R.id.tv_money,item.getMoney()+"元");

        }
    }
    @Override
    public boolean autoRefresh() {
        return true;
    }
}
