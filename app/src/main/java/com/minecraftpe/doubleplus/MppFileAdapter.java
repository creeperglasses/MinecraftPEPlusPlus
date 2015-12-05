package com.minecraftpe.doubleplus;

import java.io.*;
import java.util.List;
import android.content.Context;
import android.graphics.*;
import android.view.*;
import android.widget.*;
public class MppFileAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private Bitmap icon1;
    private Bitmap icon2;
    private List<File> list;
    public MppFileAdapter(Context context, List<File> li) {
        this.mInflater = LayoutInflater.from(context);
        this.list = li;
        this.icon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder);
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
             
                FileInputStream inputStream = new FileInputStream(file);
           
                double size = (double) inputStream.available() / 1;
            
                holder.fileSize.setText(Util.getFormatSize(size));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      
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
