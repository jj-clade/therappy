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
import android.widget.Toast;

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

		if (!isInDerivedClass()) {
			// Add the fragment, but only if this is actually the Main activity...
			getFragmentManager().beginTransaction().
			                     add(R.id.content_frame, new MainUIFragment()).
								 commit();
		}
	}

	protected TherappyApplication getTherappy() { return (TherappyApplication)getApplication(); }

	private void initializeData() {
		TherappyApplication.DataModel model=getTherappy().getModel();

		if (model.behaviors == null) {
			model.behaviors=getTree(R.array.behaviors, false);
		}
		if (model.beliefs == null) {
			model.beliefs=getTree(R.array.beliefs, false);
		}
		if (model.moods == null) {
			model.moods=getTree(R.array.moods, true);
		}
		/*if (model.triggers == null) {
			model.triggers=getTree(R.array.triggers, false);
		}*/
	}

	private StringTree getTree(int rootId, boolean isMood) {
		String[] strings=getResources().getStringArray(rootId);

		if ((strings == null) ||
		    (strings.length == 0)) {
			return null;
		}

		if (strings.length == 1) {
			return (isMood) ? (new MoodLeaf(strings[0])) :
			                  (new StringLeaf(strings[0]));
		}
		// length at least 2
		
		StringTree ret=new StringTree(strings[0]);

		for (int i=1; i<strings.length; i++) {
			if ((strings[i].length() > 5) &&
				(strings[i].substring(0, 5).equals("link:"))) {
				// refers to a child array
				int id=getResources().getIdentifier(strings[i].substring(5, strings[i].length()), "array", "com.jjclade.therappy");

				assert(id != 0);

				ret.add(getTree(id, isMood));
			} else {
				// refers to a leaf
				StringLeaf leaf;

				if (isMood) {
					leaf=new MoodLeaf(strings[i]);
					((MoodLeaf)leaf).intensity=1.0*(strings.length-1)/i;
				} else {
					leaf=new StringLeaf(strings[i]);
				}

				ret.add(leaf);
			}
		}

		return ret;
	}

	private int failure=0;

	@Override
	public void onResume() {
		super.onResume();

		if (!isInDerivedClass()) {
			initializeData();
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
			default:
				// Unknown
				return;
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
			default:
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
}
