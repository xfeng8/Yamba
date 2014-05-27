package com.intel.shanghai.Yamba;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.os.AsyncTask;
import android.app.ProgressDialog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
}
