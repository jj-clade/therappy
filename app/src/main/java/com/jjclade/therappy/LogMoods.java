package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class LogMoods extends Main {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Log Moods");

		if (savedInstanceState == null) {
			currentMood=getTherappy().getModel().moods;
			switchFragmentTo(1);
		}
	}

	private void switchFragmentTo(int pageNumber) {
		FragmentTransaction tx=getFragmentManager().beginTransaction();

		switch (pageNumber) {
			case 1:
				tx.replace(R.id.content_frame, new Page1());
				break;
			case 2:
				tx.replace(R.id.content_frame, new Page2());
				break;
			default:
				Toast.makeText(getApplicationContext(), "There's no page for that", Toast.LENGTH_SHORT);
				return;
		}

		tx.commit();
	}

	private void selectMood(int child) {
		if (currentMood.kids.size() == 0) {
			return;
		}
	}

	private StringTree currentMood=null;

	private void fillListView(ListView lv, StringTree st, ListView.OnItemClickListener oicl) {
		String[] names=st.toArray();
		lv.setAdapter(new ArrayAdapter<String>(lv.getContext(), R.layout.list_item, names));
		lv.setOnItemClickListener(oicl);
	}

	private class Page1 extends Fragment {
		public Page1() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods_1, container, false);

			// Load items from the tree into the ListView
			ListView moodlist=(ListView)(rootView.findViewById(R.id.listViewMoods));
			fillListView(moodlist, currentMood, new ListItemClickListener());

			Button button=(Button)(rootView.findViewById(R.id.next_button));
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (selected < 0) {
						// Nothing selected yet, do nothing
						return;
					}

					selectMood(selected);
					switchFragmentTo(2);
				}
			});

			return rootView;
		}

		private int selected=-1;

		private class ListItemClickListener implements ListView.OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selected=position;
			}
		}
	}

	private class Page2 extends Fragment {
		public Page2() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods_2, container, false);

			Toast.makeText(getApplicationContext(), "Page 2", Toast.LENGTH_SHORT);
			Button button=(Button)(rootView.findViewById(R.id.next_button));
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO: check that a mood is entered
					switchFragmentTo(3);
				}
			});

			return rootView;
		}
	}
}
