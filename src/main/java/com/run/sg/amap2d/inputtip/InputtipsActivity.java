package com.run.sg.amap2d.inputtip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Inputtips.InputtipsListener;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.run.sg.amap2d.util.ToastUtil;

public class InputtipsActivity extends Activity implements TextWatcher, InputtipsListener {

	private String city = "北京";
	private AutoCompleteTextView mKeywordText;
	private ListView minputlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.run.sg.amap2d.R.layout.activity_inputtip);
		minputlist = (ListView)findViewById(com.run.sg.amap2d.R.id.inputlist);
		mKeywordText = (AutoCompleteTextView)findViewById(com.run.sg.amap2d.R.id.input_edittext);
        mKeywordText.addTextChangedListener(this);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String newText = s.toString().trim();
        InputtipsQuery inputquery = new InputtipsQuery(newText, city);
        inputquery.setCityLimit(true);
        Inputtips inputTips = new Inputtips(InputtipsActivity.this, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
        
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetInputtips(final List<Tip> tipList, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            List<HashMap<String, String>> listString = new ArrayList<HashMap<String, String>>();
            for (int i = 0; i < tipList.size(); i++) {
            	HashMap<String, String> map = new HashMap<String, String>();
            	map.put("name", tipList.get(i).getName());
            	map.put("address", tipList.get(i).getDistrict());
                listString.add(map);
            }
            SimpleAdapter aAdapter = new SimpleAdapter(getApplicationContext(), listString, com.run.sg.amap2d.R.layout.item_layout,
            		new String[] {"name","address"}, new int[] {com.run.sg.amap2d.R.id.poi_field_id, com.run.sg.amap2d.R.id.poi_value_id});

            minputlist.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();

        } else {
			ToastUtil
					.showerror(this.getApplicationContext(), rCode);
		}
		
	}

}
