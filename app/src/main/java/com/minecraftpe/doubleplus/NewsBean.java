package com.minecraftpe.doubleplus;

import android.widget.ImageView;

public class NewsBean {
	
	private String id;
	private String adName;
	private String imgUrl;
	private int imgPath=-1;
	private ImageView mImageView;
	
	
	
	public ImageView getmImageView() {
		return mImageView;
	}
	public void setmImageView(ImageView mImageView) {
		this.mImageView = mImageView;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getImgPath() {
		return imgPath;
	}
	public void setImgPath(int imgPath) {
		this.imgPath = imgPath;
	}
	
	
}
