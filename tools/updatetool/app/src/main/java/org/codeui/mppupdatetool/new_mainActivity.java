package org.codeui.mppupdatetool;

import android.app.*;
import android.os.*;
import android.view.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.widget.EditText;
import java.io.*;
import android.content.*;
import android.net.*;
import org.codeui.mppupdatetool.library.*;
import com.petebevin.markdown.MarkdownProcessor;
import java.text.*;

public class new_mainActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
	private long mExitTime;
	private EditText title;
	private EditText subtit;
	private EditText madeby;
	private EditText mainpic;
	private EditText durl;
	private EditText dsave;
	private EditText pushv;
	private EditText intro;
	private FloatingActionButton fab;
	private FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setContentView(R.layout.new_main);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.statusbarcolor));}
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("M++后台管理器");
		setSupportActionBar(toolbar);
		fab = (FloatingActionButton) findViewById(R.id.fab);
		title=(EditText) findViewById(R.id.title);
		subtit=(EditText) findViewById(R.id.sub_title);
		madeby=(EditText) findViewById(R.id.madeby);
		mainpic=(EditText) findViewById(R.id.mainpic);
		durl=(EditText) findViewById(R.id.download_url);
		pushv=(EditText) findViewById(R.id.version);
		dsave=(EditText) findViewById(R.id.data_save);
		intro=(EditText) findViewById(R.id.introduction);
		File fmain = new File("/storage/sdcard0/M++");	
		if(!fmain.exists()){
			fmain.mkdirs();
		}
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
			default:

				break;
		}
		return super.onOptionsItemSelected(item);
	}	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 判断两次点击的时间间隔（默认设置为2秒）
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
			 Snackbar.make(fab, "再按一次退出M++后台管理器", Snackbar.LENGTH_LONG).show();

				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	public boolean isNetworkConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
			if (mNetworkInfo != null) {  
				return mNetworkInfo.isAvailable();  
			}  
		}  
		return false;  
	}
	public void start_update(View view){
		new Thread(update).start();
	}
	Runnable update = new Runnable(){
		@Override        
		public void run() {

			File fl=new File("/storage/sdcard0/M++/mpp_con.txt");
			File fd=new File("/storage/sdcard0/M++/"+dsave.getText().toString()+".html");
			File fc=new File("/storage/sdcard0/M++/"+dsave.getText().toString()+".txt");
			
			if(fl.exists()){
				fl.delete();
			}
			if(fd.exists()){
				fd.delete();
			}
			if(fc.exists()){
				fc.delete();
			}
			try
			{
				ftp.openConnect();
				if(!ftp.mppCheck("/hellowotl/web/htmls/","updatelock.txt")){
					
				if(!ftp.mppCheck("/hellowotl/web/htmls/mppcon/new/",dsave.getText().toString()+".html")){
				ftp.download("/hellowotl/web/htmls/","mpp_con.txt","/storage/sdcard0/M++/");
				Data data=new Data();
				data.writeData(fl,title.getText().toString()+"\n",false);
				data.writeData(fl,mainpic.getText().toString()+"\n",false);
				data.writeData(fl,madeby.getText().toString()+"\n",false);
				data.writeData(fl,subtit.getText().toString()+"\n",false);
				data.writeData(fl,durl.getText().toString()+"\n",false);
				data.writeData(fl,dsave.getText().toString()+"\n",false);
				data.writeData(fl,codeObject(intro.getText().toString())+"\n",false);
				data.writeData(fl,"\n",false);
				data.writeData(fl,"\n",false);
				data.writeData(fl,"\n",false);
				fd.createNewFile();
				fc.createNewFile();
				data.writeData(fd,"<meta charset='utf-8'>"+"\n",false);
				SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
				String date = sDateFormat.format(new java.util.Date()); 
				data.writeData(fd,date+":推送版本 "+pushv.getText().toString()+"<br>\n",false);
				ftp.ftpUpload("ftp39.idcay.com","21", "hellowotl", "F5B385C8B1e8dc","/hellowotl/web/htmls/","/storage/sdcard0/M++/","mpp_con.txt");
				ftp.ftpUpload("ftp39.idcay.com","21", "hellowotl", "F5B385C8B1e8dc","/hellowotl/web/htmls/mppcon/new/","/storage/sdcard0/M++/",dsave.getText().toString()+".html");
				ftp.ftpUpload("ftp39.idcay.com","21", "hellowotl", "F5B385C8B1e8dc","/hellowotl/web/htmls/mppcon/com/","/storage/sdcard0/M++/",dsave.getText().toString()+".txt");
				Snackbar.make(fab, "上传成功", Snackbar.LENGTH_LONG).show();
				fl.delete();
				fd.delete();
				fc.delete();
				}
				else{
					Snackbar.make(fab, "储存位置已经存在", Snackbar.LENGTH_LONG).show();
					
				}
				}
				else{
					Snackbar.make(fab, "上传保护已开启", Snackbar.LENGTH_LONG).show();
					
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			}};
			public String codeObject(String a){
				EncodeString es=new EncodeString("1234567890abcdef");
				a=es.encode(a);		
				return a;
			}
	}
		
	
