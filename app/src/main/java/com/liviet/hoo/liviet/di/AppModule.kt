package com.liviet.hoo.liviet.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.liviet.hoo.liviet.LivietApp
import com.liviet.hoo.liviet.model.database.AppDatabase
import com.liviet.hoo.liviet.utils.FIREBASE_DB_URL
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
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

    @Provides
    @Singleton
    fun provideFirebaseStore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}