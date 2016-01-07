package org.codeui.mppupdatetool;

import android.app.*;
import android.os.*;
import android.graphics.*;
import android.widget.*;
import android.support.v7.app.*;
import java.util.*;
import android.content.*;

public class MainActivity extends AppCompatActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Typeface robotothin = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Thin.ttf");
		TextView ver=(TextView) findViewById(R.id.ver);
		TextView veryel=(TextView) findViewById(R.id.veryel);
		veryel.setTypeface(robotothin);
		ver.setTypeface(robotothin);
		Timer timer = new Timer();
	    TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this,new_mainActivity.class);
				startActivity(intent);
				finish();
			}		
		};
		timer.schedule(timerTask, 4000);

	}	
		
}
