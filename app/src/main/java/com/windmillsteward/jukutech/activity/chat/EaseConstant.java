/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.windmillsteward.jukutech.activity.chat;

public class EaseConstant {
    public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
    public static final String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";
    
    public static final String MESSAGE_TYPE_RECALL = "message_recall";
    
    public static final String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
    public static final String MESSAGE_ATTR_EXPRESSION_ID = "em_expression_id";
    
    public static final String MESSAGE_ATTR_AT_MSG = "em_at_list";
    public static final String MESSAGE_ATTR_VALUE_AT_MSG_ALL = "ALL";

    
    
	public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static final int CHATTYPE_CHATROOM = 3;
    
    public static final String EXTRA_CHAT_TYPE = "chatType";
    public static final String EXTRA_USER_ID = "userId";


    public static final String RED_PACKET = "red_packet";//是否是红包
    public static final String RED_PACKET_ID = "packet_id";//红包id
//    public static final String SENDER_USER_ID = "sender_user_id";//发送人用户id
    public static final String USER_AVATAR_URL = "user_avatar_url";//发送人用户头像
    public static final String USER_NICK_NAME = "user_nick_name";//发送人用户昵称
    public static final String PACKET_DESC = "packet_desc";//红包描述

    public static final String RED_PACKET_RESULT_MODEL = "red_packet_result_model";//

    public static final String MESSAGE_JOB_PIC_URL = "message_job_pic_url" ; //图片
    public static final String MESSAGE_JOB_CONTENT = "message_job_content" ; //内容
    public static final String MESSAGE_JOB_STAGE = "message_job_stage" ; //阶段
    public static final String MESSAGE_JOB_ID = "message_job_id" ; //类型
    public static final String MESSAGE_TITLE = "message_title"; //消息标题
    public static final String MESSAGE_URL = "message_url" ; //消息Url
    public final static String MESSAGE_USER_AVATAR ="message_user_avatar" ; //当前用户的头像
    public final static String MESSAGE_USER_NAME ="message_user_name" ; //当前用户的昵称
    public static final String MESSAGE_JOB_TYPE = "message_job_type"; //作为区分自己定义的消息


}
