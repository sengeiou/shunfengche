package com.windmillsteward.jukutech.activity.chat.fragment;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMConversation.EMConversationType;
import com.hyphenate.util.NetUtils;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.chat.activity.AddContactActivity;
import com.windmillsteward.jukutech.activity.chat.activity.ChatActivity;
import com.windmillsteward.jukutech.activity.chat.activity.Face2FaceCreateGroupActivity;
import com.windmillsteward.jukutech.activity.chat.activity.GroupsActivity;
import com.windmillsteward.jukutech.activity.chat.db.InviteMessgeDao;
import com.windmillsteward.jukutech.activity.chat.model.EaseAtMessageHelper;
import com.windmillsteward.jukutech.activity.chat.model.EaseDingMessageHelper;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.customview.popup.CopyQQPopurWindow;

import static com.windmillsteward.jukutech.activity.chat.EaseConstant.CHATTYPE_CHATROOM;
import static com.windmillsteward.jukutech.activity.chat.EaseConstant.CHATTYPE_GROUP;
import static com.windmillsteward.jukutech.activity.chat.EaseConstant.EXTRA_CHAT_TYPE;
import static com.windmillsteward.jukutech.activity.chat.EaseConstant.EXTRA_USER_ID;


public class ConversationListFragment extends EaseConversationListTwoFragment {

    private TextView errorText;

    @Override
    protected void initView() {
        super.initView();
        View errorView = (LinearLayout) View.inflate(getActivity(), R.layout.em_chat_neterror_item, null);
        errorItemContainer.addView(errorView);
        errorText = (TextView) errorView.findViewById(R.id.tv_connect_errormsg);
        if (!DemoHelper.getInstance().isLoggedIn()) {

            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }
    }
    
    @Override
    protected void setUpView() {
        super.setUpView();
        // register context menu
        registerForContextMenu(conversationListView);
        conversationListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                String username = conversation.conversationId();
                if (username.equals(EMClient.getInstance().getCurrentUser()))
                    Toast.makeText(getActivity(), R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
                else {
                    // start chat acitivity
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    if(conversation.isGroup()){
                        if(conversation.getType() == EMConversationType.ChatRoom){
                            // it's group chat
                            intent.putExtra(EXTRA_CHAT_TYPE, CHATTYPE_CHATROOM);
                        }else{
                            intent.putExtra(EXTRA_CHAT_TYPE, CHATTYPE_GROUP);
                        }
                    }
                    // it's single chat
                    intent.putExtra(EXTRA_USER_ID, username);
                    startActivity(intent);
                }
            }
        });
        titleBar.setRightImageResource(R.mipmap.icon_chat_add_friend);
        // 右上角点击
        titleBar.getRightLayout().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new CopyQQPopurWindow(getActivity(), new CopyQQPopurWindow.DataCallBack() {
                    @Override
                    public void clickGroup() {
                        startActivity(new Intent(getActivity(), GroupsActivity.class));
                    }

                    @Override
                    public void clickAddFriend() {
                        startActivity(new Intent(getActivity(), AddContactActivity.class));
                    }

                    @Override
                    public void clickFaceGroup() {
                        startActivity(new Intent(getActivity(), Face2FaceCreateGroupActivity.class));
                    }
                }).showAtBottom(titleBar.getRightImage());
            }
        });
    }

    @Override
    protected void onConnectionDisconnected() {
        super.onConnectionDisconnected();
        if (NetUtils.hasNetwork(getActivity())){
         errorText.setText(R.string.can_not_connect_chat_server_connection);
        } else {
          errorText.setText(R.string.the_current_network);
        }
    }
    
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu); 
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        if (item.getItemId() == R.id.delete_message) {
            deleteMessage = true;
        } else if (item.getItemId() == R.id.delete_conversation) {
            deleteMessage = false;
        }
    	EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
    	if (tobeDeleteCons == null) {
    	    return true;
    	}
        if(tobeDeleteCons.getType() == EMConversationType.GroupChat){
            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.conversationId());
        }
        try {
            // delete conversation
            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), deleteMessage);
            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
            inviteMessgeDao.deleteMessage(tobeDeleteCons.conversationId());
            // To delete the native stored adked users in this conversation.
            if (deleteMessage) {
                EaseDingMessageHelper.get().delete(tobeDeleteCons);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        refresh();

        // update unread count 底部tab的消息数
//        ((MainActivity) getActivity()).updateUnreadLabel();
        return true;
    }

}
