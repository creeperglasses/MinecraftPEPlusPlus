package com.minecraftpe.doubleplus;
import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.EditText;
import java.io.*;
import java.text.*;

public class insharActivity extends AppCompatActivity
{
    /** Called when the activity is first created. */
   	private EditText text;
	private FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	private String tt;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_blue_dark));}

		setContentView(R.layout.inshar);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("分享至M++");
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
			public void send(View view){
			 tt=text.getText().toString();
			new Thread(update).start();
			}	
			
	Runnable update = new Runnable(){
		@Override        
		public void run() {

			File fl=new File("/storage/sdcard0/M++/data/mpp_shar.txt");
		
			if(fl.exists()){
				fl.delete();
			}
			
			try
			{
				ftp.openConnect();
						ftp.download("/hellowotl/web/htmls/", "mpp_shar.txt", "/storage/sdcard0/M++/data/");
						Data data=new Data();
				File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
				if(!fuser.exists()){

				}
				else{

					String user=data.getData(fuser,1);
					File fut=new File(user);
					String username=data.getData(fut,1);
					data.writeData(fl,username+"\n",false);
					SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
					String date = sDateFormat.format(new java.util.Date()); 
					data.writeData(fl,date+"\n",false);
					data.writeData(fl,codeObject(tt)+"\n",false);
					data.writeData(fl,"\n\n",false);
					ftp.ftpUpload("ftp39.idcay.com","21", "hellowotl", "F5B385C8B1e8dc","/hellowotl/web/htmls/","/storage/sdcard0/M++/data/","mpp_shar.txt");
					fl.delete();
					finish();
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
