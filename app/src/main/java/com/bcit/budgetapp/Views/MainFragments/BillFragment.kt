package com.bcit.budgetapp.Views.MainFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcit.budgetapp.ViewModels.BudgetViewModel
import com.bcit.budgetapp.R
import com.bcit.budgetapp.Views.bill_recycler
import com.bcit.budgetapp.Views.transaction_recycler


/**
 * A simple [Fragment] subclass.
 * Use the [BillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillFragment : Fragment()
{
    private val budgetViewModel: BudgetViewModel by activityViewModels()
    private lateinit var recyclerView : RecyclerView
    private lateinit var billButton: Button
    private lateinit var transactionButton: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    public fun billButtonClick(view: View)
    {
        println("hi")
        view.isEnabled = false
        view.setBackgroundColor(Color.GRAY)
        transactionButton.isEnabled = true
        transactionButton.setBackgroundColor(Color.WHITE)

        recyclerView.adapter = bill_recycler(budgetViewModel.budget.bills)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    public fun transactionButtonClick(view: View)
    {
        println("hello")
        view.isEnabled = false
        view.setBackgroundColor(Color.GRAY)
        billButton.isEnabled = true
        billButton.setBackgroundColor(Color.WHITE)

        recyclerView.adapter = transaction_recycler(budgetViewModel.budget.transactions)
        recyclerView.layoutManager = LinearLayoutManager(activity)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        var view = inflater.inflate(R.layout.fragment_bill, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_billFragment)
        billButton = view.findViewById<Button>(R.id.button_billFrag_bills)
        transactionButton = view.findViewById<Button>(R.id.button_bilFrag_transactions)

        billButton.setOnClickListener { billButtonClick(it) }
        transactionButton.setOnClickListener { transactionButtonClick(it) }

        billButton.isEnabled = false
        billButton.setBackgroundColor(Color.GRAY)

        recyclerView.adapter = bill_recycler(budgetViewModel.budget.bills)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Inflate the layout for this fragment
        return view

    }

}