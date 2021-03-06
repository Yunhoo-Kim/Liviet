package com.liviet.hoo.liviet.ui.user

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentSetupResultBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.ui.MainActivity
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import javax.inject.Inject


class SetUpNutritionResultFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserSetUpViewModel
    private lateinit var binding: FragmentSetupResultBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setup_result, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(UserSetUpViewModel::class.java)
        binding.viewModel = viewModel
        binding.nutriInfoList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.bodyInfoList.layoutManager = GridLayoutManager(this.context, 2)
        viewModel.calcUserNutritionResult()

        binding.dietStyleNext.setOnClickListener {
            viewModel.saveUserInfo()
//            UiUtli.addNewFragment(this.activity!!, SelectFoodFragment.newInstance(Bundle()), R.id.container_main)

            val intent = Intent(this.activity, MainActivity::class.java)
            startActivity(intent)
            this.activity!!.finish()
        }
        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): SetUpNutritionResultFragment {
            val frag = SetUpNutritionResultFragment().apply {
                this.arguments = args
            }
            return frag
        }
    }

}