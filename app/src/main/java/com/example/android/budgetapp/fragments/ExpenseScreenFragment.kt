package com.example.android.budgetapp.fragments

import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.budgetapp.R
import com.example.android.budgetapp.database.BudgetDatabase
import com.example.android.budgetapp.database.BudgetRepository
import com.example.android.budgetapp.database.BudgetViewModel
import com.example.android.budgetapp.database.daos.CategoryDao
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.databinding.FragmentExpenseScreenBinding


class ExpenseScreenFragment : Fragment() {

    private lateinit var viewModel: BudgetViewModel
    private lateinit var recyclerLayout: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentExpenseScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_expense_screen, container, false)

        var recyclerAdapter: CategoriesAdapter = CategoriesAdapter()

        var catID = 0;

        (activity as AppCompatActivity).supportActionBar?.title = "Manage Categories"


        viewModel = ViewModelProviders.of(this).get(BudgetViewModel::class.java)

        recyclerLayout = LinearLayoutManager(context)

        binding.expenseRecyclerView.apply{
            setHasFixedSize(true)
            layoutManager = recyclerLayout
            adapter = recyclerAdapter
        }

        viewModel.getCategories().observe(this,object: Observer<List<Category>> {
            private val adapter = recyclerAdapter
            override fun onChanged(t: List<Category>?) {
                if(t!=null){
                    adapter.setCategories(t)
                        catID = viewModel.getCategories().value?.size ?: 0
                }
            }
        })

        //Adds categories to database
        binding.submitButton.setOnClickListener {
            var categoryName = binding.catagoryName.text.toString()
            var categoryAmount = binding.categoryAmount.text.toString().toInt()
            if (!categoryName.matches("".toRegex()) && categoryAmount != null) {
                viewModel.insertCategory(Category(catID.toLong(), true, categoryName, categoryAmount))
            }

            view?.hideKeyboard()
        }

        return binding.root
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}
