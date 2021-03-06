package com.viinder.social.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.viinder.social.R
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        bottom_nav.setupWithNavController(navController)
        setBottomNavVisibility()
    }

    private fun setBottomNavVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_feed -> bottom_nav.visibility = View.VISIBLE
                R.id.navigation_explore -> bottom_nav.visibility = View.VISIBLE
                R.id.navigation_notification -> bottom_nav.visibility = View.VISIBLE
                R.id.navigation_current_profile -> bottom_nav.visibility = View.VISIBLE
                else -> bottom_nav.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
