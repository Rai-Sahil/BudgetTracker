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
import com.bcit.budgetapp.databinding.FragmentBillBinding
import com.bcit.budgetapp.databinding.FragmentExpenseBinding


/**
 * A simple [Fragment] subclass.
 * Use the [BillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillFragment : Fragment()
{
    private val budgetViewModel: BudgetViewModel by activityViewModels()
    private var _binding: FragmentBillBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    public fun billButtonClick(view: View)
    {
        view.isEnabled = false
        view.background.alpha = 0
        binding.buttonBilFragTransactions.isEnabled = true
        binding.buttonBilFragTransactions.background.alpha = 255
        binding.recyclerViewBillFragment.adapter = bill_recycler(budgetViewModel.budget.bills)
        binding.recyclerViewBillFragment.layoutManager = LinearLayoutManager(activity)
    }

    public fun transactionButtonClick(view: View)
    {
        view.isEnabled = false
        view.background.alpha = 0
        binding.buttonBillFragBills.isEnabled = true
        binding.buttonBillFragBills.background.alpha = 255
        binding.recyclerViewBillFragment.adapter = transaction_recycler(budgetViewModel.budget.transactions)
        binding.recyclerViewBillFragment.layoutManager = LinearLayoutManager(activity)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentBillBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        binding.buttonBillFragBills.setOnClickListener { billButtonClick(it) }
        binding.buttonBilFragTransactions.setOnClickListener { transactionButtonClick(it) }
        binding.buttonBillFragBills.isEnabled = false
        binding.buttonBillFragBills.background.alpha = 0

        binding.recyclerViewBillFragment.adapter = bill_recycler(budgetViewModel.budget.bills)
        binding.recyclerViewBillFragment.layoutManager = LinearLayoutManager(activity)
        super.onViewCreated(view, savedInstanceState)
    }
}