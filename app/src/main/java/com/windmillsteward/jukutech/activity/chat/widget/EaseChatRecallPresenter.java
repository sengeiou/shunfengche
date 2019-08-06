package com.windmillsteward.jukutech.activity.chat.widget;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRow;
import com.windmillsteward.jukutech.activity.chat.widget.presenter.EaseChatRowPresenter;

/**
 * Created by zhangsong on 17-10-12.
 */

public class EaseChatRecallPresenter extends EaseChatRowPresenter {
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new EaseChatRowRecall(cxt, message, position, adapter);
    }
}
