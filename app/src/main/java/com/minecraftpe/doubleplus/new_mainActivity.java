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

public class new_mainActivity extends AppCompatActivity
{
	
	
    /** Called when the activity is first created. */
	FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	private long mExitTime;
	private List<ConRecItem> listmain;
	private SwipeRefreshLayout mSwipeLayout;
	public String cpo;
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
        setContentView(R.layout.new_main);
		initData();
		File[] files=getCacheDir().listFiles();
		for(File f:files)
		{
			f.delete();
		}
		int displayWidth=0;
		int displayHeight=0;
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		displayWidth = displayMetrics.widthPixels;
		displayHeight = displayMetrics.heightPixels;
		final int rpos=displayWidth/320;
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("M++");
		toolbar.setNavigationIcon(R.drawable.list);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);		
		mCollapsingToolbarLayout.setTitle("M++");
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
		final Handler handler=new Handler();
		new Thread(){
			public void run(){

				handler.post(modTick);
			}
		}.start();
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
							Intent intent = new Intent(new_mainActivity.this,studioActivity.class);
							startActivity(intent);	
							
							finish();
							break;
						case R.id.check:
							Intent intentcheck = new Intent(new_mainActivity.this,checkActivity.class);
							startActivity(intentcheck);	
							break;	
						case R.id.about:
							Intent intentabout = new Intent(new_mainActivity.this,aboutusActivity.class);
							startActivity(intentabout);	
							
							break;		
						case R.id.look:
							Intent intentlook = new Intent(new_mainActivity.this,mpp_user_showActivity.class);
							startActivity(intentlook);
							
							
							break;		
							case R.id.tool:
							Intent intenttool = new Intent(new_mainActivity.this,jsentActivity.class);
							startActivity(intenttool);
							
							break;	
						case R.id.enj:
							Intent intentenj = new Intent(new_mainActivity.this,mcpeActivity.class);
							startActivity(intentenj);

							break;	
							case R.id.ret:
							Intent intentret = new Intent(new_mainActivity.this,maininputActivity.class);
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
		RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
				switch(position){
					default:
						cpo=String.valueOf(position);	
						System.out.println(cpo);
						File fp=new File("/storage/sdcard0/M++/data/con_view/pos.txt");
						if(fp.exists()){
						fp.delete();	
						}
							try
							{
								fp.createNewFile();
								Data data=new Data();
								data.writeData(fp,cpo,false);
							}
							
							catch (IOException e)
							{}
						Intent intent = new Intent(new_mainActivity.this, conActivity.class);
						intent.putExtra("pos", position);
						startActivity(intent);	
						break;	
				}
			}
        };
		final ConRecAdapter adapter=new ConRecAdapter(listmain,itemClickListener);
		RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(rpos,StaggeredGridLayoutManager.VERTICAL));
		recyclerView.setAdapter(adapter);
		
		final Handler load = new Handler();

		final Runnable task = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				initData();
				RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(rpos,StaggeredGridLayoutManager.VERTICAL));
				recyclerView.setAdapter(adapter);
				load.postDelayed(this, 100000);
			}};
		load.postDelayed(task, 10000);
		final Runnable listload = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				initData();
				load.postDelayed(this, 240000);
				}
				};
		load.postDelayed(listload, 17000);
		
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
					
					RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
					recyclerView.setLayoutManager(new StaggeredGridLayoutManager(rpos,StaggeredGridLayoutManager.VERTICAL));
					
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_menu, menu);
		return true;

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}
	public void toa(View view){
		Intent intent = new Intent(new_mainActivity.this,checkActivity.class);
		startActivity(intent);	
		
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
	

	public void login(View view){
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerLayout.closeDrawers();
		File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
		if(!fuser.exists()){
		Intent intent = new Intent(new_mainActivity.this,loginActivity.class);
		startActivity(intent);	
		
								  }
		 else{
			 Intent intent = new Intent(new_mainActivity.this,userActivity.class);
				 startActivity(intent);	
				 							  
	 }
		
	}	
	
	public void initData() {
        listmain=new ArrayList<ConRecItem>();
new Thread(listadd).start();
		/*ConRecItem p3=new ConRecItem("http://b.hiphotos.baidu.com/shitu/pic/item/faedab64034f78f04e89982a7f310a55b2191c6c.jpg","Hello Great Wall"); listmain.add(p3);
		ConRecItem p4=new ConRecItem("http://h.hiphotos.baidu.com/shitu/pic/item/d043ad4bd11373f02d5a6346a20f4bfbfaed0485.jpg","Hello GTA IV"); listmain.add(p4);
		ConRecItem p6=new ConRecItem("http://f.hiphotos.baidu.com/shitu/pic/item/b21c8701a18b87d6f244a4c6010828381e30fde1.jpg","Hello MCPE Development"); listmain.add(p6);
		ConRecItem p7=new ConRecItem("http://d.hiphotos.baidu.com/shitu/pic/item/29381f30e924b8997ac90fe168061d950b7bf676.jpg","Hello Material Design"); listmain.add(p7);
		*/
		
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
	Runnable listadd = new Runnable(){
		@Override        
		public void run() {

			File file=new File("/storage/sdcard0/M++/data/mpp_con.txt");
			File fs=new File("/storage/sdcard0/M++/data/fs_con.txt");
		if(fs.exists()){
			fs.delete();
		}
			try
			{
				ftp.openConnect();
				ftp.download("/hellowotl/web/htmls/","mpp_con.txt","/storage/sdcard0/M++/data/");
				
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
						ConRecItem p=new ConRecItem(ss[6*i+1],ss[6*i]); listmain.add(p);
					}
				
					catch(ArrayIndexOutOfBoundsException e){

					}
					
				}
			
				Data data=new Data();
				data.copyFile("/storage/sdcard0/M++/data/mpp_con.txt","/storage/sdcard0/M++/data/fs_con.txt");
				file.delete();
			}

			catch (FileNotFoundException e)
			{
				//e.printStackTrace(); 	
			}

			
			}
			};
			
	}
