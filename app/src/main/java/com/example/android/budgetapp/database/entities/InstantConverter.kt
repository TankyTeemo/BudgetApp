package com.example.android.budgetapp.database.entities

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun DateToLong(date: Date): Long? {
        return date.time
    }

    @TypeConverter
    fun longToDate(timestamp: Long?) : Date? {
        return timestamp?.let{ Date(it) }
    }
}