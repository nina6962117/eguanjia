package com.example.eguanjia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.jsontest.Jsontools.jsonwork;


import com.example.jsontest.httptools.Httpwork;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Tab页面手势滑动切换以及动画效果
 * 
 * @author D.Winter
 * 
 */
public class guanjiapager extends Activity {
	// ViewPager是google SDk中自带的一个附加包的一个类，可以用来实现屏幕间的切换。
	// android-support-v4.jar
	private ViewPager mPager;//页卡内容
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t1, t2, t3;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
    private ProgressDialog psg;
    private Button search ,search2;
    private EditText edcity,edpostname,edpostnum;
	private weatherAdaper wad;
	private postAdapter pad;
	private String city,postname,postnum;
	private ListView weatherlv,postlv;
	private View v1,v2,v3;
	
	private  final static  String jsonlog="MY JSON IS!";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pagerview);
		InitImageView();
		InitTextView();
		InitViewPager();
		//String ask=edcity.getText().toString();
		
		
		
		
	}

	/**
	 * 初始化头标
	 */
	private void InitTextView() {
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);

		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
		t3.setOnClickListener(new MyOnClickListener(2));
	}

	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.lay1, null));
		listViews.add(mInflater.inflate(R.layout.lay2, null));
		listViews.add(mInflater.inflate(R.layout.lay3, null));
		
	
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * 初始化动画
	 */
	private void InitImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.vp)
				.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			if (arg1 < 3) {
                ((ViewPager) arg0).addView(mListViews.get(arg1 % 3), 0);
            }
            // 测试页卡1内的按钮事件
            if (arg1 == 0) {
               search = (Button) arg0.findViewById(R.id.search);
               edcity=(EditText)arg0.findViewById(R.id.etcity);
       		weatherlv=(ListView)arg0.findViewById(R.id.lvweather);
       		
                search.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                    	   String ask=edcity.getText().toString();
           				
           				new weatherAsync().execute(ask);
                    }
                });
            }else if(arg1 == 1){
            	search2 = (Button) arg0.findViewById(R.id.search2);
            	postlv=(ListView)arg0.findViewById(R.id.lvpost);
            
            	search2.setOnClickListener(new OnClickListener() {
            	
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					String path= "http://apis.haoservice.com/lifeservice/exp?key=bd00732fd90444599b744d4123b57657&com=zhongtong&no=360734128776";
					new postAsync().execute(path);
					}
				});
            }
            return mListViews.get(arg1 % 3);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
		
	
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	class postAsync extends AsyncTask<String,Void, List<Map<String,Object>>>{

		@Override
		protected List<Map<String,Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Map<String,Object>> postList = new ArrayList<Map<String,Object>>();
			String jsonRes = Httpwork.sendPostrequest2(params[0], "utf-8");
			 postList=jsonwork.convertList2(jsonRes);
			Log.i( guanjiapager.jsonlog,jsonRes);
			return postList;
		}
		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			pad = new postAdapter(getApplicationContext(), result);
			postlv.setAdapter(pad);
		}

	
		
		
	}
	class weatherAsync extends AsyncTask<String,Void,List<Map<String,Object>>>{
    	
		@Override
		
		protected List<Map<String,Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
		
			
			
			 List<Map<String,Object>> weatherlist=new ArrayList<Map<String,Object>>();		    String json=Httpwork.sendPostrequest(params[0],"utf-8");
			 String jsonres=Httpwork.sendPostrequest(params[0],"utf-8");
			 Log.i( guanjiapager.jsonlog,jsonres);    
			 weatherlist=jsonwork.convertList(jsonres);
			 return weatherlist;
		}
		@Override
		protected void onPostExecute(List<Map<String,Object>> result){
		 
		  wad=new weatherAdaper(guanjiapager.this , result);
		 weatherlv.setAdapter(wad);
		}
			
		}
	public class postAdapter extends BaseAdapter{
     private List<Map<String,Object>> list;
	 public postAdapter(Context context , List<Map<String,Object>>  list){
		 this.list=list;
	 }
     @Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			postvholder pvh = new postvholder();
			if(convertView==null) {
				convertView = View.inflate(getApplicationContext(),
						R.layout.postitem, null);
				pvh.content = (TextView)convertView.findViewById(R.id.content);
				pvh.time = (TextView)convertView.findViewById(R.id.time);
				convertView.setTag(pvh);
			}else{
				pvh=(postvholder)convertView.getTag();
			}
		pvh.content.setText((String)list.get(position).get("position"));
		pvh.time.setText((String)list.get(position).get("time"));
			return convertView;
		}
		class  postvholder{
			TextView time;
			TextView content;
		}
		
	}
public class weatherAdaper extends BaseAdapter{
	private List<Map<String, Object>> list;
	public weatherAdaper(Context context, List<Map<String, Object>> list) {
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh=new ViewHolder();
		if(convertView==null){
			convertView = View.inflate(getApplicationContext(),
					R.layout.weatheritem, null);
			vh.tvdate = (TextView)convertView.findViewById(R.id.tvdate);
			vh.tvweather = (TextView) convertView.findViewById(R.id.tvweather);
			vh.tvtemp = (TextView) convertView.findViewById(R.id.tvtemp);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder)convertView.getTag();
		}
		vh.tvdate.setText((String)list.get(position).get("days"));
		vh.tvtemp.setText((String)list.get(position).get("temperature"));
		vh.tvweather.setText((String)list.get(position).get("weather"));
		return convertView;
	}
		public class ViewHolder{
			TextView tvdate;
			TextView tvweather;
			TextView tvtemp;
		
		}
	
	}
}