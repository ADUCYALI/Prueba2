package com.viinder.social.ui.current_profile.edit_profile

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.common.listener.OnViewClickListener
import com.viinder.social.data.network.CurrentUserDB
import com.viinder.social.util.TRY_AGAIN
import com.viinder.social.util.prefs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class EditProfileViewModel(private val userInstance: CurrentUserDB) : ViewModel() {

    var onClickListener: OnViewClickListener? = null
    var statusCallback: StatusCallback? = null

    var name = MutableLiveData<String>()
    var username = MutableLiveData<String>()
    var photoUrl = MutableLiveData<String>()
    var bio = MutableLiveData<String>()
    var website = MutableLiveData<String>()
    var posts = MutableLiveData<String>()
    var followers = MutableLiveData<String>()
    var following = MutableLiveData<String>()

    fun changeUserPhoto() {
        onClickListener?.onClickFromViewModel()
    }

    fun getUser() {
        viewModelScope.launch {
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
                e.printStackTrace()
            }
        }
    }

    fun saveChanges() {

        prefs.name = name.value
        prefs.username = username.value
        prefs.bio = bio.value
        prefs.website = website.value

        viewModelScope.launch {
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
    }

    fun uploadImage(imageUri: Uri) {
        viewModelScope.launch {
            try {
                userInstance.uploadPhoto(imageUri)
            } catch (e: Exception) {
                statusCallback?.onFailure(TRY_AGAIN)
                e.printStackTrace()
            }
        }
    }
}
