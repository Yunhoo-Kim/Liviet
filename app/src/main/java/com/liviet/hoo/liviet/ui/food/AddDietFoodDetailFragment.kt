package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodBinding
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodDetailBinding
import com.liviet.hoo.liviet.databinding.FragmentSelectFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.ui.LivietMainFragment
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.utils.Utils
import com.liviet.hoo.liviet.viewmodel.food.DietVM
import com.liviet.hoo.liviet.viewmodel.food.FoodItemVM
import com.liviet.hoo.liviet.viewmodel.food.FoodVM
import java.util.*
import javax.inject.Inject


class AddDietFoodDetailFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var foodId: Long = 0
    private lateinit var viewModel: DietVM
    private val itemVM: FoodItemVM by lazy {
        FoodItemVM()
    }
    private lateinit var binding: FragmentAddDietFoodDetailBinding
    private lateinit var food: Food

    private var defCarbon: Double = 0.0
    private var defProtein: Double = 0.0
    private var defFat: Double = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val adRequest = AdRequest.Builder().build()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_diet_food_detail, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DietVM::class.java)
        binding.viewModel = viewModel

        foodId = arguments!!.getLong("foodId")

        food = viewModel.getFoodById(foodId).blockingFirst()

        itemVM.bind(food)

        binding.itemViewModel = itemVM
//        binding.adView.loadAd(adRequest)

        defCarbon = food.carbonHydrate / food.amount
        defProtein = food.protein / food.amount
        defFat = food.fat / food.amount

        binding.saveDiet.setOnClickListener {
            val amount = binding.foodAmountInput.text

            if(amount.isEmpty()){
                UiUtli.makeSnackbar(it, R.string.plz_fill_info)
                return@setOnClickListener
            }

            val diet = Diet(amount = amount.toString().toInt(), date= Utils.makeCalendarToDate(Calendar.getInstance()), foodId = food.id)
            viewModel.insertDiet(diet)

            fragmentManager!!.popBackStack() // close this fragment
            fragmentManager!!.popBackStack() // close select food fragment
        }

        binding.foodAmountInput.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isEmpty()) {
                    return
                }

                binding.carbonHydrateAmount.text = "${String.format("%.2f", defCarbon * p0.toString().toInt())}g"
                binding.proteinAmount.text = "${String.format("%.2f", defProtein * p0.toString().toInt())}g"
                binding.fatAmount.text = "${String.format("%.2f", defFat * p0.toString().toInt())}g"
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
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