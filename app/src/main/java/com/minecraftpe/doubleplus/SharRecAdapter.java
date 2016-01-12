package com.minecraftpe.doubleplus;
import android.support.v7.widget.*;
import java.util.*;
import android.view.*;
import android.graphics.drawable.*;
import android.graphics.*;
import android.os.*;
import android.widget.*;
import java.io.*;
import java.net.*;
import android.webkit.*;
import java.text.*;
import com.petebevin.markdown.*;

public class SharRecAdapter extends RecyclerView.Adapter<SharRecAdapter.MasonryView>
{
	private List<SharRecItem> products;
    private static RecycleItemClickListener itemClickListener;

    public SharRecAdapter(List<SharRecItem> list,RecycleItemClickListener clickListener) {
        products=list;
        itemClickListener=clickListener;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shar_layout, viewGroup, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(final MasonryView masonryView, final int position) {
		loadImage(masonryView.uicon,products.get(position).getImg(), false);
		masonryView.user.setText(products.get(position).getUser());
		masonryView.time.setText(products.get(position).getDate());	
		String data="<style>img{max-width:100%;}body{background:#FAFAFA} </style>"+new MarkdownProcessor().markdown(products.get(position).getWebText());
		masonryView.text.loadDataWithBaseURL(null,data,"text/html","UTF-8",null);
		
		
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

		private CircularImageView uicon;
        private TextView user;
		private TextView time;
		private WebView text;

		public MasonryView(View itemView){
			super(itemView);
			uicon= (CircularImageView) itemView.findViewById(R.id.img );
			user= (TextView) itemView.findViewById(R.id.title);
			time= (TextView) itemView.findViewById(R.id.subtitle);
			text= (WebView) itemView.findViewById(R.id.shar_web);
			
			itemView.setOnClickListener(this);
		}
        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,this.getLayoutPosition());
        }



	}}
