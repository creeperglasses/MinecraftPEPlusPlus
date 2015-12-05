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
import android.net.*;
import android.support.design.widget.*;
import android.graphics.*;
import java.io.*;
import android.widget.EditText;
import android.text.*;

public class maininputActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */
String it;
	private SwipeRefreshLayout mSwipeLayout;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);


		setContentView(R.layout.maininput);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(R.color.holo_blue_bright);
		}
	
		
		}
	public void res(View view){
		Intent intent = getIntent();
		 EditText input = (EditText) findViewById(R.id.input);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		
		it = intent.getStringExtra("inputtype");	
		if(it.equals("1")){
			File flocal = new File("/storage/sdcard0/M++/user/local");	
			
			if(!flocal.exists()){
				flocal.mkdirs();
			}
			
			if(!TextUtils.isEmpty(input.getText())){	
				new Thread(inw).start();   
			}
			else{
				Snackbar.make(fab,"不能为空", Snackbar.LENGTH_LONG).show();

			}
		}
		if(it.equals("2")){

			if(!TextUtils.isEmpty(input.getText())){	
				Intent data=new Intent(Intent.ACTION_SENDTO);
				data.setData(Uri.parse("mailto:2300426346@qq.com"));
				data.putExtra(Intent.EXTRA_SUBJECT, "M++用户反馈");
				data.putExtra(Intent.EXTRA_TEXT,input.getText().toString());
				startActivity(data);	
			}
			else{
				Snackbar.make(fab,"不能为空", Snackbar.LENGTH_LONG).show();
			}
			
			}
	}
	Runnable inw = new Runnable(){
		@Override        
		public void run() {
			File fuser = new File("/storage/sdcard0/M++/user/local/userbg.txt");	
			EditText input = (EditText) findViewById(R.id.input);
			FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
			String ittext=input.getText().toString();	
			try
			{
				if(fuser.exists()){
					fuser.delete();
				}		
				fuser.createNewFile();
				FileOutputStream out = new FileOutputStream(fuser, true);

				String usmb=ittext+"\n";
				out.write(usmb.getBytes("UTF-8"));
				finish();
			}
			catch (IOException e)
			{
			
				Snackbar.make(fab,e.toString(), Snackbar.LENGTH_LONG).show();
			
			}
			
		}
		};
		}
