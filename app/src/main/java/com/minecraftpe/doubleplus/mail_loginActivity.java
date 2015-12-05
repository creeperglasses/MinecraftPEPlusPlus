package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import java.io.*;
import android.text.*;

public class mail_loginActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
		
	
		setContentView(R.layout.mail_login);
		ActionBar actionBar=getActionBar();

		actionBar.setDisplayShowHomeEnabled(false);


		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME| ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		Button mail_add = (Button) findViewById(R.id.mail_add);
		final EditText user_mail = (EditText) findViewById(R.id.user_mail);
		final EditText mail_pwd = (EditText) findViewById(R.id.mail_pwd);
		mail_add.setOnTouchListener(new Button.OnTouchListener(){


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
						if(!TextUtils.isEmpty(user_mail.getText())&&!TextUtils.isEmpty(mail_pwd.getText())){	
							File fm=new File("/storage/emulated/0/M++/data/用户邮箱.txt");
							if(fm.exists()){
								fm.delete();
							}
							if(!fm.exists()){
								try
								{
									fm.createNewFile();
									FileOutputStream out = new FileOutputStream(fm, true);
									String name=user_mail.getText().toString()+"\n";
									String pwd=mail_pwd.getText().toString()+"\n";
									out.write(name.getBytes("UTF-8"));
									out.write(pwd.getBytes("UTF-8"));
									Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
									finish();
								}
								catch (IOException e)
								{
									Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();	
								}
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
	
	
