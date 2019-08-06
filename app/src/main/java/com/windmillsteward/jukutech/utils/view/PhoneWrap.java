package com.windmillsteward.jukutech.utils.view;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

/**
 * @date: on 11/24/18
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class PhoneWrap {

    /**
     * 打电话
     *
     * @param view
     * @param phone
     */
    public static void bindPhoneView(final View view, final String phone) {
        if (view != null && !TextUtils.isEmpty(phone)) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + phone);
                    intent.setData(data);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
