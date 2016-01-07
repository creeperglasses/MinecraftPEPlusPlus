package org.codeui.mppupdatetool.library;

public class Result {
    private String response;
    private boolean succeed;   
    private String time;
    public Result() {
    }
    public Result(String res) {
        this.response = res;
    }
    public Result(boolean suc, String ti, String res) {
        this.succeed = suc;
        this.time = ti;
        this.response = res;
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
