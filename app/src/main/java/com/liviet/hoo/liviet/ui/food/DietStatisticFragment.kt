package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat.getColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseFragment
import com.liviet.hoo.liviet.databinding.FragmentStatisticBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.viewmodel.food.DietVM
import javax.inject.Inject


class DietStatisticFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DietVM
    private lateinit var binding: FragmentStatisticBinding

    private val labels = mutableListOf("탄수화물", "단백질", "지방")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistic, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DietVM::class.java)
        binding.viewModel = viewModel
        //
        binding.setLifecycleOwner(this)

        initViewData()
        return binding.root
    }

    private fun initViewData(){
//        binding.radar.setDrawWeb(true)
        binding.radar.description.isEnabled = false
//        binding.radar.webLineWidthInner = 1f
//        binding.radar.webAlpha = 100
        binding.radar.xAxis.apply {
            this.xOffset = 0f
            this.yOffset = 0f
            this.valueFormatter = IAxisValueFormatter { value, _ ->
                return@IAxisValueFormatter labels[value.toInt() % labels.size]
            }
        }
        binding.radar.yAxis.apply {
            this.setDrawLabels(false)
        }
        binding.radar.animate()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser && ::binding.isInitialized){
            viewModel.loadCharData(context!!)
            binding.radar.animate()
        }
    }

    companion object {
        fun newInstance(args: Bundle?): DietStatisticFragment {
            return DietStatisticFragment().apply {
                this.arguments = args
            }
        }
    }
}