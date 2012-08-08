package com.zoobrew.rpi.sis;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity{
	public final static String MESSAGE_user = "com.zoobrew.rpi.sis.temp.LOGINmess";
	public final static String MESSAGE_pass = "com.zoobrew.rpi.sis.temp.LOGINpass";
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    
    
    //Called when the user presses the login button
    public void LoginUser(View view) {
    	Intent intent = new Intent(this, AccountActivity.class);
    	EditText username = (EditText) findViewById(R.id.etUsername);
    	String user = username.getText().toString();
    	EditText password = (EditText) findViewById(R.id.etPassword);
    	String pass = password.getText().toString();	
    	intent.putExtra(MESSAGE_user, user);
    	intent.putExtra (MESSAGE_pass, pass);
    	startActivity(intent);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    nameValuePairs.add(new BasicNameValuePair("sid", user));
	    nameValuePairs.add(new BasicNameValuePair("PIN", pass));
	    
	    final Handler mHandler = new Handler();
	     
	    HttpHelper network = new HttpHelper(nameValuePairs);
	    try {
			HttpHelper.executeHttpPost("https://sis.rpi.edu/rss/twbkwbis.P_ValLogin", nameValuePairs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    new Thread(network).start();
	     
	    Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() { 
	         public void run() { 
	              //my_button.setBackgroundResource(R.drawable.defaultcard); 
	         } 
	    }, 2000); 
    }


	@Override
	protected void onPause() {
		super.onPause();
		//finish();
	}
    
    

}
