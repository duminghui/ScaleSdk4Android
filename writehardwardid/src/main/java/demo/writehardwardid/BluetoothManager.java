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
package demo.writehardwardid;

import android.content.Context;

import com.hcoor.sdk.BTStatusListener;
import com.hcoor.sdk.BluetoothConnectAndDataManager;
import com.hcoor.sdk.IDataListener;

/**
 * Created by dumh on 15/1/2.
 */
class BluetoothManager extends BluetoothConnectAndDataManager {
    protected BluetoothManager(Context context, BTStatusListener btStatusListener, IDataListener dataListener) {
        super(context, btStatusListener, dataListener);
    }
}
