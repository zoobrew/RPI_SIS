package com.zoobrew.rpi.sis;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AccountActivity extends Activity {
	String user;
	String pass;
	InputStream is = null;
	String error;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        
        
        
        // Get the message from the intent
	    Intent intent = getIntent();
	    user = intent.getStringExtra(Login.MESSAGE_user);
	    pass = intent.getStringExtra(Login.MESSAGE_pass);
	    
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("sid", user));
        nameValuePairs.add(new BasicNameValuePair("PIN", pass));
        
        try {
			MyWebViewClient.executeHttpPost("https://sis.rpi.edu/rss/twbkwbis.P_ValLogin", nameValuePairs);
			error= "log";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "catch";
		}
	    
	    TextView err = (TextView) findViewById(R.id.login);
	    err.setText(error);
	    
	    /* Create the text view
	    

	    TextView pass = (TextView) findViewById(R.id.Text2);
	    pass.setText(message2);
	    
	    WebView myWebView = (WebView) findViewById(R.id.login);
        //myWebView.getSettings().setJavaScriptEnabled(true);
        //load as zoomed out
        //myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        
        try{
        	myWebView.loadUrl("https://sis.rpi.edu/rss/twbkwbis.P_WWWLogin");
        	myWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        */
	    
    }
    
    /*private void start_login() {
        // TODO Auto-generated method stub
        Toast.makeText(this, "Logging in...", Toast.LENGTH_LONG).show();
        
        Thread thread = new Thread(){
        	public void run(){
		        try {
		
		            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		            nameValuePairs.add(new BasicNameValuePair("sid", user));
		            nameValuePairs.add(new BasicNameValuePair("PIN", pass));
		            
		            HttpClient httpclient = getClient();
		            //HttpPost httppost = new HttpPost(KEY_121);
		            HttpPost httppost = new HttpPost("https://sis.rpi.edu/rss/twbkwbis.P_WWWLogin");
		            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            HttpResponse response = httpclient.execute(httppost);
		            HttpEntity entity = response.getEntity();
		            is = entity.getContent();
		            // writing response to log
		            Log.d("Http Response:", response.toString());
		            Log.d("Http Content:", is.toString());
		            
		    	    
		
		            //wv.loadData(CustomHttpClient.executeHttpPost(URL, nameValuePairs), "text/html", "utf-8");
		
		        } catch (Exception e) {
		            // TODO Auto-generated catch block
		        	Log.e("log_tag", "Error in http connection "+e.toString());
		        	e.printStackTrace();
		        }
        	}
        };
        thread.start();

    }
    */
    


	//Called when the user presses the proceed button
    public void ContinueToMain(View view) {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);	
    }
    // end start_login
    /*
    @Override
 	protected void onPause() {
 		// TODO Auto-generated method stub
 		super.onPause();
 		finish();
 	}
 	*/
}
