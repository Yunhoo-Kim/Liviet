package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.model.database.AppDatabase
import com.liviet.hoo.liviet.model.user.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
@Suppress("unused")
class DaoModule {
    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()
}