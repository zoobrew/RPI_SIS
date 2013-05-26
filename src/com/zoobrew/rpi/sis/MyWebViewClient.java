package com.zoobrew.rpi.sis;

import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

	private String mHttpResults;
	private WebView view;
	final Handler mHandler = new Handler();
	final Runnable mUpdateResults = new Runnable() {
    	public void run() {
    		updateResultsInUi();
    	}
    };
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView v, String url)
	{
		Log.d("Override", url);
		view = v;
		//handler for callback to UI thread
		startLongRunningOperation(url);
		return true;
	}
	
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
    	
    	view.loadDataWithBaseURL("https://sis.rpi.edu/", mHttpResults, "text/html", null, null);
        
    }	
	
}
