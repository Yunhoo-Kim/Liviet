package com.liviet.hoo.liviet.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import com.google.android.gms.ads.MobileAds
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseActivity
import com.liviet.hoo.liviet.databinding.ActivityMainBinding
import com.liviet.hoo.liviet.databinding.ActivitySetupBinding
import com.liviet.hoo.liviet.di.ViewModelFactory
import com.liviet.hoo.liviet.ui.user.UserSetUpActivity
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.viewmodel.user.UserSetUpViewModel
import javax.inject.Inject


class  MainActivity: BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserSetUpViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root)

        MobileAds.initialize(this,
                getString(R.string.admob))

        val fragmentPageAdapter = TabPageAdapter(supportFragmentManager)
        binding.tabPageAdapter = fragmentPageAdapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserSetUpViewModel::class.java)

        try {
            // Check user exists if not setup user
            viewModel.getUserInfo().id
        }catch (e: NullPointerException){
            // start setup activity
            val intent = Intent(this, UserSetUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tabMain.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_diet -> {
                    binding.viewpagerMain.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_camera -> {
                    binding.viewpagerMain.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    binding.viewpagerMain.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

        binding.viewpagerMain.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                binding.tabMain.menu.getItem(position).isChecked = true
            }
        })
    }
}
