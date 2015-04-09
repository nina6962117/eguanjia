package com.example.eguanjia;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class kcService extends Service {
EguanjiaDBhelper2 ejh2;
SQLiteDatabase db;
Handler handler;
Timer t;
private NotificationManager nm;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		
		ejh2 = new EguanjiaDBhelper2(this , "db_kc.db3" , 1);
		 handler=new Handler(){
			   public void handleMessage(android.os.Message msg) {
				   android.support.v4.app.NotificationCompat.Builder builder=new  NotificationCompat.Builder(getApplicationContext());
			       builder.setSmallIcon(R.drawable.ic_launcher);
			       builder.setContentTitle("¿Î³ÌÍÆËÍ");
			       builder.setContentText("!!!!!!");
			       Notification noti=builder.build();
			       nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
			       nm.notify(1200, noti);
			   }
			   
		   };
	}
   @Override
public int onStartCommand(Intent intent, int flags, int startId) {
	// TODO Auto-generated method stub
	   t=new Timer();
		t.schedule(new kctimer(),1000, 6000);
	
	return super.onStartCommand(intent, flags, startId);
	
}
  public class kctimer extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message msg=handler.obtainMessage();
		handler.sendMessage(msg);
	}
	  
  }
 
   @Override
public void onDestroy() {
	// TODO Auto-generated method stub
	
	   super.onDestroy();
	  t.cancel();
}

}
