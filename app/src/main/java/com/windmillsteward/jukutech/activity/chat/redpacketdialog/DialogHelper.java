package com.windmillsteward.jukutech.activity.chat.redpacketdialog;

import android.content.Context;
import android.support.annotation.IntDef;

import com.windmillsteward.jukutech.bean.RedPacketDetailModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author CaiXi on  2016/12/8 00:33.
 * @Github: https://github.com/cxMax
 * @Description DilaogBean-dialog当前状态，dialog业务接口方法
 */

public class DialogHelper {
    private Context mContext;

    private DialogHelper() {

    }

    private DialogHelper(Context context) {
        this.mContext = context;
    }

    public static DialogHelper with(Context context) {
        return new DialogHelper(context);
    }

    public DialogController begin() {
        return new DialogController(mContext);
    }

    public interface Result {
        void showDilaog(DilaogBean bean, RedPacketDetailModel redPacketDetailModel,int packet_id);
        void hideDialog();
    }

    public static class DilaogBean {
        static final int STATUS_READY = 0;
        static final int STATUS_ROTATE = 1;

        @DilaogStatus
        private int status = STATUS_READY;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef(value = {STATUS_READY, STATUS_ROTATE})
        @interface DilaogStatus {
        }

        public DilaogBean status(int state) {
            this.status = state;
            return this;
        }

        public int getStatus() {
            return status;
        }
    }
}
