package com.mrk1869.irsensor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.glass.app.Card;

public class SampleActivity extends Activity implements IRSensorListener {

	private IRSensorManager mIrSensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Card card = new Card(this);
		card.setText("IRSensor");
		View cardView = card.toView();
		setContentView(cardView);

		mIrSensorManager = new IRSensorManager();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mIrSensorManager.setListener(this);
	}

	@Override
	protected void onPause() {
		mIrSensorManager.removeListener();
		super.onPause();
	}

	@Override
	public void recievedIRSensorValue(float irData) {
		Log.v("IRSensorManager", "ir-data:" + irData);
	}
}
