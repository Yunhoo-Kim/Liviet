package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.model.SharedPreferenceHelper
import com.liviet.hoo.liviet.model.database.AppDatabase
import com.liviet.hoo.liviet.model.liviet.VersionsRepository
import com.liviet.hoo.liviet.model.nutrition.DietDao
import com.liviet.hoo.liviet.model.nutrition.DietRepository
import com.liviet.hoo.liviet.model.nutrition.FoodDao
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.model.user.UserDao
import com.liviet.hoo.liviet.model.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
@Suppress("unused")
class RepositoryModule {

//    @Provides
//    @FragmentScope
//    fun provideUserRepository(userDao: UserDao, sharedPreferenceHelper: SharedPreferenceHelper): UserRepository = UserRepository(userDao, sharedPreferenceHelper)
//
//    @Provides
//    @FragmentScope
//    fun bindFoodRepository(foodDao: FoodDao, sharedPreferenceHelper: SharedPreferenceHelper): FoodRepository = FoodRepository(foodDao, sharedPreferenceHelper)
//
//    @Provides
//    @FragmentScope
//    fun bindDietRepository(dietRepository: DietRepository): DietRepository
//
//    @Binds
//    @FragmentScope
//    fun bindVersionRepository(versionsRepository: VersionsRepository): VersionsRepository
}