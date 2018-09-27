package com.liviet.hoo.liviet.ui.food

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemDateBinding
import com.liviet.hoo.liviet.databinding.ItemFoodBinding
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.viewmodel.food.DateItemVM
import com.liviet.hoo.liviet.viewmodel.food.FoodItemVM
import kotlinx.android.synthetic.main.item_food.view.*
import java.util.*


class MainDateListAdapter: RecyclerView.Adapter<MainDateListAdapter.ViewHolder>() {
    private lateinit var dateList: List<Date>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainDateListAdapter.ViewHolder {
        val binding: ItemDateBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_date, parent,false)
        return MainDateListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainDateListAdapter.ViewHolder, position: Int) {
        holder.bind(dateList[position])
    }

    fun updateDateList(dateList : List<Date>){
        this.dateList = dateList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if(::dateList.isInitialized) dateList.size else 0

    class ViewHolder(private val binding: ItemDateBinding): RecyclerView.ViewHolder(binding.root){
        private val dateItemVm = DateItemVM()

        fun bind(date: Date){
            dateItemVm.bind(date)
            binding.viewModel = dateItemVm
//            binding.foodCard.setOnClickListener {
//                if(food.selected) {
//                    food.selected = false
//                    it.setBackgroundResource(R.color.white)
//                    it.food_name.setTextColor(it.resources.getColor(R.color.colorPrimary))
//                }
//                else {
//                    food.selected = true
//                    it.setBackgroundResource(R.color.colorPrimary)
//                    it.food_name.setTextColor(it.resources.getColor(R.color.white))
//                }
//            }
        }
    }
}