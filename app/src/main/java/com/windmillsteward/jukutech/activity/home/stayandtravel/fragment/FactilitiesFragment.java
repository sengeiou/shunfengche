package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.bean.FactiliteBean;
import com.windmillsteward.jukutech.bean.HotelAndHouseDetailBean;
import com.windmillsteward.jukutech.customview.FlowLayout;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class FactilitiesFragment extends BaseFragment implements FactilitieView{
    public static final String DATA = "DATA";

    private HotelAndHouseDetailBean bean;

    public static FactilitiesFragment getInstance(HotelAndHouseDetailBean bean){
        FactilitiesFragment fragment = new FactilitiesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA,bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments!=null) {
            bean = (HotelAndHouseDetailBean) arguments.getSerializable(DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_facilities, container, false);

        FlowLayout fl_common = ((FlowLayout) view.findViewById(R.id.fl_common));
        FlowLayout fl_custom = ((FlowLayout) view.findViewById(R.id.fl_custom));
        FlowLayout fl_hotel = ((FlowLayout) view.findViewById(R.id.fl_hotel));
        FlowLayout fl_activity = ((FlowLayout) view.findViewById(R.id.fl_activity));
        TextView tv_left_time = (TextView) view.findViewById(R.id.tv_left_time);
        TextView tv_have_each = (TextView) view.findViewById(R.id.tv_have_each);
        TextView tv_pet = (TextView) view.findViewById(R.id.tv_pet);

        if (bean!=null){
            int can_take_pet = bean.getCan_take_pet();
            if (can_take_pet==0){
                tv_pet.setText("不可携带");
            } else {
                tv_pet.setText("可以携带");
            }
            if (bean.getIs_food_supply()==0) {
                tv_have_each.setText("无");
            } else {
                tv_have_each.setText("有");
            }
            tv_left_time.setText(bean.getLeave_time());
            List<String> public_facility_list = bean.getPublic_facility_list();
            LayoutInflater from = LayoutInflater.from(getContext());
            if (public_facility_list!=null) {
                for (String s : public_facility_list) {
                    View view1 = from.inflate(R.layout.item_hotel_detail_text, ((ViewGroup) fl_common.getParent()),false);
                    ((TextView) view1.findViewById(R.id.tv_text)).setText(s);
                    fl_common.addView(view1);
                }
            }
            List<String> room_facility_list = bean.getRoom_facility_list();
            if (public_facility_list!=null) {
                for (String s : room_facility_list) {
                    View view1 = from.inflate(R.layout.item_hotel_detail_text, ((ViewGroup) fl_common.getParent()),false);
                    ((TextView) view1.findViewById(R.id.tv_text)).setText(s);
                    fl_custom.addView(view1);
                }
            }
            List<String> service_list = bean.getService_list();
            if (service_list!=null) {
                for (String s : service_list) {
                    View view1 = from.inflate(R.layout.item_hotel_detail_text, ((ViewGroup) fl_common.getParent()),false);
                    ((TextView) view1.findViewById(R.id.tv_text)).setText(s);
                    fl_hotel.addView(view1);
                }
            }
            List<String> activity_facility_list = bean.getActivity_facility_list();
            if (activity_facility_list!=null) {
                for (String s : activity_facility_list) {
                    View view1 = from.inflate(R.layout.item_hotel_detail_text, ((ViewGroup) fl_common.getParent()),false);
                    ((TextView) view1.findViewById(R.id.tv_text)).setText(s);
                    fl_activity.addView(view1);
                }
            }
        }

        return view;
    }

    @Override
    public void initDataSuccess(FactiliteBean bean) {

    }
}
