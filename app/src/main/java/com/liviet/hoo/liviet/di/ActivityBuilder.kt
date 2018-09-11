package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.di.module.DietStyleSetUpFragmentModule
import com.liviet.hoo.liviet.di.module.LifeStyleSetUpFragmentModule
import com.liviet.hoo.liviet.di.module.UserSetUpActivityModule
import com.liviet.hoo.liviet.ui.user.DietStyleSetUpFragment
import com.liviet.hoo.liviet.ui.user.LifeStyleSetUpFragment
import com.liviet.hoo.liviet.ui.user.SetUpNutritionResultFragment
import com.liviet.hoo.liviet.ui.user.UserSetUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
@Suppress("unused")
abstract class ActivityBuilder{

    @ContributesAndroidInjector(modules = [UserSetUpActivityModule::class])
    abstract fun bindUserSetUpActivityModule(): UserSetUpActivity

    @ContributesAndroidInjector(modules = [DietStyleSetUpFragmentModule::class])
    abstract fun bindDietStyleSetUpFragmentModule(): DietStyleSetUpFragment

    @ContributesAndroidInjector(modules = [LifeStyleSetUpFragmentModule::class])
    abstract fun bindLifeStyleSetUpFragmentModule(): LifeStyleSetUpFragment

    @ContributesAndroidInjector(modules = [UserSetUpActivityModule::class])
    abstract fun bindSetupNutritionResultFragment(): SetUpNutritionResultFragment
}