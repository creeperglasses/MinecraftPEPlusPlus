package com.minecraftpe.doubleplus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minecraftpe.doubleplus.R;

import java.util.List;

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.MasonryView>{
    private List<DevRecItem> products;
    private static RecycleItemClickListener itemClickListener;


    public DevAdapter(List<DevRecItem> list,RecycleItemClickListener clickListener) {
        products=list;
        itemClickListener=clickListener;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dev_layout, viewGroup, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView masonryView, int position) {
        masonryView.imageView.setImageResource(products.get(position).getImg());
        masonryView.textView.setText(products.get(position).getTitle());
		masonryView.subText.setText(products.get(position).getSubTitle());
		
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class MasonryView extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView textView;
		private TextView subText;
		

       public MasonryView(View itemView){
           super(itemView);
           imageView= (ImageView) itemView.findViewById(R.id.masonry_item_img );
           textView= (TextView) itemView.findViewById(R.id.masonry_item_title);
		   subText= (TextView) itemView.findViewById(R.id.masonry_item_subtitle);
		   itemView.setOnClickListener(this);

       }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,this.getLayoutPosition());
        }
    }


}
