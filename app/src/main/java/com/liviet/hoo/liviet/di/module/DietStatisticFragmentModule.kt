package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.nutrition.DietRepository
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.food.DietStatisticFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class DietStatisticFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindDietStatisticFragment(dietStatisticFragment: DietStatisticFragment): DietStatisticFragment

    @FragmentScope
    @Binds
    abstract fun bindDietRepository(dietRepository: DietRepository): DietRepository

    @FragmentScope
    @Binds
    abstract fun bindFoodRepository(foodRepository: FoodRepository): FoodRepository

    @FragmentScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepository
}