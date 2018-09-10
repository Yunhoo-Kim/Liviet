package com.liviet.hoo.liviet.ui.user

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseActivity
import com.liviet.hoo.liviet.databinding.ActivitySetupBinding
import com.liviet.hoo.liviet.utils.UiUtli


class UserSetUpActivity: BaseActivity() {
    private lateinit var binding: ActivitySetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setup)
        UiUtli.replaceNewFragment(this, BodySetUpFragment.newInstance(Bundle()), R.id.container_main)
    }
}