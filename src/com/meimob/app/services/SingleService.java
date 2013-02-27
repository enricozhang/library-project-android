package com.meimob.app.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.meimob.app.MainActivity;
import com.meimob.app.R;

public class SingleService extends Service {

	public final static String AUTHORITY = "com.meimob.app.services.SingleService";
	public final static int mStartMode = Service.START_STICKY;//indicated how to behave if the service is killed
	private static final int ONGOING_NOTIFICATION = 1;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Notification notification = new Notification(R.drawable.ic_launcher, getText(R.string.app_name),
		        System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(this, getText(R.string.hello_world),
		        getText(R.string.menu_settings), pendingIntent);
		startForeground(ONGOING_NOTIFICATION, notification);
		return mStartMode;
	}
	
	

}
