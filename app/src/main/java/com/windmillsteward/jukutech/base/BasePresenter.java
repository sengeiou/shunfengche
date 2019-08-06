package com.windmillsteward.jukutech.base;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public abstract class BasePresenter {
    private List<Callback.Cancelable> calls = new ArrayList<>();

    /**
     * 添加进来
     *
     * @param cancelable
     */
    public void addCallbak(Callback.Cancelable cancelable) {
        if (!calls.contains(cancelable)) {
            calls.add(cancelable);
        }
    }

    public List<Callback.Cancelable> getCalls() {
        return calls;
    }

    public void onCancel() {
        if (calls != null) {
            for (Callback.Cancelable call : calls) {
                if (call != null) {
                    if (!call.isCancelled()) {
                        call.cancel();
                    }
                }
            }
        }
    }
}
