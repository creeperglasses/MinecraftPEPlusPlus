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
import android.support.design.widget.*;
import android.graphics.*;
import android.widget.ImageView;
import java.io.*;

public class userActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */

	
	
	private Handler handler=null;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.user);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(Color.TRANSPARENT);
			}
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("M++");
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					onBackPressed();
				} });
		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);		
		mCollapsingToolbarLayout.setTitle("个人中心");
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
		handler=new Handler();
		new Thread(){
			public void run(){
			
				handler.post(modTick);
			}
		}.start();
		}
	public void str_input(View view){
		String it="1";
		Intent intent = new Intent(userActivity.this,maininputActivity.class);
		intent.putExtra("inputtype", it);
		startActivity(intent);	
		
		
	}
	
	Runnable modTick = new Runnable(){
		@Override        
		public void run() {
	
			   ImageView utbg = (ImageView) findViewById(R.id.utbg);
			Data data=new Data();   
			File fuser = new File("/storage/sdcard0/M++/user/local/userbg.txt");	
			if(fuser.exists()){
				BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
				Bitmap bm = BitmapFactory.decodeFile(data.getData(fuser,1), options);
                utbg.setImageBitmap(bm);
			}
		 
			}
			};
		}
