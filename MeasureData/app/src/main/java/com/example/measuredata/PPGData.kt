package com.example.measuredata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "ppg_table")
class PPGData (@PrimaryKey(autoGenerate = true) val id: Long?=null, val channel1: Float, val channel2: Float, val timestamp: Long){

}