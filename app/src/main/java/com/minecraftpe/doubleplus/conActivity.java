package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.webkit.*;
import android.support.v4.widget.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.net.*;
import java.util.*;
import android.graphics.drawable.*;
import android.widget.ImageView;
import android.graphics.*;
import java.io.*;
import java.net.*;
import android.widget.TextView;
import android.support.annotation.*;

import com.cocosw.bottomsheet.BottomSheet;
import com.cocosw.bottomsheet.BottomSheetHelper;
import com.cocosw.query.CocoQuery;

public class conActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */
	private TextView title;
	private TextView by;
	private TextView intro;
	private ImageView bg;
	private File fs=new File("/storage/sdcard0/M++/data/fs_con.txt");
	private Data data=new Data();
	private String url;
	private BottomSheet sheet;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		setContentView(R.layout.con);
		bg= (ImageView) findViewById(R.id.con_show );
		title= (TextView) findViewById(R.id.con_maintitle);
		by= (TextView) findViewById(R.id.con_by);
		intro= (TextView) findViewById(R.id.con_int);
		Intent intent= getIntent();
		int pos = intent.getIntExtra("pos", 0);
		String picurl=data.getData(fs,pos*6+2);
		String titletex=data.getData(fs,pos*6+1);
		String bytex=data.getData(fs,pos*6+3);
		String mtex=data.getData(fs,pos*6+4);
		url=data.getData(fs,pos*6+5);
		title.setText(titletex);
		by.setText(bytex);
		intro.setText(mtex);
		loadImage(bg,picurl);
		
		}
	@SuppressWarnings("deprecation")
    @Nullable
    @Override
    protected Dialog onCreateDialog(final int position, Bundle args) {
		
		sheet = getShareActions("分享一个不错的资源:"+title.getText().toString()+"\n"+intro.getText().toString()+"\n"+"如果你喜欢，链接在这里"+url).build();
		sheet.setTitle("分享至...");
		
		return sheet;
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
	
	
	private void StartUrl(Uri uri){
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);  
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		startActivity(intent); 
	}
	public void surl(View view){
	
		StartUrl(Uri.parse(url));
	}
	private BottomSheet.Builder getShareActions(String text) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);

        return BottomSheetHelper.shareAction(this, shareIntent);
    }
public void to_share(View view){
	
	showDialog(0);
	
}

		}
		
