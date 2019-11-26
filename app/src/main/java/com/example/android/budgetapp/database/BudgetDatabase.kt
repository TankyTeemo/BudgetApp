package com.example.android.budgetapp.database

import android.content.Context
import androidx.room.*
import com.example.android.budgetapp.database.daos.BudgetDao
import com.example.android.budgetapp.database.daos.CategoryDao
import com.example.android.budgetapp.database.daos.ExpenditureDao
import com.example.android.budgetapp.database.entities.Budget
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure

@Database(entities = [Category::class,Expenditure::class, Budget::class],
    version = 1, exportSchema = true)
    abstract class BudgetDatabase: RoomDatabase() {

    abstract val expenditureDao: ExpenditureDao
    abstract val categoryDao: CategoryDao
    abstract val budgetDao: BudgetDao

    companion object{
        @Volatile
        private var INSTANCE: BudgetDatabase? = null

        fun getInstance(context: Context): BudgetDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BudgetDatabase::class.java,
                        "budget_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}