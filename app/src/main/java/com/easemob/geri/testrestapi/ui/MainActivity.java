package com.easemob.geri.testrestapi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.easemob.geri.testrestapi.R;
import com.easemob.geri.testrestapi.requestAPI.UserAPI;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private UserAPI requestUser;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://a1.easemob.com/1115161018115859/appdemo/").
                build();
        requestUser = retrofit.create(UserAPI.class);

        findViewById(R.id.btn_get_token).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToken();
            }
        });

    }

    //获取token
    public void getToken(){
        RequestBody body = RequestBody.create(MediaType.parse("json"),
                "{\"grant_type\":\"client_credentials\",\n" +
                "\"client_id\":\"YXA6wWPUwJTcEeanhEVDgQXM6Q\",\n" +
                "\"client_secret\":\"YXA6S-7IN-XTBu1T7I6Iw3rE3wyAvlc\"}");

        Call<ResponseBody> call = requestUser.getToken(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        Log.i(TAG, "onResponse: "+result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
