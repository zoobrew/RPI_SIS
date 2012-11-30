package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

public class Item extends Activity {
	
	protected String mHttpResults;
	protected WebView mWebView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.item);
        
        //Obtain the data from the intent that indicates the url to open
        Intent intent = getIntent();
	    int mNUM = Integer.parseInt(intent.getStringExtra(MainActivity.MENUNUM));
	    int smNUM = Integer.parseInt(intent.getStringExtra(MainActivity.SUBMENUNUM));
	    
	    //Obtain the list of addresses from array.xml file
	    Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.MenuHttp);
        int n = ta.length();
        String[][] HTMLaddresses = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
            	HTMLaddresses[i] = res.getStringArray(id);
            } else {
                // something wrong with the XML
            }
        }
        
	    String url = HTMLaddresses[mNUM][smNUM];
        mWebView = (WebView) findViewById(R.id.webview);
        //myWebView.getSettings().setJavaScriptEnabled(true);
        //load as zoomed out
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        try{
        	startLongRunningOperation(url);
        	 //myWebView.loadUrl("https://sis.rpi.edu/rss/bwskrsta.P_RegsStatusDisp");
        	 //myWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    //handler for callback to UI thread
    final Handler mHandler = new Handler();
    final Runnable mUpdateResults = new Runnable() {
    	public void run() {
    		updateResultsInUi();
    	}
    };
    
    protected void startLongRunningOperation(final String URL){
    	// Fire off a thread to do some work that we shouldn't do directly in the UI thread
    	Thread t = new Thread(){
    		public void run(){
    			try {
					mHttpResults = HttpHelper.executeHttpGet(URL);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			mHandler.post(mUpdateResults);
    		}
    	};
    	t.start();
    }
    
    private void updateResultsInUi() {
    	// Back in the UI thread -- update our UI elements based on the data in mResults
    	
    	// see http://developer.android.com/reference/android/webkit/WebView.html#loadData(java.lang.String, java.lang.String, java.lang.String)
   	 	mWebView.loadData (mHttpResults, "text/html", null);
    }
    
}
