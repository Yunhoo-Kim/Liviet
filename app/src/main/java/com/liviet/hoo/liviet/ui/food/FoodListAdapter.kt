package com.liviet.hoo.liviet.ui.food

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemFoodBinding
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.viewmodel.food.FoodItemVM
import kotlinx.android.synthetic.main.item_food.view.*


class FoodListAdapter: RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {
    private lateinit var foodList: List<Food>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListAdapter.ViewHolder {
        val binding: ItemFoodBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_food, parent,false)
        return FoodListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListAdapter.ViewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    fun updateFoodList(foodList : List<Food>){
        this.foodList = foodList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if(::foodList .isInitialized) foodList.size else 0

    class ViewHolder(private val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root){
        private val foodItemVm = FoodItemVM()

        fun bind(food: Food){
            foodItemVm.bind(food)
            binding.viewModel = foodItemVm
            binding.foodCard.setOnClickListener {
                if(food.selected) {
                    food.selected = false
                    it.setBackgroundResource(R.color.white)
                    it.food_name.setTextColor(it.resources.getColor(R.color.colorPrimary))
                }
                else {
                    food.selected = true
                    it.setBackgroundResource(R.color.colorPrimary)
                    it.food_name.setTextColor(it.resources.getColor(R.color.white))
                }
            }
        }
    }
}