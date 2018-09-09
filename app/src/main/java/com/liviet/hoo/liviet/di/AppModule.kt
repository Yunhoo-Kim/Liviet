package com.liviet.hoo.liviet.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.liviet.hoo.liviet.LivietApp
import com.liviet.hoo.liviet.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
@Suppress("unused")
class AppModule(val app: Application){
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    @Named("appContext")
    fun provideContext(app:Application): Context = app

    @Provides
    @Singleton
    fun provideDatabase(app:Application): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "liviet.db")
                    .fallbackToDestructiveMigration()
                    .build()

}