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
import com.liviet.hoo.liviet.databinding.FragmentDietStyleSetupBinding
import com.liviet.hoo.liviet.databinding.FragmentLifeStyleSetupBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import javax.inject.Inject


class LifeStyleSetUpFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserSetUpViewModel
    private lateinit var binding: FragmentLifeStyleSetupBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_life_style_setup, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserSetUpViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifeStyleNext.setOnClickListener {

            if(binding.eGroup.checkedRadioButtonId == -1) {
                Snackbar.make(this.view!!, getString(R.string.select_one_of_them), Snackbar.LENGTH_SHORT).show()
            }
            else {
                viewModel.life_type.value = if (binding.eNone.isChecked) 1.2 else if (binding.eLow.isChecked) 1.3 else if (binding.eNormal.isChecked) 1.5 else if (binding.eHigh.isChecked) 1.75 else 0.0
                UiUtli.addNewFragment(this.activity!!, SetUpNutritionResultFragment.newInstance(Bundle()), R.id.container_main)
            }
        }
        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): LifeStyleSetUpFragment {
            val frag = LifeStyleSetUpFragment().apply {
                this.arguments = args
            }
            return frag
        }
    }

}