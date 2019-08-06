package com.windmillsteward.jukutech.activity.chat.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.EaseConstant;
import com.windmillsteward.jukutech.utils.GlideUtil;

/**
 * 描述：
 * author:cyq
 * 2019/4/16
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */
public class RedPacketChat extends EaseChatRow {
//    private ImageView iv_head;//头像
    private ImageView iv_userhead;//头像
    private TextView tv_title;//标题
    private TextView tv_desc;//描述
    private TextView tv_userid;//昵称
    private RelativeLayout lay_rl_content;


    public RedPacketChat(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? R.layout.eye_row_received_cards
                : R.layout.eye_row_sent_cards,this);
    }

    @Override
    protected void onFindViewById() {
//        iv_head = findViewById(R.id.iv_head_card);
        iv_userhead = findViewById(R.id.iv_userhead);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        tv_userid = findViewById(R.id.tv_userid);
        lay_rl_content = findViewById(R.id.bubble);
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSetUpView() {
        try {
            String username = message.getFrom();
            String user_avatar_url =  message.getStringAttribute(EaseConstant.USER_AVATAR_URL);
            String nick_name =  message.getStringAttribute(EaseConstant.USER_NICK_NAME);
            String packet_desc =  message.getStringAttribute(EaseConstant.PACKET_DESC);
            GlideUtil.show(getContext(),user_avatar_url,iv_userhead);
            tv_userid.setText(nick_name);
            tv_desc.setText(packet_desc);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }



}
