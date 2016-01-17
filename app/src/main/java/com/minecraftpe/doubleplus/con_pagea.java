package com.minecraftpe.doubleplus;

import android.support.annotation.*;
import android.os.*;
import android.view.*;
import android.support.v4.app.*;
import android.webkit.*;
import java.io.*;
import com.petebevin.markdown.*;

public class con_pagea extends Fragment
{
	private File fs=new File("/storage/sdcard0/M++/data/fs_con.txt");
	private Data data=new Data();
	private String cname;
	private int cpos;
	public static con_pagea newInstance(){
        return new con_pagea();
    }


    public con_pagea() {
		
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.con_pagea,container,false );
	try{
	   WebView webview=(WebView) view.findViewById(R.id.con_web);
		cviewActivity nma=(cviewActivity) getActivity();
	 	 cpos=nma.cpos;
		cname=data.getData(fs,cpos*10+7);
		String dbase=decodeObject(cname);
		String data="<style>img{max-width:100%;}</style>"+new MarkdownProcessor().markdown(dbase);
		webview.loadDataWithBaseURL(null,data,"text/html","UTF-8",null);
		}
		catch(Exception e){
			
		}
		//webview.loadUrl("http://www.helloworldcreeper.com/htmls/mppcon/show/"+cname+".html");
	  return view;
    }
	public String decodeObject(String a){
		EncodeString es=new EncodeString("1234567890abcdef");
		a=es.decode(a);		
		return a;
	}
}
