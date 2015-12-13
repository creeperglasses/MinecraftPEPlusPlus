package com.minecraftpe.doubleplus;


public class NewsRecItem {
    private String img;
    private String title;
	private String subTitle;
	
	
    public NewsRecItem(String img, String title, String subTitle) {
        this.img = img;
        this.title = title;
		this.subTitle = subTitle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
	public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
