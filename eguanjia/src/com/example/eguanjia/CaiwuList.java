package com.example.eguanjia;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.swipe.SwipeMenu;
import com.swipe.SwipeMenuCreator;
import com.swipe.SwipeMenuItem;
import com.swipe.SwipeMenuListView;
import com.swipe.SwipeMenuListView.OnMenuItemClickListener;
import com.swipe.SwipeMenuListView.OnSwipeListener;
/*
 * 财务列表
 */
public class CaiwuList extends Activity  {
     String[] cwtype;
     String[] money;
     String[] cwtime;
     String[] cwbz;
 	  ActionBar actbar;
     cwAdapter mAdapter; 
      TextView income;
      TextView outcome;
      TextView sum;
LinearLayout footer;
     EguanjiaDBhelper cwmydbhelper;
     SwipeMenuListView listView;
      List<Map<String, Object>>  list;
      SQLiteDatabase cwsqldb;
     @SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.cwlb);
         cwmydbhelper = new EguanjiaDBhelper(this , "db_cw.db3" , 1);
         actbar = getActionBar();
         
         Intent intent = getIntent();
         Bundle data = intent.getExtras();
          listView = (SwipeMenuListView) findViewById(R.id.cw);
         //@SuppressWarnings("unchecked")
          footer =  (LinearLayout) LayoutInflater.from(this) .inflate(R.layout.cwfooter, null); 
		
           list = (List<Map<String, Object>>)data.getSerializable("cwls");
        addFooter(footer);
           mAdapter = new cwAdapter(this, list);
 		listView.setAdapter(mAdapter);
     
 		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// create "open" item
				SwipeMenuItem openItem = new SwipeMenuItem(CaiwuList.this);
				// set item background
				
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
						0xCE)));
				// set item width
				openItem.setWidth(dp2px(90));
				// set item title
				openItem.setTitle("Open");
				// set item title fontsize
				openItem.setTitleSize(18);
				// set item title font color
				openItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(openItem);

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(
						CaiwuList.this);
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		listView.setMenuCreator(creator);
		
	listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
				Map<String,Object> item = list.get(position);
				String cwid;
				switch (index) {
				case 0:
					// open
					String str=(String) item.get("id");
					Toast.makeText(getApplicationContext(), str,
						     Toast.LENGTH_SHORT).show();
					break;
				case 1:
					// delete
					
					delete(item);
					list.remove(position);
					
					mAdapter.notifyDataSetChanged();
					break;
				}
				
			}
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}
			
		
		});
listView.setOnSwipeListener(new OnSwipeListener() {
			
			@Override
			public void onSwipeStart(int position) {
				// swipe start
			}
			
			@Override
			public void onSwipeEnd(int position) {
				// swipe end
			}
		});
