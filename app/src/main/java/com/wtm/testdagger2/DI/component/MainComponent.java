package com.wtm.testdagger2.DI.component;

import com.wtm.testdagger2.DI.module.MainModule;
import com.wtm.testdagger2.MainActivity;

import dagger.Component;

/**
 * 作者：邓传亮 on 2017/3/16 09:58
 * <p>
 * 邮箱：dengchuanliang@optimumchina.com
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    public void inject(MainActivity activity) ;
}
