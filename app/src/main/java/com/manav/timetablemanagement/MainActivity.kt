package com.manav.timetablemanagement

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.manav.timetablemanagement.Fragments.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentAdapter : FragmentManager
    private lateinit var toolBar : androidx.appcompat.widget.Toolbar
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        val fragmentAdapter = FragmentManager(this)
        viewPager.adapter = fragmentAdapter


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("ResourceType")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Change the background color of the selected tab
                val cardView = tab?.customView?.findViewById<androidx.cardview.widget.CardView>(R.id.cardView)
                cardView?.background = ContextCompat.getDrawable(this@MainActivity,R.drawable.tab_selected_background)
            }

            @SuppressLint("ResourceType")
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Revert the background color of unselected tabs
                val cardView = tab?.customView?.findViewById<androidx.cardview.widget.CardView>(R.id.cardView)
                cardView?.background = ContextCompat.getDrawable(this@MainActivity, R.drawable.tab_indicator)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        // Tab titles for each day
        val tabTitles = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

        // Attach TabLayout and ViewPager2 with custom tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Inflate custom tab layout
            val tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
            val tabTitleMonday = tabView.findViewById<TextView>(R.id.tabTitle)


            // Set the title dynamically for each tab
            tabTitleMonday.text = tabTitles[position]

            // Assign the custom view to the tab
            tab.customView = tabView
        }.attach()
    }
}