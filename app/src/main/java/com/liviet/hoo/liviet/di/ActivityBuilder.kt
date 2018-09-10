package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.di.module.AgeSexSetUpFragmentModule
import com.liviet.hoo.liviet.di.module.UserSetUpActivityModule
import com.liviet.hoo.liviet.ui.user.AgeSexSetUpFragment
import com.liviet.hoo.liviet.ui.user.UserSetUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector(modules = [UserSetUpActivityModule::class])
    abstract fun bindUserSetUpActivityModule(): UserSetUpActivity

    @ContributesAndroidInjector(modules = [AgeSexSetUpFragmentModule::class])
    abstract fun bindAgeSexSetUpFragmentModule(): AgeSexSetUpFragment
}