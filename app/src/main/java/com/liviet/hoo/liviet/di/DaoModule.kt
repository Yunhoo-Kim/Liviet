package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.model.database.AppDatabase
import com.liviet.hoo.liviet.model.nutrition.DietDao
import com.liviet.hoo.liviet.model.nutrition.FoodDao
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

    @Provides
    @Singleton
    fun provideFoodDao(database: AppDatabase): FoodDao = database.foodDao()

    @Provides
    @Singleton
    fun provideDietDao(database: AppDatabase): DietDao = database.dietDao()
}