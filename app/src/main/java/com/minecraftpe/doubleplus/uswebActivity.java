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
import android.graphics.*;

public class uswebActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */
private int type;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		setContentView(R.layout.usweb);
		Intent intent= getIntent();
		type = intent.getIntExtra("type", 0);
		WebView webView = (WebView) findViewById(R.id.us_web);
		webView.getSettings().setJavaScriptEnabled(true);
		switch(type){
			case 1:
				webView.loadUrl("file:///android_asset/mpp_andus.html");
			break;
			case 2:
				webView.loadUrl("file:///android_asset/mpp_user_show.html");
				
			break;
			case 3:
				webView.loadUrl("file:///android_asset/mpp_donate.html");
				
			break;
			default:
			
			break;
		}
		}
		}
	
