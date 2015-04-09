package com.example.eguanjia;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.text.Editable;
import android.view.*;
import android.view.View.OnClickListener;
public class Addcw extends Activity{
   private EditText shouru,money,beizhu;
   private RadioGroup sz;
   private TextView date,time;
   private EguanjiaDBhelper helper;
   private SQLiteDatabase cwsql;
   private Button baocun,quxiao;
   Calendar clock=Calendar.getInstance();
   int hour = clock.get(Calendar.HOUR_OF_DAY);
   int minute = clock.get(Calendar.MINUTE);
   int year=clock.get(Calendar.YEAR);
   int month=clock.get(Calendar.MONTH);
   int day=clock.get(Calendar.DATE);
   private TimePickerDialog timepick;
   private DatePickerDialog datepick; 
   private int shouzhi;
   private String timestr;
   private String yearstr;
   private String cash;
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.newcw);
    shouru=(EditText)this.findViewById(R.id.editText1);
    date=(TextView)this.findViewById(R.id.textView3);
    time=(TextView)this.findViewById(R.id.textView2);
    sz=(RadioGroup)this.findViewById(R.id.radioGroup1);
	money=(EditText)this.findViewById(R.id.editText2);
	beizhu=(EditText)this.findViewById(R.id.editText3);
	baocun=(Button)this.findViewById(R.id.button1);
	quxiao=(Button)this.findViewById(R.id.button2);
	helper = new EguanjiaDBhelper(this , "db_cw.db3" , 1);
	cwsql=helper.getWritableDatabase(); 
	timepick=new TimePickerDialog(this, new OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			 timestr=hourOfDay+":"+minute;
			time.setText(timestr);
 		    
		}
	}, hour, minute, true);
	 datepick=new DatePickerDialog(this,new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			 yearstr=year+"年"+monthOfYear+"月"+dayOfMonth+"日";
			date.setText(yearstr);
		}
	} , year, month, day);
	 time.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			timepick.show();
		}
	});
	 date.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
    datepick.show();			
		}
	});
	 sz.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(checkedId==R.id.radio0){
				shouzhi=1;
				
			}else if(checkedId==R.id.radio1)
				shouzhi=0;
				
			
		}
	});
	 baocun.setOnClickListener(new OnClickListener() {
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			InserData();
		}
	});
   }
	private  void InserData(){
		String sql="";
		String time=yearstr+timestr; 
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this); 
		int XIANE=Integer.parseInt(prefs.getString("child_checkbox_preference", ""));
		boolean TIX=prefs.getBoolean("parent_checkbox_preference", false);
		if((Integer.parseInt(money.getText().toString()))>XIANE&&shouzhi==0&&TIX==true){
			Toast.makeText(this, "土豪别任性啦！！", 1).show();
			
		}
		sql="INSERT INTO table_cw VALUES(NULL,"+shouzhi+",'"+shouru.getText()+"','"+money.getText()+"','"+time+"','"+beizhu.getText()+"')";
	    
		cwsql.execSQL(sql);
	    System.out.print("fucking bumber is"+shouzhi);
	}
}
