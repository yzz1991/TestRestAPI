package com.easemob.geri.testrestapi.requestAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

import static android.os.FileObserver.DELETE;

/**
 * Created by Geri on 2017/1/7.
 * 用户相关
 */

public interface UserAPI {

    /**
     * 获取token
     * @param body 参数
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("token")
    Call<ResponseBody> getToken(@Body RequestBody body);

    /**
     * 注册用户
     * @param authorization token
     * @param body body参数
     * @return 结果
     */
    @Headers("Content-Type:application/json")
    @POST("users")
    Call<ResponseBody> registerUser(@Header("Authorization") String authorization, @Body RequestBody body);

    /**
     * 获取IM用户（单个）
     * @param authorization token
     * @param username 注册的userId
     * @return 结果
     */
    @Headers("Content-Type:application/json")
    @GET("users/{username}")
    Call<ResponseBody> getSingleUser(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 获取IM用户（批量）
     * @param authorization token
     * @param limit 获取的数量
     * @return 结果
     */
    @Headers("Content-Type:application/json")
    @GET("users")
    Call<ResponseBody> getBatchUser(@Header("Authorization") String authorization, @Query("limit") int limit);

    /**
     * 删除IM用户（单个）
     * @param authorization token
     * @param username 删除的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @DELETE("users/{username}")
    Call<ResponseBody> deleteSingleUser(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 删除IM用户（批量）
     * @param authorization token
     * @param limit 删除的数量
     * @return
     */
    @Headers("Content-Type:application/json")
    @DELETE("users")
    Call<ResponseBody> deleteBatchUser(@Header("Authorization") String authorization, @Query("limit") int limit);

    /**
     * 重置密码
     * @param authorization token
     * @param username 重置密码的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @PUT("users/{username}/password")
    Call<ResponseBody> ResetPassword(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 修改用户昵称
     * @param authorization token
     * @param username 修改昵称的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @PUT("users/{username}")
    Call<ResponseBody> updateNickname(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 给IM用户添加好友
     * @param authorization token
     * @param owner_username 要添加好友的userId
     * @param friend_username 被添加的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("users/{owner_username}/contacts/users/{friend_username}")
    Call<ResponseBody> addFriend(@Header("Authorization") String authorization,
                                 @Part("owner_username") String owner_username, @Part("friend_username") String friend_username);

    /**
     * 获取好友
     * @param authorization token
     * @param owner_username 要获取好友的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("users/{owner_username}/contacts/users")
    Call<ResponseBody> getFriend(@Header("Authorization") String authorization, @Part("owner_username") String owner_username);

    /**
     * 往 IM 用户的黑名单中加人
     * @param authorization token
     * @param owner_username 要添加黑名单的userId
     * @param body 传入的body参数
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("users/{owner_username}/blocks/users")
    Call<ResponseBody> addBlocks(@Header("Authorization") String authorization, @Part("owner_username") String owner_username, @Body RequestBody body);

    /**
     * 获取 IM 用户的黑名单
     * @param authorization token
     * @param owner_username 要获取黑名单的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("users/{owner_username}/blocks/users")
    Call<ResponseBody> getBlocks(@Header("Authorization") String authorization, @Part("owner_username") String owner_username);

    /**
     * 从 IM 用户的黑名单中减人
     * @param authorization token
     * @param owner_username 要删除黑名单的userId
     * @param blocked_username 被删除的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @DELETE("users/{owner_username}/blocks/users/{blocked_username}")
    Call<ResponseBody> deleteBlocks(@Header("Authorization") String authorization,
                                    @Part("owner_username") String owner_username, @Part("blocked_username") String blocked_username);

    /**
     * 解除 IM 用户的好友关系
     * @param authorization token
     * @param owner_username 要删除好友的userId
     * @param friend_username 被删除的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @DELETE("users/{owner_username}/contacts/users/{friend_username}")
    Call<ResponseBody> DeleteFriend(@Header("Authorization") String authorization,
                                    @Part("owner_username") String owner_username, @Part("friend_username") String friend_username);

    /**
     * 查看用户在线状态
     * @param authorization token
     * @param username 要查询的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("users/{username}/status")
    Call<ResponseBody> userStatus(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 查询离线消息数
     * @param authorization token
     * @param username 要查询的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("users/{owner_username}/offline_msg_count")
    Call<ResponseBody> queryOfflineMsgCount(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 查询某条离线消息状态
     * @param authorization token
     * @param username 要查询的userId
     * @param msg_id 消息id
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("users/{username}/offline_msg_status/{msg_id}")
    Call<ResponseBody> queryOfflineMsgStatus(@Header("Authorization") String authorization,
                                             @Part("username") String username, @Part("msg_id") String msg_id);

    /**
     * 用户账号禁用
     * @param authorization token
     * @param username 要禁用的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("users/{username}/deactivate")
    Call<ResponseBody>  userDeactivate(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 用户账号解禁
     * @param authorization token
     * @param username 要解禁的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("users/{username}/activate")
    Call<ResponseBody> userActivate(@Header("Authorization") String authorization, @Part("username") String username);

    /**
     * 强制用户下线
     * @param authorization token
     * @param username 要强制下线的userId
     * @return
     */
    @Headers("Content-Type:application/json")
    @GET("users/{username}/disconnect")
    Call<ResponseBody> userDisconnect(@Header("Authorization") String authorization, @Part("username") String username);


}
