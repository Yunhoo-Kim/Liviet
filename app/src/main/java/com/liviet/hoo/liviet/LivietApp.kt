package com.liviet.hoo.liviet

import android.app.Activity
import android.app.Application
import com.liviet.hoo.liviet.di.AppModule
import com.liviet.hoo.liviet.di.DaggerAppComponent
import com.liviet.hoo.liviet.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class LivietApp: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .appModule(AppModule(this))
                .networkModule(NetworkModule)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>  = activityDispatchingAndroidInjector

}