package com.example.android.budgetapp.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.budgetapp.database.entities.Budget

@Dao
interface BudgetDao {
    @Insert
    fun insertBudget(vararg budget: Budget?)

    @Update
    fun updateBudget(vararg budget: Budget?)

    @Delete
    fun deleteBudget(vararg budget: Budget?)

    @Query("SELECT * FROM budget WHERE uid = :key")
    fun getBudget(key: Long): Budget

    @Query("SELECT * FROM budget WHERE category_uid = :categoryKey")
    fun findBudgetForCategory(categoryKey: Long): List<Budget>

    @Query("SELECT * FROM Budget ORDER BY uid DESC")
    fun getAllBudgets(): LiveData<List<Budget>>
}