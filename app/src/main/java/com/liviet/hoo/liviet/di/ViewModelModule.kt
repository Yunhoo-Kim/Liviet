package com.liviet.hoo.liviet.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
@Suppress("unused")
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    //  Add New View Model below this line

    @Binds
    @IntoMap
    @ViewModelKey(UserSetUpViewModel::class)
//    @Singleton
    internal abstract fun bindUserSetUpViewModel(viewModel: UserSetUpViewModel): ViewModel
}