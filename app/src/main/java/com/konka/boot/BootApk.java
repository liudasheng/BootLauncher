package com.konka.boot;

/**
 * Created by Administrator on 2015-8-17.
 */
public class BootApk {
    private String packageName;
    private String activityName;
    private String action;

    public String getPackageName(){
        return packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getAction() {
        return action;
    }

    public void setPackageName(String packageName){
        this.packageName = packageName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setAction(String action) {
        this.action = action;
    }
}