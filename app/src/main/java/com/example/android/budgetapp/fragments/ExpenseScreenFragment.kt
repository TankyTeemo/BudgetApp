package com.example.android.budgetapp.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.budgetapp.R
import com.example.android.budgetapp.databinding.FragmentExpenseScreenBinding


class ExpenseScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentExpenseScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_expense_screen, container, false)
        binding.backButton.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_expenseScreenFragment_to_calendarScreenFragment)
        }
        return binding.root
    }

}
