package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
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
	public Main() {
		fragments=new Fragment[7];

		fragments[0]=new Fragment() {
			@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				View rootView=inflater.inflate(R.layout.main_screen, container, false);

				return rootView;
			}
		};
	}

	public static Main getInstance() {
		return theMain;
	}

	/** to be called by child fragments if they've done their thing
	 *	
	 *	@param index the index in the side menu list, or 5 for help and 6 for settings
	 */
	public void invalidateFragment(int index) {
		fragments[index]=null;
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

		if (savedInstanceState == null) {
			selectItem(0);
		}

		theMain=this;
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
				break;
			case R.id.action_settings:
				break;
			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}

	private void selectItem(int what) {
		Fragment fragment=fragments[what];

		if (fragment == null) {
			switch (what) {
				case 0:
					// already exists, do nothing
					break;
				case 1:
					// Log Moods
					fragment=new LogMoods();
					break;
				case 2:
					// Find Patterns
					fragment=new FindPatterns();
					break;
				case 3:
					// Get Help
					fragment=new GetHelp();
					break;
				case 4:
					fragment=new EditMoods();
					break;
				case 5:
					fragment=new TherappyHelp();
					break;
				case 6:
					fragment=new TherappyPreferences();
					break;
			}
			fragments[what]=fragment;
		}

		if (fragment != null) {
			getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
			if (what<5) {
				setTitle(drawerItems[what]);
			} else {
				switch (what) {
					case 5:
						// Help
						setTitle(getResources().getString(R.id.action_therappy_help));
						break;
					case 6:
						// Settings
						setTitle(getResources().getString(R.id.action_settings));
						break;
				}
			}
		}

		drawerList.setItemChecked(what, true);
		drawerLayout.closeDrawer(drawerList);
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

	private Fragment[] fragments;

	private static Main theMain;	// Not Spanish, but...
}
