package com.intel.shanghai.Yamba;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d("Yamba", "BootReceiver-onReceive.");
		context.startService(new Intent(context, RefreshService.class));
	}

}
