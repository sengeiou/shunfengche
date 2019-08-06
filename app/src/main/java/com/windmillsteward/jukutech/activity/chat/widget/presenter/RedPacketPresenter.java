package com.windmillsteward.jukutech.activity.chat.widget.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.windmillsteward.jukutech.activity.chat.activity.RedPacketDetailActivity;
import com.windmillsteward.jukutech.activity.chat.redpacketdialog.DialogHelper;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.EaseChatRow;
import com.windmillsteward.jukutech.activity.chat.widget.chatrow.RedPacketChat;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.RedPacketDetailModel;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.lang.reflect.Type;

/**
 * 描述：
 * author:cyq
 * 2019/4/16
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */
public class RedPacketPresenter extends EaseChatRowPresenter {


    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new RedPacketChat(cxt, message, position, adapter);
    }

    @Override
    public void onBubbleClick(EMMessage message) {
        final int packet_id;
        try {
            packet_id = message.getIntAttribute("packet_id");

            if (message.direct() == EMMessage.Direct.RECEIVE) {
                //接收方点击
                getRedPacketDetail(packet_id);
            } else {
                //发送方点击
                getRedPacketDetail(packet_id);
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    private void getRedPacketDetail(final int packet_id){
        new NetUtil().setUrl(APIS.URL_RED_PACKET_DETAIL)
                .addParams("packet_id",packet_id+"")
                .setCallBackData(new BaseNewNetModelimpl<RedPacketDetailModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<RedPacketDetailModel> respnse, String source) {
                        RedPacketDetailModel data = respnse.getData();
                        if (data != null){
                            if (data.getStatus() == 1 || data.getStatus() == 2 ||data.getStatus() == 3){//红包已过期被抢光或者已经抢过，则直接进入详情
                                Intent intent = new Intent(getContext(), RedPacketDetailActivity.class);
                                intent.putExtra(RedPacketDetailActivity.PACKET_ID, packet_id);
                                getContext().startActivity(intent);
                            }else {//其它情况都得先弹出红包
                                DialogHelper.with(getContext()).begin().showDilaog(new DialogHelper.DilaogBean(), data, packet_id);
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeReference<BaseResultInfo<RedPacketDetailModel>>() {
                        }.getType();
                    }
                }).buildPost();
    }


//    @Override
//    protected void handleReceiveMessage(EMMessage message) {
//        if (!message.isAcked() && message.getChatType() == EMMessage.ChatType.Chat) {
//            try {
//                EMClient.getInstance().chatManager().ackMessageRead(message.getFrom(), message.getMsgId());
//            } catch (HyphenateException e) {
//                e.printStackTrace();
//            }
//            return;
//        }
//
//        // Send the group-ack cmd type msg if this msg is a ding-type msg.
//        EaseDingMessageHelper.get().sendAckMessage(message);
//    }
}
