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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistic, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DietVM::class.java)
        binding.viewModel = viewModel
//        binding.radar.setData(
//                arrayListOf(RadarHolder("칼로리", 200),
//                        RadarHolder("탄수화물", 100),
//                        RadarHolder("단백질", 50),
//                        RadarHolder("지방", 20)))
//        binding.radar.isInteractive = false
        val entries = mutableListOf<RadarEntry>()
        entries.add(RadarEntry(14.2f, "abc"))
        entries.add(RadarEntry(1.2f, "aa"))
        entries.add(RadarEntry(10.2f, "bb"))
        entries.add(RadarEntry(24.2f, "cc"))
        entries.add(RadarEntry(4.2f, "dd"))
        val labels = mutableListOf<String>()
        labels.add("칼로리")
        labels.add("칼로")
        labels.add("로리")
        labels.add("리")
        labels.add("칼리h")
        binding.radar.data = RadarData(RadarDataSet(entries, "nnn").apply {
//            this.color = getColor(R.color.colorPrimary)
            this.color = context!!.getColor(R.color.colorPrimary)
            this.fillColor = context!!.getColor(R.color.colorPrimaryDark)
            this.fillAlpha = 90
            this.setDrawFilled(true)
        }).apply {
            this.labels = labels
        }
        binding.radar.setDrawWeb(true)
        binding.radar.description.isEnabled = false
        binding.radar.webLineWidthInner = 0.5f
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