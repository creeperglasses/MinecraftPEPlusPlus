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
public class PageFragment extends Fragment {
	public static final String ARGS_PAGE = "args_page";
	private int mPage;
	private List<NewsRecItem> listmain;
	private SwipeRefreshLayout mSwipeLayout;
	
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
		mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_sto);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

				@Override
				public void onRefresh() {
					//重新刷新页面
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
		NewsRecItem p=new NewsRecItem("http://www.helloworldcreeper.com/htmls/mppshow/news/1.png","第一条新闻","祝贺M++实现了新闻区");
		listmain.add(p);
	}
	public void initPro() {
        listmain=new ArrayList<NewsRecItem>();
		NewsRecItem p=new NewsRecItem("http://www.helloworldcreeper.com/htmls/mppshow/news/1.png","Code UI","我们所做的一切都是为了更好的mcpe");
		listmain.add(p);
	}
}

