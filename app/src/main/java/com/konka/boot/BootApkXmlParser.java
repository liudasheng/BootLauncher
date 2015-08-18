package com.konka.boot;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-8-17.
 */

public class BootApkXmlParser {

    public static List<BootApk> readXML(InputStream inStream) {
        XmlPullParser parser = Xml.newPullParser();
        try {
            List<BootApk> BootApks = null;
            BootApk BootApk = null;
            parser.setInput(inStream, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        BootApks = new ArrayList<BootApk>();
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("boot")) {
                            BootApk = new BootApk();
                        } else if (parser.getName().equals("packageName")) {
                            eventType = parser.next();
                            BootApk.setPackageName(parser.getText());
                        } else if (parser.getName().equals("activityName")) {
                            eventType = parser.next();
                            BootApk.setActivityName(parser.getText());
                        } else if (parser.getName().equals("action")) {
                            eventType = parser.next();
                            BootApk.setAction(parser.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("boot")) {
                            BootApks.add(BootApk);
                            BootApk = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
            inStream.close();
            return BootApks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
