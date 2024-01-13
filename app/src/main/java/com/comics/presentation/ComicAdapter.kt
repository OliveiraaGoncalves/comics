package com.comics.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comics.databinding.ItemListBinding
import com.comics.domain.Item

class ComicAdapter(
    private val itens: List<Item>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(item = itens[position])
    }

    override fun getItemCount(): Int = itens.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getItemHolder(parent = parent)

    private fun getItemHolder(parent: ViewGroup) = ItemViewHolder(
        itemBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    private class ItemViewHolder(val itemBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            item: Item,
        ) = with(itemBinding) {

            Glide.with(itemBinding.root)
                .load(item.image)
                .centerCrop()
                .into(itemBinding.actionImage)

            actionTitle.text = item.title
            actionSubTitle.text = item.subtitle
        }
    }

}
