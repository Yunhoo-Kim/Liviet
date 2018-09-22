package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.food.SelectFoodFragment
import com.liviet.hoo.liviet.ui.user.BodySetUpFragment
import com.liviet.hoo.liviet.ui.user.DietStyleSetUpFragment
import com.liviet.hoo.liviet.ui.user.SelectStartDietDayFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class SelectFoodFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindSelectFoodFragment(selectFoodFragment: SelectFoodFragment): SelectFoodFragment

    @FragmentScope
    @Binds
    abstract fun bindFoodRepository(foodRepository: FoodRepository): FoodRepository
}