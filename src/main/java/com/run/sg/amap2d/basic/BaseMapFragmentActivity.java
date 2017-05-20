package com.run.sg.amap2d.basic;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.SupportMapFragment;

public class BaseMapFragmentActivity extends FragmentActivity {
	private AMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.run.sg.amap2d.R.layout.basemap_fragment_activity);
		setUpMapIfNeeded();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(com.run.sg.amap2d.R.id.map)).getMap();
		}
	}

}
