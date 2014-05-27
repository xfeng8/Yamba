package com.intel.shanghai.Yamba;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends Activity  {

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.main, menu);
		 return true;
		//return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	startActivity(new Intent(this, PrefsActivity.class));
    	return true;
		//return super.onOptionsItemSelected(item);
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MyTextWatcher watcher = new MyTextWatcher();
        
        EditText text = (EditText)findViewById(R.id.editText);
        text.addTextChangedListener(watcher);
        
        // set proxy to make it working for emulator within local network
        System.setProperty("http.proxyHost",  "proxy-prc.intel.com");
        System.setProperty("http.proxyPort",  "911");
    }
    
    // handle the click on "Post update"
    public void postTwitterUpdate(View v)
    {
    	Toast.makeText(this, "we are upating...", Toast.LENGTH_LONG).show();
    	EditText et = (EditText)findViewById(R.id.editText);
    	PostTweetAsync poster=new PostTweetAsync();
    	poster.execute(et.getText().toString());
    } 
    
    private class PostTweetAsync extends AsyncTask<String, Integer, String> {
    	private ProgressDialog progress;
    	
    	@Override
        protected String doInBackground(String...post) {
        	YambaClient client = new YambaClient("student", "password");
        	try {
    			client.postStatus(post[0]);
    		} catch (YambaClientException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			return "OK";
        }

    	@Override
        protected void onProgressUpdate(Integer... progress) {
        	
        }
        
    	@Override
        protected void onPreExecute(){
        	progress =  ProgressDialog.show(MainActivity.this, "Posting to yamba server", "Please wait..");
        	
        }

        protected void onPostExecute(String result) {
        	progress.dismiss();
        	Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }
    
    private class MyTextWatcher implements TextWatcher
    {

		@Override
		public void afterTextChanged(Editable s) {
			int count = 140 - s.length();
			TextView text = (TextView) findViewById(R.id.textView1);
			text.setText(Integer.toString(count)); 
			
			// if we have 50 chars left we change the color to RED
			if (count < 50)
				text.setTextColor(Color.RED);
			else
				text.setTextColor(Color.GREEN);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			//TextView text = (TextView) findViewById(R.id.textView1);
			//text.setText("Typing.."+s); 
		}    	
    }

}
