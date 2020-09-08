package com.viinder.social.ui.add_post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viinder.social.R
import com.viinder.social.common.listener.OnFilterClickListener
import com.viinder.social.util.load
import com.zomato.photofilters.utils.ThumbnailItem
import kotlinx.android.synthetic.main.layout_filter.view.*

class FilterThumbnailAdapter(private val listener: OnFilterClickListener) :
    RecyclerView.Adapter<FilterThumbnailAdapter.HolderThumbnail>() {

    private var filterList = mutableListOf<ThumbnailItem>()

    fun setListData(items: MutableList<ThumbnailItem>) {
        filterList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderThumbnail =
        HolderThumbnail(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_filter, parent, false)
        )

    override fun onBindViewHolder(holder: HolderThumbnail, position: Int) {
        holder.bindView(filterList[position], listener)
    }

    override fun getItemCount(): Int = if (filterList.size > 0) filterList.size else 0

    inner class HolderThumbnail(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: ThumbnailItem, listener: OnFilterClickListener) = itemView.run {
            tv_filter_name.text = item.filterName
            iv_preview_filter.load(item.image)
            setOnClickListener {
                listener.onClickItem(item)
            }
        }
    }
}
