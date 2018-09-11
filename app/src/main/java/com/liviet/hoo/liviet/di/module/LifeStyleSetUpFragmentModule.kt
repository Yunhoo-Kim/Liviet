package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.user.LifeStyleSetUpFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class LifeStyleSetUpFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindLifeStyleSetUpFragment(lifeStyleSetUpFragment: LifeStyleSetUpFragment): LifeStyleSetUpFragment

    @FragmentScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepository
}