package com.windmillsteward.jukutech.activity.home.family.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.PublishRequireActivity;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：发布需求的图片选择的适配器
 * 时间：2018/1/18
 * 作者：xjh
 */

public class ImagePickerAdapter extends BaseAdapter {

    private int maxImgCount;
    private Context mContext;
    private List<String> mData;
    private OnItemClickListener listener;
    private boolean isAdded;   //是否额外添加了最后一个图片


    public ImagePickerAdapter(Context mContext, List<String> data, int maxImgCount) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        setImages(data);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onDeleteClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setImages(List<String> data) {
        mData = new ArrayList<>(data);
        if (getCount() < maxImgCount) {
            mData.add("");
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public List<String> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded) return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else return mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SelectedPicViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_select_image,parent,false);
            holder = new SelectedPicViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SelectedPicViewHolder) convertView.getTag();
        }
        int select = position;
        String path = mData.get(position);
        //如果选择的图片小于最大图片，最后一张做添加图片处理
        if (isAdded && position == getCount() - 1) {
            holder.iv_img.setImageResource(R.mipmap.icon_add_pic);
            select = PublishRequireActivity.IMAGE_ITEM_ADD;
            holder.iv_delete.setVisibility(View.GONE);
        } else {
            x.image().bind(holder.iv_img, path, ImageUtils.defaulHeadOptionsTwo());
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null) {
                        listener.onDeleteClick(v,position);
                    }
                }
            });
        }

        final int finalSelect = select;
        holder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onItemClick(v, finalSelect);
            }
        });

        return convertView;
    }



    class SelectedPicViewHolder{

        private ImageView iv_img,iv_delete;

        SelectedPicViewHolder(View itemView) {
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }
}
