package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.nutrition.DietRepository
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
}