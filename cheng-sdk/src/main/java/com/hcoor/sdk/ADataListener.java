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
package com.hcoor.sdk;

/**
 * 默认的DataListener
 * Created by dumh on 14/12/13.
 */
public abstract  class ADataListener implements IDataListener {
    @Override
    public void onReadHardwareID(String id) {

    }

    @Override
    public void onSetHardwareId(int result) {

    }

    @Override
    public void onResetHardwareId(int result) {

    }

    @Override
    public void onReadHardwareSoftVar(int var) {

    }

    @Override
    public void onNotificationWeight(int stableFlag, float weight){

    }


    @Override
    public void onNotificationBodyInfo(BodyInfo bodyInfo) {

    }

    @Override
    public void onScaleUser(int result) {

    }
}
