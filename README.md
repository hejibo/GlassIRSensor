GlassIRSensor
===========

The minimal interface to monitor infrared proximity sensor built into Google Glass

## Installation

**Warning: Rooting, unlocking, or flashing your Glass voids your warranty and can leave your device in an irrecoverable state. You will no longer receive OTA updates if you unlock or root your Glass. There is no guarantee that you will receive OTA updates even after flashing back to factory specifications. Proceed at your own risk.**

### 1.Set up android sdk on Linux

fastboot on MacOSX has some trouble and doesn't work.<br>
Please set "adb" and "fastboot" commands and do path settiings on Linux.<br>
[Get the Android SDK | Android Developers](https://developer.android.com/sdk/index.html)

### 2.Get the root permission of Google Glass

<pre>
$adb reboot bootloader
$fastboot oem unlock #You have to type this command twice.
$fastboot flash boot boot.img
$fastboot reboot
$adb root
</pre>

You can download boot.img from [here](https://developers.google.com/glass/downloads/system)

### 3.Change the permission of /sys/... raw sensor data file.

<pre>
$adb shell
$su
#chmod 664 /sys/bus/i2c/devices/4-0035/proxraw
-rw-rw-r-- system   system       4096 2013-11-04 10:05 proxraw
</pre>

### 4.Copy IRSensorListener.java and IRSensorManager.java to your project directory.

## Usage

```java

public class SampleActivity extends Activity implements IRSensorListener {

    private IRSensorManager mIrSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIrSensorManager = new IRSensorManager();
        .
        .
        .
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
```
