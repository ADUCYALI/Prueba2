package com.viinder.social.ui.explore.search

import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.viinder.social.data.model.User
import com.viinder.social.data.network.RequestData
import com.viinder.social.ui.explore.adapter.SearchAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect


class SearchViewModel(private val requestDB: RequestData) : ViewModel() {

    val adapter = MutableLiveData<SearchAdapter>()
    val textQuery = MutableLiveData<String>()

    fun queryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(query: String): Boolean {
            textQuery.value = query
            return true
        }
    }

    @ExperimentalCoroutinesApi
    fun getUserSearch(text: String) = liveData {
        try {
            requestDB.requestUsers(text).collect {
                emit(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setAdapter(userList: MutableList<User>) = adapter.value?.run {
        setData(userList)
        notifyDataSetChanged()
    }
}
