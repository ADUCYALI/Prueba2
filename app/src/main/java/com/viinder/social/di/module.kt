package com.viinder.social.di

import com.viinder.social.data.network.*
import com.viinder.social.ui.add_post.create_post.CreatePostViewModel
import com.viinder.social.ui.auth.log_in.LogInViewModel
import com.viinder.social.ui.auth.reset_password.ResetPasswordViewModel
import com.viinder.social.ui.auth.sign_up.SignUpViewModel
import com.viinder.social.ui.current_profile.CurrentProfileViewModel
import com.viinder.social.ui.current_profile.edit_profile.EditProfileViewModel
import com.viinder.social.ui.direct_message.DirectViewModel
import com.viinder.social.ui.direct_message.chat.ChatViewModel
import com.viinder.social.ui.explore.ExploreViewModel
import com.viinder.social.ui.explore.search.SearchViewModel
import com.viinder.social.ui.feed.FeedViewModel
import com.viinder.social.ui.post.PostsViewModel
import com.viinder.social.ui.post_detail.PostDetailViewModel
import com.viinder.social.ui.profile.ProfileViewModel
import com.viinder.social.ui.profile.follower_list.FollowersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthService }
    single { CurrentUserDB }
    viewModel { LogInViewModel(get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { ResetPasswordViewModel(get()) }
}

@ExperimentalCoroutinesApi
val userModule = module {
    viewModel { EditProfileViewModel(get()) }
}

val profileModule = module {
    single { FollowActionDB }
    viewModel { ProfileViewModel(get()) }
    viewModel { CurrentProfileViewModel() }
    viewModel { FollowersViewModel() }
}

val postModule = module {
    single { PostActionDB }
    viewModel { PostsViewModel(get()) }
    viewModel { PostDetailViewModel() }
    viewModel { CreatePostViewModel(get(), get()) }
}

val feedModule = module {
    viewModel { FeedViewModel(get(), get()) }
}

val searchModule = module {
    single { RequestData }
    viewModel { ExploreViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}

@ExperimentalCoroutinesApi
val directMessageModule = module {
    single { MessagingDB }
    viewModel { DirectViewModel(get()) }
    viewModel { ChatViewModel(get(), get()) }
}
