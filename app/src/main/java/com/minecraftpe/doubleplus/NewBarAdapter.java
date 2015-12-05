package com.minecraftpe.doubleplus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minecraftpe.doubleplus.R;

import java.util.List;

public class NewBarAdapter extends RecyclerView.Adapter<NewBarAdapter.MasonryView>
{
	private List<NewBarItem> products;
    private static RecycleItemClickListener itemClickListener;

    public NewBarAdapter(List<NewBarItem> list,RecycleItemClickListener clickListener) {
        products=list;
        itemClickListener=clickListener;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_bar, viewGroup, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(final MasonryView masonryView, final int position) {
	
		masonryView.textView.setText(products.get(position).getTitle());

	}


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class MasonryView extends  RecyclerView.ViewHolder implements View.OnClickListener{

        
		
        private TextView textView;
		


		public MasonryView(View itemView){
			super(itemView);
			textView= (TextView) itemView.findViewById(R.id.newbar_title);
			

			itemView.setOnClickListener(this);

		}

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,this.getLayoutPosition());
        }



	}}
