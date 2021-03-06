package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.user.BodySetUpFragment
import com.liviet.hoo.liviet.ui.user.DietStyleSetUpFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class DietStyleSetUpFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindDietStyleSetUpFragment(dietStyleSetUpFragment: DietStyleSetUpFragment): DietStyleSetUpFragment

    @FragmentScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepository
}