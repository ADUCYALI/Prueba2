package com.viinder.social.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.viinder.social.data.model.User
import com.viinder.social.data.network.FollowActionDB
import kotlinx.coroutines.launch

class ProfileViewModel(private val followActionDb: FollowActionDB) : ViewModel() {

    val instagrammer = MutableLiveData<User>()
    val id = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val bio = MutableLiveData<String>()
    val photoUrl = MutableLiveData<String>()
    val website = MutableLiveData<String>()
    val postsNumber = MutableLiveData<String>()
    val followersNumber = MutableLiveData<String>()
    val followingNumber = MutableLiveData<String>()
    val isCurrentUserFollowing = MutableLiveData<Boolean>()

    fun checkIfCurrentUserIsFollowing(instagrammerUid: String) = liveData {
        emit(followActionDb.checkIfCurrentUserIsFollowing(instagrammerUid))
    }

    fun saveFollow() {
        viewModelScope.launch {
            val instagrammer = instagrammer.value
            try {
                followActionDb.saveFollow(instagrammer!!)
                followersNumber.value =
                    followActionDb.getFollowersNumber(instagrammer.id!!).toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isCurrentUserFollowing.value = true
        }
    }

    fun stopFollowing() {
        viewModelScope.launch {
            val instagrammer = instagrammer.value
            try {
                followActionDb.stopFollowing(instagrammer!!)
                followersNumber.value =
                    followActionDb.getFollowersNumber(instagrammer.id!!).toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isCurrentUserFollowing.value = false
        }
    }
}
