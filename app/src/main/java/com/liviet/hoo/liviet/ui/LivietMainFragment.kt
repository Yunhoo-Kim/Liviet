package com.liviet.hoo.liviet.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentLivietMainBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.ui.user.UserSetUpActivity
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.food.DietVM
import javax.inject.Inject


class LivietMainFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DietVM
    private lateinit var binding: FragmentLivietMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_liviet_main, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DietVM::class.java)
        binding.viewModel = viewModel
        binding.dietList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.dateList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.initDate()

        binding.capture.setOnClickListener {
            UiUtli.makeSnackbar(it.rootView, R.string.not_ready_yet)
        }
        binding.settings.setOnClickListener {
            val intent = Intent(this.activity, UserSetUpActivity::class.java)
            startActivity(intent)
//            finish()
        }
//        viewModel.getDiet(Date())
//        viewModel.addFoods()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("Date", "OnResume")
        viewModel.getDiet(viewModel.cDate)
    }

    companion object {
        fun newInstance(args: Bundle?): LivietMainFragment {
            return LivietMainFragment().apply {
                this.arguments = args
            }
        }
    }

}