package com.mustaq.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_material_tab.*
import kotlin.math.log

class MaterialTabActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var badgeDrawable: BadgeDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_tab)

        tabLayout = findViewById(R.id.tabLayout)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {


            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e(TAG, "onTabSelected: " + "" + tab?.setText("TAB!"))
                Log.e(TAG, "onTabSelected: " + "Tab Select()" + tab?.select())
                Log.e(TAG, "onTabSelected: " + "Tab Position" + tab?.position)
                badgeDrawable= tab?.orCreateBadge!!
                    // Customize badge
                badgeDrawable.number = 125

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }

    companion object {
        const val TAG = "TAB"
    }
}