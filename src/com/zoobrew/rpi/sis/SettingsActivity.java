package com.zoobrew.rpi.sis;

import java.io.InputStream;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

//TODO convert to settingfragment
public class SettingsActivity extends PreferenceActivity {
	String user;
	String pass;
	InputStream is = null;
	String error;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.settingsmenu);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        //setContentView(R.layout.settingsmenu);
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
	    	case android.R.id.home:
		    	// app icon in action bar clicked; go home
	            Intent goHome = new Intent(this, MainActivity.class);
	            goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(goHome);
	            return true;
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
