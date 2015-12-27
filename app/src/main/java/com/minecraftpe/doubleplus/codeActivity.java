package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.TextView;
import android.content.*;
import android.graphics.*;
import java.io.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.widget.EditText;
import android.support.design.widget.*;


public class codeActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
	EditText text;
	EditText newpath;
	EditText keypath;
	EditText time;
	FloatingActionButton fab;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_blue_dark));}
		setContentView(R.layout.code);
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
		 text=(EditText) findViewById(R.id.text);		
		newpath=(EditText) findViewById(R.id.newpath);		
		keypath=(EditText) findViewById(R.id.keypath);	
		time=(EditText) findViewById(R.id.time);		
		fab = (FloatingActionButton) findViewById(R.id.fab);
				}
	public void encode(View view){
		String codetext=text.getText().toString();
		String key=keypath.getText().toString();
		if(key.length()!=16){
			Snackbar.make(fab, "密钥必须等于16位", Snackbar.LENGTH_LONG).show();		
		}
		else{
		File fs=new File(codetext);
		Data data=new Data();
		int tim=Integer.valueOf(time.getText().toString());
		String ad=data.getAllData(fs);
		String adn=RegString.getStringNoBlank(ad);
			if(adn.matches(".*madeBy.*")||adn.matches(".*showSubTitle.*")){
				if(!adn.matches(".*function madeBy.*")&&!adn.matches(".*function showSubTitle.*")){
					ad=ad+ModPELibrary.main;	
				}
			
		}
		EncodeString ec=new EncodeString(key);
		String code=ad;
			for(int i=0;i<tim;i++){
				code=ec.encode(code);
			}
		String path=newpath.getText().toString();
		File fp=new File(path);
		
		if(!fp.exists()){
			try
		{
			fp.createNewFile();
			data.writeData(fp, code, false);
		}
		catch (IOException e)
		{}
		
		Snackbar.make(fab, "代码已加密", Snackbar.LENGTH_LONG).show();
		}
		
		}
	}
				}
