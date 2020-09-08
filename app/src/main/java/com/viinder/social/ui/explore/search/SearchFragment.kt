package com.viinder.social.ui.explore.search

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.viinder.social.R
import com.viinder.social.common.listener.OnUserClickListener
import com.viinder.social.data.model.User
import com.viinder.social.databinding.FragmentSearchBinding
import com.viinder.social.ui.explore.adapter.SearchAdapter
import com.viinder.social.util.hideKeyboard
import com.viinder.social.util.setToolbar
import com.viinder.social.util.showKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject

class SearchFragment : Fragment(R.layout.fragment_search), OnUserClickListener {

    private val searchViewModel by inject<SearchViewModel>()

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentSearchBinding>(view)?.apply {
            lifecycleOwner = this@SearchFragment
            viewModel = searchViewModel
        }

        searchViewModel.run {
            adapter.value = SearchAdapter(this@SearchFragment)
            textQuery.observe(viewLifecycleOwner, Observer { text ->
                getUserSearch(text).observe(viewLifecycleOwner, Observer {
                    setAdapter(it)
                })
            })

        }
        showKeyboard()
        setToolbar(activity, tb_search, "")
    }

    override fun onItemClickListener(user: User) {
        val action = SearchFragmentDirections.actionGlobalProfile(user)
        findNavController().navigate(action)
    }

    private fun showKeyboard() {
        search_view.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                search_view.showKeyboard()
            } else {
                search_view.hideKeyboard()
            }
        }
    }
}
