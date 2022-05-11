package com.aph.unisys.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aph.unisys.data.database.dao.NewDao
import com.aph.unisys.data.database.entites.NewEntity

@Database(entities = [NewEntity::class], version = 1)
abstract class NewDatabase: RoomDatabase() {

    abstract fun getNewDao(): NewDao
}