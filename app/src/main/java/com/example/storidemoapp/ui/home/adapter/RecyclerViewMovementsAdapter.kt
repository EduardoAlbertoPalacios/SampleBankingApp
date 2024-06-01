package com.example.storidemoapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.MovementsEntity
import com.example.shared.extensions.formatNumber
import com.example.storidemoapp.databinding.MovementsItemBinding

class RecyclerViewMovementsAdapter(private val context: Context) :
    ListAdapter<MovementsEntity, RecyclerViewMovementsAdapter.MovementsViewHolder>(Diff) {

    object Diff : DiffUtil.ItemCallback<MovementsEntity>() {
        override fun areItemsTheSame(oldItem: MovementsEntity, newItem: MovementsEntity): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MovementsEntity,
            newItem: MovementsEntity
        ): Boolean =
            oldItem == newItem
    }

    class MovementsViewHolder(private val binding: MovementsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovementsEntity, context: Context) {
            binding.cardMovementsItemTxtTitle.text = item.title
            binding.cardMovementsItemTxtDescription.text = item.description
            binding.cardMovementsItemTxtAmount.let {
                val colorAmount =
                    if (item.amount > 0) com.google.android.material.R.color.design_dark_default_color_secondary
                    else com.google.android.material.R.color.design_dark_default_color_error

                it.text = item.amount.formatNumber()
                it.setTextColor(
                    ContextCompat.getColor(
                        context,
                        colorAmount
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsViewHolder =
        MovementsViewHolder(
            MovementsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovementsViewHolder, position: Int) {
        holder.bind(getItem(position), context)
    }
}