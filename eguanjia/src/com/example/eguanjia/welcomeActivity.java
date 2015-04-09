package com.example.eguanjia;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class welcomeActivity extends Activity {
	private Handler mh=new Handler(){
		@Override
		public void handleMessage(android.os.Message msg){
			startActivity(new Intent (welcomeActivity.this, MainActivity.class) ); 
			welcomeActivity.this.finish();
		}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		new Thread(new Runnable(){
			public void run(){
				mh.sendEmptyMessageDelayed(3, 2500);
			}
		}).start();
	
	}
}
