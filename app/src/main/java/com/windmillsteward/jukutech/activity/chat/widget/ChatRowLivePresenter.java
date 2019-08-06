package com.windmillsteward.jukutech.activity.chat.widget;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.windmillsteward.jukutech.activity.chat.Constant;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRow;
import com.windmillsteward.jukutech.activity.chat.widget.presenter.EaseChatRowPresenter;

/**
 * Created by zhangsong on 17-10-12.
 */

public class ChatRowLivePresenter extends EaseChatRowPresenter {
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new ChatRowConferenceInvite(cxt, message, position, adapter);
    }

    @Override
    public void onBubbleClick(EMMessage message) {
        super.onBubbleClick(message);

        String confId = message.getStringAttribute(Constant.EM_CONFERENCE_ID, "");
        String confPassword = message.getStringAttribute(Constant.EM_CONFERENCE_PASSWORD,"");
        int type = message.getIntAttribute(Constant.EM_CONFERENCE_TYPE, 0);
        DemoHelper.getInstance().goLive(confId, confPassword, message.getFrom());
    }

}
