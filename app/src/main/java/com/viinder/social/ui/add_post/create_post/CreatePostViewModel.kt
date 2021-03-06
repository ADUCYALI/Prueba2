package com.viinder.social.ui.add_post.create_post

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viinder.social.data.model.Post
import com.viinder.social.data.network.CurrentUserDB
import com.viinder.social.data.network.PostActionDB
import com.viinder.social.util.prefs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class CreatePostViewModel(
    private val postDb: PostActionDB,
    private val currentUserDB: CurrentUserDB
) : ViewModel() {

    var caption = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun addImagePost(image: ByteArray) {
        viewModelScope.launch {

            val userUid = prefs.currentUid
            val username = prefs.username
            val photoUrl = prefs.photoUrl
            val caption = caption.value
            val posts = prefs.postsNumber.plus(1)
            val newPost = Post(userUid, username, photoUrl, caption)

            prefs.postsNumber = posts
            Log.d("ps", "$posts")
            Log.d("ps", "${prefs.postsNumber}")
            postDb.createPost(newPost, image)
            currentUserDB.updatePostsNumber(posts)
        }
    }
}
