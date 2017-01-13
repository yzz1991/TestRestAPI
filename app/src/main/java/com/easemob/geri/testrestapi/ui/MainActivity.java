package com.easemob.geri.testrestapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.easemob.geri.testrestapi.R;
import com.easemob.geri.testrestapi.utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_user)
    Button btnUser;
    @BindView(R.id.btn_message)
    Button btnMessage;
    @BindView(R.id.btn_group)
    Button btnGroup;
    @BindView(R.id.btn_chatroom)
    Button btnChatRoom;
    private final String TAG = this.getClass().getSimpleName();
    //读取的本地文件
    private String mUserJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnUser.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        btnChatRoom.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_user:
                startActivity(new Intent(MainActivity.this,RestUserActivity.class));
                break;

            case R.id.btn_message:

                break;

            case R.id.btn_group:

                break;

            case R.id.btn_chatroom:

                break;
        }
    }

    /**
     * 读取sdcard中的json文件
     */
    private void readSDCardJson() {
        mUserJson = FileUtils.getInstance().getRestUser();
        //将字符串转换成jsonObject对象
        try {
            JSONObject obj = new JSONObject(mUserJson);
            JSONArray token = obj.getJSONArray("Token");
            JSONArray users = obj.getJSONArray("Users");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
