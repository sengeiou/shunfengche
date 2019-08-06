package com.windmillsteward.jukutech.customview.popup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ProvincialUrbanAreaListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddressSelectPopup extends BaseNetModelImpl {

    private PopupWindow window;

    private ImageView iv_close;
    private TextView tv_tab1;
    private View v_tab1;
    private TextView tv_tab2;
    private View v_tab2;
    private TextView tv_tab3;
    private View v_tab3;
    private RecyclerView mRecyclerView;
    private LinearLayout mProgress;
    private List<ProvincialUrbanAreaListBean> list;
    private AddressSelectPopuAdapter adapter;
    private int first_area_id;
    private String first_area_name;
    private int second_area_id;
    private String second_area_name;
    private int third_area_id;
    private String third_area_name;

    public interface OnSelectAddressListener{
        void onSelect(int first_area_id,String first_area_name,int second_area_id,String second_area_name,int third_area_id,String third_area_name);
    }


    public AddressSelectPopup(Context context, final OnSelectAddressListener listener) {
        window = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_address_select, null);
        iv_close = view.findViewById(R.id.iv_close);
        tv_tab1 = view.findViewById(R.id.tv_tab1);
        v_tab1 = view.findViewById(R.id.v_tab1);
        tv_tab2 = view.findViewById(R.id.tv_tab2);
        v_tab2 = view.findViewById(R.id.v_tab2);
        tv_tab3 = view.findViewById(R.id.tv_tab3);
        v_tab3 = view.findViewById(R.id.v_tab3);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        mProgress = view.findViewById(R.id.mProgress);
        list = new ArrayList<>();
        adapter = new AddressSelectPopuAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (first_area_id==0) {
                    first_area_id = list.get(position).getArea_id();
                    first_area_name = list.get(position).getArea_name();
                    tv_tab1.setText(first_area_name);
                } else if (second_area_id==0) {
                    second_area_id = list.get(position).getArea_id();
                    second_area_name = list.get(position).getArea_name();
                    tv_tab2.setText(second_area_name);
                } else if (third_area_id==0) {
                    third_area_id = list.get(position).getArea_id();
                    third_area_name = list.get(position).getArea_name();
                    tv_tab3.setText(third_area_name);
                }
                if (first_area_id!=0 && second_area_id!=0 && third_area_id!=0) {
                    window.dismiss();
                    if (listener!=null) {
                        listener.onSelect(first_area_id,first_area_name,second_area_id,second_area_name,third_area_id,third_area_name);
                    }
                    return;
                }
                int area_id = list.get(position).getArea_id();
                showNextData(area_id);
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
        window.setContentView(view);
    }

    public void show(View view) {
        if (window!=null) {
            window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            window.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            window.showAtLocation(view, Gravity.CENTER_HORIZONTAL,0,0);
            showNextData(0);
        }
    }

    private void showNextData(int area_id){
        if (mProgress!=null) {
            mProgress.setVisibility(View.VISIBLE);
        }
        DataLoader dataLoader = new DataLoader(this, 1);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("area_id", area_id);
        httpInfo.setUrl(APIS.URL_GET_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case 1:
                type = new TypeReference<BaseResultInfo<List<ProvincialUrbanAreaListBean>>>() {}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        if (mProgress!=null) {
            mProgress.setVisibility(View.GONE);
        }
        switch (action) {
            case 1:
                List<ProvincialUrbanAreaListBean> data = (List<ProvincialUrbanAreaListBean>) result.getData();
                if (data!=null) {
                    list.clear();
                    list.addAll(data);
                    adapter.setNewData(list);
                    if (first_area_id==0) {
                        v_tab1.setVisibility(View.VISIBLE);
                        tv_tab1.setText("省");
                    } else if (second_area_id==0) {
                        v_tab2.setVisibility(View.VISIBLE);
                        v_tab1.setVisibility(View.GONE);
                        tv_tab2.setText("市");
                    } else if (third_area_id==0) {
                        v_tab3.setVisibility(View.VISIBLE);
                        v_tab2.setVisibility(View.GONE);
                        tv_tab3.setText("区");
                    }
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        if (mProgress!=null) {
            mProgress.setVisibility(View.GONE);
        }
    }

    class AddressSelectPopuAdapter extends BaseQuickAdapter<ProvincialUrbanAreaListBean,BaseViewHolder> {

        AddressSelectPopuAdapter(@Nullable List<ProvincialUrbanAreaListBean> data) {
            super(R.layout.item_popup, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProvincialUrbanAreaListBean item) {
            if (item!=null) {
                helper.setText(R.id.tv_popupItem,item.getArea_name());
            }
        }
    }
}
