package com.run.sg.amap2d.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public final class FeatureView extends FrameLayout {

	public FeatureView(Context context) {
		super(context);

		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(com.run.sg.amap2d.R.layout.feature, this);
	}

	public synchronized void setTitleId(int titleId, boolean issub) {
		String title = this.getResources().getString(titleId);
		if (issub) {
			((TextView) (findViewById(com.run.sg.amap2d.R.id.title))).setText("         "+title);
		} else{
			((TextView) (findViewById(com.run.sg.amap2d.R.id.title))).setText(title);
		}

	}

//	public synchronized void setDescriptionId(int descriptionId) {
//		((TextView) (findViewById(R.id.description))).setText(descriptionId);
//	}

}
