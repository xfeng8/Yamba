package com.intel.shanghai.Yamba;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSLogger extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		   final Bundle bundle = intent.getExtras();

		    try {

		        if (bundle != null) {

		            final Object[] pdusObj = (Object[]) bundle.get("pdus");

		            for (int i = 0; i < pdusObj.length; i++) {

		                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
		                String phoneNumber = currentMessage.getDisplayOriginatingAddress();

		                String senderNum = phoneNumber;
		                String message = currentMessage.getDisplayMessageBody();

		                Log.d("Yamba", "senderNum: "+ senderNum + "; message: " + message);


		               // Show Alert
		                int duration = Toast.LENGTH_LONG;
		                Toast toast = Toast.makeText(context, 
		                             "senderNum: "+ senderNum + ", message: " + message, duration);
		                toast.show();

		            } // end for loop
		          } // bundle is null

		    } catch (Exception e) {
		        Log.e("Yamba", "Exception smsReceiver" +e);

		    }
	}

}
