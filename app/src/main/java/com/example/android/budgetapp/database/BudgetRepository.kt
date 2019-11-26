package com.example.android.budgetapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.android.budgetapp.database.daos.BudgetDao
import com.example.android.budgetapp.database.daos.CategoryDao
import com.example.android.budgetapp.database.daos.ExpenditureDao

class BudgetRepository(app: Application) {
    private var budgetDao: BudgetDao
    private var expenditureDao: ExpenditureDao
    private var categoryDao: CategoryDao

    init {
        val database: BudgetDatabase
        = BudgetDatabase.getInstance(app)
        budgetDao = database.budgetDao
        expenditureDao = database.expenditureDao
        categoryDao = database.categoryDao
    }



}