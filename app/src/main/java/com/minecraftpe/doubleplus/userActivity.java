package com.minecraftpe.doubleplus;


import android.app.*;
import android.os.*;
import android.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.net.*;
import android.support.design.widget.*;
import android.graphics.*;
import android.widget.ImageView;
import java.io.*;
import android.content.*;

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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_menu, menu);
		return true;

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
		
			default:

				break;
		}
		return super.onOptionsItemSelected(item);
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
