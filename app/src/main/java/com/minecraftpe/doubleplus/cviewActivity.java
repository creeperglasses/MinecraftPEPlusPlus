package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.net.*;
import java.util.*;
import android.graphics.*;
import android.support.v4.view.*;
import android.support.design.widget.*;
import java.io.*;
import android.content.*;
import android.widget.EditText;

public class cviewActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */
	public int cpos;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		setContentView(R.layout.cview);
		final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		ConPageAdapter adapter=new ConPageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		Intent intent= getIntent();
		File fp=new File("/storage/sdcard0/M++/data/con_view/pos.txt");
		Data data=new Data();
		cpos = Integer.valueOf(data.getData(fp,1));
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		
		toolbar.setTitle(intent.getStringExtra("titlemain"));
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		
//TabLayout
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
		tabLayout.setTabsFromPagerAdapter(adapter);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setupWithViewPager(viewPager);
		Random random = new Random();
		int i=random.nextInt(16777216);
		String hex = Integer.toHexString(i);
		try{
			toolbar.setBackgroundColor(Color.parseColor("#"+hex));
			tabLayout.setBackgroundColor(Color.parseColor("#"+hex));
			
			}
		catch(IllegalArgumentException e){

		}
		}
		public void send(View view){
			
			con_pageb cpb=new con_pageb();
			System.out.println(cpos);
			EditText ctx = (EditText)findViewById(R.id.ctx);
			String push=ctx.getText().toString();
			cpb.sendto(push,cpos);
		}
		}
