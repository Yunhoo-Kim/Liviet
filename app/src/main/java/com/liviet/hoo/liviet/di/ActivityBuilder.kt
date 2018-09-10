package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.di.module.BodySetUpFragmentModule
import com.liviet.hoo.liviet.di.module.DietStyleSetUpFragmentModule
import com.liviet.hoo.liviet.di.module.UserSetUpActivityModule
import com.liviet.hoo.liviet.ui.user.BodySetUpFragment
import com.liviet.hoo.liviet.ui.user.DietStyleSetUpFragment
import com.liviet.hoo.liviet.ui.user.UserSetUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector(modules = [UserSetUpActivityModule::class])
    abstract fun bindUserSetUpActivityModule(): UserSetUpActivity

    @ContributesAndroidInjector(modules = [BodySetUpFragmentModule::class])
    abstract fun bindBodySetUpFragmentModule(): BodySetUpFragment

    @ContributesAndroidInjector(modules = [DietStyleSetUpFragmentModule::class])
    abstract fun bindDietStyleSetUpFragmentModule(): DietStyleSetUpFragment
}