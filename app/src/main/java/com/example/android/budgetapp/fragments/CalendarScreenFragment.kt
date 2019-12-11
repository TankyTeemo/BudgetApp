package com.example.android.budgetapp.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.android.budgetapp.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_calendar_screen.*
import android.graphics.Color
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.budgetapp.database.BudgetViewModel
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure
import com.example.android.budgetapp.databinding.FragmentCalendarScreenBinding
import java.awt.font.NumericShaper
import java.util.*


class CalendarScreenFragment : Fragment() {

    private lateinit var viewModel: BudgetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCalendarScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_calendar_screen, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Calender"

        viewModel = ViewModelProviders.of(this).get(BudgetViewModel::class.java)

        var expID = 0;
        var catAmount = 0;

        viewModel.getExpenditures().observe(this,object: Observer<List<Expenditure>> {
            override fun onChanged(t: List<Expenditure>?) {
                if(t!=null){
                    expID = viewModel.getExpenditures().value?.size ?: 0
                }
            }
        })

        viewModel.getCategories().observe(this,object: Observer<List<Category>> {
            override fun onChanged(t: List<Category>?) {
                if(t!=null){
                    catAmount = viewModel.getCategories().value?.size ?: 0
                }
            }
        })

        //Broken Spinner
//
//        val spinner = binding.spinner
//
//        var categoryTitles = arrayListOf<String>()
//
//        var categories = viewModel.getCategories().value
//
//        for (i in 0..catAmount) {
//            categoryTitles.add(categories!![i].title ?: "")
//        }
//
//        val aa = ArrayAdapter(this.context!!, android.R.layout.simple_spinner_item, categoryTitles)
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        spinner!!.setAdapter(aa)



        binding.expenseScreenButton.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_calendarScreenFragment_to_expenseScreenFragment)
        }

        binding.graphButton.setOnClickListener { v: View ->
            v.findNavController().navigate((R.id.action_calendarScreenFragment_to_pieGraphFragment))
        }

        binding.addExpenseButton.setOnClickListener { v: View ->
            var expenseName = binding.expenseDescription.text.toString()
            var expenseAmount = binding.expenseAmount.text.toString().toInt()
            if (!expenseName.matches("".toRegex()) && expenseAmount != null) {
                viewModel.insertExpenditure(Expenditure(expID.toLong(), 0, Date(), expenseAmount.toDouble(), expenseName, 0))
            }

            view?.hideKeyboard()
        }

        view?.hideKeyboard()
        return binding.root
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}
