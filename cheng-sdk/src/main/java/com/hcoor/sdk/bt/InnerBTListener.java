package com.hcoor.sdk.bt;

/**
 * 内部蓝牙监听，不对外公布
 * Created by dumh on 14/11/5.
 */
public interface InnerBTListener {

    /**
     * 正在连接蓝牙
     *
     * @param name 设备名称
     * @param mac  MAC地址
     */
    void connecting(String name, String mac);

    /**
     * 连接开始
     *
     * @param name 设备名称
     * @param mac  MAC地址
     */
    void connectStart(String name, String mac);

    /**
     * 连接蓝牙成功
     *
     * @param name 设备名称
     * @param mac  MAC地址
     */
    void connectSuccess(String name, String mac);

    /**
     * 连接蓝牙失败
     *
     * @param name 设备名称
     * @param mac  MAC地址
     */
    void connectFailed(String name, String mac);

    /**
     * 同蓝牙断开连接
     *
     * @param name 设备名称
     * @param mac  MAC地址
     */
    void disconnected(String name, String mac);

    /**
     * @param isCanRead  是否可读
     * @param isCanWrite  是否可写
     * 可以操作数据，读取或输入
     */
    void readWriteStatus(boolean isCanRead, boolean isCanWrite);

    /**
     * 从蓝牙接收到数据
     *
     * @param uuid   uuid
     * @param values 数据
     */
    void notificationData(String uuid, byte[] values);

    /**
     * 向蓝牙传输数据成功
     *
     * @param uuid   uuid
     * @param values 写入的数据
     */
    void writeDataSuccess(String uuid, byte[] values);

    /**
     * 向蓝或传输数据失败
     *
     * @param uuid   uuid
     * @param values values
     */
    void writeDateFailed(String uuid,  byte[] values);
}
