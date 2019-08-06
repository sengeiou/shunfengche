package com.windmillsteward.jukutech.interfaces;

import com.baidu.location.BDLocation;

/**
 * @date: on 2018/10/16
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public interface MainLocationListener {
    void locationSuccess(String address, String longilati, BDLocation location);
    void locationFail(String msg);
}
