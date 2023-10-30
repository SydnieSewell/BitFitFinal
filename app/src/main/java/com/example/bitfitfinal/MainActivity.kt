package com.example.bitfitfinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val displayEntry = mutableListOf<DisplayEnteries>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define fragments
        val listFragment: Fragment = ListFragment()
        val addFragment: Fragment = AddFragment()
        val cycleSummaryFragment: Fragment= CycleSummaryFragment(displayEntry)

        // need a summary fragment as well

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.listTab -> fragment = listFragment
                R.id.addTab -> fragment = addFragment
                R.id.dashboardTab-> fragment= cycleSummaryFragment
                // need the dashboard tab for the summary tab
            }
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.listTab

    }
}