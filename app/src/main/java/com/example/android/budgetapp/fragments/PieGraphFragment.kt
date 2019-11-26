package com.example.android.budgetapp.fragments


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
import com.example.android.budgetapp.databinding.FragmentCalendarScreenBinding
import com.example.android.budgetapp.databinding.FragmentPieGraphBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

/**
 * A simple [Fragment] subclass.
 */
class PieGraphFragment : Fragment() {

    val expenditureString:Array<String?> = arrayOf("Food", "Rent", "Other")
    val floatValues = arrayOf(22.2f, 50.2f, 12.0f)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPieGraphBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pie_graph, container, false)

        setupPieChart(binding)

        binding.backButton.setOnClickListener { v: View ->
            v.findNavController().navigate((R.id.action_pieGraphFragment_to_calendarScreenFragment))
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * function to setup up the pie chart with the data
     */
    private fun setupPieChart(binding: FragmentPieGraphBinding) {

        val pieEntries = ArrayList<PieEntry>(0)

        for (i in 0 until floatValues.size){
            val myEntry = PieEntry(floatValues[i], "expenditureString[i]")

            pieEntries.add(myEntry)

            Log.i("tags", pieEntries.toString())
        }

        val dataSet = PieDataSet(pieEntries, "Percentage of Expenditures")
        val myData = PieData(dataSet)
        myData.setValueFormatter(PercentFormatter())


        binding.pieChart.data = myData
        binding.pieChart.description.text = "HI"
        binding.pieChart.isDrawHoleEnabled= false
        binding.pieChart.setUsePercentValues(true)
        // pieChart.setOnChartValueSelectedListener(this)

        binding.pieChart.invalidate()

    }
    companion object {
        fun newInstance(): PieGraphFragment = PieGraphFragment()
    }


}
