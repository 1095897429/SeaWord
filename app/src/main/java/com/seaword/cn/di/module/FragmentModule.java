package com.seaword.cn.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.seaword.cn.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zl on 2018/5/23.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return mFragment.getActivity();
    }

}
