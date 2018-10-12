package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.ActivityScope
import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.MainActivity
import com.liviet.hoo.liviet.ui.user.SetUpNutritionResultFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class MainActivityModule {

    @ActivityScope
    @Binds
    abstract fun provideMainActivity(activity: MainActivity): MainActivity

//    @FragmentScope
//    @Binds
//    abstract fun bindDietStyleSetUpFragment(dietStyleSetUpFragment: DietStyleSetUpFragment): DietStyleSetUpFragment
//
    @FragmentScope
    @Binds
    abstract fun bindSetUpNutritionResultFragment(setUpNutritionResultFragment: SetUpNutritionResultFragment): SetUpNutritionResultFragment

    @FragmentScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepository
}