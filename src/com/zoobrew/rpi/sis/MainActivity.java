package com.zoobrew.rpi.sis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class MainActivity extends ExpandableListActivity 
{	
	private static final String NAME = "NAME";
	private static final String IS_EVEN = "IS_EVEN";
	private ExpandableListAdapter mAdapter;
	public final static String MENUNUM = "com.zoobrew.rpi.sis.MenuNum";
	public final static String SUBMENUNUM= "com.zoobrew.rpi.sis.SubMenuNum";
 
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        PreferenceManager.setDefaultValues(this, R.layout.settingsmenu, false);
    
        List<Map<String, String>> menuData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> submData = new ArrayList<List<Map<String, String>>>();
        
        Resources res = getResources();
        String[] SubMenus = res.getStringArray(R.array.SubMenus);
        
        for (int i =0; i < 5; i++) 
        {
       	 	Map<String, String> curGroupMap = new HashMap<String, String>();
            menuData.add(curGroupMap);
            curGroupMap.put(NAME, Titles.Menus[i]);
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            
            for (int j = 0; j < Titles.SubMenu[i].length; j++) 
            {
                Map<String, String> curChildMap = new HashMap<String, String>();
                children.add(curChildMap);
                curChildMap.put(NAME, Titles.SubMenu[i][j]);    
            }
            submData.add(children);
        }
        
        // Set up our adapter
        mAdapter = new SimpleExpandableListAdapter(
       		 this, 
       		 menuData, // groupData describes the first-level entries 
       		 android.R.layout.simple_expandable_list_item_2, // Layout for the first-level entries new
       		 new String[] { NAME,  IS_EVEN }, // Key in the menuData maps to display 
       		 new int[] { android.R.id.text1, android.R.id.text2 }, // Data under "Item" key goes into this TextView createChildList(),
       		 submData, // describes second-level entries 
       		 android.R.layout.simple_list_item_1, // Layout for second-level entries 
       		 new String[] { NAME, IS_EVEN }, // Keys in submData maps to display 
       		 new int[] { android.R.id.text1, android.R.id.text2 } // Data under the keys above go into these TextViews 
        	 );
        setListAdapter(mAdapter);
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
		//TODO add on click functionality
        getMenuInflater().inflate(R.menu.layout_main, menu);
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
	        case R.id.menu_settings:
	        	Intent intent = new Intent(this, SettingsActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_logout:
	        	Intent logout = new Intent(this, Login.class);
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/*Called when the user presses the settings button in the action overflow
    public void ButtonPress(View view){
    	Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
    }
    */
    
    @Override
    public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) 
    {
		if (groupPosition < Titles.Menus.length)
		{
			if (childPosition < Titles.SubMenu[groupPosition].length)
			{
				Intent intent = new Intent(this, Item.class);
		    	String number1 = Integer.toString(groupPosition);
		    	intent.putExtra(MENUNUM, number1);
		    	String number2 = Integer.toString(childPosition);
		    	intent.putExtra(SUBMENUNUM, number2);
		    	startActivity(intent);
				
				return true;
			}
		}
		return false;
    }
}
