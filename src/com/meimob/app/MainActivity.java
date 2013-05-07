package com.meimob.app;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.meimob.app.services.SingleService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = new Intent(SingleService.AUTHORITY);
		startService(intent);
	
		try {
			boolean isEmulated = false;
			Class<?> environment = Class.forName("android.os.Environment");
			Method[] methods = environment.getMethods();
			Method method = environment.getMethod("isExternalStorageEmulated", null);
			Object obj = method.invoke(null, null);
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	//	startActivity(new Intent(this, ContactUtils.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
