package com.zoobrew.rpi.sis;

import org.apache.http.HttpVersion;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView v, String url)
	{
		v.loadUrl(url);	
		return true;
	}
	
	public DefaultHttpClient getClient() 
	{ 
		DefaultHttpClient ret = null;

	    //sets up parameters
	    HttpParams params = new BasicHttpParams();
	    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	    HttpProtocolParams.setContentCharset(params, "utf-8");
	    params.setBooleanParameter("http.protocol.expect-continue", false);
	
	    //registers schemes for both http and https
	    SchemeRegistry registry = new SchemeRegistry();
	    registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	    final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
	    sslSocketFactory.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	    registry.register(new Scheme("https", sslSocketFactory, 443));
	
	    ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(params, registry);
	    ret = new DefaultHttpClient(manager, params);
	    return ret;
}

}
