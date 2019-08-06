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
package com.windmillsteward.jukutech.activity.chat.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.hyphenate.chat.EMClient;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.chat.EaseUI;
import com.windmillsteward.jukutech.activity.chat.domain.EaseUser;
import com.windmillsteward.jukutech.activity.chat.widget.EaseAlertDialog;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.lang.reflect.Type;

public class AddContactActivity extends BaseActivity {
	private EditText editText;
	private RelativeLayout searchedUserLayout;
	private TextView nameText;
	private TextView nickname;
	private Button searchBtn;
	private String toAddUsername;
	private ProgressDialog progressDialog;
	private ImageView avatar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.em_activity_add_contact);
		TextView mTextView = (TextView) findViewById(R.id.add_list_friends);
		
		editText = (EditText) findViewById(R.id.edit_note);
		avatar = (ImageView) findViewById(R.id.avatar);
		String strAdd = getResources().getString(R.string.add_friend);
		mTextView.setText(strAdd);
		String strUserName = getResources().getString(R.string.user_name);
		editText.setHint(strUserName);
		searchedUserLayout = (RelativeLayout) findViewById(R.id.ll_user);
		nameText = (TextView) findViewById(R.id.name);
		nickname = (TextView) findViewById(R.id.nickname);
		searchBtn = (Button) findViewById(R.id.search);
	}
	
	
	/**
	 * search contact
	 * @param v
	 */
	public void searchContact(View v) {
		final String name = editText.getText().toString().toLowerCase();
		if (TextUtils.isEmpty(name)){
			showTips("请输入用户手机号");
		}
		String saveText = searchBtn.getText().toString();
		if (getString(R.string.button_search).equals(saveText)) {
			toAddUsername = name;
			if(TextUtils.isEmpty(name)) {
				new EaseAlertDialog(this, R.string.Please_enter_a_username).show();
				return;
			}
		addCall(new NetUtil()
				.addParams("username",name)
				.setUrl(APIS.URL_HX_USER_INFO)
				.setCallBackData(new BaseNewNetModelimpl<EaseUser>() {
					@Override
					protected void onFail(int type, String msg) {
						showTips(msg);
					}

					@Override
					protected void onSuccess(int code, BaseResultInfo<EaseUser> respnse, String source) {
						EaseUser easeUser = respnse.getData();
						if (easeUser != null){
							setData(easeUser);
						}
					}

					@Override
					protected Type getType() {
						return new TypeReference<BaseResultInfo<EaseUser>>() {
						}.getType();
					}
				}).buildPost());
		} 
	}	

	private void setData(EaseUser easeUser){
		searchedUserLayout.setVisibility(View.VISIBLE);
		nameText.setText(TextUtils.isEmpty(easeUser.getUsername())?"":easeUser.getUsername());
		nickname.setText(TextUtils.isEmpty(easeUser.getNickname())?"":easeUser.getNickname());
		GlideUtil.show(this, APIS.URL_HX_CONTACT_URL+"?username="+easeUser.getUsername(),avatar);
	}

	/**
	 *  add contact
	 * @param view
	 */
	public void addContact(View view){
		if(EMClient.getInstance().getCurrentUser().equals(nameText.getText().toString())){
			new EaseAlertDialog(this, R.string.not_add_myself).show();
			return;
		}
		
		if(DemoHelper.getInstance().getContactList().containsKey(nameText.getText().toString())){
		    //let the user know the contact already in your contact list
		    if(EMClient.getInstance().contactManager().getBlackListUsernames().contains(nameText.getText().toString())){
		        new EaseAlertDialog(this, R.string.user_already_in_contactlist).show();
		        return;
		    }
			new EaseAlertDialog(this, R.string.This_user_is_already_your_friend).show();
			return;
		}
		
		progressDialog = new ProgressDialog(this);
		String stri = getResources().getString(R.string.Is_sending_a_request);
		progressDialog.setMessage(stri);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();
		
		new Thread(new Runnable() {
			public void run() {
				
				try {
					//demo use a hardcode reason here, you need let user to input if you like
					String s = getResources().getString(R.string.Add_a_friend);
					EMClient.getInstance().contactManager().addContact(toAddUsername, s);
					runOnUiThread(new Runnable() {
						public void run() {
							progressDialog.dismiss();
							String s1 = getResources().getString(R.string.send_successful);
							Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
						}
					});
				} catch (final Exception e) {
					runOnUiThread(new Runnable() {
						public void run() {
							progressDialog.dismiss();
							String s2 = getResources().getString(R.string.Request_add_buddy_failure);
							Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		}).start();
	}
	
	public void back(View v) {
		finish();
	}
}
