package com.minecraftpe.doubleplus;


import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.TextView;
import android.content.*;
import android.graphics.*;
import java.io.*;
import android.content.pm.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;


public class aboutActivity extends AppCompatActivity
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
//		
setContentView(R.layout.about);
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
		
				
		TextView firstst = (TextView) findViewById(R.id.firstst);
		TextView firstalp = (TextView) findViewById(R.id.firstalp);
		TextView opt = (TextView) findViewById(R.id.opt);
		TextView nowalp = (TextView) findViewById(R.id.nowalp);
		File fdataf = new File("/storage/sdcard0/M++/data/应用数据1.txt");
		File ftime = new File("/storage/sdcard0/M++/data/打开应用次数.txt");
		String versionName=null;
		try
		{
			PackageInfo pkg = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
			versionName = pkg.versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{
		
		}
		nowalp.setText(versionName);
		Data data=new Data();
		if(fdataf.exists()){
		String fd=data.getData(fdataf,1);
		firstst.setText(fd);
	    String sd=data.getData(fdataf,2);
			firstalp.setText(sd);
        } 
		if(ftime.exists()){
			String ft=data.getData(ftime,1);
			opt.setText(ft);
			}

	}
	

	

}
