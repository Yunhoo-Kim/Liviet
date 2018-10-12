package com.liviet.hoo.liviet.ui.food

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.databinding.ItemAdListBinding
import com.liviet.hoo.liviet.databinding.ItemDietMainBinding
import com.liviet.hoo.liviet.databinding.ItemPlusBinding
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.utils.extension.getParentActivity
import com.liviet.hoo.liviet.viewmodel.food.DietItemVM
import com.liviet.hoo.liviet.viewmodel.food.DietVM


class DietListAdapter constructor(val dietVM: DietVM): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var dietList: List<Pair<Diet,Food>>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0 -> {
                val binding: ItemDietMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_diet_main, parent,false)
                return DietListAdapter.ViewHolder(binding)
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
        val name = dietList[position].second.name

        when(name){
            "plus"-> return 1
            "ad" -> return 2
            else -> return 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0 -> (holder as ViewHolder).bind(dietList[position].first, dietList[position].second, dietVM)
            1 -> (holder as PlusViewHolder).bind()
            2 -> (holder as AdViewHolder).bind()
        }
    }

    fun updateDietList(dietList: List<Pair<Diet, Food>>){
        this.dietList = dietList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if(::dietList.isInitialized) dietList.size else 0

    class ViewHolder(private val binding: ItemDietMainBinding): RecyclerView.ViewHolder(binding.root){
        private val dietItemVm = DietItemVM()

        fun bind(diet: Diet, food: Food, dietVM: DietVM){
            dietItemVm.bind(diet, food)
            binding.viewModel = dietItemVm

            binding.foodTextCont.setOnClickListener {
                val bundle = Bundle()
                bundle.putLong("dietId", diet.id)
                UiUtli.addNewFragment(it.getParentActivity()!!, DietDetailFragment.newInstance(bundle), R.id.container_main)
            }

            binding.foodCheck.setOnClickListener {
                val builder = AlertDialog.Builder(it.context)
                builder.setMessage(R.string.do_you_want_to_remove)
                        .setPositiveButton(R.string.remove) { _, _ ->
                            // delete
                            dietVM.deleteDiet(diet.id)
                        }
                        .setNegativeButton(R.string.cancel) { _,_ -> }
                builder.show()
            }
        }
    }

    class PlusViewHolder(private val binding: ItemPlusBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.plusCont.setOnClickListener {
                UiUtli.addNewFragment(it.getParentActivity()!!, AddDietFoodFragment.newInstance(Bundle()), R.id.container_main)
            }
        }
    }
    class AdViewHolder(private val binding: ItemAdListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val adRequest = AdRequest.Builder().build()
            val ad: AdView = binding.itemAd
            ad.loadAd(adRequest)
        }
    }
}