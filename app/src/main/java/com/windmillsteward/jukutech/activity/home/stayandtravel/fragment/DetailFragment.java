package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseFragment;

/**
 * 描述：
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class DetailFragment extends BaseFragment {

    public static final String DESC = "DESC";
    public static DetailFragment getInstance(String desc){
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(DESC,desc);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hotel_detail, container, false);

        TextView tv_detail = (TextView) view.findViewById(R.id.tv_detail);
        Bundle arguments = getArguments();
        if (arguments!=null)
        tv_detail.setText(arguments.getString(DESC));
        return view;
    }
}
