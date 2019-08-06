package com.windmillsteward.jukutech.activity.chat.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRow;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRowBigExpression;

/**
 * Created by zhangsong on 17-10-12.
 */

public class EaseChatBigExpressionPresenter extends EaseChatTextPresenter {
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new EaseChatRowBigExpression(cxt, message, position, adapter);
    }

    @Override
    protected void handleReceiveMessage(EMMessage message) {
    }
}
