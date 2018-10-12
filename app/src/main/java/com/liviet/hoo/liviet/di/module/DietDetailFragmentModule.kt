package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.nutrition.DietRepository
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.food.*
import com.liviet.hoo.liviet.ui.user.BodySetUpFragment
import com.liviet.hoo.liviet.ui.user.DietStyleSetUpFragment
import com.liviet.hoo.liviet.ui.user.SelectStartDietDayFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class DietDetailFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindDietDetailFragment(dietDetailFragment: DietDetailFragment): DietDetailFragment

    @FragmentScope
    @Binds
    abstract fun bindFoodRepository(foodRepository: FoodRepository): FoodRepository

    @FragmentScope
    @Binds
    abstract fun bindDietRepository(dietRepository: DietRepository): DietRepository
}