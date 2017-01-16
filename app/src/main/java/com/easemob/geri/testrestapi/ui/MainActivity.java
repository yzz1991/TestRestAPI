package com.easemob.geri.testrestapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.easemob.geri.testrestapi.R;
import com.easemob.geri.testrestapi.RestResultAdapter;
import com.easemob.geri.testrestapi.bean.RestResultBean;
import com.easemob.geri.testrestapi.utils.FileUtils;
import com.easemob.geri.testrestapi.utils.HttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easemob.geri.testrestapi.R.id.recyclerView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_token)
    TextView btnToken;
    @BindView(recyclerView)
    RecyclerView mRecyclerView;
    private final String TAG = this.getClass().getSimpleName();
    //读取的本地文件
    private String mJson;
    private JSONObject obj;
    private List<RestResultBean> list = new ArrayList<>();
    private RestResultAdapter mAdapter;
    private LinearLayoutManager manager;
    // 当前是否在最底部
    private boolean isBottom = true;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new RestResultAdapter(this, list);
        manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new MLRecyclerViewListener());
        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSDCardJson();
                startRestAPI();
            }
        });
        //设置RecyclerView点击事件
        mAdapter.setmOnItemClickListener(new RestResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                if(list.get(position).getCode() != 200){
                    Intent intent = new Intent(MainActivity.this, RestResponseActivity.class);
                    intent.putExtra("result",list.get(position).getResult());
                    startActivity(intent);
//                }
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
        list.clear();
        String restUrl = obj.optString("rest_url");
        String orgName = obj.optString("org_name");
        String appName = obj.optString("app_name");
        HttpManager.getInstance().setBaseUrl(restUrl + orgName + "/" + appName + "/");
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    testTokenAPI();
                    testUserAPI();
                    testMessageAPI();
                    testChatGroupAPI();
                    testChatRoomAPI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //获取token
    private void testTokenAPI() throws JSONException {
        JSONObject tokenObject = obj.optJSONObject("Token");
        String name = tokenObject.optString("name");
        String requestAPI = tokenObject.optString("api");
        String requestMethod = tokenObject.optString("method");
        JSONObject requestBody = tokenObject.optJSONObject("body");

        RestResultBean bean = HttpManager.getInstance().onHttpPost(requestAPI, requestBody);
        bean.setName(name);
        list.add(bean);
        handler.sendEmptyMessage(1);
        JSONObject resultObject = new JSONObject(bean.getResult());
        if (!resultObject.has("error") && resultObject.has("access_token")) {
            HttpManager.getInstance().setAccessToken(resultObject.optString("access_token"));
        }
    }
    //user相关
    private void testUserAPI() throws JSONException {
        JSONArray userArray = obj.optJSONArray("Users");
        RestResultBean bean = null;
        for (int i = 0; i < userArray.length(); i++) {
            JSONObject userObject = userArray.optJSONObject(i);
            String requestAPI = userObject.optString("api");
            String requestMethod = userObject.optString("method");
            Object requestBody = userObject.opt("body");
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
            list.add(bean);
            handler.sendEmptyMessage(1);
            JSONObject resultObject = new JSONObject(bean.getResult());
        }
    }

    //消息相关
    private void testMessageAPI() throws JSONException {
        JSONArray chatArray = obj.optJSONArray("Chat");
        RestResultBean bean = null;
        for (int i = 0; i < chatArray.length(); i++) {
            JSONObject userObject = chatArray.optJSONObject(i);
            String requestAPI = userObject.optString("api");
            String requestMethod = userObject.optString("method");
            Object requestBody = userObject.opt("body");
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
            list.add(bean);
            handler.sendEmptyMessage(1);
            JSONObject resultObject = new JSONObject(bean.getResult());
        }
    }

    //群组相关
    private void testChatGroupAPI() throws JSONException {
        JSONArray chatGroupArray = obj.optJSONArray("ChatGroup");
        RestResultBean bean = null;
        for (int i = 0; i < chatGroupArray.length(); i++) {
            JSONObject userObject = chatGroupArray.optJSONObject(i);
            String requestAPI = userObject.optString("api");
            String requestMethod = userObject.optString("method");
            Object requestBody = userObject.opt("body");
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
            list.add(bean);
            handler.sendEmptyMessage(1);
            JSONObject resultObject = new JSONObject(bean.getResult());
        }
    }

    //聊天室相关
    private void testChatRoomAPI() throws JSONException {
        JSONArray chatRoomArray = obj.optJSONArray("ChatRoom");
        RestResultBean bean = null;
        for (int i = 0; i < chatRoomArray.length(); i++) {
            JSONObject userObject = chatRoomArray.optJSONObject(i);
            String requestAPI = userObject.optString("api");
            String requestMethod = userObject.optString("method");
            Object requestBody = userObject.opt("body");
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
            list.add(bean);
            handler.sendEmptyMessage(1);
            JSONObject resultObject = new JSONObject(bean.getResult());
        }
    }

    /**
     * --------------------------- RecyclerView 滚动监听 --------------------------------
     * 自定义实现RecyclerView的滚动监听，监听
     */
    class MLRecyclerViewListener extends RecyclerView.OnScrollListener {
        /**
         * 监听 RecyclerView 滚动状态的变化
         *
         * @param recyclerView 当前监听的 RecyclerView 控件
         * @param newState     RecyclerView 变化的状态
         */
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            // 当 RecyclerView 停止滚动后判断当前是否在底部
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int lastItem = manager.findLastVisibleItemPosition();
                if (lastItem == (mAdapter.getItemCount() - 1)) {
                    isBottom = true;
                } else {
                    isBottom = false;
                }
            }
        }

        /**
         * RecyclerView 正在滚动中
         *
         * @param recyclerView 当前监听的 RecyclerView 控件
         * @param dx           水平变化值，表示水平滚动，正表示向右，负表示向左
         * @param dy           垂直变化值，表示上下滚动，正表示向下，负表示向上
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // 如果正在向上滚动，则也设置 isBottom 状态为false
            if (dy < 0) {
                isBottom = false;
            }
        }
    }

}
