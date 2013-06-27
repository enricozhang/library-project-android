package com.meimob.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * This is helper class to manage relevant with package and apk operation
 * 
 * @author Enrico
 * 
 */
public class PackageUtils {

    /**
     * get the drawable of icon from Apk file
     * 
     * @param context {@link Context}
     * @param path Apk file path
     * @return
     */
    public Drawable getIconFromFile(Context context, String path) {
	PackageManager pm = context.getPackageManager();
	PackageInfo info = pm.getPackageArchiveInfo(path,
		PackageManager.GET_ACTIVITIES);
	if (info != null) {
	    ApplicationInfo appInfo = info.applicationInfo;
	    if (Build.VERSION.SDK_INT >= 8) {
		appInfo.sourceDir = path;
		appInfo.publicSourceDir = path;
	    }
	    return appInfo.loadIcon(pm);
	}
	return null;
    }
    /**
     * 
     *get VerionName from class
     * @param context
     * @param paramClass
     * @return
     */
    public static String getVersionNameFromClass(Context context,
	    Class<?> paramClass) {
	ComponentName localComponentName = new ComponentName(context,
		paramClass);
	PackageManager localPackageManager = context.getPackageManager();
	String str1 = localComponentName.getPackageName();
	String str2 = "";
	try {
	    str2 = localPackageManager.getPackageInfo(str1, 0).versionName;
	} catch (NameNotFoundException e) {
	    e.printStackTrace();
	}
	return str2;
    }

}
