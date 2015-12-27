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


public class mc_managerActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
	EditText text;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_blue_dark));}
		setContentView(R.layout.mc_manager);
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
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mcm_menu, menu);
		return true;

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()){
			case R.id.remake:
				String pathtext=text.getText().toString();
				String path=pathtext+"/level.dat";
				EditText output=(EditText) findViewById(R.id.output);		
				try
				{
					byte []outputtext=HexString.readFile(path);
					output.setText(HexString.cformat(outputtext));
					FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
					Snackbar.make(fab, "已整理解析内容", Snackbar.LENGTH_LONG).show();

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			break;
			default:
			break;
			}
		return super.onOptionsItemSelected(item);
	}
	public void encode(View view) {
		
		String pathtext=text.getText().toString();
		String path=pathtext+"/level.dat";
		EditText output=(EditText) findViewById(R.id.output);		
		try
		{
			byte []outputtext=HexString.readFile(path);
			output.setText(HexString.format(outputtext));
			FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
			Snackbar.make(fab, "存档已解析", Snackbar.LENGTH_LONG).show();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		}
		
		}
