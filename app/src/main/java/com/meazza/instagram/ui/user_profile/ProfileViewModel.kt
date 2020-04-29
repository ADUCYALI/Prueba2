package com.meazza.instagram.ui.user_profile

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meazza.instagram.common.OnItemClickListener
import com.meazza.instagram.common.StatusCallback
import com.meazza.instagram.data.network.UserInstanceDB
import com.meazza.instagram.util.TRY_AGAIN
import kotlinx.coroutines.launch

class ProfileViewModel(private val userInstance: UserInstanceDB) : ViewModel() {

    var onClickListener: OnItemClickListener? = null
    var statusCallback: StatusCallback? = null

    var name = MutableLiveData<String>()
    var username = MutableLiveData<String>()
    var photoUrl = MutableLiveData<String>()
    var bio = MutableLiveData<String>()
    var website = MutableLiveData<String>()
    var posts = MutableLiveData<String>()
    var followers = MutableLiveData<String>()
    var following = MutableLiveData<String>()

    init {
        if (photoUrl.value.isNullOrEmpty()) photoUrl.value = ""
        if (name.value.isNullOrEmpty()) name.value = ""
        if (username.value.isNullOrEmpty()) username.value = ""
        if (bio.value.isNullOrEmpty()) bio.value = ""
        if (website.value.isNullOrEmpty()) website.value = ""
    }

    fun changeUserPhoto() {
        onClickListener?.onClickFromViewModel()
    }

    fun getUser() = viewModelScope.launch {
        try {
            val user = userInstance.getUser()
            name.value = user?.name
            username.value = user?.username
            photoUrl.value = user?.photoUrl
            bio.value = user?.bio
            website.value = user?.website
            posts.value = user?.postNumber.toString()
            followers.value = user?.followersNumber.toString()
            following.value = user?.followingNumber.toString()
        } catch (e: Exception) {
            statusCallback?.onFailure(TRY_AGAIN)
            e.printStackTrace()
        }
    }

    fun saveChanges() = viewModelScope.launch {
        try {
            userInstance.updateUser(
                name.value!!,
                username.value!!,
                bio.value!!,
                website.value!!
            )
        } catch (e: Exception) {
            statusCallback?.onFailure(TRY_AGAIN)
            e.printStackTrace()
        }
    }

    fun uploadImage(imageUri: Uri) = viewModelScope.launch {
        try {
            userInstance.uploadImage(imageUri)
        } catch (e: Exception) {
            statusCallback?.onFailure(TRY_AGAIN)
            e.printStackTrace()
        }
    }
}
