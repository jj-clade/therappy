package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
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
			currentTrigger=getTherappy().getModel().triggers;
			currentBelief=getTherappy().getModel().beliefs;
			currentBehavior=getTherappy().getModel().behaviors;

			switchFragmentTo(1);
		}
	}

	private void finishUp() {
		// TODO: Save the log entry...
		// Move to the main activity
		Intent intent=new Intent(this, Main.class);
		// Clear the back stack
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

	private void switchFragmentTo(int pageNumber) {
		FragmentTransaction tx=getFragmentManager().beginTransaction();

		switch (pageNumber) {
			case 1:
				// do nothing
				break;
			case 2:
				// trying to move on to trigger
				if (!(currentMood instanceof MoodLeaf)) {
					// Not yet there...
					pageNumber=1;
				}
				break;
			case 3:
				// trying to move on to belief
				if (!(currentTrigger instanceof StringLeaf)) {
					pageNumber=2;
				}
				break;
			case 4:
				// trying to move on to behavior
				if (!(currentBelief instanceof StringLeaf)) {
					pageNumber=3;
				}
				break;
			default:
				Toast.makeText(getApplicationContext(), "There's no page for that", Toast.LENGTH_SHORT);
				return;
		}

		switch (pageNumber) {
			case 1:
				tx.replace(R.id.content_frame, new Page1());
				break;
			case 2:
				tx.replace(R.id.content_frame, new Page2());
				break;
			case 3:
				tx.replace(R.id.content_frame, new Page3());
				break;
			case 4:
				tx.replace(R.id.content_frame, new Page4());
				break;
			default:
				// Can't happen...
				assert(false);
		}

		tx.commit();
		
		getFragmentManager().executePendingTransactions();
	}

	private void selectMood(int child) {
		if (currentMood.kids.size() < 1) {
			return;
		}

		currentMood=currentMood.kids.get(child);
	}

	private void selectTrigger(int child) {
		if (currentTrigger.kids.size() < 1) {
			return;
		}

		child=(child>currentTrigger.kids.size()) ? currentTrigger.kids.size()-1 : child;

		currentTrigger=currentTrigger.kids.get(child);
	}

	private void selectBelief(int child) {
		if (currentBelief.kids.size() < 1) {
			return;
		}

		child=(child>currentBelief.kids.size()) ? currentBelief.kids.size()-1 : child;

		currentBelief=currentBelief.kids.get(child);
	}

	private void selectBehavior(int child) {
		if (currentBehavior.kids.size() < 1) {
			return;
		}

		child=(child>currentBehavior.kids.size()) ? currentBehavior.kids.size()-1 : child;

		currentBehavior=currentBehavior.kids.get(child);
	}

	private StringTree currentMood=null;
	private StringTree currentTrigger=null;
	private StringTree currentBelief=null;
	private StringTree currentBehavior=null;

	private void fillListView(ListView lv, StringTree st, ListView.OnItemClickListener oicl) {
		String[] names=st.toArray();
		lv.setAdapter(null);
		lv.setAdapter(new ArrayAdapter<String>(lv.getContext(), R.layout.list_item, names));
		lv.setOnItemClickListener(oicl);
	}

	/** Mood logger */
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
					// Try to move on to trigger
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

	/** Trigger logger */
	private class Page2 extends Fragment {
		public Page2() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods_2, container, false);

			fillListView((ListView)(rootView.findViewById(R.id.listViewTriggers)), currentTrigger, new ListItemClickListener());

			Button button=(Button)(rootView.findViewById(R.id.next_button));
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (selected < 0) {
						return;
					}

					selectTrigger(selected);
					switchFragmentTo(3);
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

	/** Belief logger */
	private class Page3 extends Fragment {
		public Page3() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods_3, container, false);

			fillListView((ListView)(rootView.findViewById(R.id.listViewMoods)), currentBelief, new ListItemClickListener());

			Button button=(Button)(rootView.findViewById(R.id.next_button));
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (selected < 0) {
						return;
					}

					selectBelief(selected);
					switchFragmentTo(4);
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

	/** Behavior logger */
	private class Page4 extends Fragment {
		public Page4() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods_4, container, false);

			fillListView((ListView)(rootView.findViewById(R.id.listViewMoods)), currentBehavior, new ListItemClickListener());

			Button button=(Button)(rootView.findViewById(R.id.next_button));
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (selected < 0) {
						return;
					}

					selectBehavior(selected);
					finishUp();
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
}
