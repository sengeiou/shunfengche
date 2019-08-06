package com.windmillsteward.jukutech.activity.chat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;


import com.abbott.mutiimgloader.util.JImageLoader;
import com.abbott.mutiimgloader.weixin.WeixinMerge;


import com.squareup.picasso.Picasso;

import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.chat.EaseUI;
import com.windmillsteward.jukutech.activity.chat.domain.EaseUser;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.AvatarUtils;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EaseUserUtils {
    
    static EaseUI.EaseUserProfileProvider userProvider;
    
    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }
    
    /**
     * get EaseUser according username
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username){
        if(userProvider != null)
            return userProvider.getUser(username);
        
        return null;
    }
    
    /**
     * set user avatar
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView){
    	EaseUser user = getUserInfo(username);
        if(user != null && user.getAvatar() != null){
            try {
                GlideUtil.show(context, APIS.URL_HX_CONTACT_URL+"?username="+username,imageView);
//                GlideUtil.show(context,user.getAvatar(),imageView);
            } catch (Exception e) {
                //use default avatar
//                GlideUtil.show(context,0,imageView);
                GlideUtil.show(context, APIS.URL_HX_CONTACT_URL+"?username="+username,imageView);
            }
        }else{
//            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
//            GlideUtil.show(context,0,imageView);
            GlideUtil.show(context, APIS.URL_HX_CONTACT_URL+"?username="+username,imageView);
        }
    }
    
    /**
     * set user's nickname
     */
    public static void setUserNick(String username,TextView textView){
        if(textView != null){
        	EaseUser user = getUserInfo(username);
        	if(user != null && user.getNickname() != null){
        		textView.setText(user.getNickname());
        	}else{
        		textView.setText(username);
        	}
        }
    }

    public  static void setAvatar(final Context context, final ImageView img, final List<String> list){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Object> bitmapList = new ArrayList<>();
                for (String url : list){
                    if(url.startsWith("http")) {
                        try {
                            Bitmap   bitmap = Picasso.get().load(url).get();
                            bitmapList.add(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        bitmapList.add(url);
                    }
                }
                final Bitmap avatar = AvatarUtils.getAvatar(bitmapList, 120, 120);
                MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
                if (activity !=null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img.setImageBitmap(avatar);
                        }
                    });
                }
            }
        }).start();
    }
}