listView.setOnItemLongClickListener(new OnItemLongClickListener() {

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		Toast.makeText(getApplicationContext(), position + " long click", 0).show();
		return false;
	}
});
     } 
     private void addFooter(LinearLayout footer){
    	 DecimalFormat df = new DecimalFormat("#.00");
    	 listView.addFooterView(footer);
    	 income=(TextView)findViewById(R.id.shouru);
 		outcome=(TextView)findViewById(R.id.zhichu);
 		sum=(TextView)findViewById(R.id.zongji);
    	 Cursor srcursor=cwmydbhelper.getReadableDatabase().rawQuery(
				"select money from table_cw where shouzhi=0", null);
 		double shouru=footerconver(srcursor);
 		income.setText("收入:"+df.format(shouru));
 		Cursor zccursor=cwmydbhelper.getReadableDatabase().rawQuery(
				"select money from table_cw where shouzhi=1", null);
 		double zhichu=footerconver(zccursor);
 		outcome.setText("支出:"+df.format(zhichu));
 		double sj=shouru-zhichu;
 		sum.setText("总计:"+df.format(sj));
 		
 		
     }
     private double footerconver(Cursor cursor){
    	  double i,sum = 0;
    	 while(cursor.moveToNext()){
    		 i=cursor.getDouble(0);
    		 sum=sum+i;
    	 }
    	return sum;
     }
     public boolean onCreateOptionsMenu(Menu menu)
     {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.actbar, menu);
         return true;
     }
     public boolean onOptionsItemSelected(MenuItem item) {
 		switch (item.getItemId()) {
 		case android.R.id.home:{
 			Toast.makeText(this, "home", 1).show();
 			break;
 		}
 		case R.id.item0:{
 			Toast.makeText(this, "1", 1).show();
		startActivity(new Intent(CaiwuList.this,Addcw.class));
 			break;
 		}
 		case R.id.action_settings:{
 			Toast.makeText(this, "2", 1).show();
 			break;
 		}
 		case R.id.save:{
 			Cursor cursor = cwmydbhelper.getReadableDatabase().rawQuery(
					"select * from table_cw order by money", null);
 			converCursorToList(cursor);
 			break;
 		}
 		default:
 			break;
 		}
 		return false;
 	}
    
	private void delete(Map<String, Object> map) {
 		// delete app
		 SQLiteDatabase db=cwmydbhelper.getWritableDatabase();
		String[] cwid=new String[]{String.valueOf(map.get("id"))};
 		try {
 			Intent intent = new Intent(Intent.ACTION_DELETE);
 			//intent.setData(Uri.fromParts("package", item.packageName, null));
 			startActivity(intent);
 		} catch (Exception e) {
 		}
 		System.out.println("id is"+cwid);
 		
 		 
 	    db.delete("table_cw", "id_cw=?", cwid); 
 	}

 	private void open(Map<String, Object> map) {
 		// open app
 		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
 		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
 		//resolveIntent.setPackage(map.packageName);
 		List<ResolveInfo> resolveInfoList = getPackageManager()
 				.queryIntentActivities(resolveIntent, 0);
 		if (resolveInfoList != null && resolveInfoList.size() > 0) {
 			ResolveInfo resolveInfo = resolveInfoList.get(0);
 			String activityPackageName = resolveInfo.activityInfo.packageName;
 			String className = resolveInfo.activityInfo.name;

 			Intent intent = new Intent(Intent.ACTION_MAIN);
 			intent.addCategory(Intent.CATEGORY_LAUNCHER);
 			ComponentName componentName = new ComponentName(
 					activityPackageName, className);

 			intent.setComponent(componentName);
 			startActivity(intent);
 		}
 	}
	protected void converCursorToList(Cursor cursor)
	{
		
		list.clear();
		
            while(cursor.moveToNext())
		{
			
			Map<String, Object> map_cw = new 
				HashMap<String , Object>();
			map_cw.put("id" , cursor.getString(0));
			map_cw.put("shouzhi", cursor.getString(1));
			map_cw.put("type" , cursor.getString(2));
			map_cw.put("money" , cursor.getString(3));
			map_cw.put("time", cursor.getString(4));
			map_cw.put("desc", cursor.getString(5));
			list.add(map_cw);
			}
            mAdapter = new cwAdapter(this, list);
     		listView.setAdapter(mAdapter);
     		
				
	}
 	class cwAdapter extends BaseAdapter {
     private List<Map<String, Object>> list;
		public cwAdapter(Context context, List<Map<String, Object>> list) {
			this.list=list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Map<String, Object> getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String str;double i;
			if (convertView == null) {
				convertView = View.inflate(getApplicationContext(),
						R.layout.cw_item, null);
				 new ViewHolder(convertView);
			}
				ViewHolder holder = (ViewHolder) convertView.getTag();
			
			holder.type.setText((String)list.get(position).get("type"));
			
			
			str=(String)list.get(position).get("shouzhi");
			i=Integer.valueOf(str);
			if(i==0){
				holder.money.setTextColor(Color.RED); 
				holder.money.setText((String)list.get(position).get("money"));
			}else{
				holder.money.setTextColor(Color.GREEN);
				holder.money.setText("-"+(String)list.get(position).get("money"));
				
			}
			
			holder.time.setText((String)list.get(position).get("time"));
			
			return convertView;
			
		}

	public class ViewHolder {
			TextView type;
			TextView money;
            TextView time;
			public ViewHolder(View view) {
				type = (TextView) view.findViewById(R.id.cwtype);
				money = (TextView) view.findViewById(R.id.cwmoney);
				time = (TextView) view.findViewById(R.id.cwsj);
				view.setTag(this);
			}
		}
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
	
	  

}
