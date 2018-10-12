package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodBinding
import com.liviet.hoo.liviet.databinding.FragmentSelectFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.ui.LivietMainFragment
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.food.DietVM
import com.liviet.hoo.liviet.viewmodel.food.FoodVM
import javax.inject.Inject


class AddDietFoodFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FoodVM
    private lateinit var dietViewModel: DietVM
    private lateinit var binding: FragmentAddDietFoodBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_diet_food, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(FoodVM::class.java)
        dietViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DietVM::class.java)
        binding.viewModel = viewModel
        binding.foodSelectList.layoutManager = GridLayoutManager(this.context, 2)
        binding.foodSelectList.isNestedScrollingEnabled = false

        binding.addNewFoodBtn.setOnClickListener {
            UiUtli.addNewFragment(activity!!, AddNewDietFoodFragment.newInstance(Bundle()), R.id.container_main)
        }

        viewModel.loadFoodOnAdd()


        return binding.root
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//    }

    override fun onDestroy() {
        super.onDestroy()
        dietViewModel.getDiets()
    }

    companion object {
        fun newInstance(args: Bundle?): AddDietFoodFragment {
            return AddDietFoodFragment().apply {
                this.arguments = args
            }
        }
    }
}