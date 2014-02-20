package com.skanderjabouzi.salat;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

//Automatically called when State Change is Detected because this Receiver is Registered for PHONE_STATE intent filter in AndroidManifest.xml
public class SalatPhoneReceiver extends BroadcastReceiver {
	
	public void onReceive(Context context, Intent intent) {

        String incomingPhoneNumber =  intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Toast.makeText(context, "Phone State - "+TelephonyManager.EXTRA_STATE+" Incoming Number - "+incomingPhoneNumber, Toast.LENGTH_LONG).show();
		context.stopService(new Intent(context, AthanService.class));
		Toast.makeText(context, "Athan service stopped", Toast.LENGTH_LONG).show();
        
        /*if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.CALL_STATE_IDLE))
        {
			Toast.makeText(context, "Phone State is IDLE", Toast.LENGTH_LONG).show();
        }

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
        {
            Toast.makeText(context, "Call State is OFFHOOK",Toast.LENGTH_LONG).show();
        }

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            Toast.makeText(context, "Phone State is RINGING", Toast.LENGTH_LONG).show();
        }*/
	}

}

/*import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
//Automatically called when State Change is Detected because this Receiver is Registered for PHONE_STATE intent filter in AndroidManifest.xml
public class PhoneStateReceiver extends BroadcastReceiver {
	
	TelephonyManager manager;       
	PhoneStateMonitor phoneStateListener;
	static boolean isAlreadyListening=false;

	//This Method automatically Executed when Phone State Change is Detected
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		phoneStateListener =new PhoneStateMonitor(context);//Creating the Object of Listener
		manager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);//Getting the Telephony Service Object
		if(!isAlreadyListening)//Checking Listener is Not Registered with Telephony Services
		{
              manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);//Registering the Listener with Telephony to listen the State Change
	      isAlreadyListening=true;  //setting true to indicate that Listener is listening the Phone State
		}

	}

}

*/