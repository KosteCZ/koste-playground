package cz.koscak.jan.mytasks2;

import android.os.Build;
import android.util.Log;

/**
 * Created by jkoscak on 14. 9. 2016.
 */
public class UserAndDeviceInfo {

    /*
    int propSdkVersion      = Build.VERSION.SDK_INT; // API Level
    String propModel        = Build.MODEL;           // Model
    String propManufacturer = Build.MANUFACTURER;    // Manufacturer
    Log.i("DEVICE-INFO", "API Level:    " + propSdkVersion);
    Log.i("DEVICE-INFO", "Model:        " + propModel);
    Log.i("DEVICE-INFO", "Manufacturer: " + propManufacturer);

    String device = UserAndDeviceInfo.getDeviceName();
    Log.i("DEVICE-INFO", "Device:       " + device);

    String device = UserAndDeviceInfo.getDeviceNameAndAndroidVersion();
    Log.i("DEVICE-INFO", "DeviceAndAndroidVersion: " + device);
    */

    public static String getDeviceNameAndAndroidVersion() {
        int propSdkVersion = Build.VERSION.SDK_INT; // API Level
        String device = UserAndDeviceInfo.getDeviceName();
        if (device.startsWith("Unknown Android SDK built")) {
            device = "Unknown Android device";
        }
        if (device.length() > 25) {
            device = device.substring(0, 25);
        }
        return device + " (" + propSdkVersion + ")";
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
