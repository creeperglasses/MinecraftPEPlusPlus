package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import java.io.*;

public class settingActivity extends Activity
{
    /** Called when the activity is first created. */
	File fdataf = new File("/storage/sdcard0/M++/data/应用数据1.txt");
	File ftime = new File("/storage/sdcard0/M++/data/打开应用次数.txt");
	File fdata = new File("/storage/sdcard0/M++/data");	
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
		setContentView(R.layout.setting);
		ActionBar actionBar=getActionBar();

		actionBar.setDisplayShowHomeEnabled(false);


		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME| ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		




	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case android.R.id.home:
				finish();
				break;
			default:
				break;
		}
		return true;
	}
	public void return_env_data(View view){
		Data data=new Data();
		data.deleteData(fdataf);
		data.deleteData(ftime);
		Toast.makeText(this, "已重置", Toast.LENGTH_SHORT).show();
		
	}
	public void return_all_data(View view){
		Data data=new Data();
		try
		{
			data.deleteFolderFile("storage/sdcard0/M++/data", true);
		}
		catch (IOException e)
		{
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();	
		}
		Toast.makeText(this, "已重置", Toast.LENGTH_SHORT).show();
		
		}
	}
