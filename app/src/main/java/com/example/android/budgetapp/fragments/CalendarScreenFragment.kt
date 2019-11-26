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
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.budgetapp.databinding.FragmentCalendarScreenBinding


class CalendarScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCalendarScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_calendar_screen, container, false)
        binding.expenseScreenButton.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_calendarScreenFragment_to_expenseScreenFragment)
        }
        return binding.root
    }


}
