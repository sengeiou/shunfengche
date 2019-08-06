package com.windmillsteward.jukutech.activity.chat.widget;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRow;

/**
 * Created by easemob on 2017/7/31.
 */

public class EaseChatRowRecall extends EaseChatRow {

    private TextView contentView;

    public EaseChatRowRecall(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.em_row_recall_message, this);
    }

    @Override
    protected void onFindViewById() {
        contentView = (TextView) findViewById(R.id.text_content);
    }

    @Override
    protected void onSetUpView() {
        // 设置显示内容
        String messageStr = null;
        if (message.direct() == EMMessage.Direct.SEND) {
            messageStr = String.format(context.getString(R.string.msg_recall_by_self));
        } else {
            messageStr = String.format(context.getString(R.string.msg_recall_by_user), message.getFrom());
        }
        contentView.setText(messageStr);
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {
    }
}
