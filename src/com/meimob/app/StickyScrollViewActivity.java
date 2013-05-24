package com.meimob.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * 
 * @author Emil Sj�lander - sjolander.emil@gmail.com
 *
 */
public class StickyScrollViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticky_view_layout);
        findViewById(R.id.mybutton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "hej", Toast.LENGTH_SHORT).show();
			}
		});
    }
}