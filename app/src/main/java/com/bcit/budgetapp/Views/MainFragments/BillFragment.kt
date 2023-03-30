package com.bcit.budgetapp.Views.MainFragments

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcit.budgetapp.Models.TransactionCategory
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

    public fun buttonCLick(view: View)
    {
        val button = view as Button
        val otherButton: Button
        val sortTypes: List<TransactionCategory>
        if(button.text == "Bills")
        {
            otherButton = binding.buttonBilFragTransactions
            sortTypes = TransactionCategory.values().filter { it > TransactionCategory.CLOTHING }
            binding.recyclerViewBillFragment.adapter = bill_recycler(budgetViewModel.budget.bills)
        }
        else
        {
            otherButton = binding.buttonBillFragBills
            sortTypes = TransactionCategory.values().filter { it < TransactionCategory.ELECTRICITY }
            binding.recyclerViewBillFragment.adapter = transaction_recycler(budgetViewModel.budget.transactions)
        }

        button.isEnabled = false
        button.background.alpha = 0
        otherButton.isEnabled = true
        otherButton.background.alpha = 255

        val sortAdapter = ArrayAdapter<TransactionCategory>(view.context, android.R.layout.simple_spinner_item, sortTypes)
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBillFilter.adapter = sortAdapter
    }

    public fun setupSortSpinner()
    {

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
        binding.buttonBillFragBills.setOnClickListener { buttonCLick(it) }
        binding.buttonBilFragTransactions.setOnClickListener { buttonCLick(it) }

        binding.buttonBillFragBills.performClick()
        setupSortSpinner()

        binding.recyclerViewBillFragment.adapter = bill_recycler(budgetViewModel.budget.bills)
        binding.recyclerViewBillFragment.layoutManager = LinearLayoutManager(activity)
        super.onViewCreated(view, savedInstanceState)
    }


}