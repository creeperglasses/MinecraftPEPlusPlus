package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import android.content.*;
import android.graphics.*;
import java.io.*;
import android.graphics.drawable.*;
import android.text.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity
{
	FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			
			
			getWindow().setStatusBarColor(Color.TRANSPARENT);}
		
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			Window window = getWindow();
//// 透明状态栏
//			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//							WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);// 透明导航栏
//		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
	
		setContentView(R.layout.login);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitle(" ");
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					finish();
				} });
		
		File fmain = new File("/storage/sdcard0/M++");	
		File fuser = new File("/storage/sdcard0/M++/user");	
		File fsever = new File("/storage/sdcard0/M++/user/sever");	
		
			if(!fmain.exists()){
				fmain.mkdirs();
			}
			if(!fuser.exists()){
				fuser.mkdirs();
			}
		if(!fsever.exists()){
			fsever.mkdirs();
		}
		}
	public void loginadd(View v)
	{
		FloatingActionButton loginb = (FloatingActionButton) findViewById(R.id.loginnew);
		final EditText name = (EditText) findViewById(R.id.name);
		final EditText pwd = (EditText) findViewById(R.id.pwd);
		// TODO: Implement this method

			String usm=name.getText().toString()+".txt";
			String usp=pwd.getText().toString();
			File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
			if(!fuser.exists()){
				if(!TextUtils.isEmpty(name.getText())&&!TextUtils.isEmpty(pwd.getText())){	
					new Thread(login).start();   
				}
				else{
					Snackbar.make(loginb,"不能为空", Snackbar.LENGTH_LONG).show();
					
				}
			}
			else{
				
					if(!TextUtils.isEmpty(name.getText())&&!TextUtils.isEmpty(pwd.getText())){	

						new Thread(relogin).start();   
						

					}
					else{
						Snackbar.make(loginb,"不能为空", Snackbar.LENGTH_LONG).show();
					}

				}
				
				
				}
	Runnable login = new Runnable(){
		@Override        
		public void run() {            
// TODO Auto-generated method stub            
			FloatingActionButton loginb = (FloatingActionButton) findViewById(R.id.loginnew);
			final EditText name = (EditText) findViewById(R.id.name);
			final EditText pwd = (EditText) findViewById(R.id.pwd);
			// TODO: Implement this method

			String usm=name.getText().toString()+".txt";
			String usp=pwd.getText().toString();
			File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
			
			try
			{
				ftp.openConnect();
				if(!ftp.mppCheck("/hellowotl/web/signed/", usm)){
					File user = new File("/storage/sdcard0/M++/user/sever/"+usm);	
					user.createNewFile();
					FileOutputStream out = new FileOutputStream(user, true);

					String usmb=name.getText().toString()+"\n";
					String uspb=usp+"\n";	
					out.write(usmb.getBytes("UTF-8"));
					out.write(uspb.getBytes("UTF-8"));
					ftp.ftpUpload("ftp39.idcay.com","21", "hellowotl", "F5B385C8B1e8dc","/hellowotl/web/signed/","/storage/sdcard0/M++/user/sever/",usm);
					fuser.createNewFile();
					FileOutputStream out_user = new FileOutputStream(fuser, true);
					String wri="/storage/sdcard0/M++/user/sever/"+usm;
					out_user.write(wri.getBytes("UTF-8"));
					Snackbar.make(loginb,"注册成功", Snackbar.LENGTH_LONG).show();

				}
				else{
					ftp.download("/hellowotl/web/signed/",usm,"/storage/sdcard0/M++/user/sever/");
					Data data=new Data();
					File user = new File("/storage/sdcard0/M++/user/sever/"+usm);	
					if(!usp.equals(data.getData(user,2))){
						user.delete();
						Snackbar.make(loginb,"用户名或密码错误", Snackbar.LENGTH_LONG).show();

					}
					else{
						fuser.createNewFile();
						FileOutputStream out_user = new FileOutputStream(fuser, true);
						String wri="/storage/sdcard0/M++/user/sever/"+usm;
						out_user.write(wri.getBytes("UTF-8"));
						Snackbar.make(loginb,"登陆成功", Snackbar.LENGTH_LONG).show();

					}
				}
			}

			catch (IOException e)
			{
				Snackbar.make(loginb,"检查设置", Snackbar.LENGTH_LONG).show();
			}
			
		}
	};
	Runnable relogin = new Runnable(){
		@Override        
		public void run() {            
			FloatingActionButton loginb = (FloatingActionButton) findViewById(R.id.loginnew);
			final EditText name = (EditText) findViewById(R.id.name);
			final EditText pwd = (EditText) findViewById(R.id.pwd);
			// TODO: Implement this method

			String usm=name.getText().toString()+".txt";
			String usp=pwd.getText().toString();
			File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
			Data data =new Data();
		try
			{
				data.deleteFolderFile("/storage/sdcard0/M++/user", true);
				File fusernew = new File("/storage/sdcard0/M++/user");	
				File fsever = new File("/storage/sdcard0/M++/user/sever");	

				if(!fusernew.exists()){
					fusernew.mkdirs();
				}
				if(!fsever.exists()){
					fsever.mkdirs();
				}
				ftp.openConnect();
				if(!ftp.mppCheck("/hellowotl/web/signed/", usm)){
					File user = new File("/storage/sdcard0/M++/user/sever/"+usm);	
					user.createNewFile();
					FileOutputStream out = new FileOutputStream(user, true);

					String usmb=name.getText().toString()+"\n";
					String uspb=usp+"\n";	
					out.write(usmb.getBytes("UTF-8"));
					out.write(uspb.getBytes("UTF-8"));
					ftp.ftpUpload("ftp39.idcay.com","21", "hellowotl", "F5B385C8B1e8dc","/hellowotl/web/signed/","/storage/sdcard0/M++/user/sever/",usm);
					fuser.createNewFile();
					FileOutputStream out_user = new FileOutputStream(fuser, true);
					String wri="/storage/sdcard0/M++/user/sever/"+usm;
					out_user.write(wri.getBytes("UTF-8"));
					Snackbar.make(loginb,"注册成功", Snackbar.LENGTH_LONG).show();

				}
				else{
					ftp.download("/hellowotl/web/signed/",usm,"/storage/sdcard0/M++/user/sever/");

					File user = new File("/storage/sdcard0/M++/user/sever/"+usm);	
					if(!usp.equals(data.getData(user,2))){
						user.delete();
						Snackbar.make(loginb,"用户名或密码错误", Snackbar.LENGTH_LONG).show();

					}
					else{
						fuser.createNewFile();
						FileOutputStream out_user = new FileOutputStream(fuser, true);
						String wri="/storage/sdcard0/M++/user/sever/"+usm;
						out_user.write(wri.getBytes("UTF-8"));
						Snackbar.make(loginb,"登陆成功", Snackbar.LENGTH_LONG).show();

					}
				}
			}

			catch (IOException e)
			{
				Snackbar.make(loginb,"检查设置", Snackbar.LENGTH_LONG).show();
			}
		
		}
		};
	
	}
	
	
