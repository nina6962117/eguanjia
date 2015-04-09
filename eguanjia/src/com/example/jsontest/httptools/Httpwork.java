package com.example.jsontest.httptools;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.widget.Toast;

public class Httpwork {
 private static String path="http://api.k780.com:88/?app=weather.future&weaid=1&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
	public Httpwork(){
	 
 }
 public static String sendPostrequest(String  ask,String encode ){
	String result="";
	
	HttpClient httpclient=new DefaultHttpClient();
	 String url=path;
	
	
	try{
		
		HttpGet get=new HttpGet(url); 
	  
	 
	    HttpResponse response=httpclient.execute(get);
	    if(response.getStatusLine().getStatusCode() == 200){
	    result=EntityUtils.toString(response.getEntity(),encode);
	    System.out.print("jsonback"+result);
	    }
	 }catch(Exception e){
		 e.printStackTrace();
	 }finally{
		 httpclient.getConnectionManager().shutdown();
	 }
	 
	 return result;
	 
 }
 public static String sendPostrequest2(String  ask,String encode ){
		String result="";
		
		HttpClient httpclient=new DefaultHttpClient();
		 
		
		
		try{
			
			HttpGet get=new HttpGet(ask); 
		  
		 
		    HttpResponse response=httpclient.execute(get);
		    if(response.getStatusLine().getStatusCode() == 200){
		    result=EntityUtils.toString(response.getEntity(),encode);
		    System.out.print("jsonback"+result);
		    }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 httpclient.getConnectionManager().shutdown();
		 }
		 
		 return result;
		 
	 }
}
