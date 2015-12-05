package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.view.View.*;
import android.graphics.*;
import java.util.*;
import android.support.v4.widget.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v4.view.*;
import android.support.v7.widget.*;
import android.widget.TextView;
import java.io.*;
import android.widget.EditText;



public class jsentActivity extends AppCompatActivity
{
	 @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			Window window = getWindow();
//// 透明状态栏
//			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//							WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);// 透明导航栏
//		}
        setContentView(R.layout.jsent);
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
		}
	public void start_run(View view){
		Intent intent = new Intent(jsentActivity.this,jsrunActivity.class);
		EditText jscode = (EditText) findViewById(R.id.jscode);
		intent.putExtra("jscode", jscode.getText().toString());
		startActivity(intent);	
		}
	
}
