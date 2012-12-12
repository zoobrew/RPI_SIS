package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.loopj.android.http.*;

/**using Android Asynchronous Http Client 1.4.2
A Callback-Based Http Client Library for Android **/
public class HttpActivity extends Activity{
	
	protected String mHttpResults;
	protected WebView mWebView;
	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.item);
        mWebView = (WebView) findViewById(R.id.webview);
	        
        AsyncHttpClient myClient = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        myClient.setCookieStore(myCookieStore);
        myClient.get("https://sis.rpi.edu/rss/hwsklptp.P_StuViewLptp", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
            	mWebView.loadDataWithBaseURL("https://sis.rpi.edu", mHttpResults, "text/html", null, null);
                //System.out.println(response);
            }
        });
        
	        
	 }
	 
	 

}
