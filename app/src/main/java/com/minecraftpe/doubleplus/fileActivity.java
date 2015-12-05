package com.minecraftpe.doubleplus;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.io.*;
import android.widget.AdapterView.*;
import android.content.*;

public class fileActivity extends Activity
{
    /** Called when the activity is first created. */
	private List<File> localFile;

	public String LOCAL_PATH = "/storage/sdcard0/";
	public String LOCAL = "/storage/sdcard0/";
	String new_ll=LOCAL_PATH;
	
	ListView listMain;

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
        setContentView(R.layout.file);
		loadLocalView();
		listMain = (ListView) findViewById(R.id.list);
		listMain.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int location, long arg3) {

					String new_l=LOCAL_PATH;
					new_l+=localFile.get(location).getName()+"/";
					File f = new File(new_l);
					// 获取根目录下所有文件
					if(f.isDirectory()){
						LOCAL_PATH+=localFile.get(location).getName()+"/";
						new_l=null;
						loadLocalView();	
					}
				}
			});

		listMain.setOnItemLongClickListener(new OnItemLongClickListener(){

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View view, int location, long arg3) {

				 new_ll=LOCAL_PATH;

					File f = new File(new_ll);
					if(f.isDirectory()){
						new_ll+=localFile.get(location).getName()+"/";
					}
					
					ListDialogFragment listd = new ListDialogFragment();
					listd.show(getFragmentManager(), "listdialog");
					return true;
				}


			});}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		ActionBar actionBar=getActionBar();

		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		getMenuInflater().inflate(R.menu.file_menu, menu);
		return true;
	}	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				break;
		
				


		}




		return super.onOptionsItemSelected(item);
	}
	
	private void loadLocalView() {
		listMain = (ListView) findViewById(R.id.list);
        localFile = new ArrayList<File>();
        getFileDir(LOCAL_PATH);  
        MppFileAdapter adapter = new MppFileAdapter(this, localFile);
     
        listMain.setAdapter(adapter);
    }
	private void getFileDir(String filePath) {
        // 获取目录
        File f = new File(filePath);

        File[] files = f.listFiles();
        // 循环添加到本地列表
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isHidden() || file.getName().equals("LOST.DIR")) {
                continue;
            }
            localFile.add(file);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(!LOCAL_PATH.equals(LOCAL)){
				LOCAL_PATH=LOCAL;
				loadLocalView();
			}
			else{
				finish();
			}
		}
		return true;
	}
	public void shar_part_c(){
	//Toast.makeText(this, new_ll, Toast.LENGTH_SHORT).show();
	Data data=new Data();
		File fmain=new File("/storage/emulated/0/M++");
		File fs=new File("/storage/emulated/0/M++/mppbackup");
		File f=new File(new_ll);
	if(!fmain.exists()){
			fmain.mkdirs();
		
	}	
		if(!fs.exists()){
			fs.mkdirs();
			}
		File fn=new File("/storage/emulated/0/M++/mppbackup/"+f.getName());
			if(!fn.exists()){
			if(f.isDirectory()){
				
				data.copyFolder(new_ll,"/storage/emulated/0/M++/mppbackup/"+f.getName());
			loadLocalView();
				Toast.makeText(this, "备份完成，路径:"+"/storage/emulated/0/M++/mppbackup/"+f.getName(), Toast.LENGTH_LONG).show();		
			}
			
			else{
			
						data.copyFile(new_ll,"/storage/emulated/0/M++/mppbackup/"+f.getName());			
	loadLocalView();
				Toast.makeText(this, "备份完成，路径:"+"/storage/emulated/0/M++/mppbackup/"+f.getName(), Toast.LENGTH_LONG).show();		
	}
	}
	else{
		Toast.makeText(this, "已经存在，路径:"+"/storage/emulated/0/M++/mppbackup/"+f.getName(), Toast.LENGTH_LONG).show();		
	
	}
	}
	public void de_c(){
		File f=new File(new_ll);
		Data data=new Data();
		if(f.isDirectory()){
			try
			{
				data.deleteFolderFile(new_ll, true);
			}
			catch (IOException e)
			{
				Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();	
			}
		}
		else{
		f.delete();
		}
		Toast.makeText(this, "已删除", Toast.LENGTH_LONG).show();	
		loadLocalView();
	}
}
 class ListDialogFragment extends DialogFragment
{
	
	Data data=new Data();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = inflater.inflate(R.layout.file_danlog, container);
		Button cpc = (Button)view.findViewById(R.id.cpc);
		cpc.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					fileActivity fact= (fileActivity) getActivity();
					getDialog().dismiss();
					fact.shar_part_c();
				}
			});
		Button dec = (Button)view.findViewById(R.id.dec);
		dec.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					fileActivity fact= (fileActivity) getActivity();		
					getDialog().dismiss();
					fact.de_c();
				}
			});
		return view;
	}

}
