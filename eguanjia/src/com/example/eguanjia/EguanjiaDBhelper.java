package com.example.eguanjia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EguanjiaDBhelper extends SQLiteOpenHelper {
  private static EguanjiaDBhelper mInstance;
	final String CREATE_TABLE_SQLcw =
			"Create table table_cw(id_cw INTEGER PRIMARY KEY AUTOINCREMENT, shouzhi INTEGER,type varchar,money INTEGER, time varchar,bz varchar)";
	
	
		public EguanjiaDBhelper(Context context, String name, int version)
		{
			super(context, name, null, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// 第一个使用数据库时自动建表
			db.execSQL(CREATE_TABLE_SQLcw);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			
		}

}
