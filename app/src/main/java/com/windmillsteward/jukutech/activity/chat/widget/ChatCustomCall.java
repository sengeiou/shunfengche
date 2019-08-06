package com.windmillsteward.jukutech.activity.chat.widget;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.Constant;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRow;

public class ChatCustomCall extends EaseChatRow {

    private TextView contentvView;

    public ChatCustomCall(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        if (message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VOICE_CALL, false)){
            inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                    R.layout.eye_row_received_cards : R.layout.eye_row_sent_cards, this);
        // video call
        }else if (message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VIDEO_CALL, false)){
            inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                     R.layout.eye_row_received_cards :  R.layout.eye_row_sent_cards, this);
        }
    }

    @Override
    protected void onFindViewById() {
        contentvView = (TextView) findViewById(R .id.tv_chatcontent);
    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        contentvView.setText(txtBody.getMessage());
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {
    }
}
