package com.meazza.instagram.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.meazza.instagram.R
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        NavigationUI.setupWithNavController(bottom_nav, navController)
        setBottomNavVisibility()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.destination_welcome -> mAuth.currentUser == null
            }
        }
    }

    private fun setBottomNavVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_feed -> bottom_nav.visibility = View.VISIBLE
                R.id.navigation_search -> bottom_nav.visibility = View.VISIBLE
                R.id.navigation_add_post -> bottom_nav.visibility = View.VISIBLE
                R.id.navigation_notification -> bottom_nav.visibility = View.VISIBLE
                R.id.navigation_profile -> bottom_nav.visibility = View.VISIBLE
                else -> bottom_nav.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
