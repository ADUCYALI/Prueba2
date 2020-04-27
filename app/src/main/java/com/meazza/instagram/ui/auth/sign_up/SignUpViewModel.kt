package com.meazza.instagram.ui.auth.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.meazza.instagram.data.model.User
import com.meazza.instagram.data.network.AuthService
import com.meazza.instagram.data.network.UserInstanceDB
import com.meazza.instagram.listener.StatusListener
import com.meazza.instagram.util.*
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthService,
    private val userInstance: UserInstanceDB
) :
    ViewModel() {

    var statusListener: StatusListener? = null

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
                        !isValidEmail(email) -> statusListener?.onFailure(INVALID_EMAIL)
                        !isValidPassword(password) -> statusListener?.onFailure(INVALID_PASSWORD)
                        isValidEmail(email) && isValidPassword(password) -> {
                            authRepository.signUpByEmail(email, password)
                            val userId = authRepository.userUid
                            val user = User(userId, name, email)
                            userInstance.createUser(user)
                            statusListener?.onSuccess()
                        }
                    }
                } catch (e: FirebaseAuthUserCollisionException) {
                    statusListener?.onFailure(EMAIL_ALREADY_EXISTS)
                } catch (e: Exception) {
                    statusListener?.onFailure(REGISTRATION_ERROR)
                    e.printStackTrace()
                }
            } else {
                statusListener?.onFailure(EMPTY_FIELDS)
            }
        }
    }
}
