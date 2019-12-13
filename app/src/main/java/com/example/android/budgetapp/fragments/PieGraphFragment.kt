package com.example.android.budgetapp.fragments


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_pie_graph.*
import com.example.android.budgetapp.R
import com.example.android.budgetapp.database.BudgetRepository
import com.example.android.budgetapp.database.BudgetViewModel
import com.example.android.budgetapp.database.entities.Budget
import com.example.android.budgetapp.database.entities.Category
import com.example.android.budgetapp.database.entities.Expenditure
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
import kotlin.math.exp

/**
 * The pie fragment that shows the categories in a different way
 */
class PieGraphFragment : Fragment() {

    val expenditureString:Array<String> = arrayOf("NONE", "NONE", "NONE")
    val floatValues = arrayOf(1.0f, 1.0f, 1.0f)

    private lateinit var binding : FragmentPieGraphBinding
    private lateinit var viewModel: BudgetViewModel

    private lateinit var categoryList : LiveData<List<Category>>
    private lateinit var expenditureList : LiveData<List<Expenditure>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(BudgetViewModel::class.java)

        viewModel.getCategories().observe(this, object: Observer<List<Category>> {
            override fun onChanged(t: List<Category>?) {
                Log.i("tags", "onChanged" + viewModel.getCategories().value.toString())
                if(t!=null){
                    categoryList = viewModel.getCategories()
                    Log.i("tags", "CategoryList" + categoryList.value.toString())

                }
                setupPieChart(categoryList)
            }
        })
        Log.i("tags", "ViewModel " + viewModel.getCategories().value?.size.toString())

        categoryList = viewModel.getCategories()

//        viewModel.getExpenditures().observe(this, object: Observer<List<Expenditure>> {
//            override fun onChanged(t: List<Expenditure>?) {
//                Log.i("tags", "Expenditure" + viewModel.getExpenditures().value.toString())
//                if(t!=null) {
//                    Log.i("tags", "Expenditure bool" + viewModel.getExpenditures().value.toString())
//                    expenditureList = viewModel.getExpenditures()
//                }
//            }
//        })
//
//        expenditureList = viewModel.getExpenditures()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pie_graph, container, false)
        binding.boolText.text = "false"
        //setupPieChart(expenditureString)

        binding.backButton.setOnClickListener { v: View ->
            v.findNavController().navigate((R.id.action_pieGraphFragment_to_calendarScreenFragment))
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStart() {
        setupPieChart(categoryList)
        Log.i("tags", "onStart")
        super.onStart()

    }

    /**
     * function to setup up the pie chart with the data
     */
    private fun setupPieChart(categoryL :LiveData<List<Category>>) {
        val pieEntries = ArrayList<PieEntry>(0)

        //val expenditures = ArrayList<Double?>(0)
        //Log.i("tags", "ViewModel " + viewModel.getCategories().value?.size.toString())


        if(categoryL.value?.size != null){
            binding.boolText.text = "true"


//   Expenditure block
//            if(expenditureList.value?.size != null){
//                val expendCategory = ArrayList<Long?>(0)
//
//                for (k in 0 until categoryL.value!!.size) {
//                    expendCategory.add(categoryL.value?.get(k)?.uid)
//                    expenditures.add(0.0)
//                }
//                for (n in 0 until expenditureList.value!!.size){
//                    for (k in 0 until categoryL.value!!.size) {
//                        if(expenditureList.value?.get(n)?.category_uid == expendCategory.get(k)){
//                            val price = expenditureList.value?.get(n)!!.price
//                            val totals = expenditures.get(k)?.plus(price)
//                            expenditures.set(k, totals)
//                        }
//                    }
//                }
//            }
          //  Log.i("tags", categoryL.value?.size.toString())


            for (i in 0 until categoryL.value!!.size) {                         //length of categories
                val entryFloat = categoryL.value?.get(i)?.type
                val categoryString = categoryL.value?.get(i)?.title
                val myEntry = PieEntry(entryFloat!!.toFloat() , categoryString)

                pieEntries.add(myEntry)

                Log.i("tags", "PieEntries$pieEntries")
            }
        }
        else{ // Default PieChart when no data is entered
            //Log.i("tags", categoryList.value?.size.toString())
            for (i in 0 until floatValues.size){
                val myEntry = PieEntry(floatValues[i], "None")

                pieEntries.add(myEntry)

                Log.i("tags", "PieEntries$pieEntries")
            }
        }
        val dataSet = PieDataSet(pieEntries, "Budget Totals")
        dataSet.setColors(JOYFUL_COLORS, 100)

        val myData = PieData(dataSet)
        myData.setValueFormatter(PercentFormatter())

        binding.pieChart.data = myData
        binding.pieChart.description.text = "HI"
        binding.pieChart.isDrawHoleEnabled= false
        binding.pieChart.setUsePercentValues(false)
        binding.pieChart.setEntryLabelColor(Color.BLACK)
        binding.pieChart.animateY(1000)

        binding.pieChart.invalidate()
    }

    companion object {
        fun newInstance(): PieGraphFragment = PieGraphFragment()
    }


}
