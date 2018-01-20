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

/**
 * Created by dumh on 14/12/13.
 */
public interface BluetoothConnector {
    /**
     * 连接到设备
     * @param address 设备MAC
     * @param secure 是否安全连接
     */
    void connect(String address, boolean secure);

    /**
     * 断开连接
     */
    void disconnect();

    boolean isConnected();

    /**
     * 获取连接设备的MAC地址
     * @return MAC地址
     */
    String getConnectedDeviceAddress();

    boolean write(byte[] values);
}
