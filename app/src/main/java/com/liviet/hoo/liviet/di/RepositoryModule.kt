package com.liviet.hoo.liviet.di

import com.liviet.hoo.liviet.model.database.AppDatabase
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
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepository

    @Binds
    abstract fun bindFoodRepository(foodRepository: FoodRepository): FoodRepository

    @Binds
    abstract fun bindDietRepository(dietRepository: DietRepository): DietRepository
}