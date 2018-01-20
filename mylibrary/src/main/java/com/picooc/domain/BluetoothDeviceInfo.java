package com.picooc.domain;

public class BluetoothDeviceInfo
{
  private String deviceName;
  private String macAddress;

  public BluetoothDeviceInfo(String paramString1, String paramString2)
  {
    this.deviceName = paramString1;
    this.macAddress = paramString2;
  }

  public String getDeviceName()
  {
    return this.deviceName;
  }

  public String getMacAddress()
  {
    return this.macAddress;
  }

  public void setDeviceName(String paramString)
  {
    this.deviceName = paramString;
  }

  public void setMacAddress(String paramString)
  {
    this.macAddress = paramString;
  }

  public String toString()
  {
    return "BluetoothDeviceInfo [deviceName=" + this.deviceName + ", macAddress=" + this.macAddress + "]";
  }
}

/* Location:           /Users/dumh/Desktop/apk/picooc_1.3/classes_dex2jar.jar
 * Qualified Name:     BluetoothDeviceInfo
 * JD-Core Version:    0.6.2
 */
