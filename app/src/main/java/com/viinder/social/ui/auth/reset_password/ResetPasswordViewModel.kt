package com.viinder.social.ui.auth.reset_password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.data.network.AuthService
import com.viinder.social.util.EMPTY_FIELDS
import com.viinder.social.util.INVALID_EMAIL
import com.viinder.social.util.USER_NOT_FOUND
import com.viinder.social.util.isValidEmail
import kotlinx.coroutines.launch

class ResetPasswordViewModel(private val authRepository: AuthService) : ViewModel() {

    var statusCallback: StatusCallback? = null
    var emailToResetPassword = MutableLiveData<String>()

    fun resetPassword() {

        val email = emailToResetPassword.value

        viewModelScope.launch {
            if (!email.isNullOrEmpty()) {
                try {
                    when {
                        !isValidEmail(email) -> statusCallback?.onFailure(INVALID_EMAIL)
                        isValidEmail(email) -> {
                            authRepository.resetPassword(email)
                            statusCallback?.onSuccess()
                        }
                    }
                } catch (e: FirebaseAuthInvalidUserException) {
                    statusCallback?.onFailure(USER_NOT_FOUND)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                statusCallback?.onFailure(EMPTY_FIELDS)
            }
        }
    }
}
