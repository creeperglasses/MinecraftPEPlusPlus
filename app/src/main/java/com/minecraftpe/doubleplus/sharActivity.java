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
import android.widget.ImageView;
import org.apache.commons.net.nntp.*;
import android.widget.Toast;
import android.util.*;

public class sharActivity extends AppCompatActivity
{


    /** Called when the activity is first created. */
	FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	private long mExitTime;
	private List<SharRecItem> listmain;
	private SwipeRefreshLayout mSwipeLayout;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		 Window window = getWindow();
		 // 透明状态栏
		 window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
		 WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		 window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);// 透明导航栏
		 }*/
        setContentView(R.layout.shar);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.list);
		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);		
		mCollapsingToolbarLayout.setTitle("分享中心");
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

		setSupportActionBar(toolbar);
		TextView user_show = (TextView) findViewById(R.id.user_show);
		Data data=new Data();
		File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
		if(fuser.exists()){
			File fname=new File(data.getData(fuser,1));
			user_show.setText(data.getData(fname,1));
		}
		
		final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
					switch(item.getItemId()){
						case R.id.show:
							Intent intent = new Intent(sharActivity.this,studioActivity.class);
							startActivity(intent);	

							finish();
							break;

						case R.id.about:
							Intent intentabout = new Intent(sharActivity.this,aboutusActivity.class);
							startActivity(intentabout);	

							break;		

						case R.id.tool:
							Intent intenttool = new Intent(sharActivity.this,codeActivity.class);
							startActivity(intenttool);

							break;	
						case R.id.enj:
							Intent intentenj = new Intent(sharActivity.this,mcpeActivity.class);
							startActivity(intentenj);

							break;	
						case R.id.ret:
							Intent intentret = new Intent(sharActivity.this,maininputActivity.class);
							String it="2";
							intentret.putExtra("inputtype", it);	
							startActivity(intentret);		
							break;	
						case R.id.mcmanager:
							Intent intentmana = new Intent(sharActivity.this,mc_managerActivity.class);
							startActivity(intentmana);

							break;	
						case R.id.loadjs:
							Intent intentloadjs = new Intent(sharActivity.this,loadjsActivity.class);
							startActivity(intentloadjs);

							break;	
						case R.id.thing:
							Intent intentthing = new Intent(sharActivity.this,new_mainActivity.class);
							startActivity(intentthing);	

							finish();
							break;	
						default:

							break;
					}
					drawerLayout.closeDrawers();
					return true;
				}
			});
		RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
				switch(position){
					default:
						
						break;	
				}
			}
        };
		initData();
		final SharRecAdapter adapter=new SharRecAdapter(listmain,itemClickListener);
		RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
		recyclerView.setAdapter(adapter);

		final Handler load = new Handler();

		final Runnable task = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
				recyclerView.setAdapter(adapter);
				//load.postDelayed(this, 100000);
			}};
		load.postDelayed(task, 10000);
		
		final Runnable loadl = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			initData();
			load.postDelayed(this, 20000);
			}};
		load.postDelayed(loadl, 10000);	
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
					RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
					recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

					recyclerView.setAdapter(adapter);	
					new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								mSwipeLayout.setRefreshing(false);
							}
						}, 1000);
				}
			});
		mSwipeLayout.setColorSchemeResources(R.color.holo_blue_bright,
											 R.color.holo_blue_bright, R.color.holo_blue_bright,
											 R.color.holo_blue_bright);
	}	
		
		
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 判断两次点击的时间间隔（默认设置为2秒）
			DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			drawerLayout.closeDrawers();
			if ((System.currentTimeMillis() - mExitTime) > 2000) {

				FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

				Snackbar.make(fab, "再按一次退出M++", Snackbar.LENGTH_LONG).show();

				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void write(View view){
		Intent intent = new Intent(sharActivity.this,insharActivity.class);
			startActivity(intent);	
	}
	public void login(View view){
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerLayout.closeDrawers();
		File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
		if(!fuser.exists()){
			Intent intent = new Intent(sharActivity.this,loginActivity.class);
			startActivity(intent);	

		}
		else{
			Intent intent = new Intent(sharActivity.this,userActivity.class);
			startActivity(intent);	

		}

	}	
	public void initData() {
        listmain=new ArrayList<SharRecItem>();
		new Thread(listadd).start();
		}
	Runnable listadd = new Runnable(){
		@Override        
		public void run() {

			File file=new File("/storage/sdcard0/M++/data/mpp_shar.txt");
			File fs=new File("/storage/sdcard0/M++/data/fs_shar.txt");
			if(fs.exists()){
				fs.delete();
			}
			try
			{
				ftp.openConnect();
				ftp.download("/hellowotl/web/htmls/","mpp_shar.txt","/storage/sdcard0/M++/data/");

			}
			catch (IOException e)
			{}
			try
			{

				FileReader fr=new FileReader(file);

				BufferedReader br=new BufferedReader(fr);

				String temp=null;
				String s="";
				try{
					while ((temp = br.readLine()) != null)
					{
						s += temp + "\n";
					}
				}
				catch (IOException e)
				{
					e.printStackTrace(); 	
				}
				String [] ss=s.split("\n");
				for (int i = 0; i <= ss.length; i++) {
					try{
						int a=5*i+5;
						int b=5*i+4;
						int c=5*i+3;
						System.out.println(a);
						System.out.println(b);
						System.out.println(c);
						String cd=decodeObject(ss[ss.length*5-c]);
						SharRecItem p=new SharRecItem(ss[ss.length*5-a],ss[ss.length*5-b],cd,"http://www.helloworldcreeper.com/signed/userpic/user.png"); listmain.add(p);
					}

					catch(Exception e){

					}

				}

				Data data=new Data();
				data.copyFile("/storage/sdcard0/M++/data/mpp_shar.txt","/storage/sdcard0/M++/data/fs_shar.txt");
				file.delete();
			}

			catch (FileNotFoundException e)
			{
				//e.printStackTrace(); 	
			}


		}
	};
	public String decodeObject(String a){
		EncodeString es=new EncodeString("1234567890abcdef");
		a=es.decode(a);		
		return a;
	}
		}
