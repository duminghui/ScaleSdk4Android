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
package hcoor.library_del_bonded_devices;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by dumh on 15/4/29.
 */
public class DeleteBondedDevices {
    private static final String TAG = "DBD";
    private static final DeleteBondedDevices instance = new DeleteBondedDevices();

    public static DeleteBondedDevices getInstance() {
        return instance;
    }

    private DeleteBondedDevices() {

    }

    public final void toDel() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> devices = adapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            boolean unBondedDevice = unBondedDevice(device);
            Logger.t(TAG, 2).i("unBond[%s:%s][%s]", device.getName(), device.getAddress(), unBondedDevice);

        }
    }

    private boolean unBondedDevice(BluetoothDevice device) {
        try {
            Method m = device.getClass()
                    .getMethod("removeBond", (Class[]) null);
            return (Boolean) m.invoke(device, (Object[]) null);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Logger.e(e, "unBondedDevice");
            return false;
        }
    }
}
