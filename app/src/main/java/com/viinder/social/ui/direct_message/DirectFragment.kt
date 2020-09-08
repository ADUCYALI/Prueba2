package com.viinder.social.ui.direct_message

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.viinder.social.R
import com.viinder.social.common.listener.OnUserClickListener
import com.viinder.social.data.model.User
import com.viinder.social.databinding.FragmentDirectBinding
import com.viinder.social.ui.direct_message.adapter.DirectAdapter
import com.viinder.social.util.setToolbar
import kotlinx.android.synthetic.main.fragment_direct.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class DirectFragment : Fragment(R.layout.fragment_direct), OnUserClickListener {

    private val directViewModel by inject<DirectViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentDirectBinding>(view)?.apply {
            lifecycleOwner = this@DirectFragment
            viewModel = directViewModel
        }

        directViewModel.run {
            adapter.value = DirectAdapter(this@DirectFragment)
            getConversations().observe(viewLifecycleOwner, Observer {
                setAdapter(it)
            })
        }

        setHasOptionsMenu(true)
        setToolbar(activity, tb_direct, getString(R.string.direct))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_direct, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onItemClickListener(user: User) {
        val action = DirectFragmentDirections.actionChat(user)
        findNavController().navigate(action)
    }
}
