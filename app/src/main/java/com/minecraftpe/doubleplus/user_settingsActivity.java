package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import java.io.*;

public class user_settingsActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		
		setContentView(R.layout.user_settings);
		ActionBar actionBar=getActionBar();

		actionBar.setDisplayShowHomeEnabled(false);
		
		
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME| ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		TextView user_show = (TextView) findViewById(R.id.user_show);
		Data data=new Data();
		File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
		if(fuser.exists()){
			File fname=new File(data.getData(fuser,1));
			user_show.setText(data.getData(fname,1));
			}
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
		
		
	


	
		
	
	public void start_about_act(View view){
		Intent intent = new Intent(user_settingsActivity.this,aboutActivity.class);
		startActivity(intent);	
	}
	public void start_setting_act(View view){
		Intent intent = new Intent(user_settingsActivity.this,settingActivity.class);
		startActivity(intent);	
	}
	public void start_login_act(View view){
		Intent intent = new Intent(user_settingsActivity.this,loginActivity.class);
		startActivity(intent);	
	}
	public void start_tool_act(View view){
		Intent intent = new Intent(user_settingsActivity.this,toolActivity.class);
		startActivity(intent);	
	}
	public void start_check_act(View view){
		Intent intent = new Intent(user_settingsActivity.this,checkActivity.class);
		startActivity(intent);	
	}
	public void start_sto_act(View view){
		Intent intent = new Intent(user_settingsActivity.this,studioActivity.class);
		startActivity(intent);	
	}
	}
