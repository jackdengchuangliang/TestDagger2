package com.wtm.testdagger2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wtm.testdagger2.DI.component.DaggerMainComponent;
import com.wtm.testdagger2.DI.module.MainModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ILoginView {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    LoginPresenterCompl mLoginPresenterCompl;
    private TextView mTvLogin;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvLogin = (TextView) findViewById(R.id.tv_login);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);

        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenterCompl.startLogin(true);
            }
        });
        mTvLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mLoginPresenterCompl.startLogin(false);
                return true;
            }
        });
    }

    @Override
    public void onLoginStart() {
        mProgressBar.setVisibility(View.VISIBLE);
        Log.i(TAG, "onLoginStart:");
    }

    @Override
    public void onLoginend() {
        showMsg("请求完成");
        mProgressBar.setVisibility(View.GONE);
        Log.i(TAG, "onLoginend:");
    }

    @Override
    public void onLoginSuccess(String result) {
        showMsg(mLoginPresenterCompl.mPerson.name+result);
        Log.i(TAG, mLoginPresenterCompl.mPerson.name+"onLoginSuccess:");
    }

    @Override
    public void onLoginFaild(String error) {
        showMsg(error);
        Log.i(TAG, "onLoginFaild:");
    }

    @Override
    public Activity getContext() {
        return MainActivity.this;
    }

    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
