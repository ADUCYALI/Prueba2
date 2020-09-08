package com.viinder.social.ui.auth.log_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.data.network.AuthService
import com.viinder.social.util.EMPTY_FIELDS
import com.viinder.social.util.LOGIN_ERROR
import com.viinder.social.util.USER_NOT_FOUND
import com.viinder.social.util.WRONG_PASSWORD
import kotlinx.coroutines.launch

class LogInViewModel(private val authRepository: AuthService) : ViewModel() {

    var statusCallback: StatusCallback? = null

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    fun logIn() {

        val email = email.value
        val password = password.value

        viewModelScope.launch {
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                try {
                    authRepository.loginByEmail(email, password)
                    statusCallback?.onSuccess()
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    statusCallback?.onFailure(WRONG_PASSWORD)
                } catch (e: FirebaseAuthInvalidUserException) {
                    statusCallback?.onFailure(USER_NOT_FOUND)
                } catch (e: Exception) {
                    statusCallback?.onFailure(LOGIN_ERROR)
                    e.printStackTrace()
                }
            } else {
                statusCallback?.onFailure(EMPTY_FIELDS)
            }
        }
    }
}
