package com.example.android.budgetapp.database.daos

import androidx.room.*
import com.example.android.budgetapp.database.entities.Budget

@Dao
interface BudgetDao {
    @Insert
    fun insertBudget(budget: Budget)

    @Update
    fun updateBudget(budget: Budget)

    @Delete
    fun deleteBudget(budget: Budget)

    @Query("SELECT * FROM budget WHERE uid = :key")
    fun getBudget(key: Long): Budget

    @Query("SELECT * FROM budget WHERE category_uid = :categoryKey")
    fun findBudgetForCategory(categoryKey: Long): List<Budget>

}