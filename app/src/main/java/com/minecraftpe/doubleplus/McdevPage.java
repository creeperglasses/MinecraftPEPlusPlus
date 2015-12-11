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
import android.support.v7.widget.*;
import java.util.*;
public class McdevPage extends Fragment {
	public static final String ARGS_PAGE = "args_page";
	private int mPage;
	private List<DevRecItem> listmain;
	public static McdevPage newInstance(int page) {
		Bundle args = new Bundle();
		args.putInt(ARGS_PAGE, page);
		McdevPage fragment = new McdevPage();
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
		final View view = inflater.inflate(R.layout.dev_show,container,false);
		RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
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
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
				initStudio();
				
				 DevAdapter adapter=new DevAdapter(listmain,itemClickListener);
				recyclerView.setAdapter(adapter);
			break;
			case 2:
				 recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
				recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
				initPro();

				adapter=new DevAdapter(listmain,itemClickListener);
				recyclerView.setAdapter(adapter);
				break;
		default:
		
		break;
		}
		return view;
		}
	public void initStudio() {
        listmain=new ArrayList<DevRecItem>();
		DevRecItem p=new DevRecItem(R.drawable.userbgmd,"Code UI"," ");
		listmain.add(p);
		}
	public void initPro() {
        listmain=new ArrayList<DevRecItem>();
		DevRecItem p=new DevRecItem(R.drawable.mpp,"M++","一切，只为更好的mcpe");
		listmain.add(p);
	}
}

