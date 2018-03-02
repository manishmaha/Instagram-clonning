package com.example.crazziee.instagram.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.crazziee.instagram.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by Crazziee on 1/17/2018.
 */

public class GridImageAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutResources;
    private String mAppend;
    private ArrayList<String> ImgUrls;

    public GridImageAdapter(Context context, int LayoutResources, String append, ArrayList<String> ImgUrls){
        super(context, LayoutResources, ImgUrls);
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutResources = LayoutResources;
        mAppend = append;
        this.ImgUrls = ImgUrls;

    }



    private static class ViewHolder{
        ImageView image;
        ProgressBar mProgressBar;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;
        if(convertView == null){
            convertView=mInflater.inflate(layoutResources, parent, false);
            holder = new ViewHolder();
            holder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.gridImageProgressBar);
             holder.image = (ImageView) convertView.findViewById(R.id.gridImageView);
            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        String imgURL = getItem(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imgURL, holder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(holder.mProgressBar != null){
                    holder.mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(holder.mProgressBar != null){
                   holder. mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (holder.mProgressBar != null){
                   holder. mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (holder.mProgressBar != null){
                    holder.mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }


}
