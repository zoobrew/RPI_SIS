package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    	//login
    	
    	Intent intent = new Intent(this, AccountActivity.class);
    	EditText username = (EditText) findViewById(R.id.edit_username);
    	String message1 = username.getText().toString();
    	EditText password = (EditText) findViewById(R.id.edit_password);
    	String message2 = password.getText().toString();	
    	intent.putExtra(MESSAGE_user, message1);
    	intent.putExtra (MESSAGE_pass, message2);
    	startActivity(intent);
    	
    }


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
    
    

}
