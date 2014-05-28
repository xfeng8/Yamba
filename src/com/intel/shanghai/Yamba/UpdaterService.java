package com.intel.shanghai.Yamba;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {

	private final static int DELAY = 60000; // 1 minute delay between the get timeline operation
	private static boolean runFlag = false;
	Updater updater;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		Log.d("Yamba", "Service onCreate");
		super.onCreate();
		
		//at the beginning we create a thread
		updater = new Updater();
	}

	@Override
	public void onDestroy() {
		Log.d("Yamba", "Service onDestroy");
		super.onDestroy();
		
		this.runFlag = false;
		this.updater = null;
		this.updater.interrupt();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.d("Yamba", "Service onStartCommand");
		
		if(!this.runFlag){
			this.runFlag = true;
			this.updater.run();
		}
		return START_STICKY;
	}

	
	private class Updater extends Thread {

		public Updater()
		{
			super("updater");			
		}
		@Override
		public void run() {
			UpdaterService updaterService = UpdaterService.this;
			while(updaterService.runFlag){
				try {
					Thread.sleep(DELAY);
				} catch (Throwable e) {
					e.printStackTrace();
					updaterService.runFlag = false;
				}
			}
		}
	}
}

