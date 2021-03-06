package com.liviet.hoo.liviet.ui.user

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.MobileAds
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseActivity
import com.liviet.hoo.liviet.databinding.ActivitySetupBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import javax.inject.Inject


class UserSetUpActivity: BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserSetUpViewModel
    private lateinit var binding: ActivitySetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setup)

        setContentView(binding.root)

        MobileAds.initialize(this,
                getString(R.string.admob))

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserSetUpViewModel::class.java)
        viewModel.updateUserInfo()

        binding.viewModel = viewModel
        binding.next.setOnClickListener {
            viewModel.height.value = binding.heightSpinner.selectedItem as String
            viewModel.weight.value = binding.weightSpinner.selectedItem as String
            viewModel.age.value = binding.ageSpinner.selectedItem as String
            viewModel.sex.value = binding.male.isChecked

            UiUtli.addNewFragment(this, LifeStyleSetUpFragment.newInstance(Bundle()), R.id.container_main)
        }
    }

    override fun onResume() {
        super.onResume()
        if(::viewModel.isInitialized)
            if(viewModel.sex.value!!) binding.male.isChecked = true else binding.female.isChecked = true
    }

}