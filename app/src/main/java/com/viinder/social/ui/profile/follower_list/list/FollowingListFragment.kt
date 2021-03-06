package com.viinder.social.ui.profile.follower_list.list

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.viinder.social.R
import com.viinder.social.databinding.FragmentFollowingListBinding
import com.viinder.social.ui.profile.follower_list.FollowersViewModel
import org.koin.android.ext.android.inject


class FollowingListFragment : Fragment(R.layout.fragment_following_list) {

    private val followersViewModel by inject<FollowersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentFollowingListBinding>(view)?.apply {
            lifecycleOwner = this@FollowingListFragment
            viewModel = followersViewModel
        }
    }
}
