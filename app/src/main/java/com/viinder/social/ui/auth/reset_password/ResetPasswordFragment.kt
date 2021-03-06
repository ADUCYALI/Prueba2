package com.viinder.social.ui.auth.reset_password

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.viinder.social.R
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.databinding.FragmentResetPasswordBinding
import com.viinder.social.util.EMPTY_FIELDS
import com.viinder.social.util.INVALID_EMAIL
import com.viinder.social.util.USER_NOT_FOUND
import com.viinder.social.util.setToolbar
import kotlinx.android.synthetic.main.fragment_reset_password.*
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.ext.android.inject

class ResetPasswordFragment : Fragment(R.layout.fragment_reset_password), StatusCallback {

    private val resetViewModel by inject<ResetPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentResetPasswordBinding>(view)?.apply {
            lifecycleOwner = this@ResetPasswordFragment
            viewModel = resetViewModel
        }

        resetViewModel.statusCallback = this
        setToolbar(activity, tb_reset_password, getString(R.string.login_help))
    }

    override fun onSuccess() {
        longToast(R.string.email_reset_password)
        findNavController().popBackStack()
    }

    override fun onFailure(messageCode: Int) {
        when (messageCode) {
            INVALID_EMAIL -> longToast(R.string.invalid_email)
            USER_NOT_FOUND -> longToast(R.string.user_not_found)
            EMPTY_FIELDS -> longToast(R.string.empty_fields)
            else -> longToast(R.string.error)
        }
    }
}
