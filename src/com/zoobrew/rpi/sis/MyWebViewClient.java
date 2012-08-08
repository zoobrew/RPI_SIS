package com.zoobrew.rpi.sis;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView v, String url)
	{
		v.loadUrl(url);	
		return true;
	}	
}
