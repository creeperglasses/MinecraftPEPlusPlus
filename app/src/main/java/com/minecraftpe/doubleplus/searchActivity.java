package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.net.*;
import android.support.design.widget.*;
import android.graphics.*;
import java.io.*;
import android.content.*;
import android.widget.*;
import android.view.inputmethod.*;
import java.util.*;
import android.text.*;
import android.util.*;

public class searchActivity extends AppCompatActivity
{

	/** Called when the activity is first created. */
	private List<ConRecItem> listmain;
	private EditText tm;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));
		}
		listmain=new ArrayList<ConRecItem>();
		tm=(EditText) findViewById(R.id.setext);
		final RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
				switch(position){
					default:
					
					Intent intent = new Intent(searchActivity.this, conActivity.class);
					Data data=new Data();	
						File fl=new File("/storage/sdcard0/M++/data/slist.txt");			
					String spos=data.getData(fl,position+1);
					int pos=Integer.valueOf(spos);
					intent.putExtra("pos",pos);
						startActivity(intent);	
						break;	
				}
			}
        };
		int displayWidth=0;
		int displayHeight=0;
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		displayWidth = displayMetrics.widthPixels;
		displayHeight = displayMetrics.heightPixels;
		final int rpos=displayWidth/320;
		listmain=new ArrayList<ConRecItem>();
		ConRecAdapter adapter=new ConRecAdapter(listmain,itemClickListener);
		RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(rpos,StaggeredGridLayoutManager.VERTICAL));
		recyclerView.setAdapter(adapter);
		tm.addTextChangedListener(new TextWatcher(){

				@Override
				public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
					// TODO: Implement this method
				}

				@Override
				public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
					// TODO: Implement this method
				}

				@Override
				public void afterTextChanged(Editable edit)
				{
					
					//TODO 
					listmain=new ArrayList<ConRecItem>();
					System.out.println("okokok");
					File fs=new File("/storage/sdcard0/M++/data/fs_con.txt");
					File fl=new File("/storage/sdcard0/M++/data/slist.txt");			
					String text=tm.getText().toString();
					if(fs.exists()){
						listmain=new ArrayList<ConRecItem>();
						if(fl.exists()){
							fl.delete();
						}

						try
						{
							fl.createNewFile();

							String reg=".*" + text + ".*";

							FileReader fr=new FileReader(fs);


							BufferedReader br=new BufferedReader(fr);

							String temp=null;
							String s="";


							while ((temp = br.readLine()) != null)
							{
								s += temp + "\n";
							}



							String [] ss=s.split("\n");
							for (int i = 0; i <= ss.length; i++) {
								try{
									if(ss[10*i].matches(reg)){
									ConRecItem p=new ConRecItem(ss[10*i+1],ss[10*i]); listmain.add(p);
									String spos=String.valueOf(i);
									Data data=new Data();
									data.writeData(fl,spos+"\n",false);
									}
								}

								catch(ArrayIndexOutOfBoundsException e){ 
									}						
					}
							ConRecAdapter adapter=new ConRecAdapter(listmain,itemClickListener);
							RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
							recyclerView.setLayoutManager(new StaggeredGridLayoutManager(rpos,StaggeredGridLayoutManager.VERTICAL));
							recyclerView.setAdapter(adapter);
							
				}
					catch(Exception e){
						e.printStackTrace();
					}
					}
					
				};}
				);}
		public void back(View view){
			finish();
		}
		}
