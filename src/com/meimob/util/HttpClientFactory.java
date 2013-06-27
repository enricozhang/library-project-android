package com.meimob.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.os.Build;

public class HttpClientFactory {
	public static final int MAX_POOL_CAPACITY = 4;
	public static int NETWORK_CONNECTION_TIMEOUT;
	public static int NETWORK_SO_TIMEOUT;
	public static String USER_AGENT;

	private static ConcurrentLinkedQueue<MyHttpClient> clients;

	static {
		String str = Build.VERSION.RELEASE;
		USER_AGENT = str + ")";
		NETWORK_CONNECTION_TIMEOUT = 15000;
		NETWORK_SO_TIMEOUT = 15000;
		clients = new ConcurrentLinkedQueue<MyHttpClient>();
	}

	protected static HttpParams getDefaultHttpParams() {
		BasicHttpParams localBasicHttpParams = new BasicHttpParams();
		HttpVersion localHttpVersion = HttpVersion.HTTP_1_1;
		HttpProtocolParams.setVersion(localBasicHttpParams, localHttpVersion);
		String str = USER_AGENT;
		HttpProtocolParams.setUserAgent(localBasicHttpParams, str);
		int i = NETWORK_CONNECTION_TIMEOUT;
		HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, i);
		int j = NETWORK_SO_TIMEOUT;
		HttpConnectionParams.setSoTimeout(localBasicHttpParams, j);
		return localBasicHttpParams;
	}

	protected static SchemeRegistry getDefaultSchemeRegistry() {
		SchemeRegistry localSchemeRegistry = new SchemeRegistry();
		PlainSocketFactory localPlainSocketFactory = PlainSocketFactory
				.getSocketFactory();
		Scheme localScheme1 = new Scheme("http", localPlainSocketFactory, 80);
		localSchemeRegistry.register(localScheme1);
		SSLSocketFactory localSSLSocketFactory = SSLSocketFactory
				.getSocketFactory();
		Scheme localScheme2 = new Scheme("https", localSSLSocketFactory, 443);
		localSchemeRegistry.register(localScheme2);
		return localSchemeRegistry;
	}

	public static MyHttpClient getHttpClient() {
		Object localObject = (MyHttpClient) clients.poll();
		if (localObject == null) {
			HttpParams localHttpParams = getDefaultHttpParams();
			SchemeRegistry localSchemeRegistry = getDefaultSchemeRegistry();
			SingleClientConnManager localSingleClientConnManager = new SingleClientConnManager(
					localHttpParams, localSchemeRegistry);
			localSingleClientConnManager.closeExpiredConnections();
			localObject = new MyDefaultHttpClient(localHttpParams);

		}
		return (MyHttpClient) localObject;
	}

	class GzipDecompressingEntity extends HttpEntityWrapper {

		public GzipDecompressingEntity(HttpEntity wrapped) {
			super(wrapped);
		}

		public InputStream getContent() throws IOException,
				IllegalStateException {
			InputStream localInputStream = this.wrappedEntity.getContent();
			return new GZIPInputStream(localInputStream);
		}

		public long getContentLength() {
			return 65535L;
		}
	}

	static class MyDefaultHttpClient extends DefaultHttpClient implements
			HttpClientFactory.MyHttpClient {
		public MyDefaultHttpClient(HttpParams arg2) {
			super(arg2);
			init();
		}

		protected void init() {
			HttpClientFactory.MyDefaultHttpClient local1 = new HttpClientFactory.MyDefaultHttpClient(getDefaultHttpParams());
			clients.add(local1);
			HttpClientFactory.MyDefaultHttpClient local2 = new HttpClientFactory.MyDefaultHttpClient(getDefaultHttpParams());
			clients.add(local2);
		}

		public void recycle() {
			if (HttpClientFactory.clients.size() < 4)
				HttpClientFactory.clients.add(this);
		}
	}

	public abstract interface MyHttpClient extends HttpClient {
		public abstract void recycle();
	}
}