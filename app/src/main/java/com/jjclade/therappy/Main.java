package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends Activity {
	/** *** UGLY HACK ALERT!!! ***
	 *
	 *  Are we in a derived class of Main?
	 */
	protected boolean isInDerivedClass() {
		return (getClass() != Main.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		title=drawerTitle=getTitle();
		drawerItems=getResources().getStringArray(R.array.drawer_items_array);
		drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		drawerList=(ListView)findViewById(R.id.left_drawer);

		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawerItems));
		drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);
			}
		});

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerToggle=new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(title);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(drawerTitle);
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);

		if ((savedInstanceState != null) &&
		    (!isInDerivedClass())) {
			// Add the fragment
			getFragmentManager().beginTransaction().
			                     replace(R.id.content_frame, muf);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch(item.getItemId()) {
			case R.id.action_therappy_help:
				selectItem(5);
				break;
			case R.id.action_settings:
				selectItem(6);
				break;
			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}

	private void selectItem(int itemSelected) {
		Intent intent=null;

		switch (itemSelected) {
			case 0:
				if (isInDerivedClass()) {
					intent=new Intent(this, Main.class);
				}
				break;
			case 1:
				// Log Moods
				intent=new Intent(this, LogMoods.class);
				break;
			case 2:
				// Find Patterns
				intent=new Intent(this, FindPatterns.class);
				break;
			case 3:
				// Get Help
				intent=new Intent(this, GetHelp.class);
				break;
			case 4:
				intent=new Intent(this, EditMoods.class);
				break;
			case 5:
				intent=new Intent(this, TherappyHelp.class);
				break;
			case 6:
				intent=new Intent(this, TherappyPreferences.class);
				break;
		}

		switch (itemSelected) {
			case 5:
				// Help
				setTitle(getResources().getString(R.string.action_therappy_help));
				break;
			case 6:
				// Settings
				setTitle(getResources().getString(R.string.action_settings));
				break;
		}

		drawerList.setItemChecked(itemSelected, true);
		drawerLayout.closeDrawer(drawerList);

		if (intent != null) {
			startActivity(intent);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		this.title=title;
		getActionBar().setTitle(title);
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
	
	private CharSequence drawerTitle;
	private CharSequence title;
	private String[] drawerItems;

	/** Holds data etc. which persists across Activity changes */
	static class TherappyContext {
	}

	protected static TherappyContext context;

	public void nextButtonOnClickLogMoods(View view) {
		System.out.println("Next Button Clicked");
	}

	private static class MainUIFragment extends Fragment {
		public MainUIFragment() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.main_screen, container, false);
			return rootView;
		}
	}

	private static MainUIFragment muf;

	static {
		muf=new MainUIFragment();
	}
}
