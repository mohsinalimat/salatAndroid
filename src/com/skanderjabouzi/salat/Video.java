package com.skanderjabouzi.salat;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.telephony.PhoneStateListener;  
import android.telephony.TelephonyManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;

public class Video extends Activity {
  
	String fileName;
	static final String SEND_PHONE_NOTIFICATIONS = "com.skanderjabouzi.salat.SEND_PHONE_NOTIFICATIONS";
	PhoneReceiver receiver;
	IntentFilter filter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WakeLock.acquire(this);
		receiver = new PhoneReceiver();
		filter = new IntentFilter( SalatPhoneReceiver.PHONE_INTENT );
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		Log.d("VIDEO", "START");
		Intent intent = getIntent();
		String type = intent.getStringExtra("TYPE");
		if (type.equals("FAJR"))
		{
			fileName = "android.resource://com.skanderjabouzi.salat/raw/fajr";
		}
		else
		{
			fileName = "android.resource://com.skanderjabouzi.salat/raw/athan";
		}
		setContentView(R.layout.video);
		VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);
		myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mediaPlayer) {
				Video.this.finish();
				WakeLock.release("onComplete");
			}
		});
		
		TelephonyManager telephonyManager =  (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);  
         
        PhoneStateListener callStateListener = new PhoneStateListener() {  
        public void onCallStateChanged(int state, String incomingNumber)   
        {  
			if(state==TelephonyManager.CALL_STATE_RINGING){
				Video.this.finish();
			}  
                
			if(state==TelephonyManager.CALL_STATE_OFFHOOK){
				Video.this.finish();
			}  
                                  
			if(state==TelephonyManager.CALL_STATE_IDLE){
			}  
        }  
        };  
        telephonyManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);  
		myVideoView.setVideoURI(Uri.parse(fileName));
		//myVideoView.setMediaController(new MediaController(this));
		myVideoView.requestFocus();
		myVideoView.start();
   }
   
    @Override
    protected void onResume() {
        super.onResume();
        super.registerReceiver(receiver, filter, SEND_PHONE_NOTIFICATIONS, null);
        WakeLock.acquire(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //finish();
        //myVideoView.dismiss();
        //Video.this.finish();
		if (receiver != null) {
			unregisterReceiver(receiver);
			receiver = null;
		}
		Log.d("VIDEO", "onPause");
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        //finish();
        //myVideoView.dismiss();
        //Video.this.finish();
        WakeLock.release("onStop");
        //Video.this.finish();
		//if (receiver != null) {
			//unregisterReceiver(receiver);
			//receiver = null;
		//}
		Log.d("VIDEO", "onStop");
    }
    
	@Override
    protected void onDestroy() {
        super.onDestroy();
        //finish();
        //myVideoView.dismiss();
        //Video.this.finish();
        WakeLock.release("onDestroy");
        //Video.this.finish();
		//if (receiver != null) {
			//unregisterReceiver(receiver);
			//receiver = null;
		//}
		Log.d("VIDEO", "onDestroy");
    }
    
    class PhoneReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
			Video.this.finish();
        }
    }
}
