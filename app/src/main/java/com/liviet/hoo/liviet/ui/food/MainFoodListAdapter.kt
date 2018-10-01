package com.liviet.hoo.liviet.ui.food

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemAdListBinding
import com.liviet.hoo.liviet.databinding.ItemFoodBinding
import com.liviet.hoo.liviet.databinding.ItemFoodMainBinding
import com.liviet.hoo.liviet.databinding.ItemPlusBinding
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.utils.extension.getParentActivity
import com.liviet.hoo.liviet.viewmodel.food.FoodItemVM


class MainFoodListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var foodList: List<Food>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0 -> {
                val binding: ItemFoodMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_food_main, parent, false)
                return ViewHolder(binding)
            }
            1 -> {
                val binding: ItemPlusBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_plus, parent, false)
                return PlusViewHolder(binding)
            }
            else -> {
                val binding: ItemAdListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ad_list, parent, false)
                return AdViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val name = foodList[position].name
        when(name){
            "plus"-> return 1
            "ad" -> return 2
            else -> return 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0 -> (holder as ViewHolder).bind(foodList[position])
            1 -> (holder as PlusViewHolder).bind(foodList[position])
            2 -> (holder as AdViewHolder).bind(foodList[position])
        }
    }

    fun updateFoodList(foodList : List<Food>){
        this.foodList = foodList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if(::foodList .isInitialized) foodList.size else 0

    class ViewHolder(private val binding: ItemFoodMainBinding): RecyclerView.ViewHolder(binding.root) {
        private val foodItemVm = FoodItemVM()

        fun bind(food: Food) {
            foodItemVm.bind(food)
            binding.viewModel = foodItemVm
//            binding.foodCard.setOnClickListener {
//            }
        }
    }
    class PlusViewHolder(private val binding: ItemPlusBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.plusCont.setOnClickListener {
                UiUtli.addNewFragment(it.getParentActivity()!!, AddDietFoodFragment.newInstance(Bundle()), R.id.container_main)
//                UiUtli.addNewFragment(it.getParentActivity()!!, AddNewDietFoodFragment.newInstance(Bundle()), R.id.container_main)
            }
//            binding.plusCont.setOnClickListener {
//                UiUtli.addNewFragment()
//            }
        }
    }
    class AdViewHolder(private val binding: ItemAdListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            val adRequest = AdRequest.Builder().build()
            val ad: AdView = binding.itemAd
            ad.loadAd(adRequest)
        }
    }
}