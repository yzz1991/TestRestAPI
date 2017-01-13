package com.easemob.geri.testrestapi.requestAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Geri on 2017/1/10.
 */

public class RestUserManager {

    private static RestUserManager getInstance;
    private UserAPI mUserAPI;
    private Retrofit mRetrofit;
    private Call<ResponseBody> call;


    public RestUserManager(String baseUrl) {
        // 获取 Retrofit 对象
        mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        // 使用 Retrofit 创建自定义网络请求接口的实例
        mUserAPI = mRetrofit.create(UserAPI.class);
    }

    /**
     * 获取当前类实例
     */
    public static RestUserManager getInstance(String baseUrl) {
        if (getInstance == null) {
            getInstance = new RestUserManager(baseUrl);
        }
        return getInstance;
    }

    /**
     * 获取token
     * @param body 参数
     * @param callback 结果回调
     * @return
     */
    public void getToken(RequestBody body, final RequestCallback callback){
        call = mUserAPI.getToken(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 注册用户
     * @param authorization token
     * @param body 参数
     * @param callback 结果回调
     */
    public void registerUser(String authorization, RequestBody body, final RequestCallback callback){
        call = mUserAPI.registerUser(authorization,body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 获取IM用户（单个）
     * @param authorization tokenvfb
     * @param username 获取的userId
     * @param callback 结果回调
     */
    public void getSingleUser(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.getSingleUser(authorization,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 获取IM用户（批量）
     * @param authorization token
     * @param limit 获取数量
     * @param callback 结果返回
     */
    public void getBatchUser(String authorization, int limit, final RequestCallback callback){
        call = mUserAPI.getBatchUser(authorization,limit);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 删除IM用户（单个）
     * @param authorization token
     * @param username 要删除的userId
     * @param callback 结果返回
     */
    public void deleteSingleUser(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.deleteSingleUser(authorization, username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 删除IM用户（批量）
     * @param authorization token
     * @param limit 删除数量
     * @param callback 结果返回
     */
    public void deleteBatchUser(String authorization, int limit, final RequestCallback callback){
        call = mUserAPI.deleteBatchUser(authorization, limit);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 重置密码
     * @param authorization token
     * @param username 需要重置密码的userId
     * @param callback 结果返回
     */
    public void ResetPassword(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.ResetPassword(authorization, username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 修改用户昵称
     * @param authorization token
     * @param username 修改昵称的userId
     * @param callback 结果返回
     */
    public void updateNickname(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.updateNickname(authorization, username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 添加好友
     * @param authorization token
     * @param owner_username 要添加好友的userId
     * @param friend_username 被添加的userId
     * @param callback 结果返回
     */
    public void addFriend(String authorization, String owner_username, String friend_username, final RequestCallback callback){
        call = mUserAPI.addFriend(authorization, owner_username, friend_username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 获取好友
     * @param authorization token
     * @param owner_username 要获取好友的userId
     * @param callback 结果返回
     */
    public void getFriend(String authorization, String owner_username, final RequestCallback callback){
        call = mUserAPI.getFriend(authorization, owner_username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 将好友加入黑名单
     * @param authorization token
     * @param owner_username 要添加黑名单的userId
     * @param body 参数
     * @param callback 结果返回
     */
    public void addBlocks(String authorization, String owner_username, RequestBody body, final RequestCallback callback){
        call = mUserAPI.addBlocks(authorization, owner_username, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 获取黑名单
     * @param authorization token
     * @param owner_username 获取黑名单的userId
     * @param callback 结果返回
     */
    public void getBlocks(String authorization, String owner_username, final RequestCallback callback){
        call = mUserAPI.getBlocks(authorization, owner_username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 将好友移出黑名单
     * @param authorization token
     * @param owner_username 要删除黑名单的userId
     * @param blocked_username 被删除的userId
     * @param callback 结果返回
     */
    public void deleteBlocks(String authorization, String owner_username, String blocked_username, final RequestCallback callback){
        call = mUserAPI.deleteBlocks(authorization, owner_username, blocked_username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 删除好友
     * @param authorization token
     * @param owner_username 要删除好友的userId
     * @param friend_username 被删除的userId
     * @param callback 结果返回
     */
    public void DeleteFriend(String authorization, String owner_username, String friend_username, final RequestCallback callback){
        call = mUserAPI.DeleteFriend(authorization, owner_username, friend_username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 查看用户在线状态
     * @param authorization token
     * @param username 要查询的userId
     * @param callback 结果返回
     */
    public void userStatus(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.userStatus(authorization,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 查询离线消息数
     * @param authorization token
     * @param username 要查询的userId
     * @param callback 结果返回
     */
    public void queryOfflineMsgCount(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.queryOfflineMsgCount(authorization, username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 查询某条离线消息状态
     * @param authorization token
     * @param username 要查询的userId
     * @param msg_id 消息id
     * @param callback 结果返回
     */
    public void queryOfflineMsgStatus(String authorization, String username, String msg_id, final RequestCallback callback){
        call = mUserAPI.queryOfflineMsgStatus(authorization, username,msg_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 用户账号禁用
     * @param authorization token
     * @param username userId
     * @param callback 结果返回
     */
    public void userDeactivate(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.userDeactivate(authorization,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 用户账号解禁
     * @param authorization token
     * @param username userId
     * @param callback 结果返回
     */
    public void userActivate(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.userActivate(authorization,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }

    /**
     * 强制用户下线
     * @param authorization token
     * @param username userId
     * @param callback 结果返回
     */
    public void userDisconnect(String authorization, String username, final RequestCallback callback){
        call = mUserAPI.userDisconnect(authorization,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }


}
