package com.jjclade.therappy;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogMoods extends Main {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Log Moods");

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().
			                     add(R.id.content_frame, new Page1()).
								 commit();
		}
	}

	// Can move this to its own class file if that's easier to handle
	private static class Page1 extends Fragment {
		public Page1() {
			// empty as required for Fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView=inflater.inflate(R.layout.fragment_log_moods, container, false);

			return rootView;
		}
	}
}
