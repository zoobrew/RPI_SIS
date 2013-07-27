package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.app.ListFragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {
	OnMenuSelectedListener mCallback;
	Resources res = getResources();
	private String[][] SubMenus;
	private String[] Menu;


	int mCurrentPosition = -1;

	// The container Activity must implement this interface so the frag can deliver messages
	public interface OnMenuSelectedListener {
		/** Called by MenuFragment when a list item is selected */
		public void onMenuSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Menu = res.getStringArray(R.array.Menus);
		TypedArray ta = res.obtainTypedArray(R.array.SubMenus);
		int n = ta.length();
		SubMenus = new String[n][];
		for (int i = 0; i < n; ++i) {
			int id = ta.getResourceId(i, 0);
			if (id > 0)
				SubMenus[i] = res.getStringArray(id);
			else {
				// something wrong with the XML
			}
		}
		ta.recycle();

		// We need to use a different list item layout for devices older than Honeycomb
		int layout = Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1 ?
				android.R.layout.simple_list_item_2 : android.R.layout.simple_list_item_1;
		//android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

		// Create an array adapter for the list view, using the Ipsum headlines array
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Menu));
	}

	@Override
	public void onStart() {
		super.onStart();

		// When in two-pane layout, set the listview to highlight the selected list item
		// (We do this during onStart because at the point the listview is available.)
		if (getFragmentManager().findFragmentById(R.id.item_fragment) != null)
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
			mCallback = (OnMenuSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnMenuSelectedListener");
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Notify the parent activity of selected item
		mCallback.onMenuSelected(position);

		// Set the item as checked to be highlighted when in two-pane layout
		//getListView().setItemChecked(position, true);
	}

	public void updateMenuView(int position) {
		//ListView menu = (ListView) getActivity().findViewById(R.id.fragment_container);
		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.main,  SubMenus[position]));
		mCurrentPosition = position;
	}
}
