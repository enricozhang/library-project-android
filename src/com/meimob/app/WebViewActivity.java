package com.meimob.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       
        WebView web = new WebView(this);
        setContentView(web);
        WebSettings setting = web.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        web.addJavascriptInterface(new Object() {

			public void clickOnAndroid(final String url) {

				mHandler.post(new Runnable() {

					public void run() {

						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_VIEW);
						Uri uri = Uri.parse(url);
						intent.setDataAndType(uri, "video/*");
						startActivity(intent);
						Toast.makeText(WebViewActivity.this,"测试调用java" + String.valueOf(url),Toast.LENGTH_LONG).show();

					}

				});

			}

		}, "demo");
        web.loadUrl("http://180.168.195.80:8081/lp/v/p3.html");
    }
    
    Handler mHandler = new Handler();

}
