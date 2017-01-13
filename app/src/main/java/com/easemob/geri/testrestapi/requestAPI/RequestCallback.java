package com.easemob.geri.testrestapi.requestAPI;

/**
 * Created by Geri on 2017/1/10.
 */

public interface RequestCallback {
    /**
     * 操作成功回调
     *
     * @param object 请求结果
     */
    void onSuccess(Object object);

    /**
     * 操作失败回调
     *
     * @param error 失败信息
     */
    void onFailed(String error);
}
