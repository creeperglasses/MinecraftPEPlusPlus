package com.minecraftpe.doubleplus;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import android.support.annotation.*;
import android.widget.*;
import android.webkit.*;
import android.support.v4.widget.*;
import android.content.*;
import android.net.*;
import java.util.*;
import android.support.v7.widget.*;
import java.io.*;
public class PageFragment extends Fragment {
	public static final String ARGS_PAGE = "args_page";
	private int mPage;
	private List<NewsRecItem> listmain;
	private SwipeRefreshLayout mSwipeLayout;
	FTP ftp = new FTP("ftp39.idcay.com", "hellowotl", "F5B385C8B1e8dc");
	public static PageFragment newInstance(int page) {
		Bundle args = new Bundle();
		args.putInt(ARGS_PAGE, page);
		PageFragment fragment = new PageFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPage = getArguments().getInt(ARGS_PAGE);
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_page,container,false);
		final RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				switch(position){
					default:

						break;	
				}
			}
		};
		switch(mPage){
			case 1:
				RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
				initStudio();

				NewsRecAdapter adapter=new NewsRecAdapter(listmain,itemClickListener);
				recyclerView.setAdapter(adapter);
				break;
			case 2:
				recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
				initPro();

				adapter=new NewsRecAdapter(listmain,itemClickListener);
				recyclerView.setAdapter(adapter);
				break;
			default:

				break;
		}
		final Handler load = new Handler();

		final Runnable task = new Runnable() {

			@Override
			public void run() {
		switch(mPage){
			case 1:
				RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
				initStudio();

				NewsRecAdapter adapter=new NewsRecAdapter(listmain,itemClickListener);
				recyclerView.setAdapter(adapter);
				break;
			case 2:
				recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
				initPro();

				adapter=new NewsRecAdapter(listmain,itemClickListener);
				recyclerView.setAdapter(adapter);
				break;
			default:

				break;
		}
				load.postDelayed(this, 100000);
		}
		};
		load.postDelayed(task, 10000);
		final Runnable add = new Runnable() {

			@Override
			public void run() {
				switch(mPage){
					case 1:
						initStudio();
						break;
					case 2:
						initPro();
					break;
					default:

						break;
				}
				load.postDelayed(this, 2400000);
			}
		};
		load.postDelayed(add, 17000);
		mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_sto);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
					switch(mPage){
						case 1:
							RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
							recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
							
							NewsRecAdapter adapter=new NewsRecAdapter(listmain,itemClickListener);
							recyclerView.setAdapter(adapter);
							break;
						case 2:
							recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
							recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
							
							adapter=new NewsRecAdapter(listmain,itemClickListener);
							recyclerView.setAdapter(adapter);
							break;
						default:

							break;
					}
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

		
		return view;
		
	}
	
	public void initStudio() {
        listmain=new ArrayList<NewsRecItem>();
		
		new Thread(addnews).start();
	}
	public void initPro() {
        listmain=new ArrayList<NewsRecItem>();
		new Thread(addstudio).start();
		
	}
	Runnable addnews = new Runnable(){
		@Override        
		public void run() {

			File file=new File("/storage/sdcard0/M++/data/news.txt");
			try
			{
				ftp.openConnect();
				ftp.download("/hellowotl/web/htmls/mppshow/","news.txt","/storage/sdcard0/M++/data/");

			}
			catch (IOException e)
			{}
			try
			{
				FileReader fr=new FileReader(file);

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
						NewsRecItem p=new NewsRecItem(ss[4*i],ss[4*i+1],ss[i*4+2]); listmain.add(p);
					}
					catch(ArrayIndexOutOfBoundsException e){
					}
				}
			}
			catch (FileNotFoundException e)
			{
				//e.printStackTrace(); 	
			}
		}
	};
	Runnable addstudio = new Runnable(){
		@Override        
		public void run() {

			File file=new File("/storage/sdcard0/M++/data/studio.txt");
			try
			{
				ftp.openConnect();
				ftp.download("/hellowotl/web/htmls/mppshow/","studio.txt","/storage/sdcard0/M++/data/");

			}
			catch (IOException e)
			{}
			try
			{
				FileReader fr=new FileReader(file);

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
						NewsRecItem p=new NewsRecItem(ss[4*i],ss[4*i+1],ss[i*4+2]); listmain.add(p);
					}
					catch(ArrayIndexOutOfBoundsException e){
					}
				}
			}
			catch (FileNotFoundException e)
			{
				//e.printStackTrace(); 	
			}
		}
	};
}

