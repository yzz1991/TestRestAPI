package com.easemob.geri.testrestapi.utils;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Geri on 2017/1/13.
 */

public class RequestUtils {

    private static RequestUtils getInstance;
    private final HttpClient mClient;
    private final static String  CONTENT_TYPE = "Content-Type:application/json";

    public RequestUtils() {
        //获取HttpClient对象
        mClient = new DefaultHttpClient();
    }

    /**
     * 获取当前类实例
     */
    public static RequestUtils getInstance() {
        if (getInstance == null) {
            getInstance = new RequestUtils();
        }
        return getInstance;
    }

    /**
     * get请求
     * @param url 请求地址
     */
    public void getHttpClientGet(String url, String authorization) {
        String content = "";
        try {
            //构建一个请求对象
            HttpGet get = new HttpGet(url);
            //放入请求头的内容，必须是以键值对的形式，这里以Accept-language为例
            get.addHeader("Content-Type","application/json");
            get.addHeader("Authorization", authorization);
            //往服务器发送请求
            HttpResponse response = mClient.execute(get);
            //获取状态码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                //获取响应回来的实体内容
                HttpEntity entity = response.getEntity();
                content = EntityUtils.toString(entity, "utf-8");
            }
            Log.i("data", content);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * post请求
     * @param url 请求地址
     */
    protected void getHttpClientPost(String url,String authorization, String parameters) {
        String data = "";
        try {
            //获取一个请求对象
            HttpPost post = new HttpPost(url);
//            //把要传给服务器端的参数以name-value方式包装
//            List<NameValuePair> parameters = new ArrayList<>();
//            BasicNameValuePair param1 = new BasicNameValuePair("q", "习近平");
//            BasicNameValuePair param2 = new BasicNameValuePair("key", "19f0b2ebeec053b333acaca294186851");
//            parameters.add(param1);
//            parameters.add(param2);
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
            // 建立一个NameValuePair数组，用于存储欲传送的参数
            post.addHeader("Content-Type","application/json");
            post.setHeader("Authorization", authorization);
            //给post传参
            post.setEntity(new StringEntity(parameters, "UTF-8"));
            //往服务器改善请求
            HttpResponse response = mClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200){
                HttpEntity httpEntity = response.getEntity();
                data = EntityUtils.toString(httpEntity, "utf-8");
            }
            Log.i("data", data);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
