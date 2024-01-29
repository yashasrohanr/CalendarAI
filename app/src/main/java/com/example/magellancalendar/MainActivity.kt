package com.example.magellancalendar

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.magellancalendar.Day.DayFragment
import com.example.magellancalendar.Month.MonthFragment
import com.example.magellancalendar.Settings.SettingsFragment
import com.example.magellancalendar.Sync.SyncingFragment
import com.example.magellancalendar.Week.WeekFragment
import com.example.magellancalendar.Year.YearFragment
import com.example.magellancalendar.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var drawer : DrawerLayout
    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        drawer = binding.drawerLayout
        toggle = ActionBarDrawerToggle(
            this, drawer,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        // default fragment
        loadFragment(MonthFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            R.id.nav_day -> DayFragment()
            R.id.nav_week -> WeekFragment()
            R.id.nav_month -> MonthFragment()
            R.id.nav_year -> YearFragment()
            R.id.nav_settings -> SettingsFragment()
            R.id.nav_sync -> SyncingFragment()
            else -> throw IllegalArgumentException("Wrong menu item selected")
        }
        loadFragment(fragment)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
