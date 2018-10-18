package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.MainThread
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodBinding
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodDetailBinding
import com.liviet.hoo.liviet.databinding.FragmentDietDetailBinding
import com.liviet.hoo.liviet.databinding.FragmentSelectFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.ui.LivietMainFragment
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.utils.Utils
import com.liviet.hoo.liviet.viewmodel.food.DietItemVM
import com.liviet.hoo.liviet.viewmodel.food.DietVM
import com.liviet.hoo.liviet.viewmodel.food.FoodItemVM
import com.liviet.hoo.liviet.viewmodel.food.FoodVM
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject


class DietDetailFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var dietId: Long = 0
    private lateinit var viewModel: DietVM

    private val itemVM: DietItemVM by lazy {
        DietItemVM()
    }

    private lateinit var binding: FragmentDietDetailBinding
    private lateinit var food: Food
    private lateinit var diet: Diet

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diet_detail, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DietVM::class.java)

        dietId = arguments!!.getLong("dietId")
        diet = viewModel.getDietById(dietId).blockingFirst()
        food = viewModel.getFoodById(diet.foodId).blockingFirst()

        itemVM.bind(diet, food)
        binding.viewModel = itemVM

        binding.saveDiet.setOnClickListener {
            fragmentManager!!.popBackStack() // close this fragment
        }

        binding.removeDiet.setOnClickListener {
            viewModel.deleteDiet(dietId)
            viewModel.getDiets()
            fragmentManager!!.popBackStack() // close this fragment
        }

        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): DietDetailFragment {
            return DietDetailFragment().apply {
                this.arguments = args
            }
        }
    }
}