package com.easemob.geri.testrestapi.utils;

import android.util.Log;

import com.easemob.geri.testrestapi.bean.RestResultBean;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by lzan13 on 2017/1/14.
 * 网络请求管理类
 */
public class HttpManager {

    private final static String TAG = "HttpManager";
    private static HttpManager instance;

    private String mBaseUrl = "";
    private String mAccessToken;
//    private RestResultBean restResultBean;

    // 超时时间
    private int connectTimeout = 5 * 1000;
    private int readTimeout = 5 * 1000;

    private HttpURLConnection conn;

    private HttpManager() {
//        restResultBean = new RestResultBean();
    }

    public static HttpManager getInstance() {
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

    /**
     * 设置 base url
     */
    public void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    /**
     * 设置当前 token
     */
    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    /**
     * 初始化网络请求
     *
     * @throws IOException 抛出异常
     */
    private void initConnection(String restAPI) throws IOException {
        URL url = new URL(mBaseUrl + restAPI);
        conn = (HttpURLConnection) url.openConnection();

        // 设置请求头
        conn.setRequestProperty("Content-Type", "application/json");
        if (!restAPI.equals("token")) {
            conn.setRequestProperty("Authorization", "Bearer " + mAccessToken);
            if (restAPI.equals("chatfiles")) {
                conn.setRequestProperty("restrict-access", "true");
            }
        }
    }

    /**
     * 对 GET 请求 进行封装
     *
     * @param requestAPI 请求 API
     */
    public RestResultBean onHttpGet(String requestAPI) {
        RestResultBean bean = new RestResultBean();
        try {
            initConnection(requestAPI);
            // 设置请求方式
            conn.setRequestMethod("GET");
            // 设置超时时间
            conn.setReadTimeout(readTimeout);
            conn.setConnectTimeout(connectTimeout);
            // 链接
            conn.connect();

            // 根据响应码判断请求结果
            int statusCode = conn.getResponseCode();
            String result = "";
            if (statusCode == 200) {
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            } else {
                InputStream inputStream = conn.getErrorStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            }
            Log.d(TAG, "请求结果: "+ statusCode + result);
            bean.setCode(statusCode);
            bean.setResult(result);
            return bean;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对 POST 请求 进行封装
     *
     * @param requestAPI 请求 API
     * @param requestBody 请求体
     */
    public RestResultBean onHttpPost(String requestAPI, Object requestBody) {
        RestResultBean bean = new RestResultBean();
        try {
            initConnection(requestAPI);

            // 设置请求方式
            conn.setRequestMethod("POST");

            // 设置超时时间
            conn.setReadTimeout(readTimeout);
            conn.setConnectTimeout(connectTimeout);

            // Post 请求需要设置允许输入输出
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // Post 不能使用缓存，HttpUrlConnection 默认是true，这里需要手动设置 false
            conn.setUseCaches(false);

            // 获取输出流，经需要提交的参数写入输出流提交到服务器
            if(requestBody != null ){
                conn.getOutputStream().write(requestBody.toString().getBytes());
            }

            // 链接
            conn.connect();

            // 根据响应码判断请求结果
            int statusCode = conn.getResponseCode();
            String result = "";
            if (statusCode == 200) {
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            } else {
                InputStream inputStream = conn.getErrorStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            }
            Log.d(TAG, "请求结果: "+ statusCode + result);
            bean.setCode(statusCode);
            bean.setResult(result);
            return bean;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对 PUT 请求进行封装，和 POST 操作一样
     *
     * @param requestAPI 请求 API
     * @param requestBody 请求体
     */
    public RestResultBean onHttpPut(String requestAPI, Object requestBody) {
        RestResultBean bean = new RestResultBean();
        try {
            initConnection(requestAPI);

            // 设置请求方式
            conn.setRequestMethod("PUT");

            // 设置超时时间
            conn.setReadTimeout(readTimeout);
            conn.setConnectTimeout(connectTimeout);

            // Post 请求需要设置允许输入输出
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // Post 不能使用缓存，HttpUrlConnection 默认是true，这里需要手动设置 false
            conn.setUseCaches(false);

            // 获取输出流，经需要提交的参数写入输出流提交到服务器
            conn.getOutputStream().write(requestBody.toString().getBytes());

            // 链接
            conn.connect();

            // 根据响应码判断请求结果
            int statusCode = conn.getResponseCode();
            String result = "";
            if (statusCode == 200) {
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            } else {
                InputStream inputStream = conn.getErrorStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            }
            Log.d(TAG, "请求结果: "+ statusCode + result);
            bean.setCode(statusCode);
            bean.setResult(result);
            return bean;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对 DELETE 请求进行封装
     */
    public RestResultBean onHttpDelete(String requestAPI) {
        RestResultBean bean = new RestResultBean();
        try {
            initConnection(requestAPI);
            // 设置请求方式
            conn.setRequestMethod("DELETE");
            // 设置超时时间
            conn.setReadTimeout(readTimeout);
            conn.setConnectTimeout(connectTimeout);
            // 链接
            conn.connect();

            // 根据响应码判断请求结果
            int statusCode = conn.getResponseCode();
            String result = "";
            if (statusCode == 200) {
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            } else {
                InputStream inputStream = conn.getErrorStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            }
            Log.d(TAG, "请求结果: "+ statusCode + result);
            bean.setCode(statusCode);
            bean.setResult(result);
            return bean;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以POST方式上传文件
     *
     * @param requestAPI 请求地址
     * @return
     */
    public RestResultBean postUpLoadFile(String requestAPI, InputStream fileInputStream) {
        // 这个表示表单数据的分隔符，这个要设置在请求头中，同时请求体也会用到
        String boundary = "--------------------------275686307391441482543473";
        String end = "\r\n";
        String twoHyphens = "--";
        RestResultBean bean = new RestResultBean();
        try {
            initConnection(requestAPI);
            // 允许Input、Output，不使用Cache
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            // 设置以POST方式进行传送
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "multipart/form-data; boundary=" + boundary);
            // 构造DataOutputStream流
            DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
            // 添加分隔符
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; "
                    + "name=\"file\";filename=\"easemob.png\"" + end);
            ds.writeBytes(end);
            // 设置每次写入1024bytes
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            // 从文件读取数据至缓冲区
            while ((length = fileInputStream.read(buffer)) != -1) {
                // 将资料写入DataOutputStream中
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            // 添加分隔符
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            // 关闭流
            fileInputStream.close();
            ds.flush();
            // 根据响应码判断请求结果
            int statusCode = conn.getResponseCode();
            String result = "";
            if(statusCode == 200){
                StringBuffer sb = new StringBuffer();
                // 获取响应流
                InputStream is = conn.getInputStream();
                int ch;
                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }
                // 关闭DataOutputStream
                ds.close();
                result = sb.toString();
            }else{
                InputStream inputStream = conn.getErrorStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] butter = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(butter)) != -1) {
                    outputStream.write(butter, 0, len);
                }
                result = outputStream.toString();
            }
            Log.d(TAG, "请求结果: "+ statusCode + result);
            bean.setCode(statusCode);
            bean.setResult(result);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
