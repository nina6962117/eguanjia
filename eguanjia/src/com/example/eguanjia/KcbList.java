package com.example.eguanjia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class KcbList extends Activity {

	private ActionBar actionBar;
	
	private KcblistApdater timelineAdapter;
	 DrawerLayout kcdrawer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kcbview);
		actionBar = getActionBar();
		kcdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		Intent intent = getIntent();
        Bundle data = intent.getExtras();
        ListView listView = (ListView) findViewById(R.id.kblistview);
        @SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>)data.getSerializable("kcb");
		listView.setDividerHeight(0);
		
		timelineAdapter = new KcblistApdater(this, list);
		listView.setAdapter(timelineAdapter);
		
		actionBar.setDisplayShowHomeEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť
		actionBar.setHomeButtonEnabled(true);
		// ��Ӧ�ó���ͼ������Ϊ�ɵ���İ�ť������ͼ������������ͷ
		actionBar.setDisplayHomeAsUpEnabled(true);
	

	}
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflator = new MenuInflater(this);
		// ״̬R.menu.context��Ӧ�Ĳ˵�������ӵ�menu��
		inflator.inflate(R.menu.kbmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:{
			kcdrawer.openDrawer(Gravity.LEFT);
			break;
		}
		}
		return false;
	}

	

}
