package com.example.android.budgetapp.fragments


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_pie_graph.*
import com.example.android.budgetapp.R
import com.example.android.budgetapp.database.BudgetRepository
import com.example.android.budgetapp.database.BudgetViewModel
import com.example.android.budgetapp.database.entities.Budget
import com.example.android.budgetapp.databinding.FragmentCalendarScreenBinding
import com.example.android.budgetapp.databinding.FragmentPieGraphBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.*
import java.lang.NullPointerException
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class PieGraphFragment : Fragment() {

    val expenditureString:Array<String> = arrayOf("NONE", "NONE", "NONE")
    val floatValues = arrayOf(1.0f, 1.0f, 1.0f)
    private lateinit var binding : FragmentPieGraphBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pie_graph, container, false)

        setupPieChart(expenditureString)

        binding.backButton.setOnClickListener { v: View ->
            v.findNavController().navigate((R.id.action_pieGraphFragment_to_calendarScreenFragment))
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStart() {
        setupPieChart(expenditureString)
        Log.i("tags", "onStart")
        super.onStart()

    }

    /**
     * function to setup up the pie chart with the data
     */
    private fun setupPieChart(entries :Array<String>) {
        val repo = BudgetRepository(activity!!.application)
        val budgetList = repo.getBudgets()
        val categoryList = repo.getCategories()

        val pieEntries = ArrayList<PieEntry>(0)

        if(budgetList.value?.size != null){
            Log.i("tags", budgetList.value?.size.toString())
            for (i in 0 until budgetList.value!!.size) {
                val entryFloat = budgetList.value?.get(i)?.amount
                val categoryString = categoryList.value?.get(i)?.title
                val myEntry = PieEntry(entryFloat!!.toFloat(), categoryString)

                pieEntries.add(myEntry)

                Log.i("tags", pieEntries.toString())
            }
        }
        else{ // Default PieChart when no data is entered
            for (i in 0 until floatValues.size){
                val myEntry = PieEntry(floatValues[i], entries[i])

                pieEntries.add(myEntry)

                Log.i("tags", pieEntries.toString())
            }
        }
        val dataSet = PieDataSet(pieEntries, "Percentage of Expenditures")
        dataSet.setColors(JOYFUL_COLORS, 100)

        val myData = PieData(dataSet)
        myData.setValueFormatter(PercentFormatter())

        binding.pieChart.data = myData
        binding.pieChart.description.text = "HI"
        binding.pieChart.isDrawHoleEnabled= false
        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.setEntryLabelColor(Color.BLACK)
        binding.pieChart.animateY(1000)
        // pieChart.setOnChartValueSelectedListener(this)
        binding.pieChart.invalidate()
    }

    companion object {
        fun newInstance(): PieGraphFragment = PieGraphFragment()
    }


}
