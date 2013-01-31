package ru.truba.touchgallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {
	
	public static final String TITLE = "title";
	public static final String SUBTITLE = "subtitle";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
		setListAdapter(createAdapter());
	}
	
	protected Map<String, String> createElement(String title, String subtitle) 
	{
		Map<String, String> result = new HashMap<String, String>();
		result.put(TITLE, title);
		result.put(SUBTITLE, subtitle);
		return result;
	}
	public List<Map<String, String>> getData() 
	{
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		result.add(createElement("Web load", "In this example, you provide list of URLs to display in gallery"));
		result.add(createElement("Local load", "In this example, you provide list of files  to display in gallery"));
		return result;
	}
	public ListAdapter createAdapter() 
	{
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), 
				android.R.layout.simple_list_item_2, 
				new String[]{TITLE, SUBTITLE}, 
				new int[]{android.R.id.text1, android.R.id.text2});
		return adapter;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = null;
		switch (position) 
		{
		case 0:
			i = new Intent(this, GalleryUrlActivity.class);
			break;
		case 1:
			i = new Intent(this, GalleryFileActivity.class);
			break;
		}
		startActivity(i);
	}
	
}
