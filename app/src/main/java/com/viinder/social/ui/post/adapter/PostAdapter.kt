package com.viinder.social.ui.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.viinder.social.R
import com.viinder.social.common.listener.OnPostClickListener
import com.viinder.social.data.model.Post
import com.viinder.social.databinding.LayoutPostBinding

class PostAdapter(
    options: FirestorePagingOptions<Post>,
    private val listener: OnPostClickListener
) : FirestorePagingAdapter<Post, PostAdapter.PostHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_post,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostHolder, position: Int, model: Post) {
        holder.itemBinding.run {
            post = model
            root.setOnClickListener {
                listener.onClickPost(model)
            }
        }
    }

    inner class PostHolder(val itemBinding: LayoutPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}
