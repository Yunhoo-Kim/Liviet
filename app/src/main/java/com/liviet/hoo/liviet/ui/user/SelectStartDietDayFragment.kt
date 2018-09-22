package com.liviet.hoo.liviet.ui.user

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentStartDietDayBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import javax.inject.Inject

class SelectStartDietDayFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserSetUpViewModel
    private lateinit var binding: FragmentStartDietDayBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_diet_day, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(UserSetUpViewModel::class.java)
        binding.viewModel = viewModel
        binding.dietStyleNext.setOnClickListener {
            if(binding.dayGroup.checkedRadioButtonId == -1){
                Snackbar.make(this.view!!, getString(R.string.select_one_of_them), Snackbar.LENGTH_SHORT).show()
            }
            else{
                UiUtli.addNewFragment(this.activity!!, SetUpNutritionResultFragment.newInstance(Bundle()), R.id.container_main)
            }
        }

        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): SelectStartDietDayFragment {
            val fragment = SelectStartDietDayFragment().apply {
                this.arguments = args
            }
            return fragment
        }
    }
}