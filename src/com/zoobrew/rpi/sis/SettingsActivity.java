package com.zoobrew.rpi.sis;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SettingsActivity extends Activity {
	String user;
	String pass;
	InputStream is = null;
	String error;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsmenu);
	}
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
		//TODO add on click functionality
        getMenuInflater().inflate(R.menu.layout_settings, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		// Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_logout:
	        	Intent logout = new Intent(this, Login.class);
	        	startActivity(logout);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
    @Override
 	protected void onPause() {
 		// TODO Auto-generated method stub
 		super.onPause();
 		finish();
 	}
}
