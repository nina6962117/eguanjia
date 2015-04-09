package com.example.jsontest.Jsontools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class jsonwork {
   public jsonwork(){
	   
   }
   public static  List<Map<String,Object>> convertList(String json) {
	 
	  List<Map<String,Object>> resweather=new ArrayList<Map<String,Object>>();
	   try{
	   JSONObject js=new JSONObject(json);
	  JSONArray result=js.getJSONArray("result");
	  for(int i=0;i<result.length();i++){
		  Map<String,Object> dailymap=new HashMap<String, Object>();
		  JSONObject dateweather=result.getJSONObject(i);
		  dailymap.put("days",dateweather.getString("days"));
		  dailymap.put( "temperature",dateweather.getString( "temperature"));
		  dailymap.put( "weather",dateweather.getString( "weather"));
		  resweather.add(dailymap);
	  }
	
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return resweather;
	   
   }
   public static List<Map<String,Object>> convertList2(String json){
	   List<Map<String,Object>> respost=new ArrayList<Map<String,Object>>();
	   try {
		JSONObject js = new JSONObject(json);
		   JSONObject result = js.getJSONObject("result");
		   JSONArray list = result.getJSONArray("list");
		   for(int i=0;i<list.length();i++){
				  Map<String,Object> dailymap=new HashMap<String, Object>();
				  JSONObject postpot=list.getJSONObject(i);
				  dailymap.put("time",postpot.getString("time"));
				  dailymap.put( "position",postpot.getString( "context"));
				 
				  respost.add(dailymap);
			  }
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   
	   return respost;
   }
}
