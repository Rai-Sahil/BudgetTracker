package com.bcit.budgetapp.mainFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcit.budgetapp.BudgetViewModel
import com.bcit.budgetapp.R
import com.bcit.budgetapp.bill_recycler


/**
 * A simple [Fragment] subclass.
 * Use the [BillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillFragment : Fragment()
{
    private val budgetViewModel: BudgetViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        var view = inflater.inflate(R.layout.fragment_bill, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView_billFragment)
        //insert data here
        //dummy data


        if (recyclerView != null)
        {
            recyclerView.adapter = bill_recycler(budgetViewModel.budget.transactions.filter { it.recurring })
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
        // Inflate the layout for this fragment
        return view

    }


}