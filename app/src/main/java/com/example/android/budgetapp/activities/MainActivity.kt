package com.example.android.budgetapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.android.budgetapp.R
import com.example.android.budgetapp.fragments.PieGraphFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pie_graph.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        graph_button.setOnClickListener{
            val fragment: Fragment = PieGraphFragment.newInstance()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.container, fragment).commit()
        }
    }


}
