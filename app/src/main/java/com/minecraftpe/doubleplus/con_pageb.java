package com.minecraftpe.doubleplus;

import android.support.annotation.*;
import android.os.*;
import android.view.*;
import android.support.v4.app.*;
import android.support.design.widget.*;
import android.widget.*;
import java.util.*;
import android.support.v7.widget.*;
import android.support.v4.widget.*;
import java.io.*;
import android.content.*;

public class con_pageb extends Fragment
{
	private List<NewsRecItem> listmain;
	private View view;
	private String cname;
	private File fs=new File("/storage/sdcard0/M++/data/fs_con.txt");
	FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	private Data data=new Data();
	private RecyclerView recyclerView;
	private int cpos;
	private RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			switch(position){
				default:

					break;	
			}
		}
	};
	public static con_pageb newInstance(){
        return new con_pageb();
    }


    public con_pageb() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view= inflater.inflate(R.layout.con_pageb,container,false );
		try{
		cviewActivity nma=(cviewActivity) getActivity();
		cpos=nma.cpos;
		System.out.println(nma.cpos);
		recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
		initData();

		final CviewRecAdapter adapter=new CviewRecAdapter(listmain,itemClickListener);
		recyclerView.setAdapter(adapter);
		initData();
		final Handler load = new Handler();

		final Runnable task = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
				recyclerView.setAdapter(adapter);
				load.postDelayed(this, 15000);
				initData();
			}};
		load.postDelayed(task, 150000);

		final SwipeRefreshLayout mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
					recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
					recyclerView.setAdapter(adapter);
					new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								mSwipeLayout.setRefreshing(false);
							}
						}, 1000);
				}
			});
		mSwipeLayout.setColorSchemeResources(R.color.holo_blue_bright,
											 R.color.holo_blue_bright, R.color.holo_blue_bright,
											 R.color.holo_blue_bright);
				}
				catch(Exception e){
					
				}
		return view;
    }

	
	public void sendto(String str,int pos)
	{
		//System.out.println(str);
		File fcv = new File("/storage/sdcard0/M++/data/con_view");	
		if(!fcv.exists()){
			fcv.mkdirs();
		}
		cname=data.getData(fs,pos*10+6);
		cpos=pos;
		File fp=new File("/storage/sdcard0/M++/data/con_view/"+cname+".txt");
	if(!fp.exists()){
		
	}
	else{
		
			File fuser = new File("/storage/sdcard0/M++/user/user.txt");	
			if(!fuser.exists()){
				
			}
			else{
				
				String user=data.getData(fuser,1);
				File fut=new File(user);
				String username=data.getData(fut,1);
				try
				{
				data.writeData(fp, username, false);		
				data.writeData(fp, str + "\n\n", true);
				new Thread(update).start();
				}
				catch (IOException e)
				{}
	}}}
		
	public void initData() {
		
        listmain=new ArrayList<NewsRecItem>();
		cname=data.getData(fs,cpos*6+6);
		new Thread(load).start();
		File fp=new File("/storage/sdcard0/M++/data/con_view/"+cname+".txt");
		if(!fp.exists()){

		}
		try
		{
			FileReader fr=new FileReader(fp);


			BufferedReader br=new BufferedReader(fr);

			String temp=null;
			String s="";
			try{
				while ((temp = br.readLine()) != null)
				{
					s += temp + "\n";
				}
			}
			catch (IOException e)
			{
				e.printStackTrace(); 	
			}
			String [] ss=s.split("\n");
			for (int i = 0; i <= ss.length; i++) {
				try{
					NewsRecItem p=new NewsRecItem("http://www.helloworldcreeper.com/signed/userpic/user.png",ss[3*i],ss[3*i+1]);
					listmain.add(p);
				}
				catch(ArrayIndexOutOfBoundsException e){

				}	


			}
		}
		catch (FileNotFoundException e)
		{}
		
	}
	
	Runnable load = new Runnable(){
		@Override        
		public void run() {   
			try
			{
				ftp.openConnect();
				ftp.download("/hellowotl/web/htmls/mppcon/com/", cname+".txt", "/storage/sdcard0/M++/data/con_view/");
			}
			catch (IOException e)
			{}
		}};
	Runnable update = new Runnable(){
		@Override        
		public void run() {   
			ftp.ftpUpload("ftp39.idcay.com","21", "hellowotl", "F5B385C8B1e8dc","/hellowotl/web/htmls/mppcon/com/","/storage/sdcard0/M++/data/con_view/",cname+".txt");
			
		}};
	}
