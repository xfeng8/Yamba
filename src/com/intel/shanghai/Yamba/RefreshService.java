package com.intel.shanghai.Yamba;

import java.util.List;

import com.marakana.android.yamba.clientlib.YambaClient.Status;

import android.app.IntentService;
import android.content.Intent;
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
		try {
			List<Status> timeline = ((YambaApplication)getApplication()).getYambaClient().getTimeline(20);
			for (Status status : timeline){
				Log.d("Yamba", status.getUser() + ": " +status.getMessage() + "=" + status.getCreatedAt());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
