package com.easemob.geri.testrestapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easemob.geri.testrestapi.R;
import com.easemob.geri.testrestapi.bean.RestResultBean;
import com.easemob.geri.testrestapi.utils.FileUtils;
import com.easemob.geri.testrestapi.utils.HttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_token)
    TextView btnToken;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private final String TAG = this.getClass().getSimpleName();
    //读取的本地文件
    private String mJson;
    private JSONObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSDCardJson();
                startRestAPI();
            }
        });

    }

    /**
     * 读取sdcard中的json文件
     */
    private void readSDCardJson() {
        mJson = FileUtils.getInstance().getRestUser();
        //将字符串转换成jsonObject对象
        try {
            obj = new JSONObject(mJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void startRestAPI() {
        String restUrl = obj.optString("rest_url");
        String orgName = obj.optString("org_name");
        String appName = obj.optString("app_name");
        HttpManager.getInstance().setBaseUrl(restUrl + orgName + "/" + appName + "/");
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    testTokenAPI();
                    testUserAPI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void testTokenAPI() throws JSONException {
        JSONObject tokenObject = obj.optJSONObject("Token");
        String name = tokenObject.optString("name");
        String requestAPI = tokenObject.optString("api");
        String requestMethod = tokenObject.optString("method");
        JSONObject requestBody = tokenObject.optJSONObject("body");

        RestResultBean bean = HttpManager.getInstance().onHttpPost(requestAPI, requestBody);
        bean.setName(name);
        JSONObject resultObject = new JSONObject(bean.getResult());
        if (!resultObject.has("error") && resultObject.has("access_token")) {
            HttpManager.getInstance().setAccessToken(resultObject.optString("access_token"));
        }
    }

    private void testUserAPI() throws JSONException {
        JSONArray userArray = obj.optJSONArray("Users");
        for (int i = 0; i < userArray.length(); i++) {
            JSONObject userObject = userArray.optJSONObject(i);
            String requestAPI = userObject.optString("api");
            String requestMethod = userObject.optString("method");
            Object requestBody = userObject.opt("body");
            RestResultBean bean = null;
            if (requestMethod.equals("GET")) {
                bean = HttpManager.getInstance().onHttpGet(requestAPI);
            } else if (requestMethod.equals("POST")) {
                bean = HttpManager.getInstance().onHttpPost(requestAPI, requestBody);
            } else if (requestMethod.equals("PUT")) {
                bean = HttpManager.getInstance().onHttpPut(requestAPI, requestBody);
            } else if (requestMethod.equals("DELETE")) {
                bean = HttpManager.getInstance().onHttpDelete(requestAPI);
            }
            bean.setName(userObject.optString("name"));
            JSONObject resultObject = new JSONObject(bean.getResult());
        }
    }
}
