package com.liviet.hoo.liviet.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
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
                getString(R.string.admob_id))
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserSetUpViewModel::class.java)

        try {
            viewModel.getUserInfo().id
        }catch (e: NullPointerException){
            // start setup activity
            val intent = Intent(this, UserSetUpActivity::class.java)
            startActivity(intent)
            finish()
        }finally{
            UiUtli.replaceNewFragment(this, LivietMainFragment.newInstance(Bundle()), R.id.container_main)
        }
    }
}
