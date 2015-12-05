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

public class severActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */

	private SwipeRefreshLayout mSwipeLayout;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
// 透明状态栏
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
							WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);// 透明导航栏
		}
		setContentView(R.layout.sever);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("M++");
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					finish();	
				} });	
		WebView webView = (WebView) findViewById(R.id.sev_web);

		webView.getSettings().setJavaScriptEnabled(true);

		webView.requestFocus();		
		webView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					lodurl(view, url);
					return false;
				}
			});
		WebView webview = (WebView) findViewById(R.id.sev_web);
		webview.loadUrl("http://helloworldcreeper.com/htmls/mpp_sever.html");
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
					WebView webView = (WebView) findViewById(R.id.sev_web);

					webView.loadUrl(webView.getUrl());
					new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								mSwipeLayout.setRefreshing(false);
							}
						}, 5000);
				}
			});
		mSwipeLayout.setColorSchemeResources(R.color.holo_blue_bright,
											 R.color.holo_blue_bright, R.color.holo_blue_bright,
											 R.color.holo_blue_bright);

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
		WebView webView = (WebView) findViewById(R.id.sev_web);

		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
