package com.zoobrew.rpi.sis;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{
	protected boolean mResults;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Set default setting values upon entering the app
		PreferenceManager.setDefaultValues(this, R.layout.settingsmenu, false);
		setContentView(R.layout.login);
		CookieSyncManager.createInstance(this);
		CookieManager.getInstance().setAcceptCookie(true);
	}

	//handler for callback to UI thread from login thread
	final Handler mHandler = new Handler();
	final Runnable mUpdateResults = new Runnable() {
		public void run() {
			updateResultsInUi();
		}
	};

	protected void startLongRunningOperation(){
		// Fire off a thread to do some work that we shouldn't do directly in the UI thread
		Thread t = new Thread(){
			@Override
			public void run(){
				mResults = LoginUser();
				mHandler.post(mUpdateResults);
			}
		};
		t.start();
	}
	// Back in the UI thread -- update our UI elements based on the data in mResults
	private void updateResultsInUi() {
		if (mResults )
		{
			Intent intent = new Intent(this, MainMenuActivity.class);
			startActivity(intent);
		} else
			Toast.makeText(this, "Incorrect username or password" , Toast.LENGTH_SHORT).show();
	}


	public boolean LoginUser() {
		EditText username = (EditText) findViewById(R.id.etUsername);
		EditText password = (EditText) findViewById(R.id.etPassword);
		String user = username.getText().toString();
		String pass = password.getText().toString();
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("sid", user));
		nameValuePairs.add(new BasicNameValuePair("PIN", pass));
		return HttpHelper.executeHttpPost("https://sis.rpi.edu/rss/twbkwbis.P_ValLogin", nameValuePairs);
	}

	//Called when the user presses the login button
	public void ButtonPress(View view){
		EditText username = (EditText) findViewById(R.id.etUsername);
		String user = username.getText().toString();
		if ((user.length() == 9) && (user.substring(0, 3).equalsIgnoreCase("660"))){
			Toast.makeText(this, "Logging in" , Toast.LENGTH_SHORT).show();
			startLongRunningOperation();
		} else
			Toast.makeText(this, "Error with Username" , Toast.LENGTH_SHORT).show();

	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public void onResume() {
		super.onResume();
	}



}
