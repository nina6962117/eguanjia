package com.example.eguanjia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chiemy.cardview.view.CardAdapter;
import com.chiemy.cardview.view.CardView;
import com.chiemy.cardview.view.CardView.OnCardClickListener;


public class cardkcb extends FragmentActivity implements OnCardClickListener{
	List<String> list;
	private TestFragment frag;
	List<Map<String, Object>> kclist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cardkcblist);
		Intent intent = getIntent();
        Bundle data = intent.getExtras();
        kclist = (List<Map<String, Object>>)data.getSerializable("kcb");
        initUI();
	}
	
	private void initUI() {
		CardView cardView = (CardView) findViewById(R.id.cardView1);
		cardView.setOnCardClickListener(this);
		cardView.setItemSpace(Utils.convertDpToPixelInt(this, 20));
		
		MyCardAdapter adapter = new MyCardAdapter(this);
		adapter.addAll(initData());
		cardView.setAdapter(adapter);
		
		FragmentManager manager = getSupportFragmentManager();
		frag = new TestFragment();
		manager.beginTransaction().add(R.id.contentView, frag).commit();
	}
	
	@Override
	public void onCardClick(final View view, final int position) {
		Toast.makeText(cardkcb.this, position + "", Toast.LENGTH_SHORT).show();
		Bundle bundle = new Bundle();
		bundle.putString("text", list.get(position%list.size()));
		frag.show(view,bundle);
	}
	
	
	
	private List<String> initData() {
		list = new ArrayList<String>();
		list.add("周一");
		list.add("周二");
		list.add("周三");
		list.add("周四");
		list.add("周五");
		list.add("周六");
		list.add("周日");
		return list;
	}

	public class MyCardAdapter extends CardAdapter<String>{

		public MyCardAdapter(Context context) {
			super(context);
		}
		
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}
		
		@Override
		protected View getCardView(int position,
				View convertView, ViewGroup parent) {
			if(convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(cardkcb.this);
				convertView = inflater.inflate(R.layout.item_layout, parent, false);
			}
			TextView c1 = (TextView) convertView.findViewById(R.id.c1);
			TextView c2 = (TextView) convertView.findViewById(R.id.c2);
			TextView c3 = (TextView) convertView.findViewById(R.id.c3);
			TextView c4 = (TextView) convertView.findViewById(R.id.c4);
			TextView c5 = (TextView) convertView.findViewById(R.id.c5);
			TextView c6 = (TextView) convertView.findViewById(R.id.c6);
			
			
			String class1=(String)kclist.get(position).get("c1");
			String class2=(String)kclist.get(position).get("c2");
			String class3=(String)kclist.get(position).get("c3");
			String class4=(String)kclist.get(position).get("c4");
			String class5=(String)kclist.get(position).get("c5");
			String class6=(String)kclist.get(position).get("c6");
			c1.setText(class1);
			c2.setText(class2);
			c3.setText(class3);
			c4.setText(class4);
			c5.setText(class5);
			c6.setText(class6);
			TextView tv = (TextView) convertView.findViewById(R.id.textView1);
			String text = getItem(position%list.size());
			tv.setText(text);
			return convertView;
		}
	public	class viewHolder{
		
		}
	
	}

}
