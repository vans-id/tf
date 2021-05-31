package com.djevannn.tastyfood.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.djevannn.core.data.Resource
import com.djevannn.core.ui.FoodAdapter
import com.djevannn.tastyfood.databinding.FragmentHomeBinding
import com.djevannn.tastyfood.ui.detail.DetailFoodActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val foodAdapter = FoodAdapter()
            foodAdapter.onItemClick = { selectedData ->
                val intent =
                    Intent(activity, DetailFoodActivity::class.java)
                intent.putExtra(
                    DetailFoodActivity.EXTRA_FOOD,
                    selectedData
                )
                startActivity(intent)
            }

            viewModel.setQuery("steak")
            viewModel.foods.observe(viewLifecycleOwner, { foods ->
                if (foods != null) {
                    when (foods) {
                        is Resource.Loading -> binding.progressBar.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            foodAdapter.setData(foods.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            AlertDialog.Builder(context)
                                .setTitle("Attention!")
                                .setMessage(foods.message)
                                .setPositiveButton("Ok") { dialog, _ ->
                                    dialog.cancel()
                                }.create().apply {
                                    setCanceledOnTouchOutside(
                                        false
                                    )
                                    show()
                                }
                        }
                    }
                }
            })

            with(binding.rvFoods) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = foodAdapter
            }

        }
    }

    fun searchRecipe(query: String) {
        val trimmed = query.trim().toLowerCase(Locale.ROOT)
        viewModel.setQuery(trimmed)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}