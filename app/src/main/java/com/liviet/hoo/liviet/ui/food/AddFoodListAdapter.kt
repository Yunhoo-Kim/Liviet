package com.liviet.hoo.liviet.ui.food

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemAddFoodBinding
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.utils.extension.getParentActivity
import com.liviet.hoo.liviet.viewmodel.food.FoodItemVM


class AddFoodListAdapter: RecyclerView.Adapter<AddFoodListAdapter.ViewHolder>(){
    private lateinit var foodList: List<Food>
    lateinit var originFoodList: List<Food>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFoodListAdapter.ViewHolder {
        val binding: ItemAddFoodBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_add_food, parent,false)
        return AddFoodListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddFoodListAdapter.ViewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    fun updateSearchList(foodList: List<Food>){
        this.foodList = foodList
        notifyDataSetChanged()
    }

    fun updateFoodList(foodList : List<Food>){
        this.foodList = foodList
        this.originFoodList = foodList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if(::foodList .isInitialized) foodList.size else 0

    class ViewHolder(private val binding: ItemAddFoodBinding): RecyclerView.ViewHolder(binding.root){
        private val foodItemVm = FoodItemVM()

        fun bind(food: Food){
            foodItemVm.bind(food)
            binding.viewModel = foodItemVm
            binding.foodCard.setOnClickListener {
                val bundle = Bundle()
                bundle.putLong("foodId", food.id)
                UiUtli.hideSoftKeyboard(it, it.context)
                UiUtli.addNewFragment(it.getParentActivity()!!, AddDietFoodDetailFragment.newInstance(bundle), R.id.container_main)
            }
        }
    }
}