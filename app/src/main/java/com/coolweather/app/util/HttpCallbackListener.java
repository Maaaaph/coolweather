package com.coolweather.app.util;

/**
 * Created by haha on 2016/4/18.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
