package com.example.android.budgetapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.android.budgetapp.database.entities.Budget
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure

class BudgetViewModel(app: Application): AndroidViewModel(app) {
    private val repository: BudgetRepository
            = BudgetRepository(app)
    private val categories: LiveData<List<Category>>
            = repository.getCategories()
    private val budgets: LiveData<List<Budget>>
            = repository.getBudgets()
    private val expenditures: LiveData<List<Expenditure>>
            = repository.getExpenditures()

    fun getCategories(): LiveData<List<Category>>{
        return categories
    }
    
    fun getBudgets(): LiveData<List<Budget>>{
        return budgets
    }
    
    fun getExpenditures(): LiveData<List<Expenditure>>{
        return expenditures
    }

    fun insertCategory(cat: Category){
        repository.insertCategory(cat)
    }

    fun insertExpenditure(exp: Expenditure){
        repository.insertExpenditure(exp)
    }

    fun deleteCategories(cat: Category) {
        repository.deleteCategory(cat)
    }

    fun deleteExpenditures(exp: Expenditure){
        repository.deleteExpenditure(exp)
    }

    fun deleteBudgets(budget: Budget){
        repository.deleteBudget(budget)
    }
}