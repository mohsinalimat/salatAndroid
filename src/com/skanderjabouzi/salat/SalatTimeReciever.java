package com.skanderjabouzi.salat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.util.Log;
import android.widget.Toast;

public class SalatTimeReciever extends BroadcastReceiver {
		
	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();
		
		Log.i("ACTION2", action);
		if (action.equals("android.intent.action.TIME_SET") || action.equals("android.intent.action.TIMEZONE_CHANGED"))
		{
			SalatBootReceiver.setAlarm(context);
		}

		Log.d("SalatTimeReceiver", "DATE CHANGED");
	}
}
