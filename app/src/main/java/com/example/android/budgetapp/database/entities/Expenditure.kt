package com.example.android.budgetapp.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.time.Instant
import java.util.*

@Entity(foreignKeys = arrayOf(
    ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("category_uid"),
        onDelete = CASCADE)
    )
)
data class Expenditure (
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "category_uid")
    val category_uid: Long,
    @ColumnInfo
    val date: Date?,
    @ColumnInfo
    val price: Double,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val recurrence: Int?
)