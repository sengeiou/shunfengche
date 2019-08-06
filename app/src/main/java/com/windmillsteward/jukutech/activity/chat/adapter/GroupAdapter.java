/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.windmillsteward.jukutech.activity.chat.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMGroup;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.utils.EaseUserUtils;
import com.windmillsteward.jukutech.interfaces.APIS;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends ArrayAdapter<EMGroup> {

    private LayoutInflater inflater;
    private String newGroup;
    private String addFaceGroup;
    private String addPublicGroup;

    public GroupAdapter(Context context, int res, List<EMGroup> groups) {
        super(context, res, groups);
        this.inflater = LayoutInflater.from(context);
        newGroup = context.getResources().getString(R.string.The_new_group_chat);
        addFaceGroup = "添加公开群";
        addPublicGroup = "面对面建群";
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else if (position == 3) {
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.ease_search_bar, parent, false);
            }
            final EditText query = (EditText) convertView.findViewById(R.id.query);
            final ImageButton clearSearch = (ImageButton) convertView.findViewById(R.id.search_clear);
            query.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    getFilter().filter(s);
                    if (s.length() > 0) {
                        clearSearch.setVisibility(View.VISIBLE);
                    } else {
                        clearSearch.setVisibility(View.INVISIBLE);
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void afterTextChanged(Editable s) {
                }
            });
            clearSearch.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    query.getText().clear();
                }
            });
        } else if (getItemViewType(position) == 1) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.em_row_add_group, parent, false);
            }
            ((ImageView) convertView.findViewById(R.id.avatar)).setImageResource(R.mipmap.icon_creat_group);
            ((TextView) convertView.findViewById(R.id.name)).setText(newGroup);
        } else if (getItemViewType(position) == 2) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.em_row_add_group, parent, false);
            }
            ((ImageView) convertView.findViewById(R.id.avatar)).setImageResource(R.mipmap.icon_faceface);
            ((TextView) convertView.findViewById(R.id.name)).setText(addFaceGroup);
            ((TextView) convertView.findViewById(R.id.header)).setVisibility(View.GONE);
        } else if (getItemViewType(position) == 3) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.em_row_add_group, parent, false);
            }
            ((ImageView) convertView.findViewById(R.id.avatar)).setImageResource(R.mipmap.icon_faceface);
            ((TextView) convertView.findViewById(R.id.name)).setText(addPublicGroup);
            ((TextView) convertView.findViewById(R.id.header)).setVisibility(View.VISIBLE);
        } else {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.em_row_group, parent, false);
            }
            ((TextView) convertView.findViewById(R.id.name)).setText(getItem(position - 4).getGroupName());
            ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);
            EMGroup group = getItem(position-4);
            if (group != null) {
                List<String> totalList = new ArrayList<>();
                String owner = group.getOwner();
                totalList.add(TextUtils.isEmpty(owner) ? "" : owner);
//                List<String> adminList = group.getAdminList();
//                List<String> members = group.getMembers();
//                totalList.addAll(adminList);
//                totalList.addAll(members);
				//群管理数组
				List<String> adminList = group.getAdminList();
				int adminSize = 0;
				if (adminList != null) {
					if (adminList.size() > 8) {
						adminSize = 8;
					} else {
						adminSize = adminList.size();
					}
				}
				for (int i = 0; i < adminSize; i++) {
					totalList.add(adminList.get(i));
				}
				//群成员数组
				List<String> members = group.getMembers();
				int memberSize = 0;
				if (members != null) {
					if (members.size() > 8) {
						memberSize = 8;
					} else {
						memberSize = members.size();
					}
				}
				for (int i = 0; i < memberSize; i++) {
					totalList.add(members.get(i));
				}
                int size = 0;
                if (totalList != null) {
                    if (totalList.size() > 9) {
                        size = 9;
                    } else {
                        size = totalList.size();
                    }
                }
                List<String> finalList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    finalList.add(APIS.URL_HX_CONTACT_URL + "?username=" + totalList.get(i));
                }
                EaseUserUtils.setAvatar(getContext(), avatar, finalList);
            }
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount() + 4;
    }

}
