package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import java.io.*;
import android.text.*;
import android.net.*;

public class mailActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
// 透明状态栏
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
							WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);// 透明导航栏
		}
		setContentView(R.layout.mail);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		ActionBar actionBar=getActionBar();

		actionBar.setDisplayShowHomeEnabled(false);


		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME| ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		Button fab_send = (Button) findViewById(R.id.fab_send);
		final EditText to = (EditText) findViewById(R.id.to);
		final EditText subject = (EditText) findViewById(R.id.subject);
		final EditText body = (EditText) findViewById(R.id.body);
		fab_send.setOnTouchListener(new Button.OnTouchListener(){


				public final float[] BT_NOT_SELECTED=new float[]
				{ 1, 0, 0, 0, 0,
					0, 1, 0, 0, 0,
					0, 0, 1, 0, 0,
					0, 0, 0, 1, 0 };
				@Override
				public boolean onTouch(View v, MotionEvent event)
				{
					// TODO: Implement this method
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						v.getBackground().setColorFilter(0xffcccccc, PorterDuff.Mode.MULTIPLY);
						v.setBackgroundDrawable(v.getBackground());
						/*File fm=new File("/storage/emulated/0/M++/data/用户邮箱.txt");
						if(!fm.exists()){
							Intent intent = new Intent(mailActivity.this,mail_loginActivity.class);
							startActivity(intent);	
						}
						else{*/
							if(!TextUtils.isEmpty(to.getText())&&!TextUtils.isEmpty(subject.getText())&&!TextUtils.isEmpty(body.getText())){	
							/*Data data=new Data();
							String name=data.getData(fm,1);
							String pwd=data.getData(fm,2);
							try{
								MailSenderInfo mailInfo = new MailSenderInfo();
								mailInfo.setMailServerHost("smtp.qq.com");
								mailInfo.setMailServerPort("25");
								mailInfo.setValidate(true);
								mailInfo.setUserName(name); 
								mailInfo.setPassword(pwd);
								mailInfo.setFromAddress(name);
								mailInfo.setToAddress(to.getText().toString()); 
								mailInfo.setSubject(subject.getText().toString()); 
								mailInfo.setContent(body.getText().toString()); 
								
								SimpleMailSender sms = new SimpleMailSender();
								sms.sendTextMail(mailInfo);
								Toast.makeText(getApplicationContext(), "已发送", Toast.LENGTH_SHORT).show();
								
								}
								catch(Exception e){
									Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
									
								}*/
								try{
								Intent data=new Intent(Intent.ACTION_SENDTO);
								data.setData(Uri.parse("mailto:"+to.getText().toString()));
								data.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
								data.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
								startActivity(data);
								}
								catch(Exception e){
									Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

								}
							}
							else{
								Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
								
							}
						}
					
					else if(event.getAction() == MotionEvent.ACTION_UP){
						v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
						v.setBackgroundDrawable(v.getBackground());

					}
					return false;
				}


			});
	}




	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case android.R.id.home:
				finish();
				break;
			default:
				break;
		}
		return true;
	}
}
	
	
