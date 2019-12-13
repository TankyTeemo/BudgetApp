package com.example.android.budgetapp.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.android.budgetapp.R
import com.example.android.budgetapp.database.BudgetViewModel
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure
import com.example.android.budgetapp.databinding.FragmentDailyExpendituresBinding

/**
 * This fragment is to view a list of expenditures depending on what day is clicked on the calendar screen.
 * The list should be shorted by the date
 */
class ExpenditureListFragment : Fragment() {


    private lateinit var viewModel: BudgetViewModel
    private lateinit var recyclerLayout: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentDailyExpendituresBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_daily_expenditures, container, false)

        //TODO: change to an expenditure adapter
        var recyclerAdapter = CategoriesAdapter()

        var expenditureId = 0

        (activity as AppCompatActivity).supportActionBar?.title = "Expenditures"


        viewModel = ViewModelProviders.of(this).get(BudgetViewModel::class.java)

        recyclerLayout = LinearLayoutManager(context)

        binding.expenditureRowRecyclerView.apply{
            setHasFixedSize(true)
            layoutManager = recyclerLayout
            adapter = recyclerAdapter
        }

        viewModel.getExpenditures().observe(this,object: Observer<List<Expenditure>> {
            private val adapter = recyclerAdapter
            override fun onChanged(t: List<Expenditure>?) {
                if(t!=null){
                    // TODO: change the adapter
                    //adapter.setCategories(t)
                    expenditureId = viewModel.getExpenditures().value?.size ?: 0
                }
            }
        })

        return binding.root
    }
}
