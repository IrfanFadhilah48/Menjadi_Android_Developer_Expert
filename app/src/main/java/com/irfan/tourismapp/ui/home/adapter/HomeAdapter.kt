package com.irfan.tourismapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.tourismapp.R
import com.irfan.tourismapp.databinding.ItemTourismBinding
import com.irfan.tourismapp.entity.data.model.Tourism

class HomeAdapter(private val onItemClick:(Int, Tourism) -> Unit): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val dataTourism = arrayListOf<Tourism>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tourism, parent, false)
        )
    }

    override fun getItemCount(): Int = dataTourism.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataTourism[position])
    }

    fun addDataTourism(data: List<Tourism>){
        dataTourism.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTourismBinding.bind(itemView)
        fun bind(data: Tourism){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
                tvItemSubtitle.text = data.address
            }
            binding.root.setOnClickListener {
                onItemClick.invoke(adapterPosition, data)
            }
        }
    }
}