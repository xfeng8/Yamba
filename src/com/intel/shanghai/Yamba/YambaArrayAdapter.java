package com.intel.shanghai.Yamba;

import java.util.List;

import com.marakana.android.yamba.clientlib.YambaClient.Status;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class YambaArrayAdapter extends ArrayAdapter<Status> {

	private Context context;
	private List<Status> values;
	
	public YambaArrayAdapter(Context context,List<Status> values) {
		super(context, R.layout.list_layout, values);
		Log.d("Yamba", "YambaArrayAdapter constructer");
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	        Log.d("Yamba", "getView start");
		    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    
		    View rowView = inflater.inflate(R.layout.list_layout, parent, false);
		    TextView tv_User = (TextView) rowView.findViewById(R.id.labelUser);
		    TextView tv_Date = (TextView) rowView.findViewById(R.id.labelDate);
		    TextView tv_Message = (TextView) rowView.findViewById(R.id.labelMessage);
		    ImageView imageView = (ImageView) rowView.findViewById(R.id.iconJudet);
		    
		    tv_User.setText(values.get(position).getUser());
		    tv_Date.setText(values.get(position).getCreatedAt().toString());
		    //Log.d("Yamba", "values.get(position).getCreatedAt()="+values.get(position).getCreatedAt());
		    
		    
		    tv_Message.setText(values.get(position).getMessage());
		    tv_Message.setBackgroundColor(Color.GRAY);
		    
		    imageView.setImageResource(R.drawable.ic_launcher);
		    Log.d("Yamba", "getView complete");
		    return rowView;
	}
	
	

}
