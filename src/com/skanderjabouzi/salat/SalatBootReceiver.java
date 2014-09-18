package com.skanderjabouzi.salat;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.util.Log;

public class SalatBootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();

		Log.i("ACTION2", action);
		if (action.equals("android.intent.action.BOOT_COMPLETED"))
		{
			SalatApplication salatApp = new SalatApplication(context);
			salatApp.initCalendar();
			salatApp.setSalatTimes(0);
			salatApp.setAlarm(context);
		}
	}
}
