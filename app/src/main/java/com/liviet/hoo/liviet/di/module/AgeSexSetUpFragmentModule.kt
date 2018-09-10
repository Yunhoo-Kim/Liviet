package com.liviet.hoo.liviet.di.module

import com.liviet.hoo.liviet.di.FragmentScope
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.user.AgeSexSetUpFragment
import dagger.Binds
import dagger.Module


@Module
@Suppress("unused")
abstract class AgeSexSetUpFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindAgeSexSetUpFragment(ageSexSetUpFragment: AgeSexSetUpFragment): AgeSexSetUpFragment

    @FragmentScope
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepository
}