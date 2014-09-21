package com.sise.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sise.bean.App;

import android.util.Log;

public class ParseJsonUtils {

	public final static String TAG = "ParseJsonUtils";

	/**
	 * 使用JSONObject解析JSON
	 * @param jsonData : JSON数据
	 */
	public static void parseJSONWithJSONObject(String jsonData) {
		try {
			JSONArray jsonArray = new JSONArray(jsonData);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String id = jsonObject.getString("id");
				String name = jsonObject.getString("name");
				String version = jsonObject.getString("version");

				Log.d(TAG, "id is " + id);
				Log.d(TAG, "name is " + name);
				Log.d(TAG, "version is " + version);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 使用GSON解析JSON数组
	 * @param jsonData ：JSON数据
	 */
	public static void parseJSONWithGSON(String jsonData){
		Gson gson=new Gson();
		
		List<App> appList=gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
		
		for (App app : appList) {
			Log.d(TAG, "id is "+app.getId());
			Log.d(TAG, "name is "+app.getName());
			Log.d(TAG, "version is "+app.getVersion());
		}
		
	}
	
	
	
	

}
