package com.hcoor.sdk.oldcmd;

/**
 * 来往命令处理模版
 * Created by dumh on 14/11/5.
 */
public interface ICmd<T> {
    /**
     * 蓝牙返给应用的数据
     *
     * @param values 数据
     * @param source 源数据
     * @return 修改后的数据
     */
    T s2a(byte[] values, T source);

    /**
     * 获取应用到蓝牙的数据
     *
     * @return 应用到蓝牙的数据
     */
    byte[] a2s();
}
