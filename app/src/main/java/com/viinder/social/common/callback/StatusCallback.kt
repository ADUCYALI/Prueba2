package com.viinder.social.common.callback

interface StatusCallback {
    fun onSuccess()
    fun onFailure(messageCode: Int)
}