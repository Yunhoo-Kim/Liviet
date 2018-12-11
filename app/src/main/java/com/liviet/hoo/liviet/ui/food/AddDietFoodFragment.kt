package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentAddDietFoodBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
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
        binding.foodSelectList.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.addNewFoodBtn.setOnClickListener {
            UiUtli.hideSoftKeyboard(activity!!)
            UiUtli.addNewFragment(activity!!, AddNewDietFoodFragment.newInstance(Bundle()), R.id.container_main)
        }

        viewModel.loadFoodOnAdd()

        binding.searchFood.apply {
            //            this.setSearch
            isActivated = true
            onActionViewExpanded()
            setIconifiedByDefault(false)
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.filter.filter(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.filter.filter(query)
                    return false
                }
            })
        }

        binding.setLifecycleOwner(this)
        return binding.root
    }

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