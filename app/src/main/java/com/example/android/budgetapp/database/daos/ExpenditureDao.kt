package com.example.android.budgetapp.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.budgetapp.database.entities.Expenditure

@Dao
interface ExpenditureDao {

    @Insert
    fun insertExpenditure(vararg expenditure: Expenditure?)

    @Delete
    fun deleteExpenditure(vararg expenditure: Expenditure?)

    @Update
    fun updateExpenditure(vararg expenditure: Expenditure?)

    @Query("SELECT * FROM expenditure WHERE uid = :key")
    fun getExpenditure(key: Long): Expenditure?

    @Query("SELECT * FROM expenditure WHERE category_uid = :categoryKey")
    fun findExpendituresForCategory(categoryKey: Long): LiveData<List<Expenditure>>?

    @Query("DELETE FROM expenditure")
    fun clear()

    @Query("SELECT * FROM expenditure ORDER BY uid DESC")
    fun getAllExpenditures(): LiveData<List<Expenditure>>
}