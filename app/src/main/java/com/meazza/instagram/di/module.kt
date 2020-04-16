package com.meazza.instagram.di

import com.meazza.instagram.repository.AuthRepository
import com.meazza.instagram.ui.auth.log_in.LogInViewModel
import com.meazza.instagram.ui.auth.reset_password.ResetPasswordViewModel
import com.meazza.instagram.ui.auth.sign_up.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthRepository }
    viewModel { LogInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ResetPasswordViewModel(get()) }
}