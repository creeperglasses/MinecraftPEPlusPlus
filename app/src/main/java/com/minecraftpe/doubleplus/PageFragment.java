package com.minecraftpe.doubleplus;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import android.support.annotation.*;
import android.widget.*;
import android.webkit.*;
import android.support.v4.widget.*;
import android.content.*;
import android.net.*;
public class PageFragment extends Fragment {
	public static final String ARGS_PAGE = "args_page";
	private int mPage;
	public static PageFragment newInstance(int page) {
		Bundle args = new Bundle();
		args.putInt(ARGS_PAGE, page);
		PageFragment fragment = new PageFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPage = getArguments().getInt(ARGS_PAGE);
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_page,container,false);
		WebView webView = (WebView) view.findViewById(R.id.sto_web);

		webView.getSettings().setJavaScriptEnabled(true);

		webView.requestFocus();		
		webView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					if(isNetworkConnected(getActivity())){	
					lodurl(view, url);
					}
					return false;
				}
			});
		switch(mPage){
				case 1:
				if(isNetworkConnected(getActivity())){
				webView.loadUrl("http://helloworldcreeper.com/htmls/mpp_sever.html");
		}
				break;
				case 2:
				if(isNetworkConnected(getActivity())){
			webView.loadUrl("http://helloworldcreeper.com/htmls/mpp_sto.html");
				}
			break;
			}
			final SwipeRefreshLayout mSwipeLayout;
		mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_sto);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
					
					WebView webView = (WebView) view.findViewById(R.id.sto_web);
					if(isNetworkConnected(getActivity())){
					webView.loadUrl(webView.getUrl());
					}
					new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								mSwipeLayout.setRefreshing(false);
							}
						}, 1000);
				}
			});
		mSwipeLayout.setColorSchemeResources(R.color.holo_blue_bright,
											 R.color.holo_blue_bright, R.color.holo_blue_bright,
											 R.color.holo_blue_bright);
		
		return view;
	}
	public void lodurl(final WebView webView, final String url) {
		new Thread(new Runnable() {
				@Override
				public void run() {
					
					webView.loadUrl(url);
				}
			});
		}
	public boolean isNetworkConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
			if (mNetworkInfo != null) {  
				return mNetworkInfo.isAvailable();  
			}  
		}  
		return false;  
	}
}

