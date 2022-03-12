package com.example.measuredata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PPGDataDao {
    @Insert()
    fun insert(data: PPGData)
    @Insert()
    fun insertAll(data: List<PPGData>)

    @Query("DELETE FROM ppg_table")
    fun deleteAll()
}