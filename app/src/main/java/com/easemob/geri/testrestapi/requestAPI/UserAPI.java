package com.easemob.geri.testrestapi.requestAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Geri on 2017/1/7.
 * 用户相关
 */

public interface UserAPI {

    /**
     * 获取token
     */
    @Headers("Content-Type:application/json")
    @POST("token")
    Call<ResponseBody> getToken(@Body RequestBody body);

    /**
     * 注册用户
     */
    @Headers("Content-Type:application/json")
    @POST("users")
    Call<ResponseBody> registerUser(@Header("Authorization") String authorization, @Body ResponseBody body);

    /**
     * 获取到单个好友
     */
    @Headers("Content-Type:application/json")
    @GET("users/{username}")
    Call<ResponseBody> getSingleUser(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 获取
     */


}
