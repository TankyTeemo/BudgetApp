package com.example.android.budgetapp.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.budgetapp.database.entities.Category

@Dao
interface CategoryDao {
    @Insert
    fun insertCategory(vararg category: Category?)

    @Update
    fun updateCategory(category: Category)

    @Query("SELECT * from category WHERE uid = :key")
    fun getCategory(key: Long): Category?

    @Delete
    fun deleteCategory(category: Category)

    @Query("DELETE FROM category")
    fun clear()

    @Query("SELECT * FROM category")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE active = :key")
    fun getAllActiveCategories(key: Boolean = true): LiveData<List<Category>>
}