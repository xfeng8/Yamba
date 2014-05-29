package com.intel.shanghai.Yamba;

import java.util.List;

import com.marakana.android.yamba.clientlib.YambaClient.Status;
import com.marakana.android.yamba.clientlib.YambaClientException;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class TimelineActivity extends ListActivity{
	YambaArrayAdapter yambaArrayAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Yamba", "TimelineActivity onCreate");
		
		//yambaArrayAdapter = new YambaArrayAdapter(TimelineActivity.this, null);
		//setListAdapter(yambaArrayAdapter);
		
		DisplayMessage displayer = new DisplayMessage();
		displayer.execute(20);
	}
	
	private class DisplayMessage extends AsyncTask<Integer, Integer, Void>{
		List<com.marakana.android.yamba.clientlib.YambaClient.Status> values;
		
		@Override
		protected Void doInBackground(Integer... lines) {
			try {
				values = 
						((YambaApplication)getApplication()).getYambaClient().getTimeline(lines[0]);
				
				for (com.marakana.android.yamba.clientlib.YambaClient.Status status : values){
					Log.d("Yamba", status.getUser() + ": " +status.getMessage() + "=" + status.getCreatedAt());
					//yambaArrayAdapter.add(status);
				}
				//setListAdapter(new YambaArrayAdapter(TimelineActivity.this, values));
				
			} catch (YambaClientException e) {
				e.printStackTrace();
			}	
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			setListAdapter(new YambaArrayAdapter(TimelineActivity.this, values));
			//yambaArrayAdapter.notifyDataSetChanged();
		}
		
		
					
	}
}
