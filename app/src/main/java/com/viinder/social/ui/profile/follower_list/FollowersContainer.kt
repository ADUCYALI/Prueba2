package com.viinder.social.ui.profile.follower_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.viinder.social.R
import com.viinder.social.common.adapter.InstagramViewPagerAdapter
import com.viinder.social.data.model.User
import com.viinder.social.ui.profile.follower_list.list.FollowerListFragment
import com.viinder.social.ui.profile.follower_list.list.FollowingListFragment
import com.viinder.social.util.setToolbar
import kotlinx.android.synthetic.main.container_followers.*

class FollowersContainer : Fragment(R.layout.container_followers) {

    private var user: User? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = arguments?.let { FollowersContainerArgs.fromBundle(it).user }

        setTabLayout()
        setToolbar(activity, tb_follower, user?.username!!)
    }

    private fun setTabLayout() {

        pager_followers.adapter =
            InstagramViewPagerAdapter(this, FollowerListFragment(), FollowingListFragment())

        TabLayoutMediator(
            tab_layout_follower,
            pager_followers,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.followers_number, user?.followersNumber.toString())
                    1 -> tab.text = getString(R.string.following_number, user?.followingNumber.toString())
                }
            }
        ).attach()
    }
}
