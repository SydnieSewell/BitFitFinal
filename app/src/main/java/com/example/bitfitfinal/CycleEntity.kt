package com.example.bitfitfinal


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cycle_table")
data class CycleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "dayOfweek") val dayOfweek: String?,
    @ColumnInfo(name = " cycleLength") val  cycleLength: Int?
)