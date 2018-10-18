package com.liviet.hoo.liviet.ui.food

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis
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
    private lateinit var labels: MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistic, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DietVM::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(activity)

        labels = mutableListOf(
                getString(R.string.kcal),
                getString(R.string.carbohydrate),
                getString(R.string.protein),
                getString(R.string.fat))

        viewModel.loadCharData(context!!)

        initViewData()
        return binding.root
    }

    private fun initViewData(){
        initTodayStatistic()
        initWeeklyStatistic()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser && ::binding.isInitialized){
            viewModel.loadCharData(context!!)
            binding.horizontalGraph.apply {
                invalidate()
                animateY(700)
            }
            binding.weeklyChart.xAxis.apply {
                valueFormatter = IAxisValueFormatter { value, _ ->
                    return@IAxisValueFormatter viewModel.weeklyDateData[value.toInt() % labels.size]
                }
            }

            binding.weeklyChart.apply {
                invalidate()
                animateXY(700, 700)
            }
        }
    }

    private fun initWeeklyStatistic(){
        binding.weeklyChart.apply {
            description.isEnabled = false
            isClickable = false
            setTouchEnabled(false)
            setScaleEnabled(false)
            legend.isEnabled = false
            setBorderWidth(2f)

        }

        binding.weeklyChart.xAxis.apply {
//            setDrawAxisLine(false)
            setDrawGridLines(false)
            granularity = 1f
            textSize = 10f
            position = XAxis.XAxisPosition.BOTTOM
        }

        binding.weeklyChart.axisLeft.apply {
            setDrawLabels(false)
            setDrawAxisLine(false)
            setDrawGridLines(false)
            isEnabled = false
            granularity = 1f
        }

        binding.weeklyChart.axisRight.apply {
            setDrawAxisLine(true)
            setDrawGridLines(false)
            isEnabled = false
        }
    }

    private fun initTodayStatistic(){
        binding.horizontalGraph.apply {
            description.isEnabled = false
            isClickable = false
            legend.isEnabled = false
            setFitBars(true)
        }

        binding.horizontalGraph.xAxis.apply {
            setDrawAxisLine(false)
            setDrawGridLines(false)
            granularity = 1f
            textSize = 15f
            valueFormatter = IAxisValueFormatter { value, _ ->
                return@IAxisValueFormatter labels[value.toInt() % labels.size]
            }
            position = XAxis.XAxisPosition.BOTTOM
        }

        binding.horizontalGraph.axisLeft.apply {
            setDrawLabels(false)
            setDrawAxisLine(false)
            setDrawGridLines(false)
            isEnabled = false
            granularity = 1f
            axisMinimum = 0f
            axisMaximum = 100f
            yOffset = 10f
        }
        binding.horizontalGraph.axisRight.apply {
            setDrawAxisLine(true)
            setDrawGridLines(false)
            isEnabled = false
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