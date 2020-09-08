package com.viinder.social.util.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.firebase.firestore.DocumentReference
import com.viinder.social.data.model.User
import com.viinder.social.util.circleImage
import com.viinder.social.util.squareResize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@BindingAdapter("setRoundImage")
fun setRoundImage(imageView: ImageView, url: String?) {
    imageView.circleImage(url)
}

@BindingAdapter("setSquareImage")
fun setSquareImage(imageView: ImageView, url: String?) {
    imageView.squareResize(url, 800)
}

@BindingAdapter("setUserPhotoChat")
fun setUserPhotoChat(imageView: ImageView, reference: DocumentReference?) {

    CoroutineScope(Dispatchers.Main).launch {

        val document = reference?.get()?.await()
        val user = document?.toObject(User::class.java)
        val url = user?.photoUrl.toString()

        imageView.circleImage(url)
    }
}
