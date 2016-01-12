package com.minecraftpe.doubleplus;

public class SharRecItem
{
	private String user;
    private String webtext;
	private String img;
	private String date;
	
	
	SharRecItem(String user,String date,String webtext,String img){
		this.user=user;
		this.webtext=webtext;
		this.img=img;
		this.date=date;
		}
	public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWebText() {
        return webtext;
    }

    public void setWebText(String webtext) {
        this.webtext = webtext;
    }
	public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
