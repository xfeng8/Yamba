package com.intel.shanghai.Yamba;

import com.marakana.android.yamba.clientlib.YambaClient;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

public class YambaApplication extends Application implements OnSharedPreferenceChangeListener{
	private SharedPreferences prefs = null;
	YambaClient yambaClient;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.prefs = PreferenceManager.getDefaultSharedPreferences(YambaApplication.this);
		this.prefs.registerOnSharedPreferenceChangeListener(this);
		Log.d("Yamba", "YambaApplication onCreate");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		this.prefs.unregisterOnSharedPreferenceChangeListener(this);
		Log.d("Yamba", "YambaApplication onTerminate");
	}
	
	public synchronized YambaClient getYambaClient() {
		if(this.yambaClient == null){
		
	        try{
	        	this.yambaClient = new YambaClient(this.prefs.getString("username", "usr"), 
	        			this.prefs.getString("password", "passwd"));
	        }catch(Exception e){
	        	Log.d("Yamba", e.getMessage());
	        	startActivity(new Intent(YambaApplication.this, PrefsActivity.class));
	        }
		}
		return this.yambaClient;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		this.yambaClient = null;	
	}

}
