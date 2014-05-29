package com.intel.shanghai.Yamba;

import java.util.List;

import com.marakana.android.yamba.clientlib.YambaClient.Status;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RefreshService extends IntentService {

	public RefreshService() {
		super("Yamba");
		Log.d("Yamba", "constructor");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.d("Yamba", "intent service started.");
		
		DBHelper helper = new DBHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		try {
			List<Status> timeline = ((YambaApplication)getApplication()).getYambaClient().getTimeline(20);
			for (Status status : timeline){
				Log.d("Yamba", status.getUser() + ": " +status.getMessage() + "=" + status.getCreatedAt());
				values.clear();
				values.put(DBHelper.C_USER, status.getUser());
				values.put(DBHelper.C_MESSAGE, status.getMessage());
				values.put(DBHelper.C_CREATED_AT, status.getCreatedAt().getTime());
				db.insertOrThrow(DBHelper.TABLE, null, values);
			}
		} 
		catch (Throwable e) {
			e.printStackTrace();
		} 
		finally	{			
			db.close();
		}
	}

}
