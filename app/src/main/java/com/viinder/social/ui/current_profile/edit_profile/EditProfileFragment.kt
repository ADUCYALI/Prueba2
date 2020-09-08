package com.viinder.social.ui.current_profile.edit_profile

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.viinder.social.R
import com.viinder.social.common.callback.StatusCallback
import com.viinder.social.common.listener.OnViewClickListener
import com.viinder.social.common.permission.PermissionRequest
import com.viinder.social.common.permission.PermissionState
import com.viinder.social.databinding.FragmentEditProfileBinding
import com.viinder.social.util.TRY_AGAIN
import com.viinder.social.util.circleImage
import com.viinder.social.util.setToolbar
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.ext.android.inject


@ExperimentalCoroutinesApi
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile), OnViewClickListener,
    StatusCallback {

    companion object {
        const val GALLERY_REQUEST_CODE = 101
    }

    private val editProfileViewModel by inject<EditProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editProfileViewModel.getUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DataBindingUtil.bind<FragmentEditProfileBinding>(view)?.apply {
            lifecycleOwner = this@EditProfileFragment
            viewModel = editProfileViewModel
        }

        editProfileViewModel.onClickListener = this

        setHasOptionsMenu(true)
        setToolbar(activity, tb_edit_profile, getString(R.string.edit_profile), R.drawable.ic_close)
    }

    private fun pickImageFromGallery() {
        startActivityForResult(
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
            GALLERY_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    val imageUri = data?.data
                    imageUri?.let { editProfileViewModel.uploadImage(it) }
                    iv_change_user_photo.circleImage(imageUri)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mn_save_changes) {
            editProfileViewModel.saveChanges()
            findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickFromViewModel() {
        when (PermissionRequest(activity as Activity)
            .allPermissions()) {
            PermissionState.ALL_PERMISSIONS_GRANTED -> {
                pickImageFromGallery()
            }
            PermissionState.DENIED -> {
            }
            PermissionState.PERMANENTLY_DENIED -> {
            }
        }
    }

    override fun onSuccess() {}

    override fun onFailure(messageCode: Int) {
        if (messageCode == TRY_AGAIN) longToast(R.string.error)
    }
}
