package com.example.eguanjia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * 学生易管家
 * @author：11物联 应震宇 118325140 江苏师范大学科文学院
 * 开始时间：
 * 结束时间：
 */
public class MainActivity extends ActionBarActivity  {
private	EguanjiaDBhelper mydbhelper;
	EguanjiaDBhelper2 mydbhelper2;     
	        Button bncw;
            Button bnkc;
            Button bnjk;
           TextView tv;
            SQLiteDatabase sqldb;
            SQLiteDatabase sqldb2;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bncw=(Button)findViewById(R.id.bcw);
		bnkc=(Button)findViewById(R.id.bkc);
		bnjk=(Button)findViewById(R.id.bjk);
		tv=(TextView)findViewById(R.id.textView1);
		 mydbhelper = new EguanjiaDBhelper(this , "db_cw.db3" , 1);
		 mydbhelper2 = new EguanjiaDBhelper2(this , "db_kc.db3" , 1);
		 sqldb = mydbhelper.getWritableDatabase(); 
		 sqldb2 = mydbhelper2.getWritableDatabase();
		 tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i=new Intent(MainActivity.this,SettingActivity.class);
			startActivity(i);
			
			}
		});
		bncw.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0){
				
				Cursor cursor = mydbhelper.getReadableDatabase().rawQuery(
						"select * from table_cw ", null);
				Bundle cwls = new Bundle();
				cwls.putSerializable("cwls", converCursorToList(cursor));
				
				Intent intent = new Intent(MainActivity.this,CaiwuList.class);
				intent.putExtras(cwls);
				startActivity(intent);
			}
		});
		bnkc.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg1){
				startActivity(new Intent (MainActivity.this, guanjiapager.class) );  
			}
		});
		bnjk.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg1){
			
				sqldb2.execSQL("INSERT INTO week VALUES('语文','物理','英语','体育','数学','化学')");
				sqldb2.execSQL("INSERT INTO week VALUES('语文','物理','英语','体育','数学','化学')");
				sqldb2.execSQL("INSERT INTO week VALUES('语文','物理','英语','体育','数学','化学')");
				sqldb2.execSQL("INSERT INTO week VALUES('语文','物理','英语','体育','数学','化学')");
				sqldb2.execSQL("INSERT INTO week VALUES('语文','物理','英语','体育','数学','化学')");
				sqldb2.execSQL("INSERT INTO week VALUES('语文','物理','英语','体育','数学','化学')");
				sqldb2.execSQL("INSERT INTO week VALUES('语文','物理','英语','体育','数学','化学')");
				
				
				 Cursor cursor = mydbhelper2.getReadableDatabase().rawQuery(
							"select * from week", null);
					Bundle kcb = new Bundle();
					
					kcb.putSerializable("kcb", converCursorToList2(cursor));
					Intent intent = new Intent(MainActivity.this,cardkcb.class);
					intent.putExtras(kcb);
					startActivity(intent);
			}
		});
		
	}
	protected ArrayList<Map<String ,  Object>> converCursorToList(Cursor cursor)
	{
		ArrayList<Map<String, Object>> result = 
			new ArrayList<Map<String , Object>>();
            while(cursor.moveToNext())
		{
			
			Map<String, Object> map_cw = new 
				HashMap<String , Object>();
			//取出查询记录中第2列、第3列的值
			map_cw.put("id" , cursor.getString(0));
			map_cw.put("shouzhi", cursor.getString(1));
			map_cw.put("type" , cursor.getString(2));
			map_cw.put("money" , cursor.getString(3));
			map_cw.put("time", cursor.getString(4));
			map_cw.put("desc", cursor.getString(5));
			result.add(map_cw);
			}
		return result;		
	}
	protected ArrayList<Map<String , Object>> converCursorToList2(Cursor cursor)
	{
		ArrayList<Map<String , Object>> result = 
			new ArrayList<Map<String , Object>>();
            while(cursor.moveToNext())
		{
			
			Map<String , Object> map_kc = new 
				HashMap<String , Object>();
			//取出查询记录中第2列、第3列的值
			map_kc.put("c1" , cursor.getString(0));
			map_kc.put("c2" , cursor.getString(1));
			map_kc.put("c3", cursor.getString(2));
			map_kc.put("c4", cursor.getString(3));
			map_kc.put("c5", cursor.getString(4));
			map_kc.put("c6", cursor.getString(5));
		
			
			result.add(map_kc);
			}
           
		return result;		
	}


	
}
