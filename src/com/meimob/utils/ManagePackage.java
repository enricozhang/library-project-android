package com.meimob.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * This is helper class to manage relevant with package and apk operation
 * 
 * @author Enrico
 * 
 */
public class ManagePackage {

	/**
	 * get the Apk file icon
	 * 
	 * @param context
	 *            {@link Context}
	 * @param path
	 *            Apk file absolute path
	 * @return
	 */
	public Drawable getApkIcon(Context context, String path) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
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

}
