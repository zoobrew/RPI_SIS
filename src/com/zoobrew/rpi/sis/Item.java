package com.zoobrew.rpi.sis;

import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Item extends Activity {
	
	protected String mHttpResults;
	protected WebView mWebView;
	protected DefaultHttpClient mClient;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.item);
        mWebView = (WebView) findViewById(R.id.webview);
        setupWeb();
        
    }
        
    public void setupWeb(){
    	      
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
	    
	    //Enable progress bars
	    
	    final Activity activity = this;
	    mWebView.setWebChromeClient(new WebChromeClient() {
	      public void onProgressChanged(WebView view, int progress) {
	        // Activities and WebViews measure progress with different scales.
	        // The progress meter will automatically disappear when we reach 100%
	        activity.setProgress(progress * 1000);
	      }
	    });
	    
	    
	    //Sync cookies with httpClient
	    
	    mClient = HttpHelper.getClient();
	    Cookie sessionInfo;
	    List<Cookie> cookies = mClient.getCookieStore().getCookies();

	    if (! cookies.isEmpty()){
	    		System.out.print("COOKIE");
	            CookieSyncManager.createInstance(getApplicationContext());
	            CookieManager cookieManager = CookieManager.getInstance();

	            for(Cookie cookie : cookies){
	                    sessionInfo = cookie;
	                    String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
	                    cookieManager.setCookie("sis.rpi.edu", cookieString);
	                    CookieSyncManager.getInstance().sync();
	            }
	    }
	    
	    
	    //setup Webview
	    mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        //mWebView.setWebViewClient(new MyWebViewClient());
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
   	 	mWebView.loadDataWithBaseURL("https://sis.rpi.edu/", mHttpResults, "text/html", null, null);
    	//mWebView.loadData(mHttpResults, "text/html", null);
    }
    
}
