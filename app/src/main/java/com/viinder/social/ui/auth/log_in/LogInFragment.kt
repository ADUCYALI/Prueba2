package com.viinder.social.ui.auth.log_in

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.viinder.social.R
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.databinding.FragmentLogInBinding
import com.viinder.social.util.*
import kotlinx.android.synthetic.main.fragment_log_in.*
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import org.koin.android.ext.android.inject

class LogInFragment : Fragment(R.layout.fragment_log_in), StatusCallback {

    private val logInViewModel by inject<LogInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentLogInBinding>(view)?.apply {
            lifecycleOwner = this@LogInFragment
            viewModel = logInViewModel
        }

        logInViewModel.statusCallback = this
        setUiAction()
    }

    private fun setUiAction() {
        tv_reset_password.setOnClickListener {
            findNavController().navigate(R.id.next_action)
        }

        tv_goto_sign_up.setOnClickListener {
            findNavController().navigate(R.id.action_sign_up)
        }
    }

    private fun showAlert(message: String) {
        alert(message) {
            okButton { it.dismiss() }
        }.show()
    }

    override fun onSuccess() {
        findNavController().navigate(R.id.action_feed)
    }

    override fun onFailure(messageCode: Int) {
        when (messageCode) {
            EMPTY_FIELDS -> showAlert(resources.getString(R.string.empty_fields))
            INVALID_EMAIL -> showAlert(resources.getString(R.string.invalid_email))
            INVALID_PASSWORD -> showAlert(resources.getString(R.string.invalid_password))
            WRONG_PASSWORD -> showAlert(resources.getString(R.string.wrong_password))
            USER_NOT_FOUND -> showAlert(resources.getString(R.string.user_not_found))
            LOGIN_ERROR -> showAlert(resources.getString(R.string.login_error))
            else -> showAlert(resources.getString(R.string.error))
        }
    }
}
