package com.bcit.budgetapp.mainFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import com.bcit.budgetapp.ViewModels.BudgetViewModel
import com.bcit.budgetapp.R
import com.bcit.budgetapp.Models.Bill
import com.bcit.budgetapp.Models.BillType
import com.bcit.budgetapp.Models.Transaction
import com.bcit.budgetapp.Models.TransactionCategory
import java.sql.Date

class ExpenseFragment : Fragment()
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        doTransactionStuff(view)

        view.findViewById<Spinner>(R.id.expense_spinner_freq).isEnabled = false

        view.findViewById<CheckBox>(R.id.checkBox_expense).setOnCheckedChangeListener { buttonView, isChecked ->
            view.findViewById<Spinner>(R.id.expense_spinner_freq).isEnabled = isChecked
        }

        var button = view.findViewById<Button>(R.id.button_expense_add).setOnClickListener{

            val datePicker = view.findViewById<DatePicker>(R.id.datePicker_expense)
            val editTextNumber = view.findViewById<EditText>(R.id.editTextNumberDecimal_expense)
            val spinner = view.findViewById<Spinner>(R.id.expense_spinner)
            val spinnerFreq = view.findViewById<Spinner>(R.id.expense_spinner_freq)
            val date = Date(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            val checkbox = view.findViewById<CheckBox>(R.id.checkBox_expense)
            var amount = 0.0

            if(editTextNumber.text.toString().isNotEmpty())
            {
                amount = editTextNumber.text.toString().toDouble()
            }

            if(checkbox.isChecked)
            {
                val bill = Bill(amount, date, (spinner.selectedItem as TransactionCategory), (spinnerFreq.selectedItem as BillType))
                budgetViewModel.budget.addBill(bill)
            }
            else
            {
                val transaction = Transaction(amount, date, (spinner.selectedItem as TransactionCategory))
                budgetViewModel.budget.addTransaction(transaction)
            }
        }
    }

    private fun doTransactionStuff(view: View)
    {
        val spinner = view.findViewById<Spinner>(R.id.expense_spinner)
        val adapter = ArrayAdapter<TransactionCategory>(view.context, android.R.layout.simple_spinner_item, TransactionCategory.values())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val spinnerFreq = view.findViewById<Spinner>(R.id.expense_spinner_freq)
        val adapterFreq = ArrayAdapter<BillType>(view.context, android.R.layout.simple_spinner_item, BillType.values())
        adapterFreq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFreq.adapter = adapterFreq
    }

}