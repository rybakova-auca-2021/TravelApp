package com.example.travelapp.presentation.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.travelapp.data.model.PlaceModel
import com.example.travelapp.databinding.PackageCardBinding

class PackagesAdapter(private var items: List<PlaceModel>) :
    RecyclerView.Adapter<PackagesAdapter.ItemsViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: PlaceModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PackageCardBinding.inflate(inflater, parent, false)

        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newList: List<PlaceModel>) {
        val diffResult = DiffUtil.calculateDiff(
            DisplayableItemDiffCallback(
                items,
                newList
            )
        )
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ItemsViewHolder(private val binding: PackageCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = items[position]
                    itemClickListener?.onItemClick(clickedItem)
                }
            }
        }

        fun bind(item: PlaceModel) {
            binding.titleCard.text = item.name
            Glide.with(binding.imageCard.context)
                .load(item.main_image)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.imageCard)
        }
    }

    class DisplayableItemDiffCallback(
        private val oldList: List<PlaceModel>,
        private val newList: List<PlaceModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
