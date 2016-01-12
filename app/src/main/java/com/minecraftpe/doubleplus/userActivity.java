package com.minecraftpe.doubleplus;

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
import android.widget.ImageView;
import java.io.*;

public class userActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */

	
	
	private Handler handler=null;
	private TextView intro;
	private TextView sign;
	public String st;
	public String it;
	private File user;
	private String up;
	FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.user);
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
		mCollapsingToolbarLayout.setTitle("个人中心");
		mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
		mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
		handler=new Handler();
		new Thread(){
			public void run(){
			
				handler.post(modTick);
			}
			
		}.start();
		sign=(TextView) findViewById(R.id.sign);
		intro=(TextView) findViewById(R.id.introduction);
		
		Data data=new Data();
		File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
		up=data.getAllData(fuser);
		System.out.println(up);
		user=new File(up);
		if(data.getLine(user)==3){
			try
			{
				data.writeData(user, "编辑你的个性签名"+"\n", false);
				data.writeData(user, "编辑你的个人简介"+"\n", false);
				new Thread(update).start();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		
		}
			sign.setText(data.getData(user,3));
			intro.setText(data.getData(user,4));
			st=sign.getText().toString();
			it=intro.getText().toString();
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_menu, menu);
		return true;

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
		case R.id.edit :
				Intent intent = new Intent(userActivity.this,user_settingsActivity.class);
				startActivity(intent);
				
			break;
			default:

				break;
		}
		return super.onOptionsItemSelected(item);
	}
	public void str_input(View view){
		String it="1";
		Intent intent = new Intent(userActivity.this,maininputActivity.class);
		intent.putExtra("inputtype", it);
		startActivity(intent);	
		
		
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
			public void view_sign(View view){
				AlertDialog dialog=(new AlertDialog.Builder(userActivity.this)).setMessage(st)
					.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1,int p2){
							
						}
					})
					.create();
					dialog.show();
			}	
			
			public void view_intro(View view){
				AlertDialog dialog=(new AlertDialog.Builder(userActivity.this)).setMessage(it)
					.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1,int p2){

						}
					})
					.create();
				dialog.show();
			}
	Runnable update = new Runnable(){
		@Override        
		public void run() { 
			try
			{
				ftp.openConnect();
				up=user.getName();
			ftp.ftpUpload("ftp39.idcay.com", "21", "hellowotl", "F5B385C8B1e8dc", "/hellowotl/web/signed/", "/storage/sdcard0/M++/user/sever/", up);
			}
			catch (IOException e)
			{}
		}
		};
		}
