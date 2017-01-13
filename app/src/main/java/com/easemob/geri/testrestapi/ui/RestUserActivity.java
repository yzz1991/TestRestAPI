package com.easemob.geri.testrestapi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.easemob.geri.testrestapi.R;
import com.easemob.geri.testrestapi.utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Geri on 2017/1/12.
 */
public class RestUserActivity extends AppCompatActivity {

    @BindView(R.id.user_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private final String TAG = this.getClass().getSimpleName();
    //请求地址
    private String baseUrl;
    private JSONArray actions;
    //token
    private String mAuthorization;
    //下标
    private int count = 0;
    private String mUserJson;
    private String mUsername;
    //批量获取时设置一次获取20个
    private int limit = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        /**
         * 设置头部
         */
        mToolbar.setTitle("User Relevant");
        setSupportActionBar(mToolbar);
        //toolbar字体颜色
        mToolbar.setTitleTextColor(0xffffffff);
        //设置back图标
        mToolbar.setNavigationIcon(R.mipmap.arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
//        if (TextUtils.isEmpty(mUserJson)) {
//            //读取sdcard中的json文件
//            readSDCardJson();
//        }


    }

    /**
     * 读取sdcard中的json文件
     */
    private void readSDCardJson() {
        mUserJson = FileUtils.getInstance().getRestUser();
        //将字符串转换成jsonObject对象
        try {
            JSONObject obj = new JSONObject(mUserJson);
            String org_name = obj.getString("org_name");
            String app_name = obj.getString("app_name");
            String rest_url = obj.getString("rest_url");
            baseUrl = rest_url + org_name + "/" + app_name + "/";
            actions = obj.getJSONArray("actions");
            Log.i(TAG, "baseUrl: " + baseUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //设定菜单各按钮的点击动作
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            //开始请求
//            getToken();
            return true;
        }
    };

//    //获取token
//    public void getToken() {
//        if (!TextUtils.isEmpty(mAuthorization)) {
//            //直接调用下一个请求接口（注册用户）
//            registerUser();
//            return;
//        }
//        try {
//            JSONObject body = actions.getJSONObject(count).getJSONObject("body");
//            RequestBody requestBody = RequestBody.create(MediaType.parse("json"), body.toString());
//            RestUserManager.getInstance(baseUrl).getToken(requestBody, new RequestCallback() {
//                @Override
//                public void onSuccess(Object object) {
//                    try {
//                        Response<ResponseBody> response = (Response) object;
//                        //请求成功返回的结果码
//                        int code = response.code();
//                        if (response.isSuccessful()) {
//                            //请求结果
//                            String s = response.body().string();
//                            Log.i(TAG, "onSuccess: " + s);
//                            //将请求结果转成Json，进行解析
//                            JSONObject tokenJson = new JSONObject(s);
//                            //拿到token
//                            String token = tokenJson.getString("access_token");
//                            mAuthorization = "Bearer " + token;
//                            Log.i(TAG, "getToken: " + mAuthorization);
//                            //直接调用下一个请求接口（注册用户）
//                            registerUser();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailed(String error) {
//                    Log.e(TAG, "onFailed: " + error);
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    //注册用户
//    public void registerUser() {
//        //下标+1
//        count++;
//        try {
//            JSONObject body = actions.getJSONObject(count).getJSONObject("body");
//            RequestBody requestBody = RequestBody.create(MediaType.parse("json"), body.toString());
//            RestUserManager.getInstance(baseUrl).registerUser(mAuthorization, requestBody, new RequestCallback() {
//                @Override
//                public void onSuccess(Object object) {
//                    try {
//                        Response<ResponseBody> response = (Response) object;
//                        //返回结果码
//                        int code = response.code();
//                        Log.i(TAG, "registerUser: " + code);
//                        if (response.isSuccessful()) {
//                            //请求结果
//                            String s = response.body().string();
//                            Log.i(TAG, "onSuccess: " + s);
//                            //将请求结果转成Json，进行解析
//                            JSONObject userJson = new JSONObject(s);
//                            JSONArray entities = userJson.getJSONArray("entities");
//                            mUsername = entities.getJSONObject(0).getString("username");
//                            //请求下一个接口（获取单个用户）
//                            getSingleUser();
//                        } else {
//                            String error = response.errorBody().string();
//
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailed(String error) {
//                    Log.e(TAG, "onFailed: " + error);
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //获取IM用户（单个）
//    public void getSingleUser() {
//        //下标+1
//        count++;
//        RestUserManager.getInstance(baseUrl).getSingleUser(mAuthorization, mUsername, new RequestCallback() {
//            @Override
//            public void onSuccess(Object object) {
//                try {
//                    Response<ResponseBody> response = (Response) object;
//                    //返回结果码
//                    int code = response.code();
//                    Log.i(TAG, "getSingleUser: " + code);
//                    if(response.isSuccessful()){
//
//                    }else{
//                        String error = response.errorBody().string();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailed(String error) {
//                Log.e(TAG, "onFailed: " + error);
//            }
//        });
//    }
//
//    //获取IM用户（批量）
//    public void getBatchUser(){
//        //下标+1
//        count++;
//        RestUserManager.getInstance(baseUrl).getBatchUser(mAuthorization, limit, new RequestCallback() {
//            @Override
//            public void onSuccess(Object object) {
//                Response<ResponseBody> response = (Response) object;
//                int code = response.code();
//                Log.i(TAG, "getBatchUser: "+code);
//            }
//
//            @Override
//            public void onFailed(String error) {
//
//            }
//        });
//    }
}
