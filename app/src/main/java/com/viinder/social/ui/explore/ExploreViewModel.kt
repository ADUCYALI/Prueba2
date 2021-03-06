package com.viinder.social.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.viinder.social.data.network.PostActionDB
import com.viinder.social.ui.post.adapter.PostAdapter

class ExploreViewModel(private val postsDb: PostActionDB) : ViewModel() {

    val adapter = MutableLiveData<PostAdapter>()

    fun getPostQuery(id: String) = liveData {
        emit(postsDb.getPostsQuery(id))
    }
}
