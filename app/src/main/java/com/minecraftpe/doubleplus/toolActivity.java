package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;


public class toolActivity extends Activity
{
    /** Called when the activity is first created. */
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
		
		setContentView(R.layout.tool);
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
	public void start_file_act(View view){
		Intent intent = new Intent(toolActivity.this,fileActivity.class);
		startActivity(intent);	
	}
	public void start_mail_act(View view){
		Intent intent = new Intent(toolActivity.this,mailActivity.class);
		startActivity(intent);	
	}
}
