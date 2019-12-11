package com.example.android.budgetapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.budgetapp.R
import com.example.android.budgetapp.database.entities.Category

class CategoriesAdapter(): RecyclerView.Adapter<CategoriesAdapter.CatHolder>() {
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): CategoriesAdapter.ViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.categories_row, parent, false)
//        return ViewHolder(view)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val view = (LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_row,parent, false))
        return CatHolder(view)
    }


    private var categories:
            List<Category>? = null

    class CatHolder(val catView: View) :
        RecyclerView.ViewHolder(catView)

    fun setCategories(cats: List<Category>) {
        categories = cats

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categories?.size ?: 0
    }



    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val cat = categories?.get(position)
        //holder.category_and_amount.text = ${cat.}

        val categoryText = holder.catView.findViewById<TextView>(R.id.category_text)

        if (categories != null) {
            val cat = categories?.get(position)
                ?: Category(
                    0,
                    false,
                    "",
                    0
                )
            categoryText.text = "${cat.title} $${cat.type}"

        } else {
            categoryText.text = ""
        }

        //holder.category_and_amount.text = categories[position].title.toString() + " $" + categories[position].type.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_and_amount: TextView = itemView.findViewById(R.id.category_text)
    }

}
