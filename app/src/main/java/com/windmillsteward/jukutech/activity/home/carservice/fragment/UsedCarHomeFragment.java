package com.windmillsteward.jukutech.activity.home.carservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarDetailActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarListActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarTypeSelectActivity;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.CarGridAdapter;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.CarGridHeader1Adapter;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.CarGridHeader2Adapter;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.UsedCarHomeFragmentPresenter;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.fragment.ModuleBannerView;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.ModuleBannerPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.bean.CarPriceListBean;
import com.windmillsteward.jukutech.bean.RecommendCarListBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.UsedCarHomeBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 描述： 二手车
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class UsedCarHomeFragment extends BaseFragment implements View.OnClickListener, UsedCarHomeFragmentView, ModuleBannerView {


    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;

    private FlyBanner fl_banner;
    private MyGridView myGridView1;
    private MyGridView myGridView2;

    private List<RecommendCarListBean> recommendRarListBeans;
    private CarGridAdapter carGridAdapter;

    private UsedCarHomeFragmentPresenter presenter;
    private List<CarClassListBean> carClassListBeans;
    private CarGridHeader1Adapter carGridHeader1Adapter;
    private List<CarPriceListBean> carPriceListBeans;
    private CarGridHeader2Adapter carGridHeader2Adapter;
    public boolean needRefresh;

    private ModuleBannerPresenter moduleBannerPresenter;
    private List<SliderPictureInfo> bannerList = new ArrayList<>();


    /**
     * 获取实例
     */
    public static UsedCarHomeFragment getInstance() {
        UsedCarHomeFragment fragment = new UsedCarHomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usedcard_home, container, false);
        initView(view);
        initToolbar();
        initRecyclerView();
        initHeader();
        presenter = new UsedCarHomeFragmentPresenter(this);
        presenter.initData(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            presenter.initData(true);
            needRefresh = false;
        }
    }

    private void initRecyclerView() {
        recommendRarListBeans = new ArrayList<>();
        carGridAdapter = new CarGridAdapter(getContext(), recommendRarListBeans);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(carGridAdapter);
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.initData(false);
            }
        });
        carGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA, recommendRarListBeans.get(position).getCar_id());
                startAtvDonFinish(CarDetailActivity.class, bundle);
            }
        });
        initFooter();
        carGridAdapter.removeEmptyView();
    }

    private void initFooter() {
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.view_foot_end, (ViewGroup) mRecyclerView.getParent(), false);
        carGridAdapter.addFooterView(footer);
    }

    private void initHeader() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_header_carservice, mRecyclerView, false);
        carGridAdapter.addHeaderView(inflate);
        fl_banner = (FlyBanner) inflate.findViewById(R.id.fl_banner);
        myGridView1 = (MyGridView) inflate.findViewById(R.id.myGridView1);
        myGridView2 = (MyGridView) inflate.findViewById(R.id.myGridView2);
        inflate.findViewById(R.id.tv_look_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CarListActivity.class);
                startActivity(intent);
            }
        });
        carClassListBeans = new ArrayList<>();
        carGridHeader1Adapter = new CarGridHeader1Adapter(getContext(), carClassListBeans);
        myGridView1.setAdapter(carGridHeader1Adapter);
        carPriceListBeans = new ArrayList<>();
        carGridHeader2Adapter = new CarGridHeader2Adapter(getContext(), carPriceListBeans);
        myGridView2.setAdapter(carGridHeader2Adapter);

        myGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CarClassListBean carClassListBean = carClassListBeans.get(position);
                Intent intent = new Intent(getContext(), CarListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(CarListActivity.BRAND_ID, carClassListBean.getBrand_id());
                bundle.putString(CarListActivity.BRAND_NAME, carClassListBean.getBrand_name());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        myGridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CarPriceListBean carPriceListBean = carPriceListBeans.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt(CarListActivity.PRICE_ID, carPriceListBean.getPrice_id());
                bundle.putString(CarListActivity.PRICE_NAME, carPriceListBean.getName());
                Intent intent = new Intent(getContext(), CarListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        ViewGroup.LayoutParams layoutParams = fl_banner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(getActivity(), GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH ;
        layoutParams.height = 575 * layoutParams.width / 1080;
        fl_banner.setLayoutParams(layoutParams);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.icon_default_banner);
        fl_banner.setImages(images);

        moduleBannerPresenter = new ModuleBannerPresenter(getActivity(), this);
        moduleBannerPresenter.getBannerList(4);

    }

    private void initToolbar() {
        iv_back.setOnClickListener(this);
        tv_searchHint.setText("输入类别或关键字");
        tv_postJob.setVisibility(View.VISIBLE);
        tv_postJob.setText("发布信息");
        linear_search.setOnClickListener(this);
        tv_postJob.setOnClickListener(this);
    }

    private void initView(View view) {
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        tv_searchHint = (TextView) view.findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) view.findViewById(R.id.linear_search);
        tv_postJob = (TextView) view.findViewById(R.id.tv_postJob);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.finish();
                }
                break;
            case R.id.tv_postJob:
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_search:
                SearchItemActivity.go(getContext(), 40, 0);
                break;
        }
    }

    @Override
    public void initDataSuccess(final UsedCarHomeBean bean) {
        common_refresh.refreshComplete();
        recommendRarListBeans.clear();
        recommendRarListBeans.addAll(bean.getRecommendRarListBeans());
        carGridAdapter.notifyDataSetChanged();
        carClassListBeans.clear();
        carClassListBeans.addAll(bean.getCarClassListBeans());
        carGridHeader1Adapter.notifyDataSetChanged();
        carPriceListBeans.clear();
        carPriceListBeans.addAll(bean.getCarPriceListBeans());
        carGridHeader2Adapter.notifyDataSetChanged();
//        Glide.with(getContext()).load(bean.getTopBanner().getHref_url()==null?"":bean.getTopBanner().getPic_url()).placeholder(R.mipmap.pic_list).error(R.mipmap.pic_list).into(iv_banner);

//        iv_banner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString(Define.INTENT_DATA,bean.getTopBanner().getHref_url());
//                startAtvDonFinish(CommonWebActivity.class, bundle);
//            }
//        });
    }

    @Override
    public void getBannerListSuccess(List<SliderPictureInfo> list) {
        bannerList.clear();
        List<String> picList = new ArrayList<>();
        this.bannerList = list;
        for (SliderPictureInfo info : list) {
            String pic_url = info.getPic_url();
            picList.add(pic_url);
        }
        if (list.size() > 0) {
            fl_banner.setImagesUrl(picList);
        }

        fl_banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bannerList.size() == 0) {
                    return;
                }
                SliderPictureInfo sliderPictureInfo = bannerList.get(position);
                moduleBannerPresenter.bannerClick(sliderPictureInfo);
            }
        });
    }

    @Override
    public void getBannerListFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean.getIs_authen() == 1) {
            Bundle bundle = new Bundle();
            bundle.putInt(CarTypeSelectActivity.TYPE, 1);
            startAtvDonFinish(CarTypeSelectActivity.class, bundle);
            needRefresh = true;
        } else if (bean.getIs_authen() == 0) {
            startAtvDonFinish(PersonalAuthenticationActivity.class);
        } else if (bean.getIs_authen() == 2) {
            showTips("客服正在审核认证信息，审核完成后可发布信息", 1);
        }
    }
}
