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
package com.hcoor.sdk.net.base;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hcoor.sdk.data.DataSdk;
import com.hcoor.sdk.data.NetRequestListener;

import java.util.HashMap;

/**
 * 网络请求基础类
 * Created by dumh on 14/12/6.
 */
public abstract class BaseNetRequestWrapper {
    private static final String TAG = "BaseNetRequestWrapper";
    private static final String SECRET_KEY = "FIUjGub34uMmOecWCM2mOdWt4mNZuZ4M";
    private static final String APP_KEY = "MPnvrGcQYGKNVpxWOghUgq6Scall";
    private static final String DEBUG_URL = "http://www.hcoor.com/sdk-interface/";
    private static final String RELEASE_URL = "http://appsvr.hcoor.com/sdk-interface/";
    private static final String REQUEST_URL_FORMAT = "%s%s?%sbdsig=%s";
    private static final String PARAMS_KEY_COMMAND = "command";
    private static final String PARAMS_APP_KEY = "appKey";
    private HashMap<String, String> params = new HashMap<>();

    private RequestQueue requestQueue;
    private NetRequestListener listener;

    protected BaseNetRequestWrapper(RequestQueue requestQueue, NetRequestListener listener) {
        this.requestQueue = requestQueue;
        this.listener = listener;
    }

    protected abstract String getModel();

    protected abstract String getCommand();

    private StringRequest getRequest() {
        String url = getRequestUrl();
        Log.i(TAG, String.format("[m:%s][c:%s][url:%s]", getModel(), getCommand(), url));
//        StringRequest request = new StringRequest(Request.Method.POST, url, listener::onNetSuccess, error -> listener.onError(0, error.toString()));
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onNetSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(0,error.toString());
            }
        });
        RetryPolicy retryPolicy = getRetryPolicy();
        if (retryPolicy != null) {
            request.setRetryPolicy(getRetryPolicy());
        }
        return request;
    }


    protected RetryPolicy getRetryPolicy() {
        return null;
    }

    public void addRequest2Queue() {
//        getRequest();
        requestQueue.add(getRequest());
    }

    protected <T> void addParams(String key, T values) {
        params.put(key, String.valueOf(values));
    }

    private String getRequestUrl() {
        params.put(PARAMS_KEY_COMMAND, getCommand());
        params.put(PARAMS_APP_KEY, APP_KEY);
        String url = DataSdk.isDebugLine() ? DEBUG_URL : RELEASE_URL;
        return String.format(REQUEST_URL_FORMAT, url, getModel(), HttpParamsUtils.getBaseParams(params), HttpParamsUtils.getSignature(params, SECRET_KEY));

    }
}
