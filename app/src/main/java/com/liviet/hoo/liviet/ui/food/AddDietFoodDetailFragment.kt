package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodBinding
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodDetailBinding
import com.liviet.hoo.liviet.databinding.FragmentSelectFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.ui.LivietMainFragment
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.food.FoodItemVM
import com.liviet.hoo.liviet.viewmodel.food.FoodVM
import javax.inject.Inject


class AddDietFoodDetailFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var foodId: Long = 0
    private lateinit var viewModel: FoodVM
    private lateinit var binding: FragmentAddDietFoodDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_diet_food_detail, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(FoodVM::class.java)
        binding.viewModel = viewModel

        foodId = arguments!!.getLong("foodId")

        var itemVM = FoodItemVM()
        itemVM.bind(viewModel.getFood(foodId).blockingFirst())
        binding.itemViewModel = itemVM

        itemVM.amountText.observe(this, Observer {
            Log.d("Amount text", it)
            // TODO: update nutrition info according to amount
        })


        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): AddDietFoodDetailFragment {
            return AddDietFoodDetailFragment().apply {
                this.arguments = args
            }
        }
    }
}