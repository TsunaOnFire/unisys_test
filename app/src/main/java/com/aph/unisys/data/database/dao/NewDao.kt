package com.aph.unisys.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aph.unisys.data.database.entites.NewEntity

@Dao
interface NewDao {
    @Query("SELECT * FROM new_table ORDER BY publishedAt DESC")
    suspend fun getAllQuotes():List<NewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<NewEntity>)

    @Query("DELETE FROM new_table")
    suspend fun deleteAllNews()
}