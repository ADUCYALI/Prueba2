package com.viinder.social.ui.auth.sign_up

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.viinder.social.R
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.databinding.FragmentSignUpBinding
import com.viinder.social.util.EMPTY_FIELDS
import com.viinder.social.util.INVALID_EMAIL
import com.viinder.social.util.INVALID_PASSWORD
import com.viinder.social.util.REGISTRATION_ERROR
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import org.koin.android.ext.android.inject

class SignUpFragment : Fragment(R.layout.fragment_sign_up), StatusCallback {

    private val signUpViewModel by inject<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentSignUpBinding>(view)?.apply {
            lifecycleOwner = this@SignUpFragment
            viewModel = signUpViewModel
        }

        signUpViewModel.statusCallback = this
        setUiAction()
    }

    private fun setUiAction() {
        tv_goto_log_in.setOnClickListener {
            findNavController().navigate(R.id.action_login)
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
            REGISTRATION_ERROR -> showAlert(resources.getString(R.string.registration_error))
            else -> showAlert(resources.getString(R.string.error))
        }
    }
}
