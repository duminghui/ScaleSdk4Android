package com.picooc.bluetoothscan;

import java.util.HashMap;

public class SampleGattAttributes
{
  public static String CLIENT_CHARACTERISTIC_CONFIG;
  public static String HEART_RATE_MEASUREMENT;
  public static String LATIN_NOTIFY_UUID;
  public static String LATIN_SERVICE_UUID;
  public static String LATIN_WRITE_UUID;
  private static HashMap<String, String> attributes = new HashMap();

  static
  {
    HEART_RATE_MEASUREMENT = "0000C004-0000-1000-8000-00805f9b34fb";
    CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    LATIN_SERVICE_UUID = "0000fff0-0000-1000-8000-00805f9b34fb";
    LATIN_NOTIFY_UUID = "0000fff1-0000-1000-8000-00805f9b34fb";
    LATIN_WRITE_UUID = "0000fff2-0000-1000-8000-00805f9b34fb";
    attributes.put("0000fff00000-1000-8000-00805f9b34fb", "Heart Rate Service");
    attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information Service");
    attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
    attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");
  }

  public static String lookup(String paramString1, String paramString2)
  {
    String str = (String)attributes.get(paramString1);
    if (str == null)
      return paramString2;
    return str;
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     SampleGattAttributes
 * JD-Core Version:    0.6.2
 */
