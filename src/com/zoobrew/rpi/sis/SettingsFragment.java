package com.zoobrew.rpi.sis;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

//TODO convert to settingfragment
public class SettingsFragment extends PreferenceFragment  {
	String user;
	String pass;
	InputStream is = null;
	String error;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.settingsmenu);
        Activity activity = getActivity();
	}
    
	
	
}
