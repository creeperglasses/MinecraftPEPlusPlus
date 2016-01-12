package com.minecraftpe.doubleplus;
import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.io.*;


public class user_settingsActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
	private EditText sign;
	private EditText intro;
	private LinearLayout layout;
	private File user;
	private String up;
	FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_blue_dark));}

		setContentView(R.layout.user_settings);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("编辑用户信息");
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);
		layout=(LinearLayout) findViewById(R.id.layout);
		sign=(EditText) findViewById(R.id.sign);
		intro=(EditText) findViewById(R.id.intro);
		Data data=new Data();
		File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
		up=data.getAllData(fuser);
		System.out.println(up);
		user=new File(up);
		sign.setText(data.getData(user,3));
		intro.setText(data.getData(user,4));
			}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.us_menu, menu);
		return true;

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
			case R.id.done:
				String	st=sign.getText().toString();
				String it=intro.getText().toString();
				Data data=new Data();
				data.changeLine(up,"/storage/sdcard0/M++/user/sever/user.txt",3,st);
				data.changeLine(up,"/storage/sdcard0/M++/user/sever/user.txt",4,it);
				new Thread(update).start();
				
				break;
			default:

				break;
		}
		return super.onOptionsItemSelected(item);
	}	
	Runnable update = new Runnable(){
		@Override        
		public void run() { 
			try
			{
				ftp.openConnect();
				up=user.getName();
				ftp.ftpUpload("ftp39.idcay.com", "21", "hellowotl", "F5B385C8B1e8dc", "/hellowotl/web/signed/", "/storage/sdcard0/M++/user/sever/", up);
				finish();
			}
			catch (IOException e)
			{}
		}
	};
			}
