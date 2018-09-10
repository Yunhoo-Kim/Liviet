package com.liviet.hoo.liviet.ui.user

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentBodySetupBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import javax.inject.Inject


class BodySetUpFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserSetUpViewModel
    private lateinit var binding: FragmentBodySetupBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_body_setup, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserSetUpViewModel::class.java)
        binding.viewModel = viewModel
        binding.next.setOnClickListener {
            viewModel.height.value = binding.heightSpinner.selectedItem as String
            viewModel.weight.value = binding.weightSpinner.selectedItem as String
            viewModel.age.value = binding.ageSpinner.selectedItem as String
            viewModel.sex.value = binding.male.isChecked

            Log.d("Select NextButton","age item ${binding.ageSpinner.selectedItem}")
            Log.d("Select NextButton","height item ${binding.heightSpinner.selectedItem}")
            Log.d("Select NextButton","weight item ${binding.weightSpinner.selectedItem}")
            Log.d("Select NextButton","Is Man ${binding.male.isChecked}")

            UiUtli.addNewFragment(activity!!, DietStyleSetUpFragment.newInstance(Bundle()), R.id.container_main)
        }

        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): BodySetUpFragment {
            val frag = BodySetUpFragment().apply {
                this.arguments = args
            }
            return frag
        }
    }

}