package com.minecraftpe.doubleplus;
import java.util.*;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.*;
import android.graphics.drawable.*;
import android.os.*;
import java.io.*;
import java.net.*;
import android.graphics.*;
import android.support.v7.graphics.*;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ConRecAdapter extends RecyclerView.Adapter<ConRecAdapter.MasonryView>
{
	private List<ConRecItem> products;
    private static RecycleItemClickListener itemClickListener;
   
    public ConRecAdapter(List<ConRecItem> list,RecycleItemClickListener clickListener) {
        products=list;
        itemClickListener=clickListener;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.con_layout, viewGroup, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(final MasonryView masonryView, final int position) {
		masonryView.imageView.setDrawingCacheEnabled(true);
		loadImage(masonryView.imageView, products.get(position).getImg(), false);
		Random random = new Random();
		int i=random.nextInt(16777216);
		String hex = Integer.toHexString(i);
	try{
		masonryView.bar.setBackgroundColor(Color.parseColor("#"+hex));
		}
		catch(IllegalArgumentException e){
			
		}
		masonryView.bar.getBackground().setAlpha(80);
		masonryView.textView.setText(products.get(position).getTitle());

		}


    @Override
    public int getItemCount() {
        return products.size();
    }
	private final Map<String, Drawable> cache = new HashMap<String, Drawable>();

	public void loadImage(final ImageView imageView, final String urlString) {
		loadImage(imageView, urlString, true);
	}

	public void loadImage(final ImageView imageView, final String urlString, boolean useCache) {
		if (useCache && cache.containsKey(urlString)) {
			imageView.setImageDrawable(cache.get(urlString));
		}

		//You may want to show a "Loading" image here
		//imageView.setImageResource(R.drawable.image_indicator);


		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				imageView.setImageDrawable((Drawable) message.obj);
			}
		};

		Runnable runnable = new Runnable() {
			public void run() {
				Drawable drawable = null;
				Bitmap bitmap = null;
				try {
					InputStream is = download(urlString);
					 
					drawable = Drawable.createFromStream(is, "src");

					if (drawable != null) {
						cache.put(urlString, drawable);
					}
					
				} catch (Exception e) {
					//Log.e(this.getClass().getSimpleName(), "Image download failed", e);
					//Show "download fail" image 
					//drawable = imageView.getResources().getDrawable(R.drawable.image_fail);
				}

				//Notify UI thread to show this image using Handler
				Message msg = handler.obtainMessage(1, drawable);
				handler.sendMessage(msg);
			}
		};
		new Thread(runnable).start();

	}

	/**
	 * Download image from given url.
	 * Make sure you have "android.permission.INTERNET" permission set in AndroidManifest.xml.
	 * 
	 * @param urlString
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private InputStream download(String urlString) throws MalformedURLException, IOException {
		InputStream inputStream = (InputStream) new URL(urlString).getContent();
		return inputStream;
	}
	
    //viewholder
    public static class MasonryView extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView textView;
		private FrameLayout layout;
		private LinearLayout bar;
		

		

		public MasonryView(View itemView){
			super(itemView);
			imageView= (ImageView) itemView.findViewById(R.id.con_img );
			textView= (TextView) itemView.findViewById(R.id.con_title);
			layout= (FrameLayout) itemView.findViewById(R.id.con_bg);
			bar= (LinearLayout) itemView.findViewById(R.id.con_bar);
			
			
			
			itemView.setOnClickListener(this);

		}

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,this.getLayoutPosition());
        }
    
	
	
}}
