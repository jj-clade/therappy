package com.jjclade.therappy;

import android.app.Application;

public class TherappyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		data=new DataModel();
	}

	public DataModel getModel() {
		return data;
	}

	public static class DataModel {
		public StringTree beliefs=null;
		public StringTree behaviors=null;
		public StringTree moods=null;
		public StringTree triggers=null;
	}

	private DataModel data;
}
