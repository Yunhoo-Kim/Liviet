package com.liviet.hoo.liviet.ui.user

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemNutriInfoBinding
import com.liviet.hoo.liviet.model.nutrition.NutritionResult
import com.liviet.hoo.liviet.viewmodel.user.NutriItemVM


class NutritionListAdapter: RecyclerView.Adapter<NutritionListAdapter.ViewHolder>() {
    private lateinit var nutritionResultList: List<NutritionResult>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemNutriInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nutri_info,
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nutritionResultList[position])
    }

    fun updateNutritionResultList(nutritionResultList: List<NutritionResult>){
        this.nutritionResultList = nutritionResultList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if(::nutritionResultList.isInitialized) nutritionResultList.size else 0

    class ViewHolder(private val binding: ItemNutriInfoBinding): RecyclerView.ViewHolder(binding.root){
        private val nutritionVM = NutriItemVM()

        fun bind(nutritionResult: NutritionResult){
            nutritionVM.bind(nutritionResult)
            binding.viewModel = nutritionVM
        }
    }
}