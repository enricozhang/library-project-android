package com.meimob.util;

import org.apache.http.HttpHost;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpBase {

	public HttpBase(Context context) {
		super();
		init(context);
	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	private static ConnectivityManager mConnectivityManager;

	public static HttpHost globalProxy() {
		HttpHost localHttpHost;
		if (mConnectivityManager == null) {
			localHttpHost = null;
			return localHttpHost;
		}
		while (true) {
			NetworkInfo localNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 0)) {
				String str = localNetworkInfo.getExtraInfo();
				if ((str != null) && (str.toLowerCase().contains("cmwap"))) {
					localHttpHost = new HttpHost("10.0.0.172");
					continue;
				}
				if ((str != null) && (str.toLowerCase().contains("ctwap"))) {
					localHttpHost = new HttpHost("10.0.0.200");
					continue;
				}
			}
			localHttpHost = null;
		}
	}
}
