package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.EditText;
import android.content.*;
import android.graphics.*;
import java.io.*;
import android.text.*;
import android.net.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.webkit.*;

public class checkActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_blue_dark));}
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			Window window = getWindow();
//// 透明状态栏
//			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//							WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);// 透明导航栏
//		}
		setContentView(R.layout.check);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("添加到M++");
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		
			toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					finish();
				} });	
	
	WebView webview = (WebView) findViewById(R.id.user_web);
	webview.loadUrl("file:///android_asset/mpp_check_show.html");
	}
		public void send(View view){
			FloatingActionButton fab_send = (FloatingActionButton) findViewById(R.id.fab_sent);
				try{
				Intent data=new Intent(Intent.ACTION_SENDTO);
				data.setData(Uri.parse("mailto:2300426346@qq.com"));
				data.putExtra(Intent.EXTRA_SUBJECT, "");
				data.putExtra(Intent.EXTRA_TEXT,"");
				startActivity(data);
			}
			catch(Exception e){
				
				Snackbar.make(fab_send, e.toString(), Snackbar.LENGTH_LONG).show();
				
			}
		
	}
	}
	
	
