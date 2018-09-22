package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentSelectFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.viewmodel.food.FoodVM
import javax.inject.Inject


class SelectFoodFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FoodVM
    private lateinit var binding: FragmentSelectFoodBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_food, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(FoodVM::class.java)
        binding.viewModel = viewModel
        binding.foodSelectList.layoutManager = GridLayoutManager(this.context, 2)

        viewModel.getFoods()

        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): SelectFoodFragment {
            return SelectFoodFragment().apply {
                this.arguments = args
            }
        }
    }

}