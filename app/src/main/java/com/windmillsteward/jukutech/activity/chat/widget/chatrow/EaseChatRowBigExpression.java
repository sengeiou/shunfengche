package com.windmillsteward.jukutech.activity.chat.widget.chatrow;

import android.content.Context;
import android.text.TextUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.EaseConstant;
import com.windmillsteward.jukutech.activity.chat.EaseUI;
import com.windmillsteward.jukutech.activity.chat.domain.EaseEmojicon;
import com.windmillsteward.jukutech.activity.chat.utils.EaseUserUtils;
import com.windmillsteward.jukutech.utils.GlideUtil;

/**
 * big emoji icons
 *
 */
public class EaseChatRowBigExpression extends EaseChatRowText {

    private ImageView imageView;
    private ImageView iv_userhead;
    private TextView tv_userid;


    public EaseChatRowBigExpression(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }
    
    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? 
                R.layout.ease_row_received_bigexpression : R.layout.ease_row_sent_bigexpression, this);
    }

    @Override
    protected void onFindViewById() {
        percentageView = (TextView) findViewById(R.id.percentage);
        tv_userid = (TextView) findViewById(R.id.tv_userid);
        iv_userhead = (ImageView) findViewById(R.id.iv_userhead);
        imageView = (ImageView) findViewById(R.id.image);
    }


    @Override
    public void onSetUpView() {
        if (EMMessage.Direct.RECEIVE == message.direct()) {
            String user_avatar_url = message.getStringAttribute(EaseConstant.USER_AVATAR_URL, "");
            String nick_name = message.getStringAttribute(EaseConstant.USER_NICK_NAME, "");
            GlideUtil.show(getContext(), user_avatar_url, iv_userhead);
            tv_userid.setText(nick_name);
        }
        String emojiconId = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_EXPRESSION_ID, null);
        EaseEmojicon emojicon = null;
        if(EaseUI.getInstance().getEmojiconInfoProvider() != null){
            emojicon =  EaseUI.getInstance().getEmojiconInfoProvider().getEmojiconInfo(emojiconId);
        }
        if(emojicon != null){
            if(emojicon.getBigIcon() != 0){
                GlideUtil.show(activity,emojicon.getBigIcon(),imageView,R.drawable.ease_default_expression,R.drawable.ease_default_expression);
//                Glide.with(activity).load(emojicon.getBigIcon()).placeholder(R.drawable.ease_default_expression).into(imageView);
            }else if(emojicon.getBigIconPath() != null){
//                Glide.with(activity).load(emojicon.getBigIconPath()).placeholder(R.drawable.ease_default_expression).into(imageView);
                GlideUtil.show(activity,emojicon.getBigIconPath(),imageView,R.drawable.ease_default_expression,R.drawable.ease_default_expression);
            }else{

                imageView.setImageResource(R.drawable.ease_default_expression);
            }
        }
    }
}
