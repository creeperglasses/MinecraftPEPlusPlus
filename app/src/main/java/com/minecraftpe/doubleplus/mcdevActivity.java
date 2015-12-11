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
import java.io.*;
import android.content.*;
import android.support.v4.view.*;

public class mcdevActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */


@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);


		setContentView(R.layout.mcdev);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(Color.TRANSPARENT);
		}
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("M++");
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					onBackPressed();
				} });
		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);		
		mCollapsingToolbarLayout.setTitle("MCPE Development");
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);//设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
	
	ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
	DevFragmentPagerAdapter adapter = new DevFragmentPagerAdapter(getSupportFragmentManager(),
																  this);
	viewPager.setAdapter(adapter);
//TabLayout
	TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
	tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
	tabLayout.setTabMode(TabLayout.MODE_FIXED);
	tabLayout.setupWithViewPager(viewPager);

	
		}
		
	

}
