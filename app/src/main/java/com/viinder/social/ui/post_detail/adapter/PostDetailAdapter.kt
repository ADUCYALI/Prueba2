package com.viinder.social.ui.post_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.viinder.social.R
import com.viinder.social.data.model.Post
import com.viinder.social.databinding.LayoutPostDetailBinding

class PostDetailAdapter(options: FirestorePagingOptions<Post>) :
    FirestorePagingAdapter<Post, PostDetailAdapter.PostDetailHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostDetailHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_post_detail,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostDetailHolder, position: Int, model: Post) {
        holder.itemBinding.run {
            post = model
        }
    }

    inner class PostDetailHolder(val itemBinding: LayoutPostDetailBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}