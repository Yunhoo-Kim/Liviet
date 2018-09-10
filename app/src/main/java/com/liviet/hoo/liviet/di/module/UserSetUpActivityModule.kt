package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.ActivityScope
import com.liviet.hoo.liviet.ui.user.UserSetUpActivity
import dagger.Binds
import dagger.Module


@Module
abstract class UserSetUpActivityModule {

    @ActivityScope
    @Binds
    abstract fun provideUserSetUpActivity(activity: UserSetUpActivity): UserSetUpActivity
}