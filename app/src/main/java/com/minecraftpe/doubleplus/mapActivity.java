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

public class mapActivity extends AppCompatActivity
{
    
	/** Called when the activity is first created. */

	private SwipeRefreshLayout mSwipeLayout;
	private List<ConRecItem> listmain;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		
		setContentView(R.layout.map);
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
		RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
       	recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
		initData();
		RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

			}};

		final ConRecAdapter adapter=new ConRecAdapter(listmain,itemClickListener);
        recyclerView.setAdapter(adapter);	
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
					RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
					recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
					initData();
					recyclerView.setAdapter(adapter);	
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
	
	private void initData() {
        listmain=new ArrayList<ConRecItem>();
		ConRecItem p3=new ConRecItem("http://b.hiphotos.baidu.com/shitu/pic/item/faedab64034f78f04e89982a7f310a55b2191c6c.jpg","Hello Great Wall"); listmain.add(p3);
		ConRecItem p4=new ConRecItem("http://h.hiphotos.baidu.com/shitu/pic/item/d043ad4bd11373f02d5a6346a20f4bfbfaed0485.jpg","Hello GTA IV"); listmain.add(p4);
		ConRecItem p6=new ConRecItem("http://f.hiphotos.baidu.com/shitu/pic/item/b21c8701a18b87d6f244a4c6010828381e30fde1.jpg","Hello MCPE Development"); listmain.add(p6);
		ConRecItem p7=new ConRecItem("http://d.hiphotos.baidu.com/shitu/pic/item/29381f30e924b8997ac90fe168061d950b7bf676.jpg","Hello Material Design"); listmain.add(p7);
		
		
		}
	
	}
