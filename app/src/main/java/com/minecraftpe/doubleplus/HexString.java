package com.minecraftpe.doubleplus;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
public class HexString{
	public static String format(byte []bt){
		int line=0 ;
		StringBuilder buf=new StringBuilder() ;
		for(byte d:bt){

			buf.append(String.format("%02x", d)) ;
			line++ ;	
		}
		return buf.toString();
	}
	public static String cformat(byte []bt){
		int line=0 ;
		StringBuilder buf=new StringBuilder() ;
		for(byte d:bt){
			buf.append(String.format("%02x  ", d)) ;
			line++ ;
			if(line%16==0)
				buf.append("\n");
		}
		return buf.toString();
	}
	public static byte[] readFile(String file) throws IOException{
		InputStream is=new FileInputStream(file) ;
		int length=is.available() ;
		byte bt[]=new byte[length] ;
		is.read(bt) ;
		return bt;
	}

// 转化十六进制编码为字符串 
	public static String toStringHex(String s) 
	{ 
		byte[] baKeyword = new byte[s.length()/2]; 
		for(int i = 0; i < baKeyword.length; i++) 
		{ 
			try 
			{ 
				baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16)); 
			} 
			catch(Exception e) 
			{ 
				e.printStackTrace(); 
			} 
		} 
		try 
		{ 
			s = new String(baKeyword, "utf-8");//UTF-16le:Not 
		} 
		catch (Exception e1) 
		{ 
			e1.printStackTrace(); 
		} 
		return s; 
	} 
	}
	
