//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                   _ooOoo_
//                  o8888888o
//                  88" . "88
//                  (| -_- |)
//                  O\  =  /O
//               ____/`---'\____
//             .'  \\|     |//  `.
//            /  \\|||  卍  |||//  \
//           /  _||||| -:- |||||-  \
//           |   | \\\  -  /// |   |
//           | \_|  ''\---/''  |   |
//          \  .-\__  `-`  ___/-. /
//         ___`. .'  /--.--\  `. . __
//      ."" '<  `.___\_<|>_/___.'  >'"".
//     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//      \  \ `-.   \_ __\ /__ _/   .-` /  /
//======`-.____`-.___\_____/___.-`____.-'======
//                   `=---='
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//              佛祖保佑    永无BUG
package com.hcoor.sdk.bt;

import android.bluetooth.BluetoothDevice;

/**
 * 蓝牙扫描内部类
 * Created by dumh on 14/12/13.
 */
public interface InnerScanListener {
    //开始扫描
    void scanStart();

    /**
     * 扫描到的设备
     *
     * @param device 设备信息
     */
    void scanDevice(BluetoothDevice device);

    /**
     * 扫描结束，是否发现有可用设备
     *
     * @param device 扫描到的设备，如果为空，则没有描到设备
     */
    void scanFinished(BluetoothDevice device);
}
