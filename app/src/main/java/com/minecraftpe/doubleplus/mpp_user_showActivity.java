package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.webkit.*;
import android.support.v4.widget.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.drawable.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.graphics.*;

public class mpp_user_showActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */

	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				getWindow().setStatusBarColor(Color.TRANSPARENT);}
		setContentView(R.layout.mpp_user_show);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitle(" ");
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					finish();
				} });
		
		WebView webView = (WebView) findViewById(R.id.user_web);

		webView.getSettings().setJavaScriptEnabled(true);

		webView.requestFocus();		
		webView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					lodurl(view, url);
					return false;
				} 
			});
		WebView webview = (WebView) findViewById(R.id.user_web);
		webView.loadUrl("file:///android_asset/mpp_user_show.html");
	}
	public void lodurl(final WebView webView, final String url) {
		new Thread(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl(url);
				}
			});



	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		WebView webView = (WebView) findViewById(R.id.user_web);

		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
