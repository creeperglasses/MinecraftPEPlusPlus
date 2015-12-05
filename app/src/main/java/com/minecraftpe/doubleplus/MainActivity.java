package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.io.*;
import java.text.*;
import android.content.pm.*;
import android.content.pm.PackageManager.*;
import android.webkit.*;
import android.content.*;
import android.net.*;
import android.graphics.*;



public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
	String snt=null;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
		
		File[] files=getCacheDir().listFiles();
		for(File f:files)
		{
			f.delete();
		}
		Typeface robotothin = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Thin.ttf");
		TextView ver=(TextView) findViewById(R.id.ver);
		TextView veryel=(TextView) findViewById(R.id.veryel);
		veryel.setTypeface(robotothin);
		ver.setTypeface(robotothin);
		
		
		WebView webView = (WebView) findViewById(R.id.main_web);
		webView.getSettings().setJavaScriptEnabled(true);
		if(isNetworkConnected(this)){
		webView.loadUrl("http://helloworldcreeper.com/htmls/mppmainshow.html");
		}
		File fmain = new File("/storage/sdcard0/M++");	
		File fdata = new File("/storage/sdcard0/M++/data");	
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
	
			e.printStackTrace(); 	
		}

		try {
			if(!fmain.exists()){
				fmain.mkdirs();
			}
			if(!fdata.exists()){
				fdata.mkdirs();
			}
			if(!fdataf.exists()){
				fdataf.createNewFile();
				FileOutputStream out = new FileOutputStream(fdataf, true);
				SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
				String date = sDateFormat.format(new java.util.Date()); 
				String firstdate=date+"\n";
				String firstalpha=versionName+"\n";
				out.write(firstdate.getBytes("UTF-8"));
				out.write(firstalpha.getBytes("UTF-8"));
			}
			if(!ftime.exists()){
				ftime.createNewFile();
				FileOutputStream out = new FileOutputStream(ftime, true);
				String opentime="0";
				out.write(opentime.getBytes("UTF-8"));
			}
			if(ftime.exists()){
				FileReader fr=new FileReader(ftime);
				BufferedReader br=new BufferedReader(fr);
				String hasopened=null;
				String temp=null;
				String s="";

				try
				{
					while ((temp = br.readLine()) != null)
					{
						s += temp + "\n";
					}
				}
				catch (IOException e)
				{
					e.printStackTrace(); 	
				}
				String [] ss=s.split("\n");
				for (int i = 0; i < ss.length; i++) {
					hasopened=ss[0];
				}
				int newopentime=Integer.parseInt(hasopened);	
				int thisopentime=newopentime+1;
				snt=Integer.toString(thisopentime);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace(); 
		}
		ftime.delete();

		try
		{
			ftime.createNewFile();
			FileOutputStream out = new FileOutputStream(ftime, true);
			out.write(snt.getBytes("UTF-8"));
		}
		catch (IOException e)
		{
			e.printStackTrace(); 

		}
	
		
		Timer timer = new Timer();
	    TimerTask timerTask = new TimerTask() {
	    @Override
        public void run() {
	   // TODO Auto-generated method stub
	   
	   Intent intent = new Intent(MainActivity.this,new_mainActivity.class);
	   startActivity(intent);
	   finish();
	}		
   };
		timer.schedule(timerTask, 4000);
		
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
	
