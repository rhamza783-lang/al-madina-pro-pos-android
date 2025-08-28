package com.almadina.pos.di

import android.content.Context
import androidx.room.Room
import com.almadina.pos.data.local.AppDatabase
import com.almadina.pos.data.local.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --- DATABASE ---
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pos_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideOrderDao(db: AppDatabase): OrderDao = db.orderDao()

    //
    // --- NOTE: We have intentionally removed provideRetrofit() and provideApiService() ---
    // --- to bypass the KSP 'error.NonExistentClass' bug.                       ---
    //
}
