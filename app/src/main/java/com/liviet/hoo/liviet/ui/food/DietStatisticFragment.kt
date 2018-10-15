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

        val entries = mutableListOf<RadarEntry>()
        entries.add(RadarEntry(100.2f, "bb"))
        entries.add(RadarEntry(24.2f, "cc"))
        entries.add(RadarEntry(40.2f, "dd"))

        val standardDiet = viewModel.getStandardDietRadar().apply {
            this.label = "일일 권장량"
            this.color = context!!.getColor(R.color.colorPrimaryBlue)
            this.fillColor = context!!.getColor(R.color.colorPrimaryBlue)
            this.fillAlpha = 100
            this.setDrawFilled(true)
            this.setDrawHighlightIndicators(false)
        }

        val todayDiet = RadarDataSet(entries, "일일 섭취량").apply {
            this.color = context!!.getColor(R.color.colorPrimary)
            this.fillColor = context!!.getColor(R.color.colorPrimaryDark)
            this.fillAlpha = 100
            this.setDrawFilled(true)
            this.setDrawHighlightIndicators(false)
        }

        binding.radar.data = RadarData(listOf(todayDiet, standardDiet)).apply {
            this.labels = labels
            this.setDrawValues(false)
        }

        binding.radar.setDrawWeb(true)
        binding.radar.description.isEnabled = false
        binding.radar.webLineWidthInner = 1f
        binding.radar.webAlpha = 100
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
        return binding.root
    }

    companion object {
        fun newInstance(args: Bundle?): DietStatisticFragment {
            return DietStatisticFragment().apply {
                this.arguments = args
            }
        }
    }
}