package com.hcoor.sdk;

/**
 * 蓝牙监听
 * Created by dumh on 14/11/8.
 */
public interface BTStatusListener {
    /**
     * 扫描开始
     */
    void onScanStart();

    /**
     * 扫描结束
     *
     * @param isFindCanUseDevice 是否发现可用设备：true:发现，false:未发现
     */
    void onScanEnd(boolean isFindCanUseDevice);

    /**
     * 连接开始
     */
    void onConnectStart();

    /**
     * 连接中
     */
    void onConnecting();

    /**
     * 连接成功
     */
    void onConnectSuccess();

    /**
     * 连接失败
     */
    void onConnectFailed();

    /**
     * 断开连接
     */
    void onDisconnected();

    /**
     * 可以操作数据，写入，读取
     *
     * @param isCanRead  是否可以读取数据
     * @param isCanWrite 是否可以写数据
     */
    void onReadWriteStatus(boolean isCanRead, boolean isCanWrite);
}
