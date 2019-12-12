package com.example.android.budgetapp.database

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.android.budgetapp.database.daos.BudgetDao
import com.example.android.budgetapp.database.daos.CategoryDao
import com.example.android.budgetapp.database.daos.ExpenditureDao
import com.example.android.budgetapp.database.entities.Budget
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure

class BudgetRepository(app: Application) {
    private var budgetDao: BudgetDao
    private var expenditureDao: ExpenditureDao
    private var categoryDao: CategoryDao
    private var categoryData: LiveData<List<Category>>
    private var budgetData: LiveData<List<Budget>>
    private var expenditureData: LiveData<List<Expenditure>>

    init {
        val database: BudgetDatabase
        = BudgetDatabase.getInstance(app)
        budgetDao = database.budgetDao
        expenditureDao = database.expenditureDao
        categoryDao = database.categoryDao
        categoryData = categoryDao.getAllCategories()
        expenditureData = expenditureDao.getAllExpenditures()
        budgetData = budgetDao.getAllBudgets()
    }

    fun getCategories(): LiveData<List<Category>>{
        return categoryData
    }

    fun getBudgets(): LiveData<List<Budget>>{
        return  budgetData
    }

    fun getExpenditures(): LiveData<List<Expenditure>>{
        return expenditureData
    }

    fun deleteCategory(category: Category){
        AsyncDeleteCategory(categoryDao).execute(category)
    }

    fun deleteBudget(budget: Budget){
        AsyncDeleteBudget(budgetDao).execute(budget)
    }

    fun deleteExpenditure(expenditure: Expenditure){
        AsyncDeleteExpenditure(expenditureDao).execute(expenditure)
    }

    fun updateCategory(category: Category){
        AsyncUpdateCategory(categoryDao).execute(category)
    }

    fun updateBudget(budget: Budget){
        AsyncUpdateBudget(budgetDao).execute(budget)
    }

    fun updateExpenditure(expediture: Expenditure){
        AsyncUpdateExpenditure(expenditureDao).execute(expediture)
    }

    fun insertBudget(budget: Budget){
        AsyncInsertBudget(budgetDao).execute(budget)
    }

    fun insertCategory(category: Category){
        AsyncInsertCategory(categoryDao).execute(category)
    }

    fun insertExpenditure(expenditure: Expenditure){
        AsyncInsertExpenditure(expenditureDao).execute(expenditure)
    }

    class AsyncInsertBudget(private val budgetDao: BudgetDao): AsyncTask<Budget, Void, Unit>(){
        override fun doInBackground(vararg budget: Budget?) {
            budgetDao.insertBudget(*budget)
        }
    }

    class AsyncDeleteBudget(private val budgetDao: BudgetDao): AsyncTask<Budget, Void, Unit>(){
        override fun doInBackground(vararg budget: Budget?) {
            budgetDao.deleteBudget(*budget)
        }
    }

    class AsyncUpdateBudget(private val budgetDao: BudgetDao): AsyncTask<Budget, Void, Unit>(){
        override fun doInBackground(vararg budget: Budget?) {
            budgetDao.updateBudget(*budget)
        }
    }

    class AsyncInsertExpenditure(private val expenditureDao: ExpenditureDao): AsyncTask<Expenditure, Void, Unit>(){
        override fun doInBackground(vararg expenditure: Expenditure?) {
            expenditureDao.insertExpenditure(*expenditure)
        }
    }

    class AsyncDeleteExpenditure(private val expenditureDao: ExpenditureDao): AsyncTask<Expenditure, Void, Unit>(){
        override fun doInBackground(vararg expenditure: Expenditure?) {
            expenditureDao.deleteExpenditure(*expenditure)
        }
    }

    class AsyncUpdateExpenditure(private val expenditureDao: ExpenditureDao): AsyncTask<Expenditure, Void, Unit>(){
        override fun doInBackground(vararg expenditure: Expenditure?) {
            expenditureDao.updateExpenditure(*expenditure)
        }
    }

    class AsyncInsertCategory(private val categoryDao: CategoryDao): AsyncTask<Category, Void, Unit>(){
        override fun doInBackground(vararg category: Category?) {
            categoryDao.insertCategory(*category)
        }
    }

    class AsyncDeleteCategory(private val categoryDao: CategoryDao): AsyncTask<Category, Void, Unit>(){
        override fun doInBackground(vararg category: Category?) {
            categoryDao.deleteCategory(*category)
        }
    }

    class AsyncUpdateCategory(private val categoryDao: CategoryDao): AsyncTask<Category, Void, Unit>(){
        override fun doInBackground(vararg category: Category?) {
            categoryDao.updateCategory(*category)
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: BudgetRepository? = null

        fun getInstance(app: Application): BudgetRepository {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = BudgetRepository(app)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}