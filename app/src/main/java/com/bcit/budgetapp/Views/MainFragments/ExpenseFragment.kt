package com.bcit.budgetapp.Views.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import com.bcit.budgetapp.Models.Bill
import com.bcit.budgetapp.Models.BillType
import com.bcit.budgetapp.ViewModels.BudgetViewModel
import com.bcit.budgetapp.R
import com.bcit.budgetapp.Models.Transaction
import com.bcit.budgetapp.Models.TransactionCategory
import com.bcit.budgetapp.databinding.FragmentExpenseBinding
import java.sql.Date

val userID: String = "sahilrai"

class ExpenseFragment : Fragment()
{
    private val budgetViewModel: BudgetViewModel by activityViewModels()
    private var _binding: FragmentExpenseBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setupSpinners(view)

        binding.expenseSpinnerFreq.isEnabled = false

        binding.checkBoxExpense.setOnCheckedChangeListener { view, isChecked -> onChecked(view, isChecked)}
        binding.buttonExpenseAdd.setOnClickListener{it -> addExpenseButtonClick(it) }
    }

    private fun onChecked(view: View, isChecked: Boolean)
    {
        binding.expenseSpinnerFreq.isEnabled = isChecked

        if(isChecked)
        {
            val adapter = ArrayAdapter<TransactionCategory>(view.context, android.R.layout.simple_spinner_item, TransactionCategory.values().filter { it > TransactionCategory.BILLS })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.expenseSpinner.adapter = adapter
        }
        else
        {
            val adapter = ArrayAdapter<TransactionCategory>(view.context, android.R.layout.simple_spinner_item, TransactionCategory.values().filter { it < TransactionCategory.BILLS })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.expenseSpinner.adapter = adapter
        }
    }

    private fun addExpenseButtonClick(view: View)
    {
        var amount = 0.0
        val date = Date(binding.datePickerExpense.year, binding.datePickerExpense.month, binding.datePickerExpense.dayOfMonth)

        if(binding.editTextNumberDecimalExpense.text.toString().isNotEmpty())
        {
            amount = binding.editTextNumberDecimalExpense.text.toString().toDouble()
        }

        if(binding.checkBoxExpense.isChecked)
        {
            val bill = Bill(userID, amount, date, (binding.expenseSpinner.selectedItem as TransactionCategory), (binding.expenseSpinnerFreq.selectedItem as BillType))
            budgetViewModel.budget.addBill(bill)
        }
        else
        {
            val transaction = Transaction(userID, amount, date, (binding.expenseSpinner.selectedItem as TransactionCategory))
            budgetViewModel.budget.addTransaction(transaction)
        }

    }

    private fun setupSpinners(view: View)
    {
        val adapter = ArrayAdapter<TransactionCategory>(view.context, android.R.layout.simple_spinner_item, TransactionCategory.values().filter { it < TransactionCategory.BILLS })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.expenseSpinner.adapter = adapter

        val adapterFreq = ArrayAdapter<BillType>(view.context, android.R.layout.simple_spinner_item, BillType.values())
        adapterFreq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.expenseSpinnerFreq.adapter = adapterFreq
    }


}