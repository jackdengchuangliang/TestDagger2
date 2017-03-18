package com.wtm.testdagger2;

import android.os.SystemClock;

import javax.inject.Inject;

/**
 * 作者：邓传亮 on 2017/3/16 09:47
 * <p>
 * 邮箱：dengchuanliang@optimumchina.com
 */
public class LoginPresenterCompl implements ILoginPresenter {
    private ILoginView mLoginView;
    public Person mPerson;

    @Inject
    public LoginPresenterCompl(ILoginView loginView) {
        mLoginView = loginView;
        mPerson = new Person("张三",18);
    }

    public void startLogin(final boolean flag){
        mLoginView.onLoginStart();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(500);
                mLoginView.getContext().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (flag)
                            mLoginView.onLoginSuccess("成功了");
                        else
                            mLoginView.onLoginFaild("失败了");

                        mLoginView.onLoginend();
                    }
                });
            }
        }).start();
    }
}
