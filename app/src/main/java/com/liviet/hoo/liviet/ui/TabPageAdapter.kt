package com.liviet.hoo.liviet.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.liviet.hoo.liviet.ui.user.DietStyleSetUpFragment


class TabPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                LivietMainFragment.newInstance(Bundle())
            }
            else -> DietStyleSetUpFragment.newInstance(Bundle())
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Users"
            1 -> "Blogs"
            2 -> "Albums"
            else -> "Third Tab"
        }
    }
}