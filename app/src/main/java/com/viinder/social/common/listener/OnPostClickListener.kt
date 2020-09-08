package com.viinder.social.common.listener

import com.viinder.social.data.model.Post

interface OnPostClickListener {
    fun onClickPost(post: Post)
}