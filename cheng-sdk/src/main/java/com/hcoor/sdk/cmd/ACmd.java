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
package com.hcoor.sdk.cmd;

import android.util.Log;

import com.hcoor.sdk.HexUtils;

/**
 * Created by dumh on 14/12/8.
 */
public abstract class ACmd<T> {
    /**
     * 蓝牙返给应用的数据
     *
     * @param values 数据
     * @param source 源数据
     * @return 结果数据
     */
    public final T s2a(byte[] values, T source) {
        T t = _s2a(values, source);
        log("接收到数据(s2a)", values);
        Log.i(getTAG(), String.format("结果数据：T:%s[%s]", t.getClass().getSimpleName(), t.toString()));
        return t;
    }

    protected abstract T _s2a(byte[] values, T source);

    /**
     * 获取应用到蓝牙的数据
     *
     * @return 应用到蓝牙的数据
     */
    public final byte[] a2s() {
        byte[] bytes = _a2s();
        log("发送数据(a2s)", bytes);
        return bytes;
    }

    protected abstract byte[] _a2s();

    protected abstract String getTAG();

    private void log(String cmdWhere, byte[] values) {
        Log.i(getTAG(), String.format("%s:%s", cmdWhere, HexUtils.byte2HexLog(values)));
    }
}
