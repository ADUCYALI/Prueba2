package com.viinder.social.common.listener

import com.viinder.social.data.model.User

interface OnUserClickListener {
    fun onItemClickListener(user: User)
}