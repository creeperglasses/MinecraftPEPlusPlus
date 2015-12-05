package com.minecraftpe.doubleplus;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LocalAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Bitmap icon1;
    private Bitmap icon2;
    private List<File> list;
    public LocalAdapter(Context context, List<File> li) {
        this.mInflater = LayoutInflater.from(context);
        this.list = li;
        // 文件夹显示图片
        this.icon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder);
        // 文件显示图片
        this.icon2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.doc);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
         
            view = mInflater.inflate(R.layout.file_list, null);
           
            holder = new ViewHolder();
            holder.fileName = (TextView) view.findViewById(R.id.text_name);
            holder.fileSize = (TextView) view.findViewById(R.id.text_size);
            holder.icon = (ImageView) view.findViewById(R.id.image_icon);
       
            view.setTag(holder);
        } else {
          
            holder = (ViewHolder) view.getTag();
        }
     
        File file = list.get(position);
     
        if (!file.isDirectory()) {
            try {
                // 创建输入流
                FileInputStream inputStream = new FileInputStream(file);
                // 获得流大小
                double size = (double) inputStream.available() / 1;
                // 获取文件大小
                holder.fileSize.setText(Util.getFormatSize(size));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 获取文件名
        holder.fileName.setText(file.getName());
        if (file.isDirectory()) {
      
            holder.icon.setImageBitmap(icon1);
        } else {
          
            holder.icon.setImageBitmap(icon2);
        }
        return view;
    }

  
    private class ViewHolder {
        
        private ImageView icon;

      
        private TextView fileName;

     
        private TextView fileSize;
    }

}
