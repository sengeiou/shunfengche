package com.windmillsteward.jukutech.activity.chat.widget.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.windmillsteward.jukutech.activity.chat.widget.ChatRowVoiceCall;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRow;

/**
 * Created by zhangsong on 17-10-12.
 */

public class EaseChatVoiceCallPresenter extends EaseChatRowPresenter {
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new ChatRowVoiceCall(cxt, message, position, adapter);
    }
}
