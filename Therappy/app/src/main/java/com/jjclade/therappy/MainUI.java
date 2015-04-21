package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainUI extends Activity
{
	/** Utility method to replace content with a fragment
	 *  @param fragment new fragment to replace
	 *  @param enableBack whether to push the previous frame onto the back stack
	 */
	public static void switchContent(Fragment fragment, boolean enableBack) {
		FragmentTransaction tx=mainUI.getFragmentManager().beginTransaction();

		tx.replace(R.id.content_frame, fragment);

		if (enableBack) {
			tx.addToBackStack(null);
		}

		tx.commit();
	}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		if (mainUI != this) {
			mainUI=this;
		}

//		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		drawerMenuItems=getResources().getStringArray(R.array.menu_array);
		drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		drawerList=(ListView)findViewById(R.id.left_drawer);

		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.main, drawerMenuItems));
		drawerList.setOnItemClickListener(listViewListener);

		mainMenu=new MainMenu();

		switchContent(mainMenu, false);
    }

	/** Swap fragments into the main content view */
	private void selectItem(int position) {
		Fragment fragment;
		
		switch (position) {
			case 1:
				fragment=mainMenu;
				break;
			case 2:
				return;
			case 3:
				return;
			case 4:
				return;
			case 5:
				return;
			default:
				return;
		}

		switchContent(fragment, true);

		drawerList.setItemChecked(position, true);
		if (position > 1) {
			getActionBar().setTitle(drawerMenuItems[position]);
		}
		drawerLayout.closeDrawer(drawerList);
	}

	private String[] drawerMenuItems;
	private DrawerLayout drawerLayout;
	private ListView drawerList;

	private Fragment mainMenu;

	private static MainUI mainUI=null;

	private ListView.OnItemClickListener listViewListener=new ListView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}
	};
}