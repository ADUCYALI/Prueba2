package com.viinder.social.ui.auth.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.data.model.User
import com.viinder.social.data.network.AuthService
import com.viinder.social.data.network.CurrentUserDB
import com.viinder.social.util.*
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthService,
    private val userInstance: CurrentUserDB
) : ViewModel() {

    var statusCallback: StatusCallback? = null

    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()

    fun signUp() {

        val name = userName.value
        val email = userEmail.value
        val password = userPassword.value

        viewModelScope.launch {
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                try {
                    when {
                        !isValidEmail(email) -> statusCallback?.onFailure(INVALID_EMAIL)
                        !isValidPassword(password) -> statusCallback?.onFailure(INVALID_PASSWORD)
                        isValidEmail(email) && isValidPassword(password) -> {
                            authRepository.signUpByEmail(email, password)
                            val userId = authRepository.userUid
                            val user = User(userId, name, email)
                            userInstance.createUser(user)
                            statusCallback?.onSuccess()
                        }
                    }
                } catch (e: FirebaseAuthUserCollisionException) {
                    statusCallback?.onFailure(EMAIL_ALREADY_EXISTS)
                } catch (e: Exception) {
                    statusCallback?.onFailure(REGISTRATION_ERROR)
                    e.printStackTrace()
                }
            } else {
                statusCallback?.onFailure(EMPTY_FIELDS)
            }
        }
    }
}
