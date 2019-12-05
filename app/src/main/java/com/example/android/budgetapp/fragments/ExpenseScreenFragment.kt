package com.example.android.budgetapp.fragments

import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.budgetapp.R
import com.example.android.budgetapp.database.BudgetDatabase
import com.example.android.budgetapp.database.BudgetRepository
import com.example.android.budgetapp.database.daos.CategoryDao
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.databinding.FragmentExpenseScreenBinding


class ExpenseScreenFragment : Fragment() {

    private var databaseHelper: BudgetRepository? = null
    private var categories: TextView? = null
    private var arrayList: ArrayList<String>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentExpenseScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_expense_screen, container, false)

        categories = binding.addCategoryHeader

        databaseHelper = BudgetRepository.getInstance(activity!!.application)

        //arrayList = listOf(databaseHelper!!.getCategories())

        arrayList = arrayListOf<String>()

        binding.submitButton.setOnClickListener( {
            var categoryName = binding.catagoryName.text
            var categoryAmount = binding.categoryAmount.text
            if (!categoryName.matches("".toRegex()) && !categoryAmount.matches("".toRegex())) {
                arrayList!!.add((categoryName.toString() + " $" + categoryAmount.toString() + "\n"))
                binding.categoryList.text = ""
                for (i in arrayList!!.indices) {
                    binding.categoryList.text = binding.categoryList.text.toString() + "\n" + arrayList!![i]
                }
            }
        })

        return binding.root
    }

}
