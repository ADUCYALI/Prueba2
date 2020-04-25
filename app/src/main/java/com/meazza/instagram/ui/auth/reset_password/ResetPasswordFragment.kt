package com.meazza.instagram.ui.auth.reset_password

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.meazza.instagram.R
import com.meazza.instagram.databinding.FragmentResetPasswordBinding
import com.meazza.instagram.ui.StatusListener
import com.meazza.instagram.util.EMPTY_FIELDS
import com.meazza.instagram.util.INVALID_EMAIL
import com.meazza.instagram.util.USER_NOT_FOUND
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.ext.android.inject

class ResetPasswordFragment : Fragment(R.layout.fragment_reset_password),
    StatusListener {

    private val resetViewModel by inject<ResetPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentResetPasswordBinding>(view)?.apply {
            lifecycleOwner = this@ResetPasswordFragment
            viewModel = resetViewModel
        }

        resetViewModel.statusListener = this
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
