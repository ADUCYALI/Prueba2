package com.viinder.social.ui.direct_message

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.viinder.social.data.model.User
import com.viinder.social.data.network.MessagingDB
import com.viinder.social.ui.direct_message.adapter.DirectAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DirectViewModel(private val database: MessagingDB) : ViewModel() {

    val adapter = MutableLiveData<DirectAdapter>()

    fun getConversations() = liveData {
        emit(database.getConversations())
    }

    fun setAdapter(users: MutableList<User>) = adapter.value?.run {
        setUserList(users)
        notifyDataSetChanged()
    }
}
