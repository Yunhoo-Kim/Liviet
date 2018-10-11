package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.MediaStore
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
import com.liviet.hoo.liviet.databinding.FragmentSelectFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.model.nutrition.Food
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
    private lateinit var food: Food
    private var defCarbon: Float = 0.0f
    private var defProtein: Float = 0.0f
    private var defFat: Float = 0.0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_diet_food_detail, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(FoodVM::class.java)
        binding.viewModel = viewModel

        foodId = arguments!!.getLong("foodId")

        var itemVM = FoodItemVM()
        food = viewModel.getFood(foodId).blockingFirst()
        itemVM.bind(food)
        binding.itemViewModel = itemVM

        defCarbon = food.carbon_hydrate / food.amount.toFloat()
        defProtein = food.protein / food.amount.toFloat()
        defFat = food.fat / food.amount.toFloat()

        binding.saveDiet.setOnClickListener {
            // save diet info to db and close fragment
        }

        binding.foodAmountInput.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isEmpty()) {
                    return
                }

                binding.carbonHydrateAmount.text = "${defCarbon * p0.toString().toInt()}${food.measure}"
                binding.proteinAmount.text = "${defProtein * p0.toString().toInt()}${food.measure}"
                binding.fatAmount.text = "${defFat * p0.toString().toInt()}${food.measure}"

                Log.d("Amount Input", p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("Amount Input", p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("Amount Input", p0.toString())
            }

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