package com.example.android.budgetapp.database.daos

import androidx.room.*
import com.example.android.budgetapp.database.entities.Expenditure

@Dao
interface ExpenditureDao {

    @Insert
    fun insertExpenditure(expenditure: Expenditure)

    @Delete
    fun deleteExpenditure(expenditure: Expenditure)

    @Update
    fun updateExpenditure(expenditure: Expenditure)

    @Query("SELECT * FROM expenditure WHERE uid = :key")
    fun getExpenditure(key: Long): Expenditure?

    @Query("SELECT * FROM expenditure WHERE category_uid = :categoryKey")
    fun findExpendituresForCategory(categoryKey: Long): List<Expenditure>?

    @Query("DELETE FROM expenditure")
    fun clear()
}