package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.webkit.*;
import android.support.v4.widget.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.widget.TextView;
import java.io.*;
import android.widget.ImageView;
import android.graphics.*;

public class studioActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */
	long mExitTime;
	private SwipeRefreshLayout mSwipeLayout;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}

		setContentView(R.layout.studio);
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("M++");
		toolbar.setNavigationIcon(R.drawable.list);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		TextView user_show = (TextView) findViewById(R.id.user_show);
		final Handler handler=new Handler();
		new Thread(){
			public void run(){

				handler.post(modTick);
			}
		}.start();
		Data data=new Data();
		File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
		if(fuser.exists()){
			File fname=new File(data.getData(fuser,1));
			user_show.setText(data.getData(fname,1));
		}
		NavigationView view =(NavigationView) findViewById(R.id.navigation_view);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
			@Override 
				public void onClick(View v) { 
					DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
					drawerLayout.openDrawer(GravityCompat.START);
			
				} });	
		view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

				@Override
				public boolean onNavigationItemSelected(MenuItem item)
				{
					// TODO: Implement this method
					DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
					
					switch(item.getItemId()){
						case R.id.thing:
							Intent intent = new Intent(studioActivity.this,new_mainActivity.class);
							startActivity(intent);	
							
							finish();
							break;
						case R.id.check:
							Intent intentcheck = new Intent(studioActivity.this,checkActivity.class);
							startActivity(intentcheck);
							
							break;		
						case R.id.about:
							Intent intentabout = new Intent(studioActivity.this,aboutActivity.class);
							startActivity(intentabout);	
							
							break;
						case R.id.look:
							Intent intentlook = new Intent(studioActivity.this,mpp_user_showActivity.class);
							startActivity(intentlook);
							
							break;				
					case R.id.tool:
							Intent intenttool = new Intent(studioActivity.this,jsentActivity.class);
							startActivity(intenttool);	
							
							break;	
							case R.id.enj:
							Intent intentenj = new Intent(studioActivity.this,mcpeActivity.class);
							startActivity(intentenj);

							break;	
	
						case R.id.ret:
							Intent intentret = new Intent(studioActivity.this,maininputActivity.class);
							String it="2";
							intentret.putExtra("inputtype", it);	
							startActivity(intentret);		
							break;		
							default:
							
							break;
							
					}
					drawerLayout.closeDrawers();
					return true;
				}



			});
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		MppFragmentPagerAdapter adapter = new MppFragmentPagerAdapter(getSupportFragmentManager(),
																	this);
		viewPager.setAdapter(adapter);
//TabLayout
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setupWithViewPager(viewPager);
		
				}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 判断两次点击的时间间隔（默认设置为2秒）
			DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			drawerLayout.closeDrawers();
			
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				
				DrawerLayout drawerayout = (DrawerLayout) findViewById(R.id.drawer_layout);
				
				Snackbar.make(drawerayout, "再按一次退出M++", Snackbar.LENGTH_LONG).show();
				
				
					mExitTime	= System.currentTimeMillis();
			}
			else
			{
				finish();
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}	
	Runnable modTick = new Runnable(){
		@Override        
		public void run() {

			ImageView utbg = (ImageView) findViewById(R.id.utbg);
			Data data=new Data();   
			File fuser = new File("/storage/sdcard0/M++/user/local/userbg.txt");	
			if(fuser.exists()){
				BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
				Bitmap bm = BitmapFactory.decodeFile(data.getData(fuser,1), options);
                utbg.setImageBitmap(bm);
			}

		}
	};
public void login(View view){
	DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	
	drawerLayout.closeDrawers();
	File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
	if(!fuser.exists()){
		Intent intent = new Intent(studioActivity.this,loginActivity.class);
		startActivity(intent);	
		
	}
	else{
		Intent intent = new Intent(studioActivity.this,userActivity.class);
		startActivity(intent);	
		}
	
}	
}
