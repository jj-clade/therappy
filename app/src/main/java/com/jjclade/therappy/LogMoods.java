package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LogMoods extends Main {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Log Moods");

		if (savedInstanceState == null) {
			switchFragmentTo(1);
		}
	}

	private void switchFragmentTo(int pageNumber) {
		FragmentTransaction tx=getFragmentManager().beginTransaction();

		switch (pageNumber) {
			case 1:
				tx.add(R.id.content_frame, new Page1());
				break;
			case 2:
				tx.add(R.id.content_frame, new Page2());
				break;
			default:
				Toast.makeText(getApplicationContext(), "There's no page for that", Toast.LENGTH_SHORT);
				return;
		}

		tx.commit();
	}

	private class Page1 extends Fragment {
		public Page1() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods_1, container, false);

			Button button=(Button)(rootView.findViewById(R.id.next_button));
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO: check that a mood is entered
					switchFragmentTo(2);
				}
			});

			return rootView;
		}

		private LogMoods activity;
	}

	private class Page2 extends Fragment {
		public Page2() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods_2, container, false);

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
