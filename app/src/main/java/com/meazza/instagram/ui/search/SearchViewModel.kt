package com.meazza.instagram.ui.search

import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.meazza.instagram.data.model.User
import com.meazza.instagram.data.network.RequestDB
import com.meazza.instagram.ui.search.adapter.SearchAdapter


class SearchViewModel(private val requestDB: RequestDB) : ViewModel() {

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

    fun getUserSearch(text: String) = liveData {
        try {
            emit(requestDB.requestUsers(text))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setAdapter(userList: MutableList<User>) = adapter.value?.run {
        setData(userList)
        notifyDataSetChanged()
    }
}