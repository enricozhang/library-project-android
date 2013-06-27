/**
 * 系统项目名称
 * com.etagmedia.pt.utils
 * DeviceInfo.java
 * 
 * 2011-8-1-下午01:17:08
 *  2011eTagmedia-版权所有
 * 
 */
package com.meimob.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Collection of the mobile information
 * 
 * @author: Enrico Zhang
 * @rewrite: Enrico Zhang 2011-8-1 下午01:17:08
 * 
 * @version 1.0.0
 * 
 */
public class DeviceInfo {
    private static final String ANDROID = "Android-";
    
    /**
     * 获取手机型号信息
     */
    public static String device() {
	return Build.MODEL;
    }

    /**
     *返回Android版本信息如（4.0、4.1等）
     * @return
     */
    public static String platform() {
	StringBuilder localStringBuilder = new StringBuilder();
	String str = Build.VERSION.RELEASE;
	return localStringBuilder.append(ANDROID).append(str).toString();
    }
    
    /**
     *获取手机唯一标识信息
     * @param context
     * @return
     */
    public static String imei(Context context) {
	return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
		.getDeviceId();
    }
    
    /**
     * 获取运营商信息
     */
    public static String carrier(Context paramContext) {
	return getCarrier(paramContext);
    }
    
    private static String getCarrier(Context paramContext) {
	String carrier = "";
	ContentResolver localContentResolver = paramContext
		.getContentResolver();
	Uri localUri = Uri.parse("content://telephony/carriers");
	String[] arrayOfString = new String[1];
	arrayOfString[0] = "name";
	String str2 = null;
	Cursor localCursor = localContentResolver.query(localUri,
		arrayOfString, "current=1", null, str2);
	if (localCursor != null)
	    try {
		if (localCursor.moveToFirst())
		    carrier = localCursor.getString(0);
	    } finally {
		localCursor.close();
	    }
	return carrier;
    }
}
