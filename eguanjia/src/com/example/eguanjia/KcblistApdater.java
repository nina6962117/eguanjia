package com.example.eguanjia;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KcblistApdater extends BaseAdapter {

	private Context context;
	private List<Map<String,Object>> list;
	private LayoutInflater inflater;

	public KcblistApdater(Context context, List<Map<String, Object>> list2) {
		super();
		this.context = context;
		this.list = list2;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.kcbview_item, null);
			viewHolder = new ViewHolder();

			viewHolder.name = (TextView) convertView.findViewById(R.id.image_1);
			viewHolder.room = (TextView) convertView.findViewById(R.id.KC);
			viewHolder.room = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.name.setText((String)list.get(position).get("name"));
		viewHolder.room.setText((String)list.get(position).get("room"));
		//viewHolder.teacher.setText((String)list.get(position).get("teacher"));
		return convertView;
	}

	 class ViewHolder {
		public TextView name;
		public TextView room;
		public TextView teacher;
	}
}
