package com.bcit.budgetapp.Views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcit.budgetapp.Models.Bill
import com.bcit.budgetapp.R


class bill_recycler(private val mList: List<Bill>) :
    RecyclerView.Adapter<bill_recycler.ViewHolder>()
{

    // Holds the views
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        //val textView: TextView = itemView.findViewById(R.id.textView) 
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_bill_billfragment, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        var date = (mList[position].date.month + 1).toString() + "-" + mList[position].date.day.toString() + "-" + mList[position].date.year.toString()
        holder.itemView.findViewById<TextView>(R.id.budget_amount).text = mList[position].category.name
        holder.itemView.findViewById<TextView>(R.id.budget_due).text = mList[position].amount.toString()
        holder.itemView.findViewById<TextView>(R.id.budget_type).text = date
        holder.itemView.findViewById<TextView>(R.id.budget_freq).text = mList[position].billType.name
    }

    // return the number of the items in the list
    override fun getItemCount(): Int
    {
        return mList.size
    }

}