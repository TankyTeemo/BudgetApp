package com.example.android.budgetapp.database.entities

import androidx.room.*
import java.util.*

@Entity
data class Category (
    @PrimaryKey val uid: Long,
    @ColumnInfo
    var active: Boolean?,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val type: Int?
)