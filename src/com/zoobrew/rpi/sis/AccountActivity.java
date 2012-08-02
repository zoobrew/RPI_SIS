package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;


public class AccountActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        Toast.makeText(this, "Logging in...", Toast.LENGTH_LONG).show();
        
        
     // Get the message from the intent
	    Intent intent = getIntent();
	    String message1 = intent.getStringExtra(Login.MESSAGE_user);
	    String message2 = intent.getStringExtra(Login.MESSAGE_pass);
	    

	    /* Create the text view
	    TextView user = (TextView) findViewById(R.id.Text1);
	    user.setText(message1);

	    TextView pass = (TextView) findViewById(R.id.Text2);
	    pass.setText(message2);
	    */
	    WebView myWebView = (WebView) findViewById(R.id.login);
        myWebView.getSettings().setJavaScriptEnabled(true);
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
	    
    }
	
	//Called when the user presses the proceed button
    public void ContinueToMain(View view) {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);	
    }
    
    
    
    
    /*private void start_login(String[] array) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "Logging in...", Toast.LENGTH_LONG).show();

        WebView wv = new WebView(this);     
        this.setContentView(wv);

        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("UserName", <my username here>));
            nameValuePairs.add(new BasicNameValuePair("PIN", <my password here>));
            nameValuePairs.add(new BasicNameValuePair("Domain", "STUDENT"));
            nameValuePairs.add(new BasicNameValuePair("p2", "https://wish.wis.ntu.edu.sg/pls/webexe/aus_stars_check.check_subject_web2"));

            wv.loadData(CustomHttpClient.executeHttpPost(URL, nameValuePairs), "text/html", "utf-8");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }// end start_login

    @Override
 	protected void onPause() {
 		// TODO Auto-generated method stub
 		super.onPause();
 		finish();
 	}
 	*/
}
