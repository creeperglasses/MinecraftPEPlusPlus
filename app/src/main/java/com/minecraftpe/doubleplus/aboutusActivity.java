package com.minecraftpe.doubleplus;


import android.app.*;
import android.os.*;
import android.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.net.*;
import android.support.design.widget.*;
import android.graphics.*;
import android.widget.ImageView;
import java.io.*;
import android.content.*;

public class aboutusActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */


	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);


		setContentView(R.layout.aboutus);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(Color.TRANSPARENT);
		}
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.ret);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					onBackPressed();
				} });
		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);		
		mCollapsingToolbarLayout.setTitle("关于我们");
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);//设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

		}
	public void str_mcdev(View view){	
		Intent intent = new Intent(aboutusActivity.this,mcdevActivity.class);
		startActivity(intent);
	}
	public void togit(View view){
		StartUrl(Uri.parse("https://git.coding.net/helow/MCPE_Development.git"));
		
	}
	public void toabout(View view){
		Intent intent = new Intent(aboutusActivity.this,uswebActivity.class);
		intent.putExtra("type", 1);
		startActivity(intent);	
	}
	public void touser(View view){
		Intent intent = new Intent(aboutusActivity.this,uswebActivity.class);
		intent.putExtra("type", 2);
		startActivity(intent);	
	}
	public void todonate(View view){
		Intent intent = new Intent(aboutusActivity.this,uswebActivity.class);
		intent.putExtra("type", 3);
		startActivity(intent);	
	}
	private void StartUrl(Uri uri){
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);  
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		startActivity(intent); 
	}
		}
