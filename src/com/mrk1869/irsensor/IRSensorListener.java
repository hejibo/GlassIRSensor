package com.mrk1869.irsensor;

import java.util.EventListener;

public interface IRSensorListener extends EventListener {

	public void recievedIRSensorValue(float irData);

}
