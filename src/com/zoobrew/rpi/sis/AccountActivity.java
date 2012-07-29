package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AccountActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        
        
     // Get the message from the intent
	    Intent intent = getIntent();
	    //String message1 = intent.getStringExtra(Login.MESSAGE_user);
	    //String message2 = intent.getStringExtra(Login.MESSAGE_pass);
	    
	    String message1 = intent.getStringExtra(MainActivity.MESSAGE1);
	    String message2 = intent.getStringExtra(MainActivity.MESSAGE2);

	    // Create the text view
	    TextView user = (TextView) findViewById(R.id.Text1);
	    user.setText(message1);

	    TextView pass = (TextView) findViewById(R.id.Text2);
	    pass.setText(message2);
	    
    }
	
	//Called when the user presses the proceed button
    public void ContinueToMain(View view) {
    	
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    	
    }

}
