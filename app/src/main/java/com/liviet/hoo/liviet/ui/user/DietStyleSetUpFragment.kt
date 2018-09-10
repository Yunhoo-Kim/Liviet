package com.liviet.hoo.liviet.ui.user

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentDietStyleSetupBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import javax.inject.Inject


class DietStyleSetUpFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserSetUpViewModel
    private lateinit var binding: FragmentDietStyleSetupBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diet_style_setup, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserSetUpViewModel::class.java)
        binding.viewModel = viewModel
        binding.next.setOnClickListener {
            viewModel.printUserInfo()
        }

        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): DietStyleSetUpFragment {
            val frag = DietStyleSetUpFragment().apply {
                this.arguments = args
            }
            return frag
        }
    }

}