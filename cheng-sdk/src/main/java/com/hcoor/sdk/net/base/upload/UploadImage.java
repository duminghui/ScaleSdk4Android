package com.hcoor.sdk.net.base.upload;

import android.util.Log;

import org.apache.http.protocol.HTTP;

import java.io.File;


/**
 * 上传图片至服务器端
 *
 * @author fangyuehan 20140902
 */
public class UploadImage {

    private static final String TAG = "UploadImage";

    HttpMultipartPost uploadTask;
    OnImageUploadCallBack callBack;

    public UploadImage(String url, String filePath, OnImageUploadCallBack l) {
        this.callBack = l;
        File f = new File(filePath);
        if (!f.exists()) { // 文件不存在
            Log.i(TAG, String.format("filepath:%s", f.getAbsolutePath()));
            l.uploadFailed(OnImageUploadCallBack.CODE_FILE_NOT_EXISTS);
            return;
        }

        uploadTask = new HttpMultipartPost(url, new String[]{filePath}, HTTP.UTF_8);

//        uploadTask.setCallBack((i) -> {
//            Log.d(TAG, "进度   i = " + i);
//            if (callBack != null) {
//                callBack.uploadProgress(i);
//            }
//        });
//        uploadTask.setCallBackMsg(msg -> {
//            if (callBack != null) {
//                if ("{\"msg\":\"post failed!\"}".equals(msg)) {
//                    callBack.uploadFailed(OnImageUploadCallBack.CODE_POST_ERROR);
//                } else {
//                    callBack.uploadComplete(msg);
//                }
//            }
//        });
        uploadTask.setCallBack(new HttpMultipartPost.CallBack() {
            @Override
            public void update(Integer i) {
                Log.d(TAG, "进度   i = " + i);
                if (callBack != null) {
                    callBack.uploadProgress(i);
                }
            }
        });
        uploadTask.setCallBackMsg(new HttpMultipartPost.CallBackMsg() {
            @Override
            public void msg(String msg) {
                if (callBack != null) {
                    if ("{\"msg\":\"post failed!\"}".equals(msg)) {
                        callBack.uploadFailed(OnImageUploadCallBack.CODE_POST_ERROR);
                    } else {
                        callBack.uploadComplete(msg);
                    }
                }
            }
        });
    }

    public void execute() {
        if (uploadTask != null) {
            uploadTask.execute();
        } else {
            Log.i(TAG, "uploadTask is null");
        }
    }

    public interface OnImageUploadCallBack {
        // 上传监听回调
        final int CODE_FILE_NOT_EXISTS = 1;

        final int CODE_POST_ERROR = 2;

        // 上传进度
        void uploadProgress(int rate);

        // 上传完成
        void uploadComplete(String response);

        // 上传失败
        void uploadFailed(int code);
    }
}
