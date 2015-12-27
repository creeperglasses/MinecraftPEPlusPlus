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
import android.widget.EditText;
import android.support.design.widget.*;
import java.util.concurrent.*;
import android.net.*;


public class loadjsActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
	EditText path;
	EditText cpath;
	EditText time;
	EditText key;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_blue_dark));}
		setContentView(R.layout.loadjs);
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
		path=(EditText) findViewById(R.id.path);		
		cpath=(EditText) findViewById(R.id.cpath);	
		time=(EditText) findViewById(R.id.time);	
		key=(EditText) findViewById(R.id.key);		
	}
	@Override
    public void onStop()
	{
	super.onStop();
		File fdc=new File("/storage/sdcard0/M++/data/load.js");
		if(fdc.exists()){
			fdc.delete();
		}
		}
	public void load(View view){
		
		String fpath=path.getText().toString();
		loadto("*/*",Uri.parse("file://"+fpath));
		
	}
	public void cload(View view){
		String fpath=cpath.getText().toString();
		String keystring=key.getText().toString();
		int etime=Integer.valueOf(time.getText().toString());
		File fdc=new File("/storage/sdcard0/M++/data/load.js");
		if(fdc.exists()){
			fdc.delete();
		}
		File fp=new File(fpath);
		Data data=new Data();
		EncodeString ec=new EncodeString(keystring);
		String ad=data.getAllData(fp);
		String code=ad;
		for(int i=0;i<etime;i++){
			code=ec.decode(code);
		}
		try
		{
			data.writeData(fdc, code, false);
		}
		catch (IOException e)
		{}
		loadto("*/*",Uri.parse("file:///storage/sdcard0/M++/data/load.js"));
	}
	public void loadto(String type, Uri uri) {
        Intent intent;
		intent = new Intent("android.intent.action.VIEW");
		
            intent.setDataAndType(uri, type);
        	startActivity(intent);
        
    }
	
	}
