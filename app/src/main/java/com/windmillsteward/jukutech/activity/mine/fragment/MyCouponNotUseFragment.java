package com.windmillsteward.jukutech.activity.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.MyCouponAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.MyCouponPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.MyCouponBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述：优惠券-未使用
 * author:cyq
 * 2018-03-05
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyCouponNotUseFragment extends BaseFragment implements MyCouponView{

    private RecyclerView rv_content;
    private CommonRefreshLayout common_refresh;

    private int status = 0;//优惠状态【0：未使用，1：已使用，2：已过期】
    private int type; // 如果是选优惠券进来，type!=0
    private float curr_price; // 如果是选优惠券进来，传递过来的价格
    private List<MyCouponBean.ListBean> list;
    private MyCouponAdapter adapter;

    private MyCouponPresenter presenter;

    private int page,pageSize;

    public static MyCouponNotUseFragment getInstance(int status,int type,float curr_price) {
        MyCouponNotUseFragment fragment = new MyCouponNotUseFragment();
        Bundle args = new Bundle();
        args.putInt(Define.INTENT_DATA, status);
        args.putInt(Define.INTENT_TYPE, type);
        args.putFloat(Define.CURR_PRICE, curr_price);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments!=null) {
            status = arguments.getInt(Define.INTENT_DATA);
            type = arguments.getInt(Define.INTENT_TYPE,0);
            curr_price = arguments.getFloat(Define.CURR_PRICE,-1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateView(R.layout.fragment_my_coupon);
        initView(view);
        initData();
        return view;
    }

    public void initData(){
        if (presenter == null){
            presenter = new MyCouponPresenter(this);
        }
        presenter.initData(getAccessToken(),1,10,status);
    }

    private void initView(View view) {
        rv_content = (RecyclerView) view.findViewById(R.id.rv_content);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);

        rv_content.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new MyCouponAdapter(list);
        rv_content.setAdapter(adapter);
        rv_content.setHasFixedSize(true);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(getAccessToken(),page,10,status);
                }
            }
        },rv_content);

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(getAccessToken(),1,10,status);
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyCouponBean.ListBean listBean = list.get(position);
                if (type!=0 && listBean!=null) {
//                    if (curr_price<Float.valueOf(listBean.getCoupon_money())) {
//                        showTips("无法使用该优惠券",1);
//                    } else {
//                        Intent data = new Intent();
//                        Bundle extras = new Bundle();
//                        extras.putInt(Define.RECEIVE_ID,listBean.getReceive_id());  // todo 价格是不是要用String哦，可能会有小数
//                        data.putExtras(extras);
//                        MyCouponActivity activity = (MyCouponActivity) getActivity();
//                        if (activity!=null) {
//                            activity.setResult(200, data);
//                            activity.finish();
//                        }
//                    }
                } else {
                    int coupon_status = listBean.getCoupon_status();
                    if (coupon_status == 0){
                        //TODO 此处要接分享
                        showShareDialog(listBean);
                    }
                }
            }
        });
    }

    /**
     * 展示分享对话框
     * @param bean
     */
    private void showShareDialog(MyCouponBean.ListBean bean){
        UMImage thumb =  new UMImage(getActivity(), R.mipmap.icon_logo_rectangle);

        UMWeb web = new UMWeb(bean.getShare_url());
        web.setTitle(ConfigUtil.getAppName(getContext()));//标题
        web.setThumb(thumb);  //缩略图

        web.setDescription(bean.getCoupon_name());//描述

        new ShareAction(getActivity())
                .withText(bean.getCoupon_name()).withMedia(web)
                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            showTips("分享成功",1);
        }
        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            showTips("分享失败",1);
        }
        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            showTips("分享取消",1);
        }
    };

    @Override
    public void initDataSuccess(MyCouponBean bean) {
        list.clear();
        list.addAll(bean.getList());
        page = bean.getPageNumber();
        pageSize = bean.getTotalPage();
        adapter.setNewData(list);
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void refreshDataSuccess(MyCouponBean bean) {
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
    public void loadNextDataSuccess(MyCouponBean bean) {
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

    @Override
    public int registStartMode() {
        return singleTask;
    }
}
