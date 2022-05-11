package com.aph.unisys.core.di

import android.content.Context
import androidx.room.Room
import com.aph.unisys.data.database.NewDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val NEW_DATABASE_NAME="new_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context,NewDatabase::class.java,NEW_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNewDao(db: NewDatabase) = db.getNewDao()
}