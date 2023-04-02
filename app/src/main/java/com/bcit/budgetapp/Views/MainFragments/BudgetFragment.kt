package com.bcit.budgetapp.Views.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcit.budgetapp.Models.Budget
import com.bcit.budgetapp.Models.TransactionCategory
import com.bcit.budgetapp.R
import com.bcit.budgetapp.ViewModels.BudgetViewModel
import com.bcit.budgetapp.Views.BudgetAdapter
import com.bcit.budgetapp.databinding.FragmentBudgetBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BudgetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BudgetFragment : Fragment()
{

    private val budgetViewModel: BudgetViewModel by activityViewModels()
    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!
    private var budgetAdapter: BudgetAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        setupRecyclerView(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup the Spinner
        val spinner = view.findViewById<Spinner>(R.id.spinner_budgetFragment_cat)
        val adapter = ArrayAdapter<TransactionCategory>(requireContext(), android.R.layout.simple_spinner_item, TransactionCategory.values().filter { it <= TransactionCategory.BILLS && it != TransactionCategory.NONE })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        binding.buttonBudgetFragmentUpdate.setOnClickListener { view -> updateBudgetButtonClick(view) }
        val totalBudgetAmount = view.findViewById<TextView>(R.id.textView_budgetFragment_amount)

        var budgetObserver = Observer<ArrayList<Budget>> { budgetList ->
            budgetAdapter?.update(budgetList)
            totalBudgetAmount.text = budgetViewModel.getTotalBudget().toString()
        }

        budgetViewModel.budgets.observe(viewLifecycleOwner, budgetObserver)


    }

    private fun updateBudgetButtonClick(view: View){
        var amount = 0.0
        var category = TransactionCategory.NONE
        if(binding.editTextBudgetFragmentAmount.text.toString().isNotEmpty()){
            amount = binding.editTextBudgetFragmentAmount.text.toString().toDouble()
        }
        if(binding.spinnerBudgetFragmentCat.selectedItem != TransactionCategory.NONE){
            category = binding.spinnerBudgetFragmentCat.selectedItem as TransactionCategory
        }
        val budget = Budget(userID, amount, category)
        budgetViewModel.addOrUpdateBudget(budget)
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_budgetFragment_catList)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        val data = budgetViewModel.budgets
        budgetAdapter = BudgetAdapter(budgetViewModel.budgets?.value)
        recyclerView.adapter = budgetAdapter
    }


}