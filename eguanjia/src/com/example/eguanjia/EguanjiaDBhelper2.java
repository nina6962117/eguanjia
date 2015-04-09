package com.example.eguanjia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EguanjiaDBhelper2 extends SQLiteOpenHelper {
	  
	  
	private SQLiteDatabase database;  
	
	final String CREATE_TABLE_SQLkc ="Create table table_kc(ID_KC INTEGER PRIMARY KEY AUTOINCREMENT, LESSON_NAME varchar,TEACHER VARCHAR,ROOM VARCHAR)";
	final String CREATE_TABLE_SQLkb1="Create table table_kb1(LESSON_NAME VARCHAR, M1 INTEGER,M2 INTEGER, M3 INTEGER,M4 INTEGER,M5 INTEGER)" ;
	final String CREATE_TABLE_WEEK="Create table week(CLASS1 VARCHAR, CLASS2 VARCHAR, CLASS3 VARCHAR, CLASS4  VARCHAR, CLASS5 VARCHAR, CLASS6 VARCHAR)";
	public EguanjiaDBhelper2(Context context, String name, int version)
		{
			super(context, name, null, version);
			database = getWritableDatabase();
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// 第一个使用数据库时自动建表
			
			db.execSQL(CREATE_TABLE_SQLkc);
			db.execSQL(CREATE_TABLE_SQLkb1);
			
			db.execSQL(CREATE_TABLE_WEEK);  
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			
				  
		}

}
