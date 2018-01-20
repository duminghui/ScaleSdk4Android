package com.hcoor.sdk;

/**
 * Created by dumh on 14/12/9.
 */
public interface IDataListener {

    void onReadHardwareID(String id);

    void onSetHardwareId(int result);

    void onResetHardwareId(int result);

    void onReadHardwareSoftVar(int var);

    void onScaleUser(int result);
    /**
     * 通知称重时的身体重量
     *
     * @param stableFlag 重量锁定标记，0：锁定，1：未锁定
     * @param weight     重量
     */
    void onNotificationWeight(int stableFlag, float weight);

    /**
     * 称重稳定后返回的身体数据
     *
     * @param bodyInfo 身体数据
     */
    void onNotificationBodyInfo(BodyInfo bodyInfo);
}
