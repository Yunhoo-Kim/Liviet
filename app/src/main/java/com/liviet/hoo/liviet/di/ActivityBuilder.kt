package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.di.module.*
import com.liviet.hoo.liviet.ui.LivietMainFragment
import com.liviet.hoo.liviet.ui.food.AddDietFoodDetailFragment
import com.liviet.hoo.liviet.ui.food.AddDietFoodFragment
import com.liviet.hoo.liviet.ui.food.AddNewDietFoodFragment
import com.liviet.hoo.liviet.ui.food.SelectFoodFragment
import com.liviet.hoo.liviet.ui.user.*
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

    @ContributesAndroidInjector(modules = [SelectStartDietDayFragmentModule::class])
    abstract fun bindSelectStartDietDayFragment(): SelectStartDietDayFragment

    @ContributesAndroidInjector(modules = [SelectFoodFragmentModule::class])
    abstract fun bindSelectFoodFragment(): SelectFoodFragment

    @ContributesAndroidInjector(modules = [AddDietFoodFragmentModule::class])
    abstract fun bindAddDietFoodFragment(): AddDietFoodFragment

    @ContributesAndroidInjector(modules = [AddDietFoodFragmentModule::class])
    abstract fun bindAddDietFoodDetailFragment(): AddDietFoodDetailFragment

    @ContributesAndroidInjector(modules = [AddDietFoodFragmentModule::class])
    abstract fun bindAddNewDietFoodFragment(): AddNewDietFoodFragment

    @ContributesAndroidInjector(modules = [LivietMainFragmentModule::class])
    abstract fun bindLivietMainFragment(): LivietMainFragment

    @ContributesAndroidInjector(modules = [UserSetUpActivityModule::class])
    abstract fun bindSetupNutritionResultFragment(): SetUpNutritionResultFragment
}