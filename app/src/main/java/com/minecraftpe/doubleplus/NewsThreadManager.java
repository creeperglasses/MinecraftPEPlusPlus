package com.minecraftpe.doubleplus;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *      线程管理器
 **/
public class NewsThreadManager {
	private ExecutorService service;
	
	private NewsThreadManager(){
		int num = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num);
		
	}
	
	private static final NewsThreadManager manager= new NewsThreadManager();
	
	public static NewsThreadManager getInstance(){
		return manager;
	}
	
	public void addTask(Runnable runnable){
		service.execute(runnable);
	}
}
