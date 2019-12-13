package com.example.android.budgetapp.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.budgetapp.database.daos.BudgetDao
import com.example.android.budgetapp.database.daos.CategoryDao
import com.example.android.budgetapp.database.daos.ExpenditureDao
import com.example.android.budgetapp.database.entities.Budget
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception
import java.util.*

@RunWith(AndroidJUnit4::class)
class BudgetDatabaseTest {

    private lateinit var budgetDao: BudgetDao
    private lateinit var expenditureDao: ExpenditureDao
    private lateinit var categoryDao: CategoryDao
    private lateinit var db: BudgetDatabase

    @Before
    fun createDb() {
        // gets the target context
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // using an in-memory database because the information stored here disappears
        //when the process is killed
        db = Room.inMemoryDatabaseBuilder(context, BudgetDatabase::class.java)
            .allowMainThreadQueries() // disables the check preventing queries on the main thread
            .build() // builds the database
        expenditureDao = db.expenditureDao // sets the database table to the global var
        categoryDao = db.categoryDao // sets the database table to the global var
        budgetDao = db.budgetDao // sets the database table to the global var
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCategory() {
        val category = Category(uid = 1,active = true,title = "Test",type = 0)
        categoryDao.insertCategory(category)
        val checkCategory = categoryDao.getCategory(1)
        assertEquals(checkCategory?.title, "Test")
    }

    @Test
    @Throws(Exception::class)
    fun clearCategoryTable() {
        val category = Category(uid = 1,active = true,title = "Test2" +
                "",type = 0)
        categoryDao.insertCategory(category)
        categoryDao.clear()
        val checkCategory = categoryDao.getCategory(1)
        assertEquals(checkCategory?.title, null)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetExpenditure() {
        val category = Category(uid = 1,active = true,title = "Test",type = 0)
        categoryDao.insertCategory(category)
        val expenditure = Expenditure(uid = 1,category_uid = 1,date = Date(),price = 10.0,description = "Test",recurrence = 1)
        expenditureDao.insertExpenditure(expenditure)
        val checkExpenditure = expenditureDao.getExpenditure(1)
        assertEquals(checkExpenditure?.category_uid, 1.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun getAllExpenditureOfACategory() {

    }

    @Test
    @Throws(Exception::class)
    fun deleteBudget() {
        val category = Category(uid = 1,active = true,title = "Test",type = 0)
        categoryDao.insertCategory(category)
        val budget = Budget(uid = 1, amount = 20.00, date = Date(124),category_uid = 1 )
        budgetDao.insertBudget(budget)
        budgetDao.deleteBudget(budget)
        var check = budgetDao.getBudget(1)
        assertEquals(check?.uid, null)
    }
}