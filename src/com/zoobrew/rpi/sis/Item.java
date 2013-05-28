package com.zoobrew.rpi.sis;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class Item extends Activity {
	
	protected String mHttpResults;
	public static WebView mWebView;
	protected DefaultHttpClient mClient;
	
    //handler for callback to UI thread
    final Handler mHandler = new Handler();
    final Runnable mUpdateResults = new Runnable() {
    	public void run() {
    		updateResultsInUi();
    	}
    };

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);

        //getWindow().requestFeature(Window.FEATURE_PROGRESS);
        mWebView = (WebView) findViewById(R.id.webview);
        setupWeb();
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.layout_main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		// Handle item selection
	    switch (item.getItemId()) {
	    	case android.R.id.home:
		    	//app icon in action bar clicked; go home
	            Intent goHome = new Intent(this, MainMenuActivity.class);
	            goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(goHome);
	            return true;
	        case R.id.menu_settings:
	        	//Settings icon clicked
	        	Intent intent = new Intent(this, SettingsActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_logout:
	        	//Log out selected
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
        
    public void setupWeb(){
    	      
        //Obtain the data from the intent that indicates the url to open
        Intent intent = getIntent();
	    int mNUM = Integer.parseInt(intent.getStringExtra(MainMenuActivity.MENUNUM));
	    int smNUM = Integer.parseInt(intent.getStringExtra(MainMenuActivity.SUBMENUNUM));
	    
	    //Obtain the URL from array.xml file
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
        ta.recycle();
	    String url = HTMLaddresses[mNUM][smNUM];
	    
	    //Enable progress bars
	    
	    /*final Activity activity = this;
	    mWebView.setWebChromeClient(new WebChromeClient() {
	      public void onProgressChanged(WebView view, int progress) {
	        // Activities and WebViews measure progress with different scales.
	        // The progress meter will automatically disappear when we reach 100%
	        activity.setProgress(progress * 1000);
	      }
	    });
	    */
	    
	    mClient = HttpHelper.getClient();
	    
	    //setup Webview
	    mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        //mWebView.getSettings().setSavePassword(true);
        //mWebClient.shouldOverrideUrlLoading(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        //mWebView.setWebChromeClient(new WebChromeClient()); 
        try{
        	startLongRunningOperation(url);
        	//myWebView.loadUrl("https://sis.rpi.edu/rss/bwskrsta.P_RegsStatusDisp");
        	//myWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        }
        catch(Exception e){
        	e.printStackTrace();
        }
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
    	
    	// see http://developer.android.com/reference/android/webkit/WebView.html#loadData(java.lang.String, java.lang.String, java.lang.String)
    	mWebView.loadDataWithBaseURL("https://sis.rpi.edu/", mHttpResults, "text/html", null, null);
        
    }
    
}
