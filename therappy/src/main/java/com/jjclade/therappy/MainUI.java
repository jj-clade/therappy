package com.jjclade.therappy;

import android.app.Activity;
import android.os.Bundle;

public class MainUI extends Activity
{
	/** Utility method to replace content with a fragment
	 *  @param fragment new fragment to replace
	 *  @param enableBack whether to push the previous frame onto the back stack
	 */
	public static void switchContent(Fragment fragment, boolean enableBack) {
		FragmentTransaction tx=getFragmentManager().beginTransaction();

		tx.replace(R.id.content_frame, fragment);

		if (enableBack) {
			tx.addToBackStack(null);4
		}

		tx.commit();
	}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		drawerMenuItems=getResources().getStringArray(R.array.menu_array);
		drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		drawerList=(ListView)findViewById(R.id.left_drawer);

		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawerMenuItems));
		drawerList.setOnItemClickHandler(listViewListener);

		mainMenu=new MainMenu();

		switchContext(mainMenu, false);
    }

	/** Swap fragments into the main content view */
	private void selectItem(int position) {
		Fragment fragment;
		
		switch (position) {
			case 0:
				fragment=mainMenu;
				break;
			case 1:
				return;
			case 2:
				return;
			case 3:
				return;
			case 4:
				return;
			default:
				return;
		}

		switchContent(fragment, true);

		drawerList.setItemChecked(position, true);
		if (position > 0) {
			getActionBar().setTitle(drawerMenuItems[position]);
		}
		drawerLayout.closeDrawer(drawerList);
	}

	private String[] drawerMenuItems;
	private DrawerLayout drawerLayout;
	private ListView drawerList;

	private Fragment mainMenu;

	private ListView.OnItemClickListener listViewListener=new ListView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}
	};
}
