package com.liviet.hoo.liviet.ui.food

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemDateBinding
import com.liviet.hoo.liviet.utils.Utils
import com.liviet.hoo.liviet.viewmodel.food.DateItemVM
import com.liviet.hoo.liviet.viewmodel.food.DietVM
import java.util.*


class MainDateListAdapter constructor(val viewModel: DietVM): RecyclerView.Adapter<MainDateListAdapter.ViewHolder>() {
    private lateinit var dateList: List<Calendar>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainDateListAdapter.ViewHolder {
        val binding: ItemDateBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_date, parent,false)
        return MainDateListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainDateListAdapter.ViewHolder, position: Int) {
        holder.bind(dateList[position], viewModel)
    }

    fun initDateList(dateList : List<Calendar>){
        this.dateList = dateList
        notifyDataSetChanged()
    }
    fun updateDateList(dateList: List<Calendar>, l1: Int, l2: Int){
        this.dateList = dateList
        notifyItemChanged(l1)
        notifyItemChanged(l2)
    }

    override fun getItemCount(): Int = if(::dateList.isInitialized) dateList.size else 0

    class ViewHolder(private val binding: ItemDateBinding): RecyclerView.ViewHolder(binding.root){
        private val dateItemVm = DateItemVM()

        fun bind(calendar: Calendar, viewModel: DietVM){
            val date = Utils.makeCalendarToDate(calendar)
            binding.viewModel = dateItemVm
            dateItemVm.bind(date)

            if(date.date == viewModel.tDate.date){
                binding.dayOfWeek.setTextColor(binding.dayOfWeek.resources.getColor(R.color.colorPrimary))
            }else{
                binding.dayOfWeek.setTextColor(binding.dayOfWeek.resources.getColor(R.color.black))
            }

            if(date == viewModel.cDate){
                binding.day.setBackgroundResource(R.drawable.circle_background)
                binding.day.setTextColor(binding.day.resources.getColor(R.color.white))
            }else{
                binding.day.setBackgroundResource(R.color.white)
                binding.day.setTextColor(binding.day.resources.getColor(R.color.black))
            }

            binding.dateCont.setOnClickListener {
                val pDate = viewModel.cDate
                viewModel.cDate = Utils.makeCalendarToDate(calendar)
                viewModel.updateDate(pDate)
//                viewModel.updateDate()
                viewModel.getDiets()
            }
        }
    }
}