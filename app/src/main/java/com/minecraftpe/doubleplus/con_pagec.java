package com.minecraftpe.doubleplus;

import android.support.annotation.*;
import android.os.*;
import android.view.*;
import android.support.v4.app.*;
import android.webkit.*;
import java.io.*;

public class con_pagec extends Fragment
{
	private File fs=new File("/storage/sdcard0/M++/data/fs_con.txt");
	private Data data=new Data();
	private String cname;
	private int cpos;
	public static con_pagec newInstance(){
        return new con_pagec();
    }


    public con_pagec() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.con_pagec,container,false );
		WebView webview=(WebView) view.findViewById(R.id.con_web);
		cviewActivity nma=(cviewActivity) getActivity();
		cpos=nma.cpos;
		cname=data.getData(fs,cpos*6+6);
		webview.loadUrl("http://www.helloworldcreeper.com/htmls/mppcon/new/"+cname+".html");
		
		return view;
    }

}
