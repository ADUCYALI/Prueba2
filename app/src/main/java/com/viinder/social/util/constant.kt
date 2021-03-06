package com.viinder.social.util

import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.viinder.social.di.App
import kotlinx.coroutines.ExperimentalCoroutinesApi

/** Errors Code Constants */
const val EMPTY_FIELDS = 101
const val INVALID_EMAIL = 102
const val INVALID_PASSWORD = 103
const val EMAIL_ALREADY_EXISTS = 104
const val USER_NOT_FOUND = 105
const val WRONG_PASSWORD = 106
const val REGISTRATION_ERROR = 107
const val LOGIN_ERROR = 108
const val TRY_AGAIN = 109

/** Firebase Constants */
const val USER_REF = "user"
const val FOLLOW_REF = "follow"
const val DIRECT_MESSAGE_REF = "direct_message"
const val CONVERSATION_REF = "conversation"
const val PROFILE_PHOTO_REF = "profile_photo"
const val DOC_REFERENCE = "doc_ref"
const val POST_REF = "post"
const val UID = "uid"
const val NAME = "name"
const val USERNAME = "username"
const val BIO = "bio"
const val PHOTO_URL = "photoUrl"
const val WEBSITE = "website"
const val SENT_AT = "sentAt"
const val FOLLOWER = "follower"
const val FOLLOWING = "following"
const val POST_NUMBER = "postNumber"
const val POSTS_NUMBER = "postsNumber"
const val FOLLOWERS_NUMBER = "followersNumber"
const val FOLLOWING_NUMBER = "followingNumber"
const val POST_IMAGE_URL = "postImageUrl"

/** Common Constants*/
const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
const val PATTERN_DATE = "MMMM d"

/** Common Variables*/
val factory: DrawableCrossFadeFactory = DrawableCrossFadeFactory.Builder()
    .setCrossFadeEnabled(true).build()

@ExperimentalCoroutinesApi
val prefs by lazy { App.preferences }



