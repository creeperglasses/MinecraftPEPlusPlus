package com.minecraftpe.doubleplus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
import android.text.*;

public class Data
{
	
	public String getData(File file,int line){
	  String output=null;
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
		for (int i = 0; i < ss.length; i++) {
			output=ss[line-1];
		}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace(); 	
		}
		return output;
	}
	public void deleteData(File file){
		if(file.exists()){
			file.delete();
		}
	}
	
	public boolean hadFile(File file){
		boolean had=false;
		if(file.exists()){
			had=true;
		}
		return had;
		}
	public void copyFile(String oldPath, String newPath) { 
		try { 
			int bytesum = 0; 
			int byteread = 0; 
			File oldfile = new File(oldPath); 
			if (oldfile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath); 
				byte[] buffer = new byte[1444]; 
				int length; 
				while ( (byteread = inStream.read(buffer)) != -1) { 
					bytesum += byteread; //字节数 文件大小 
					System.out.println(bytesum); 
					fs.write(buffer, 0, byteread); 
				} 
				inStream.close(); 
			} 
		} 
		catch (Exception e) { 
			
			e.printStackTrace(); 

		} 

	} 
	public void copyFolder(String oldPath, String newPath) { 

		try { 
			(new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹 
			File a=new File(oldPath); 
			String[] file=a.list(); 
			File temp=null; 
			for (int i = 0; i < file.length; i++) { 
				if(oldPath.endsWith(File.separator)){ 
					temp=new File(oldPath+file[i]); 
				} 
				else{ 
					temp=new File(oldPath+File.separator+file[i]);
				} 

				if(temp.isFile()){ 
					FileInputStream input = new FileInputStream(temp); 
					FileOutputStream output = new FileOutputStream(newPath + "/" + 
																   (temp.getName()).toString()); 
					byte[] b = new byte[1024 * 5]; 
					int len; 
					while ( (len = input.read(b)) != -1) { 
						output.write(b, 0, len); 
					} 
					output.flush(); 
					output.close(); 
					input.close(); 
				} 
				if(temp.isDirectory()){//如果是子文件夹 
					copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
				} 
			} 
		} 
		catch (Exception e) { 
			System.out.println("复制整个文件夹内容操作出错"); 
			e.printStackTrace(); 

		} 

	}
	public void deleteFolderFile(String filePath, boolean deleteThisPath)
	 throws IOException {
		 if (!TextUtils.isEmpty(filePath)) {
			 File file = new File(filePath);
			 if (file.isDirectory()) {// 处理目录
				 File files[] = file.listFiles();
				 for (int i = 0; i < files.length; i++) {
					 deleteFolderFile(files[i].getAbsolutePath(), true);
			}
			}
			 if (deleteThisPath) {
			 if (!file.isDirectory()) {// 如果是文件，删除
				 file.delete();
			 } else {// 目录
				if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
				 file.delete();
					}
		      }
			}
		 }
		 }
		 }

