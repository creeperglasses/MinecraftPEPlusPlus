package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.EditText;
import android.content.*;
import android.graphics.*;
import java.io.*;
import android.text.*;
import android.net.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;

public class checkActivity extends AppCompatActivity
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
		setContentView(R.layout.check);
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
		public void send(View view){
			FloatingActionButton fab_send = (FloatingActionButton) findViewById(R.id.fab_sent);
		final EditText ind = (EditText) findViewById(R.id.ind);
		final EditText name = (EditText) findViewById(R.id.name);
		final EditText who = (EditText) findViewById(R.id.who);
		final EditText byt = (EditText) findViewById(R.id.byt);
		final EditText byw = (EditText) findViewById(R.id.byw);
		final EditText byurl = (EditText) findViewById(R.id.byurl);
		final EditText styn = (EditText) findViewById(R.id.styn);
		final EditText stw = (EditText) findViewById(R.id.stw);
		final EditText sturl = (EditText) findViewById(R.id.sturl);
		final EditText wiu = (EditText) findViewById(R.id.wiu);
		final EditText retyn = (EditText) findViewById(R.id.retyn);
		final EditText type = (EditText) findViewById(R.id.type);
		final EditText iurl = (EditText) findViewById(R.id.iurl);
		final EditText pic = (EditText) findViewById(R.id.picurl);
		final EditText lurl = (EditText) findViewById(R.id.lurl);
				

		if(!TextUtils.isEmpty(wiu.getText())){	

			try{
				Intent data=new Intent(Intent.ACTION_SENDTO);
				data.setData(Uri.parse("mailto:2300426346@qq.com"));
				data.putExtra(Intent.EXTRA_SUBJECT, "M++"+type.getText().toString()+"审核");
				data.putExtra(Intent.EXTRA_TEXT,"作品名称:"+name.getText().toString()+"\n"
							  +"作品作者:"+who.getText().toString()+"\n"
							  +"作品介绍:"+ind.getText().toString()+"\n"
							  +"作品url:"+iurl.getText().toString()+"\n"
							  +"图片url:"+pic.getText().toString()+"\n"  
							  +"是否原创:"+byt.getText().toString()+"\n"
							  +"原作者:"+byw.getText().toString()+"\n"
							  +"同意声明url:"+byurl.getText().toString()+"\n"
							  +"是否代表工作室:"+styn.getText().toString()+"\n"
							  +"所属工作室:"+stw.getText().toString()+"\n"
							  +"logourl:"+sturl.getText().toString()+"\n"
							  +"外部链接:"+lurl.getText().toString()+"\n"  
							  +"上传者:"+wiu.getText().toString()+"\n"
							  +"是否返回消息:"+retyn.getText().toString());
				startActivity(data);
			}
			catch(Exception e){
				
				Snackbar.make(fab_send, e.toString(), Snackbar.LENGTH_LONG).show();
				
			}
		}
		else{
			Snackbar.make(fab_send,"作品上传者不能为空", Snackbar.LENGTH_LONG).show();
			
		}
	}
	}
	
	
