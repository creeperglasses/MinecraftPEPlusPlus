package com.minecraftpe.doubleplus;


import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.graphics.*;
import java.io.*;
import android.widget.Toast;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.*;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.net.*;

public class mcpeActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);


		setContentView(R.layout.mcpe);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(Color.TRANSPARENT);
		}
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("MinecraftPE");
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					onBackPressed();
				} });
		String[] downlistg = new String[] { "从Google Play上下载MinecraftPE"};
		String[] downlista= new String[] { "从Amazon Store上下载MinecraftPE"};
		String[] downlistm = new String[] { "从三星应用商店下载MinecraftPE"};
		
		
		ListAdapter adapterg = new ArrayAdapter < String > (this, R.layout.mcpe_down, R.id.intext, downlistg);
        ListView listViewg = (ListView) findViewById(R.id.down_list_g);
		listViewg.setAdapter(adapterg);	
		ListAdapter adaptera = new ArrayAdapter < String > (this, R.layout.mcpe_down, R.id.intext, downlista);
        ListView listViewa = (ListView) findViewById(R.id.down_list_a);
		listViewa.setAdapter(adaptera);	
		ListAdapter adapterm = new ArrayAdapter < String > (this, R.layout.mcpe_down, R.id.intext, downlistm);
        ListView listViewm = (ListView) findViewById(R.id.down_list_m);
		listViewm.setAdapter(adapterm);	
		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);		
		mCollapsingToolbarLayout.setTitle("MinecraftPE");
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
		listViewg.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg, View view, int pos, long tim)
				{
					// TODO: Implement this method
					StartUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.mojang.minecraftpe"));
					}
	});	
		listViewa.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg, View view, int pos, long tim)
				{
					// TODO: Implement this method
					StartUrl(Uri.parse("http://www.amazon.cn/mojang-minecraft-pocket-edition/dp/b00992cf6w"));
					}
			});	
		listViewm.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg, View view, int pos, long tim)
				{
					// TODO: Implement this method
					StartUrl(Uri.parse("http://apps.samsung.cn/topApps/topAppsDetail.as?productId=000000769956"));
					}
			});	
		}
	private boolean isInstallByread(String packageName)
	{
		// TODO: Implement this method
		return new File("/data/data/" + packageName).exists();  
	}
	private void launchApp(String packageName) {  

		if (isInstallByread(packageName)) {  

			Intent intent = getPackageManager().getLaunchIntentForPackage(  
                packageName);  
			startActivity(intent);  
			Toast.makeText(this, "Enjoy!", Toast.LENGTH_LONG).show();		
		}  
		if (!isInstallByread(packageName)) {  

		}  
	}
	public void startmc(View view) 
    {
		launchApp("com.mojang.minecraftpe");
		}
	private void StartUrl(Uri uri){
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);  
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		startActivity(intent); 
	}
		}
		
