package com.konka.boot;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class BootReceiver extends BroadcastReceiver
{
    private final static String BOOTAPKXML = "/etc/bootlauncher.xml";
    private final static String DEFPACKAGENAME = "tv.tvcloud.tvsee.webapp";
    private final static String DEFACTIVITYNAME = "tv.tvcloud.tvsee.webapp.StartBxManagerEx";

	@Override
	public void onReceive(Context context, Intent intent)
	{
        File xmlFile = new File(BOOTAPKXML);
        if(xmlFile.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(xmlFile);
                BootApkXmlParser bootApkXmlParser = new BootApkXmlParser();
                List<BootApk> bootApkList = bootApkXmlParser.readXML(inputStream);
                if( bootApkList.size() > 0 ){
                    Log.i("myLog", "find BootApk in " + BOOTAPKXML);
                    BootApk bootApk = bootApkList.get(0);
                    startActivity(context, bootApk.getPackageName(), bootApk.getActivityName());
                }else{
                    Log.i("myLog", "not find BootApk in " + BOOTAPKXML + ", start default launcher");
                    startActivity(context, DEFPACKAGENAME, DEFACTIVITYNAME);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Log.i("myLog", BOOTAPKXML + " not exists, start default launcher.");
            startActivity(context, DEFPACKAGENAME, DEFACTIVITYNAME);
        }

		Log.i("myLog", "boot" + System.currentTimeMillis());
	}

    public void startActivity(Context contex, String packageName, String activityName) {
        try {
            Log.i("myLog", "start:" + packageName + " " + activityName);
            ComponentName component = new ComponentName(packageName, activityName);
            Intent i = new Intent();
            i.setComponent(component);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contex.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
