package com.wtm.testdagger2.DI.module;

import com.wtm.testdagger2.ILoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：邓传亮 on 2017/3/16 09:57
 * <p>
 * 邮箱：dengchuanliang@optimumchina.com
 */
@Module
public class MainModule {
    private ILoginView mLoginView;
    @Singleton
    public MainModule(ILoginView loginView) {
        mLoginView=loginView;
    }

    @Provides
    ILoginView provideILoginView(){
        return mLoginView;
    }
}
