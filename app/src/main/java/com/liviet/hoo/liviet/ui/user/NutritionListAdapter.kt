package com.liviet.hoo.liviet.ui.user

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemBodyInfoBinding
import com.liviet.hoo.liviet.databinding.ItemNutriInfoBinding
import com.liviet.hoo.liviet.model.nutrition.NutritionResult
import com.liviet.hoo.liviet.viewmodel.user.NutriItemVM


class NutritionListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var nutritionResultList: List<NutritionResult>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0 -> {
                val binding: ItemNutriInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nutri_info,
                        parent, false)
                return ViewHolder(binding)
            }
            else -> {
                val binding: ItemBodyInfoBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_body_info,
                        parent, false)
                return ViewHolder1(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0->{
                (holder as ViewHolder).bind(nutritionResultList[position])
            }
            else -> {
                (holder as ViewHolder1).bind(nutritionResultList[position])
            }
        }
    }

    fun updateNutritionResultList(nutritionResultList: List<NutritionResult>){
        this.nutritionResultList = nutritionResultList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if(nutritionResultList[position].ratio.equals("0", true)) 0 else 1
    }

    override fun getItemCount(): Int = if(::nutritionResultList.isInitialized) nutritionResultList.size else 0

    class ViewHolder(private val binding: ItemNutriInfoBinding): RecyclerView.ViewHolder(binding.root){
        private val nutritionVM = NutriItemVM()

        fun bind(nutritionResult: NutritionResult){
            nutritionVM.bind(nutritionResult)
            binding.viewModel = nutritionVM
        }
    }

    class ViewHolder1(private val binding: ItemBodyInfoBinding): RecyclerView.ViewHolder(binding.root){
        private val nutritionVM = NutriItemVM()

        fun bind(nutritionResult: NutritionResult){
            nutritionVM.bind(nutritionResult)
            binding.viewModel = nutritionVM
        }
    }
}