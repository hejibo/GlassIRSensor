package com.mrk1869.irsensor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.TimelineManager;

public class SampleService extends Service {
	private static final String LIVE_CARD_ID = "IRSensor";

	private TimelineManager mTimelineManager;
	private LiveCard mLiveCard;

	@Override
	public void onCreate() {
		super.onCreate();
		mTimelineManager = TimelineManager.from(this);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mLiveCard = mTimelineManager.createLiveCard(LIVE_CARD_ID);

		Intent i = new Intent(this, SampleActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
