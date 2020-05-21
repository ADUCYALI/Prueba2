package com.meazza.instagram.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.meazza.instagram.data.model.Post
import com.meazza.instagram.data.network.PostActionDB
import com.meazza.instagram.ui.post.adapter.PostAdapter

class PostsViewModel(private val postsDb: PostActionDB) : ViewModel() {

    val adapter = MutableLiveData<PostAdapter>()

    fun getPosts(userId: String) = liveData {
        emit(postsDb.getPosts(userId))
    }

    fun setAdapter(posts: MutableList<Post>) = adapter.value?.run {
        setList(posts)
        notifyDataSetChanged()
    }
}