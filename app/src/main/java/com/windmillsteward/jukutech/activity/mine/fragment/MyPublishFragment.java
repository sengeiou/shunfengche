package com.windmillsteward.jukutech.activity.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarDetailActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.MyPositionDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditBuyCarDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditCarDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditFinancingDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditHouseDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditIdeaThinkDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditInsuranceDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditIntelligentFamilyDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditLegalExpertActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditLoanDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditPositionDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditRentCarDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditResumeDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.EditTravelDetailActivity;
import com.windmillsteward.jukutech.activity.mine.activity.ReasonActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.MyPublishAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.MyPublishPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：个人中心-我的发布
 * 时间：2018/2/25/025
 * 作者：xjh
 */
public class MyPublishFragment extends BaseFragment implements MyPublishView {

    private static final String PUBLISH_STATUS = "PUBLISH_STATUS";
    private static final int REQUEST_CODE_DELETE = 104;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private List<MyPublishBean.ListBean> list;
    private MyPublishAdapter adapter;

    private MyPublishPresenter presenter;
    private int page,pageSize;

    private int publish_status;
    private int type;

    /**
     *
     * @param publish_status 审核状态 0审核中 1通过 2不通过
     * @return
     */
    public static MyPublishFragment getInstence(int publish_status) {
        MyPublishFragment fragment = new MyPublishFragment();
        Bundle args = new Bundle();
        args.putInt(PUBLISH_STATUS,publish_status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments!=null)
        publish_status = arguments.getInt(PUBLISH_STATUS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_publish, container, false);
        initView(view);
        initRecyclerView();
        presenter = new MyPublishPresenter(this);
        presenter.initData(getAccessToken(),1,10,type,publish_status);
        return view;
    }


    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    private void initRecyclerView() {
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),1,10,type,publish_status);
            }
        });
        list = new ArrayList<>();
        adapter = new MyPublishAdapter(getContext(), list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page,10,type,publish_status);
                }
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyPublishBean.ListBean bean = list.get(position);
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    // 【0：全部，1.职位，2.简历，3.智慧家庭，4.旅游，5.房屋-卖房，6.酒店，7.思想智库，8.房屋-买房，9.房屋-出租，10.房屋-求租，
                    // 11.大健康，12.法律专家，13.资本管理，14.车辆买卖-卖车，15车辆买卖-买车，16.车辆买卖-车主发布租车信息，17.车辆买卖-乘客发布租车信息】
                    // 18 资本管理-贷款
                    switch (bean.getType()) {
                        case 1:  // 职位编辑详情
                            bundle.putInt(EditPositionDetailActivity.DETAIL_ID, list.get(position).getId());
                            bundle.putInt(EditPositionDetailActivity.POSITION, position);
                            bundle.putInt(EditPositionDetailActivity.PUBLISH_STATUS, publish_status);
                            startAtvDonFinishForResult(EditPositionDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 2:  // 简历详情编辑
                            bundle.putInt(EditResumeDetailActivity.RESUME_ID, list.get(position).getId());
                            bundle.putInt(EditResumeDetailActivity.POSITION, position);
                            bundle.putInt(EditResumeDetailActivity.PUBLISH_STATUS, publish_status);
                            startAtvDonFinishForResult(EditResumeDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 3:  // 智慧家庭编辑
                            bundle.putInt(EditIntelligentFamilyDetailActivity.DETAIL_ID, list.get(position).getId());
                            bundle.putInt(EditIntelligentFamilyDetailActivity.POSITION, position);
                            bundle.putInt(EditIntelligentFamilyDetailActivity.PUBLISH_STATUS, publish_status);
                            startAtvDonFinishForResult(EditIntelligentFamilyDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 4:  // 旅游
                            bundle.putInt(EditTravelDetailActivity.DETAIL_ID, list.get(position).getId());
                            bundle.putInt(EditTravelDetailActivity.POSITION, position);
                            bundle.putInt(EditTravelDetailActivity.PUBLISH_STATUS, publish_status);
                            startAtvDonFinishForResult(EditTravelDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 5:  // 卖房
                            bundle.putInt(EditHouseDetailActivity.DETAIL_ID, list.get(position).getId());
                            bundle.putInt(EditHouseDetailActivity.POSITION, position);
                            bundle.putInt(EditHouseDetailActivity.PUBLISH_STATUS, publish_status);
                            bundle.putInt(EditHouseDetailActivity.CLASS_TYPE, 1);
                            startAtvDonFinishForResult(EditHouseDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 6:  // 酒店
                            bundle.putInt(HotelAndHouseDetailActivity.HOTEL_ID,list.get(position).getId());
                            startAtvDonFinishForResult(HotelAndHouseDetailActivity.class,bundle,REQUEST_CODE_DELETE);
                            break;
                        case 7:  // 思想智库详情编辑
                            bundle.putInt(EditIdeaThinkDetailActivity.REQUIRE_ID, list.get(position).getId());
                            bundle.putInt(EditIdeaThinkDetailActivity.POSITION, position);
                            bundle.putInt(EditIdeaThinkDetailActivity.PUBLISH_STATUS, publish_status);
                            startAtvDonFinishForResult(EditIdeaThinkDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 8:  // 买房
                            bundle.putInt(EditHouseDetailActivity.DETAIL_ID, list.get(position).getId());
                            bundle.putInt(EditHouseDetailActivity.POSITION, position);
                            bundle.putInt(EditHouseDetailActivity.PUBLISH_STATUS, publish_status);
                            bundle.putInt(EditHouseDetailActivity.CLASS_TYPE, 2);
                            startAtvDonFinishForResult(EditHouseDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 9:  // 出租
                            bundle.putInt(EditHouseDetailActivity.DETAIL_ID, list.get(position).getId());
                            bundle.putInt(EditHouseDetailActivity.POSITION, position);
                            bundle.putInt(EditHouseDetailActivity.PUBLISH_STATUS, publish_status);
                            bundle.putInt(EditHouseDetailActivity.CLASS_TYPE, 3);
                            startAtvDonFinishForResult(EditHouseDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 10:  // 求租
                            bundle.putInt(EditHouseDetailActivity.DETAIL_ID, list.get(position).getId());
                            bundle.putInt(EditHouseDetailActivity.POSITION, position);
                            bundle.putInt(EditHouseDetailActivity.PUBLISH_STATUS, publish_status);
                            bundle.putInt(EditHouseDetailActivity.CLASS_TYPE, 4);
                            startAtvDonFinishForResult(EditHouseDetailActivity.class, bundle,REQUEST_CODE_DELETE);
                            break;
                        case 11:  // 大健康
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditInsuranceDetailActivity.class, bundle);
                            break;
                        case 12:  // 法律专家
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditLegalExpertActivity.class, bundle);
                            break;
                        case 13:  // 资本管理
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditFinancingDetailActivity.class, bundle);
                            break;
                        case 14:  // 车辆买卖-卖车
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditCarDetailActivity.class, bundle);
                            break;
                        case 15:  // 车辆买卖-买车
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditBuyCarDetailActivity.class, bundle);
                            break;
                        case 16:  // 车辆买卖-车主发布租车信息
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditRentCarDetailActivity.class, bundle);
                            break;
                        case 17:
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditRentCarDetailActivity.class, bundle);
                            break;
                        case 18:  // 资本管理-贷款
                            bundle.putInt(Define.INTENT_DATA,list.get(position).getId());
                            bundle.putInt(Define.INTENT_DATA_TWO,publish_status);
                            startAtvDonFinish(EditLoanDetailActivity.class, bundle);
                            break;
                    }
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.tv_reason) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ReasonActivity.TEXT,list.get(position).getAudit_reason());
                    startAtvDonFinish(ReasonActivity.class, bundle);
                }
            }
        });
    }


    public void setType(int type) {
        this.type = type;
        presenter.initData(getAccessToken(),1,10,type,publish_status);
    }

    @Override
    public void initDataSuccess(MyPublishBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(MyPublishBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.notifyDataSetChanged();
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void loadNextDataSuccess(MyPublishBean bean) {
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged();
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        checkEnd();
    }

    @Override
    public void loadNextDataFailure() {
        page--;
        adapter.loadMoreFail();
        checkEnd();
    }

    private void checkEnd() {
        if (page>=pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }

    public void refreshData() {
        presenter.refreshData(getAccessToken(),1,10,type,publish_status);
    }
}
