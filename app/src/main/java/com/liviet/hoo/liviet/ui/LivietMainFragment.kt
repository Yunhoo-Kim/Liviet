package com.liviet.hoo.liviet.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentLivietMainBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.viewmodel.LivietMainVM
import javax.inject.Inject


class LivietMainFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LivietMainVM
    private lateinit var binding: FragmentLivietMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_liviet_main, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(LivietMainVM::class.java)
        binding.viewModel = viewModel
        binding.foodList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.dateList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.initDate()
        viewModel.addFoods()

        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): LivietMainFragment {
            return LivietMainFragment().apply {
                this.arguments = args
            }
        }
    }

}