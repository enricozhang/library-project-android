package com.meimob.utils;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.meimob.app.R;

/**
 * @author Enrico
 * 
 */
public class ShotcutUtil {

	static final String ACTION_INSTALL = "com.android.launcher.action.INSTALL_SHORTCUT";
	static final String ACTION_UNINSTALL = "com.android.launcher.action.UNINSTALL_SHORTCUT";

	public static int getVersionCode(Activity context) {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (packInfo != null) {
			return packInfo.versionCode;
		}
		return 0;
	}

	/**
	 * 返回当前程序版本名
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return null;
			}
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}

	/**
	 * 删除快捷方式 需要权限 <uses-permission
	 * android:name="com.android.launcher.permission.UNINSTALL_SHORTCUT"/>
	 */
	public static void delShortcut(Activity context) {
		/*
		 * //该代码不管用，决定采用从数据库中删除的办法 Intent shortcut = new
		 * Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
		 * shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(R.string.app_name));
		 * ComponentName localComponentName = new ComponentName(context.getPackageName(),
		 * "."+context.getLocalClassName()); Intent intent = new
		 * Intent(Intent.ACTION_MAIN).setComponent(localComponentName);
		 * shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		 * context.sendBroadcast(shortcut); return;
		 */
		String dbPath = "/data/data/com.android.launcher/databases/launcher.db";
		SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		db.delete("favorites", "iconPackage=?", new String[] { context.getApplication()
				.getPackageName() });
		db.close();

	}

	/**
	 * 检测快捷方式是否存在 需要权限<uses-permission
	 * android:name="com.android.launcher.permission.READ_SETTINGS"/>
	 * 
	 * @param context
	 */
	public static boolean isExistShortcut(Activity context, String authorities) {
		boolean isInstallShortcut = false;
		final ContentResolver cr = context.getContentResolver();

		/*
		 * if (android.os.Build.VERSION.SDK_INT < 8) { AUTHORITIES =
		 * "com.android.launcher.settings"; } else { AUTHORITIES =
		 * "com.android.launcher2.settings"; }
		 */
		final Uri CONTENT_URI = Uri.parse("content://" + authorities + "/favorites?notify=true");
		Cursor c = cr.query(CONTENT_URI, new String[] { "iconPackage" }, "iconPackage=?",
				new String[] { context.getApplication().getPackageName() }, null);
		if (c != null) {
			if (c.getCount() > 0) {
				isInstallShortcut = true;
			}
			c.close();
		}
		return isInstallShortcut;
	}

	/**
	 * 创建快捷方式 需要权限 <uses-permission
	 * android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
	 */
	public static void createShortcut(Activity context, Class<?> startupActivityClass,
			int appName, int icon, boolean delete) {
		Intent shortcut;
		if (delete) {
			shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
		} else {
			shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		}
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(appName));
		shortcut.putExtra("duplicate", false); // 不允许重复创建
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		// intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		// Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		intent.setClass(context.getApplicationContext(), startupActivityClass);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		// 快捷方式的图标
		ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
		context.sendBroadcast(shortcut);
	}

	public static void deleteShotcut(Activity activity) {
		final Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
		shortcutIntent
				.setComponent(new ComponentName(activity.getPackageName(), "SplashActivity"));

		final Intent intent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, activity.getString(R.string.app_name));
		intent.setComponent(new ComponentName(activity.getPackageName(), "YourClassName"));
		intent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");

		activity.sendBroadcast(intent, null);
	}

	public static void delShortcutFromDesktop(Context paramContext, String packageName,
			String cls, String appName) {
		Intent localIntent1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
		String str = appName;
		PackageManager localPackageManager = paramContext.getPackageManager();
		int i = 8320;
		try {
			ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(
					packageName, i);
			if (str == null)
				str = localPackageManager.getApplicationLabel(localApplicationInfo).toString();
			localIntent1.putExtra("android.intent.extra.shortcut.NAME", str);
			ComponentName localComponentName = new ComponentName(packageName, cls);
			Intent localIntent2 = new Intent(Intent.ACTION_MAIN).setComponent(localComponentName);
			localIntent2.addCategory(Intent.CATEGORY_LAUNCHER);
			localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
			paramContext.sendBroadcast(localIntent1);
			return;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			while (true)
				localNameNotFoundException.printStackTrace();
		}
	}

	public static void getAuthorityFromPermission(Activity context) {
		String read_settings = "com.android.launcher.permission.READ_SETTINGS";
		String write_settings = "com.android.launcher.permission.WRITE_SETTINGS";
		List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(
				PackageManager.GET_PROVIDERS);
		if (packs != null) {
			for (PackageInfo pack : packs) {
				ProviderInfo[] providers = pack.providers;
				if (providers != null) {
					for (ProviderInfo provider : providers) {
						if ((read_settings.equals(provider.readPermission) || (write_settings
								.equals(provider.writePermission)))) {
							Toast.makeText(context, pack.applicationInfo.packageName,
									Toast.LENGTH_LONG).show();
							if (isExistShortcut(context, provider.authority)) {
								continue;
							} else {
								/*
								 * ShotcutUtil.createShortcut(context, SplashActivity.class,
								 * R.string.app_name, R.drawable.icon, false);
								 */
								continue;

							}

						}
					}
				}
			}
		}
		return;
	}
}
