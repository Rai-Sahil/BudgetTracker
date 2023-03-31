package com.bcit.budgetapp.Views.MainFragments

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcit.budgetapp.Models.Transaction
import com.bcit.budgetapp.Models.TransactionCategory
import com.bcit.budgetapp.ViewModels.BudgetViewModel
import com.bcit.budgetapp.Views.bill_recycler
import com.bcit.budgetapp.Views.transaction_recycler
import com.bcit.budgetapp.databinding.FragmentBillBinding

enum class SortType
{
    MOST_RECENT,
    LEAST_RECENT
}
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
            sortTypes = TransactionCategory.values().filter { it > TransactionCategory.BILL }
            binding.recyclerViewBillFragment.adapter = bill_recycler(budgetViewModel.budget.bills)
        }
        else
        {
            otherButton = binding.buttonBillFragBills
            sortTypes = TransactionCategory.values().filter { it < TransactionCategory.BILL }
            binding.recyclerViewBillFragment.adapter = transaction_recycler(budgetViewModel.budget.transactions)
        }

        button.isEnabled = false
        button.background.alpha = 0
        otherButton.isEnabled = true
        otherButton.background.alpha = 255

        val filterAdapter = ArrayAdapter<TransactionCategory>(view.context, android.R.layout.simple_spinner_item, sortTypes)
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBillFilter.adapter = filterAdapter
    }

    public fun setupSortSpinner(view: View)
    {
        val sortAdapter = ArrayAdapter<SortType>(view.context, android.R.layout.simple_spinner_item, SortType.values())
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBillSort.adapter = sortAdapter
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

    public fun sortRecycler(transactionCategory: TransactionCategory)
    {
        println("hello")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        binding.buttonBillFragBills.setOnClickListener { buttonCLick(it) }
        binding.buttonBilFragTransactions.setOnClickListener { buttonCLick(it) }

        binding.buttonBillFragBills.performClick()
        setupSortSpinner(view)

        binding.recyclerViewBillFragment.adapter = bill_recycler(budgetViewModel.budget.bills)
        binding.recyclerViewBillFragment.layoutManager = LinearLayoutManager(activity)

        binding.spinnerBillSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(p0: AdapterView<*>?)
            {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                //(binding.recyclerViewBillFragment.adapter as bill_recycler).sort(binding.spinnerBillSort.getItemAtPosition(position) as SortType)
            }
        }

        binding.spinnerBillFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(p0: AdapterView<*>?)
            {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {

            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

}