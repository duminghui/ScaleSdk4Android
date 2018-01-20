package com.hcoor.sdk.net.base.upload;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 文件上传
 *
 * @author zhaoyong
 */
public class HttpMultipartPost extends AsyncTask<HttpResponse, Integer, String> {
    private static final String TAG = HttpMultipartPost.class.getSimpleName();
    private String url;
    private long totalSize;
    private String[] files;
    private FormBodyPart[] parts;
    private CallBack mCallBack;
    private CallBackMsg mCallBackMsg;
    private String encode = HTTP.UTF_8;

    /**
     * @param url        url
     * @param files      文件路径
     * @param parts      其他参数
     * @param fileEncode 编码 默认UTF-8
     */
    public HttpMultipartPost(String url, String[] files, String fileEncode,
                             FormBodyPart... parts) {
        super();
        this.url = url;
        this.files = files;
        this.parts = parts;
        this.encode = TextUtils.isEmpty(fileEncode) ? HTTP.UTF_8 : fileEncode;
    }

    @Override
    protected void onPreExecute() {
        if (!URLUtil.isNetworkUrl(url)) {
            throw new IllegalArgumentException("unvalid url for post!");
        }
    }

    @Override
    protected String doInBackground(HttpResponse... arg0) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(url);

        try {
//            CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
//                    HttpMultipartMode.BROWSER_COMPATIBLE, null,
//                    Charset.forName(encode), num -> publishProgress((int) ((num / (float) totalSize) * 100)));
            CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName(encode), new CustomMultiPartEntity.ProgressListener() {
                @Override
                public void transferred(long num) {
                    publishProgress((int)((num/(float)totalSize)*100));
                }
            });
            // add all file
            for (String file : files) {
                multipartContent.addPart("files", new FileBody(new File(file)));
            }
            // add other parts
            for (FormBodyPart part : parts) {
                multipartContent.addPart(part);
            }
            totalSize = multipartContent.getContentLength();

            // Send it
            httpPost.setEntity(multipartContent);
            HttpResponse response = httpClient.execute(httpPost, httpContext);
            String serverResponse = EntityUtils.toString(response.getEntity());
            return serverResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"msg\":\"post failed!\"}";
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        if (mCallBack != null) {
            mCallBack.update(progress[0]);
        }
    }

    @Override
    protected void onPostExecute(String param) {
        Log.d(TAG, param + "");
        if (mCallBackMsg != null) {
            mCallBackMsg.msg(param);
        }
    }

    public void setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public void setCallBackMsg(CallBackMsg mCallBackMsg) {
        this.mCallBackMsg = mCallBackMsg;
    }

    public interface CallBack {
        public void update(Integer i);
    }

    public interface CallBackMsg {
        public void msg(String msg);
    }
}
