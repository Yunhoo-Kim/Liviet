package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.user.BodySetUpFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class BodySetUpFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindBodySetUpFragment(bodySetUpFragment: BodySetUpFragment): BodySetUpFragment

    @FragmentScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepository
}