package com.zoobrew.rpi.sis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class MainMenuActivity extends ExpandableListActivity 
{	
	private static final String NAME = "NAME";
	private static final String IS_EVEN = "IS_EVEN";
	private ExpandableListAdapter mAdapter;
	public final static String MENUNUM = "com.zoobrew.rpi.sis.MenuNum";
	public final static String SUBMENUNUM= "com.zoobrew.rpi.sis.SubMenuNum";
	private String[][] SubMenus;
	private String[] Menu;
 
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
    
        /** Set up Arrays from the array.xml file **/
        List<Map<String, String>> menuData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> submData = new ArrayList<List<Map<String, String>>>();
        Resources res = getResources();
        Menu = res.getStringArray(R.array.Menus);
        TypedArray ta = res.obtainTypedArray(R.array.SubMenus);
        int n = ta.length();
        SubMenus = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                SubMenus[i] = res.getStringArray(id);
            } else {
                // something wrong with the XML
            }
        }
        ta.recycle(); // Important!
        
        /** Set up Main and expandable Submenu into arrays **/ 
        for (int i =0; i < Menu.length; i++) 
        {
       	 	Map<String, String> curGroupMap = new HashMap<String, String>();
            menuData.add(curGroupMap);
            curGroupMap.put(NAME, Menu[i]);
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            
            for (int j = 0; j < SubMenus[i].length; j++) 
            {
                Map<String, String> curChildMap = new HashMap<String, String>();
                children.add(curChildMap);
                curChildMap.put(NAME, SubMenus[i][j]);    
            }
            submData.add(children);
        }
        
        // Set up our adapter
        mAdapter = new SimpleExpandableListAdapter(
       		 this, 
       		 menuData, // menuData describes the first-level entries 
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
        getMenuInflater().inflate(R.menu.layout_main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		// Handle item selection
	    switch (item.getItemId()) {
	    	case android.R.id.home:
		    	//app icon in action bar clicked; go home
	            Intent goHome = new Intent(this, MainMenuActivity.class);
	            goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(goHome);
	            return true;
	        case R.id.menu_settings:
	        	//settings icon selected
	        	Intent intent = new Intent(this, SettingsActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_logout:
	        	//Log Out selected
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    /** When Submenu item is selected start the appropriate activity **/
    @Override
    public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) 
    {
		if (groupPosition < Menu.length)
		{
			if (childPosition < SubMenus[groupPosition].length)
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
