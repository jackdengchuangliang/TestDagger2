package com.wtm.testdagger2;

import android.app.Activity;

/**
 * 作者：邓传亮 on 2017/3/16 09:44
 * <p>
 * 邮箱：dengchuanliang@optimumchina.com
 */
public interface ILoginView {
    void onLoginStart();
    void onLoginend();
    void onLoginSuccess (String result);
    void onLoginFaild (String error);
    Activity getContext();
}
