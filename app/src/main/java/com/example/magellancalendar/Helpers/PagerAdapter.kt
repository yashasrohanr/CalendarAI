package com.example.magellancalendar.Helpers

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.magellancalendar.Month.MonthFragment

class PagerAdapter: FragmentStateAdapter() {
    override fun getCount(): Int {
        return 5000
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getItem(position: Int): Fragment {
        var month = (position % 12)
        var year = (position % 12) + 1970
        return MonthFragment.newInstance(year, month)
    }
}