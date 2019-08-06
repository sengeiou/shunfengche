package com.windmillsteward.jukutech.activity.home.stayandtravel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.SelectPhotoActivity;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/1/26
 * 作者：xjh
 */

public class SelectPhotoAdapter extends RecyclerView.Adapter<SelectPhotoAdapter.PhotoHolder> {

    private Context context;
    private List<String> list;
    private int maxImgCount;
    private boolean isAdded;
    private OnItemClickListener listener;

    public SelectPhotoAdapter(Context context, List<String> list, int maxImgCount) {
        this.context = context;
        this.list = list;
        this.maxImgCount = maxImgCount;

        setImages(list);
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_photo, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, final int position) {
        int select = position;
        String path = list.get(position);
        //如果选择的图片小于最大图片，最后一张做添加图片处理
        if (isAdded && position == getItemCount() - 1) {
            holder.iv_img.setImageResource(R.mipmap.icon_add_pic);
            select = SelectPhotoActivity.IMAGE_ITEM_ADD;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onDeleteClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setImages(List<String> data) {
        list = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            list.add("");
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }


    class PhotoHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        ImageView iv_delete;

        PhotoHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);

        }
    }
}
