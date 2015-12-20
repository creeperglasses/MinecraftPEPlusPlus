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

public class CviewRecAdapter extends RecyclerView.Adapter<CviewRecAdapter.MasonryView>
{

	private List<NewsRecItem> products;
    private static RecycleItemClickListener itemClickListener;

    public CviewRecAdapter(List<NewsRecItem> list,RecycleItemClickListener clickListener) {
        products=list;
        itemClickListener=clickListener;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cvie_adp, viewGroup, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(final MasonryView masonryView, final int position) {
		masonryView.imageView.setDrawingCacheEnabled(true);
		loadImage(masonryView.imageView,products.get(position).getImg(), false);
		masonryView.title.setText(products.get(position).getTitle());
		masonryView.subText.setText(products.get(position).getSubTitle());	
	}


    @Override
    public int getItemCount() {
        return products.size();
    }
	private final Map<String, Drawable> cache = new HashMap<String, Drawable>();

	public void loadImage(final CircularImageView imageView, final String urlString) {
		loadImage(imageView, urlString, true);
	}

	public void loadImage(final CircularImageView imageView, final String urlString, boolean useCache) {
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

        private CircularImageView imageView;
        private TextView title;
		private TextView subText;

		public MasonryView(View itemView){
			super(itemView);
			imageView= (CircularImageView) itemView.findViewById(R.id.img );
			title= (TextView) itemView.findViewById(R.id.title);
			subText= (TextView) itemView.findViewById(R.id.subtitle);



			itemView.setOnClickListener(this);

		}

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,this.getLayoutPosition());
        }



	}}
