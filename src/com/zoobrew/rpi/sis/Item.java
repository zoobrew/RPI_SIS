package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Item extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        //load as zoomed out
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        try{
        myWebView.loadUrl("https://sis.rpi.edu/rss/bwskrsta.P_RegsStatusDisp");
        myWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
}
