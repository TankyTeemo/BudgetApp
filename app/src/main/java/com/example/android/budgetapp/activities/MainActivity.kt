package com.example.android.budgetapp.activities

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.budgetapp.R
import com.example.android.budgetapp.database.BudgetRepository
import com.example.android.budgetapp.database.entities.Budget
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure
import kotlinx.android.synthetic.main.activity_main.*
import com.example.android.budgetapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
//
    /**
     * Called when app is launched
     * @param savedInstanceState used if orientation is changed otherwise null
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
