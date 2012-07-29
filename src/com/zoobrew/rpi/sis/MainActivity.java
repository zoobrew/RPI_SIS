package com.zoobrew.rpi.sis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
//import android.view.MenuItem;
//import android.support.v4.app.NavUtils;

public class MainActivity extends ExpandableListActivity 
{	
	private static final String NAME = "NAME";
	private static final String IS_EVEN = "IS_EVEN";
	private ExpandableListAdapter mAdapter;
	public final static String MESSAGE1 = "com.zoobrew.rpi.sis.Main1";
	public final static String MESSAGE2 = "com.zoobrew.rpi.sis.Main2";
 
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
    
        List<Map<String, String>> menuData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> submData = new ArrayList<List<Map<String, String>>>();
        
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
       		 android.R.layout.simple_expandable_list_item_1, // Layout for the first-level entries new
       		 new String[] { NAME,  IS_EVEN }, // Key in the groupData maps to display 
       		 new int[] { android.R.id.text1, android.R.id.text2 }, // Data under "Item" key goes into this TextView createChildList(),
       		 submData, // describes second-level entries 
       		 android.R.layout.simple_list_item_2, // Layout for second-level entries 
       		 new String[] { IS_EVEN, NAME }, // Keys in childData maps to display 
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
    public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) 
    {
		if (groupPosition < Titles.Menus.length)
		{
			if (childPosition < Titles.SubMenu[groupPosition].length)
			{
				Intent intent = new Intent(this, AccountActivity.class);
		    	String number1 = Integer.toString(groupPosition);
		    	intent.putExtra(MESSAGE1, number1);
		    	String number2 = Integer.toString(childPosition);
		    	intent.putExtra(MESSAGE2, number2);
		    	startActivity(intent);
				
				return true;
			}
			else
				return false;
		}
		else
			return false;
    }
}
