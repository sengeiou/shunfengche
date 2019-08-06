package com.windmillsteward.jukutech.activity.shoppingcart.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.StoreGoodsListActivity;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.AddOrderListActivity;
import com.windmillsteward.jukutech.activity.shoppingcart.adapter.ShoppingCartListFragmentAdapter;
import com.windmillsteward.jukutech.activity.shoppingcart.presenter.ShoppingCartListFragmentPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.BeforeAddOrderRequest;
import com.windmillsteward.jukutech.bean.EditShoppingCartListBean;
import com.windmillsteward.jukutech.bean.ShoppingCarListBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.flowlayout.FlowLayout;
import com.windmillsteward.jukutech.customview.flowlayout.TagAdapter;
import com.windmillsteward.jukutech.customview.flowlayout.TagFlowLayout;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.NumberFormatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * 描述：我的
 * author:cyq
 * 2017-09-20
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener, ShoppingCartListFragmentView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private CommonRefreshLayout common_refresh;
    private ImageView iv_select;
    private LinearLayout linear_select;
    private TextView tv_total_price;
    private TextView tv_result;

    private EasyPopup easyPopup;
    private TextView tv_commodity_price;
    private TextView tv_commodity_inventory;
    private ImageView iv_pic;
    private ImageView iv_close;
    private TagFlowLayout commodity_model_name;
    private TextView iv_add;
    private TextView tv_number;
    private TextView iv_reduce;
    private TextView tv_sure;

    private List<ShoppingCarListBean.StoreCommodityListBean.ListBean> list;
    private ShoppingCartListFragmentAdapter adapter;
    private ShoppingCartListFragmentPresenter presenter;
    private int page;
    private int pageSize;
    // 当前编辑的商店
    private int storePosition;
    // 当前编辑的商品
    private int shoppingPosition;
    private int cart_id;

    private boolean isInit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateView(R.layout.fragment_shoppingcart);
        initView(view);
        initToolbar();
        initRecyclerView();
        initPopup();
        presenter = new ShoppingCartListFragmentPresenter(this);
        presenter.initData(1, 10, getAccessToken());
        isInit = true;
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        presenter.refreshData(1, 10, getAccessToken());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden && isInit) {
            presenter.initData(1, 10, getAccessToken());
        }
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new ShoppingCartListFragmentAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page<pageSize) {
                    page++;
                    presenter.loadNextData(page,10,getAccessToken());
                }
            }
        }, mRecyclerView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingCarListBean.StoreCommodityListBean.ListBean bean = list.get(position);
                switch (view.getId()) {
                    case R.id.tv_edit:
                        bean.setEdit(!bean.isEdit());
                        List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = bean.getCommodity_list();
                        for (ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean listBean : commodity_list) {
                            listBean.setEdit(!listBean.isEdit());
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.iv_select:
                        bean.setSelect(!bean.isSelect());
                        List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list1 = bean.getCommodity_list();
                        for (ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean listBean : commodity_list1) {
                            listBean.setSelect(bean.isSelect());
                        }
                        adapter.notifyDataSetChanged();
                        checkListSelectNumSize();
                        break;
                    case R.id.tv_title:
                        if (position<list.size()) {
                            Bundle bundle = new Bundle();
                            bundle.putInt(Define.INTENT_DATA,bean.getStore_id());
                            bundle.putString(Define.INTENT_DATA_TWO,bean.getStore_name());
                            startAtvDonFinish(StoreGoodsListActivity.class,bundle);
                        }
                        break;
                }
            }
        });
        adapter.setOnSecondItemChildClickListener(new ShoppingCartListFragmentAdapter.OnSecondItemChildClickListener() {
            @Override
            public void clickSelect(int storePosition,int shoppingPosition) {
                ShoppingCarListBean.StoreCommodityListBean.ListBean listBean = list.get(storePosition);

                List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = listBean.getCommodity_list();
                ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean commodityListBean = commodity_list.get(shoppingPosition);
                if (commodityListBean.getCommodity_status()!=2) {
                    showTips("该商品已失效",0);
                    return;
                }
                commodityListBean.setSelect(!commodityListBean.isSelect());

                int i=0;
                for (ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean bean : commodity_list) {
                    if (bean.isSelect()) {
                        i++;
                    }
                }
                if (i==commodity_list.size()) {
                    listBean.setSelect(true);
                } else {
                    listBean.setSelect(false);
                }
                adapter.notifyItemChanged(storePosition);
                checkListSelectNumSize();
            }

            @Override
            public void clickDelete(int adapterPosition, int position) {
                ArrayList<EditShoppingCartListBean> cartListBeans = new ArrayList<>();
                ShoppingCarListBean.StoreCommodityListBean.ListBean listBean = list.get(adapterPosition);
                List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = listBean.getCommodity_list();
                ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean bean = commodity_list.get(position);
                EditShoppingCartListBean bean1 = new EditShoppingCartListBean();
                bean1.setCart_commodity_id(bean.getCart_commodity_id());
                bean1.setCommodity_num(0);
                bean1.setCommodity_model_id(bean.getCommodity_model_id());
                cartListBeans.add(bean1);
                presenter.deleteShopping(getAccessToken(),cart_id,cartListBeans);
            }

            @Override
            public void clickChoice(int storePosition, int shoppingPosition) {
                ShoppingCartFragment.this.storePosition = storePosition;
                ShoppingCartFragment.this.shoppingPosition = shoppingPosition;

                // 得到店铺
                ShoppingCarListBean.StoreCommodityListBean.ListBean listBean = list.get(storePosition);
                // 得到商品
                List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = listBean.getCommodity_list();
                if (commodity_list!=null && commodity_list.size()>shoppingPosition) {
                    ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean bean = commodity_list.get(shoppingPosition);
                    // 得到分类
                    List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean> commodity_model_list = bean.getCommodity_model_list();
                    setData(commodity_model_list,bean.getCommodity_model_id());
                }

            }

            @Override
            public void clickReduce(int storePosition, int shoppingPosition) {
                ShoppingCarListBean.StoreCommodityListBean.ListBean listBean = list.get(storePosition);
                List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = listBean.getCommodity_list();
                ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean commodityListBean = commodity_list.get(shoppingPosition);
                if (commodityListBean.getCommodity_num()>1){
                    commodityListBean.setCommodity_num(commodityListBean.getCommodity_num()-1);

                    ArrayList<EditShoppingCartListBean> cartListBeans = new ArrayList<>();
                    EditShoppingCartListBean bean1 = new EditShoppingCartListBean();
                    bean1.setCart_commodity_id(commodityListBean.getCart_commodity_id());
                    bean1.setCommodity_num(commodityListBean.getCommodity_num());
                    bean1.setCommodity_model_id(commodityListBean.getCommodity_model_id());
                    cartListBeans.add(bean1);
                    presenter.editShoppingNum(getAccessToken(),cart_id,cartListBeans);

                    adapter.notifyItemChanged(storePosition);
                }
            }

            @Override
            public void clickAdd(int storePosition, int shoppingPosition) {
                ShoppingCarListBean.StoreCommodityListBean.ListBean listBean = list.get(storePosition);
                List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = listBean.getCommodity_list();
                ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean commodityListBean = commodity_list.get(shoppingPosition);
                commodityListBean.setCommodity_num(commodityListBean.getCommodity_num()+1);

                ArrayList<EditShoppingCartListBean> cartListBeans = new ArrayList<>();
                EditShoppingCartListBean bean1 = new EditShoppingCartListBean();
                bean1.setCart_commodity_id(commodityListBean.getCart_commodity_id());
                bean1.setCommodity_num(commodityListBean.getCommodity_num());
                bean1.setCommodity_model_id(commodityListBean.getCommodity_model_id());
                cartListBeans.add(bean1);
                presenter.editShoppingNum(getAccessToken(),cart_id,cartListBeans);

                adapter.notifyItemChanged(storePosition);
            }

            @Override
            public void onItemClick(int storePosition, int shoppingPosition) {
                ShoppingCarListBean.StoreCommodityListBean.ListBean listBean = list.get(storePosition);
                List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = listBean.getCommodity_list();
                ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean bean = commodity_list.get(shoppingPosition);
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,bean.getCommodity_id());
                startAtvDonFinish(SpecialtyDetailActivity.class, bundle);
            }
        });

        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refreshData(1, 10, getAccessToken());
            }
        });
    }

    private void checkListSelectNumSize( ) {
        int num=0;
        double total_price=0;
        for (ShoppingCarListBean.StoreCommodityListBean.ListBean bean : list) {
            for (ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean listBean : bean.getCommodity_list()) {
                if (listBean.isSelect()) {
                    num++;
                    double commodity_price = listBean.getCommodity_price() * listBean.getCommodity_num();
                    total_price += commodity_price;
                }
            }
        }
        tv_result.setText("结算("+num+")");
        tv_total_price.setText(NumberFormatUtil.save2Point(total_price));
        if (num==0) {
            tv_result.setEnabled(false);
            tv_result.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_text_78));
        } else {
            tv_result.setEnabled(true);
            tv_result.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_f6c650));
        }

        int j =0;
        for (ShoppingCarListBean.StoreCommodityListBean.ListBean bean : list) {
            if (bean.isSelect()) {
                j++;
            }
        }
        if (j==list.size() && j>0) {
            iv_select.setImageResource(R.mipmap.icon_select);
            iv_select.setTag(true);
        } else {
            iv_select.setImageResource(R.mipmap.icon_select_n);
            iv_select.setTag(false);
        }
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_goods_moel, null);
        tv_commodity_price = inflate.findViewById(R.id.tv_commodity_price);
        tv_commodity_inventory = inflate.findViewById(R.id.tv_commodity_inventory);
        iv_pic = inflate.findViewById(R.id.iv_pic);
        iv_close = inflate.findViewById(R.id.iv_close);
        commodity_model_name = inflate.findViewById(R.id.commodity_model_name);
        iv_add = inflate.findViewById(R.id.iv_add);
        tv_number = inflate.findViewById(R.id.tv_number);
        iv_reduce = inflate.findViewById(R.id.iv_reduce);
        tv_sure = inflate.findViewById(R.id.tv_sure);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = tv_number.getText().toString().trim();
                int i = Integer.valueOf(trim) + 1;
                if (i==0) {
                    i=1;
                }
                tv_number.setText(String.valueOf(i));
            }
        });
        iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = tv_number.getText().toString().trim();
                int i = Integer.valueOf(trim) - 1;
                if (i==0) {
                    i=1;
                }
                tv_number.setText(String.valueOf(i));
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean> model_list = list.get(storePosition).getCommodity_list().get(shoppingPosition).getCommodity_model_list();
                Set<Integer> selectedList = commodity_model_name.getSelectedList();
                for (Integer integer : selectedList) {
                    if (integer<model_list.size()) {

                        ArrayList<EditShoppingCartListBean> cartListBeans = new ArrayList<>();
                        ShoppingCarListBean.StoreCommodityListBean.ListBean listBean = list.get(storePosition);
                        List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = listBean.getCommodity_list();
                        ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean bean = commodity_list.get(shoppingPosition);
                        List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean> commodity_model_list = bean.getCommodity_model_list();

                        EditShoppingCartListBean bean1 = new EditShoppingCartListBean();
                        bean1.setCart_commodity_id(bean.getCart_commodity_id());
                        bean1.setCommodity_num(Integer.parseInt(tv_number.getText().toString()));
                        bean1.setCommodity_model_id(commodity_model_list.get(integer).getCommodity_model_id());
                        cartListBeans.add(bean1);
                        presenter.editShopping(getAccessToken(),cart_id,cartListBeans);
                    }
                }
                easyPopup.dismiss();
                adapter.notifyItemChanged(storePosition);
            }
        });

        easyPopup = new EasyPopup(getContext())
                .setContentView(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                //指定任意 ViewGroup 背景变暗
                .setDimView(mRecyclerView)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                    }
                })
                .createPopup();
    }

    public void setData(final List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean> data,
                        int curr_model_id) {
        if (data==null || data.size()==0) {
            return;
        }
        int currPosition = 0;
        ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean curr=null;

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getCommodity_model_id()==curr_model_id) {
                currPosition = i;
                curr = data.get(i);
            }
        }
        if (curr==null)
            return;

        tv_commodity_price.setText("￥"+curr.getCommodity_price());
        tv_commodity_inventory.setText("库存" + curr.getCommodity_inventory());
        GlideUtil.show(this,curr.getCommodity_model_image(),iv_pic);
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        TagAdapter<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean> adapter = new TagAdapter<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean>(data) {
            @Override
            public View getView(FlowLayout parent, int position, ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean o) {
                TextView view = (TextView) inflater.inflate(R.layout.item_goods_model_popup_bg, parent, false);
                view.setText(o.getCommodity_model_name());
                return view;
            }
        };
        adapter.setSelectedList(currPosition);
        commodity_model_name.setAdapter(adapter);
        commodity_model_name.setMaxSelectCount(1);
        commodity_model_name.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet!=null && selectPosSet.size()>0) {
                    for (Integer integer : selectPosSet) {
                        if (integer>=0 && integer<data.size()) {
                            ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean bean1 = data.get(integer);
                            tv_commodity_price.setText("￥"+bean1.getCommodity_price());
                            tv_commodity_inventory.setText("库存" + bean1.getCommodity_inventory());
                            GlideUtil.show(ShoppingCartFragment.this,bean1.getCommodity_model_image(),iv_pic);
                        }
                    }
                }
            }
        });
        if (getActivity()!=null) {
            View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
            easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            easyPopup.showAtAnchorView(parent, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER, 0, 0);
        }
    }

    private void initToolbar() {
        toolbar_iv_back.setVisibility(View.GONE);
        toolbar_iv_title.setText("购物车");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setText("编辑");
        toolbar_tv_right.setOnClickListener(this);
        toolbar_tv_right.setTag(false);

        Bundle arguments = getArguments();
        if (arguments!=null) {
            boolean b = arguments.getBoolean(Define.INTENT_DATA);
            if (b) {
                toolbar_iv_back.setVisibility(View.VISIBLE);
                toolbar_iv_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentActivity activity = getActivity();
                        if (activity!=null)
                        activity.finish();
                    }
                });
            }
        }
    }


    @Override
    public int registStartMode() {
        return singleTask;
    }


    private void initView(View view) {
        toolbar_iv_back = (ImageView) view.findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) view.findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) view.findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) view.findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
        iv_select = (ImageView) view.findViewById(R.id.iv_select);
        linear_select = (LinearLayout) view.findViewById(R.id.linear_select);
        tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        iv_select.setTag(false);
        iv_select.setOnClickListener(this);
        tv_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_tv_right:
                toolbar_tv_right.setTag(!((Boolean) toolbar_tv_right.getTag()));
                for (ShoppingCarListBean.StoreCommodityListBean.ListBean bean : list) {
                    bean.setEdit(((Boolean) toolbar_tv_right.getTag()));
                    for (ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean listBean : bean.getCommodity_list()) {
                        listBean.setEdit(((Boolean) toolbar_tv_right.getTag()));
                    }
                }
                adapter.notifyDataSetChanged();
                if (((Boolean) toolbar_tv_right.getTag())) {
                    toolbar_tv_right.setText("完成");
                } else {
                    toolbar_tv_right.setText("编辑");
                }
                break;
            case R.id.iv_select:
                iv_select.setTag(!(Boolean)iv_select.getTag());
                for (ShoppingCarListBean.StoreCommodityListBean.ListBean bean : list) {
                    bean.setSelect((Boolean) iv_select.getTag());
                    for (ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean listBean : bean.getCommodity_list()) {
                        listBean.setSelect(bean.isSelect());
                    }
                }
                adapter.notifyDataSetChanged();
                checkListSelectNumSize();
                break;
            case R.id.tv_result:
                BeforeAddOrderRequest request = new BeforeAddOrderRequest();
                request.setCart_id(cart_id);
                request.setAccess_token(getAccessToken());
                request.setAddress_id(0);
                ArrayList<BeforeAddOrderRequest.CommodityListBean> commodity_list = new ArrayList<>();
                for (ShoppingCarListBean.StoreCommodityListBean.ListBean listBean : list) {
                    List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list1 = listBean.getCommodity_list();
                    for (ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean commodityListBean : commodity_list1) {
                        if (commodityListBean.isSelect()) {
                            BeforeAddOrderRequest.CommodityListBean bean = new BeforeAddOrderRequest.CommodityListBean();
                            bean.setCart_commodity_id(commodityListBean.getCart_commodity_id());
                            bean.setCommodity_id(commodityListBean.getCommodity_id());
                            bean.setCommodity_model_id(commodityListBean.getCommodity_model_id());
                            bean.setCommodity_num(commodityListBean.getCommodity_num());
                            bean.setStore_id(listBean.getStore_id());
                            commodity_list.add(bean);
                        }
                    }
                }
                request.setListBeans(commodity_list);
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA, JSON.toJSONString(request));
                startAtvDonFinish(AddOrderListActivity.class, bundle);
                break;
        }
    }


    @Override
    public void initDataSuccess(ShoppingCarListBean bean) {
        list.clear();
        if (bean.getStore_commodity_list() == null){
            return;
        }
        list.addAll(bean.getStore_commodity_list().getList());
        page = bean.getStore_commodity_list().getPageNumber();
        pageSize = bean.getStore_commodity_list().getTotalPage();
        adapter.notifyDataSetChanged();
        checkEnd();

        cart_id = bean.getCart_id();
        tv_total_price.setText("0.00");
        checkListSelectNumSize();
    }

    @Override
    public void refreshDataSuccess(ShoppingCarListBean bean) {
        list.clear();
        if (bean.getStore_commodity_list() == null){
            return;
        }
        list.addAll(bean.getStore_commodity_list().getList());
        page = bean.getStore_commodity_list().getPageNumber();
        pageSize = bean.getStore_commodity_list().getTotalPage();
        adapter.notifyDataSetChanged();
        common_refresh.refreshComplete();
        checkEnd();
        tv_total_price.setText("0.00");
        checkListSelectNumSize();
    }

    @Override
    public void refreshDataFailure() {
        common_refresh.refreshComplete();
        checkEnd();
    }

    @Override
    public void loadNextDataSuccess(ShoppingCarListBean bean) {
        if (bean.getStore_commodity_list() == null){
            return;
        }
        list.addAll(bean.getStore_commodity_list().getList());
        adapter.notifyDataSetChanged();
        checkEnd();
    }

    @Override
    public void loadNextFailure() {
        page--;
        adapter.loadMoreFail();
        checkEnd();
    }

    @Override
    public void editShoppingCartSuccess() {
        presenter.refreshData(1,10,getAccessToken());
    }

    @Override
    public void deleteShoppingCartSuccess() {
        presenter.refreshData(1,10,getAccessToken());
    }

    private void checkEnd() {
        if (page>=pageSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }
    }
}