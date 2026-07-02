package com.example.android_apps.pertemuan_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_apps.data.model.RuangModel
import com.example.android_apps.databinding.ItemRuangBinding

class RuangAdapter(private val items: List<RuangModel>) : RecyclerView.Adapter<RuangAdapter.RuangViewHolder>() {

    inner class RuangViewHolder(val binding: ItemRuangBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuangViewHolder {
        val binding = ItemRuangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RuangViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RuangViewHolder, position: Int) {
        val item = items[position]

        // Memeriksa apakah ini data dari internet atau data dummy cadangan
        if (item.id.contains("Aula") || item.id.contains("Ruang") || item.id.contains("Gedung")) {
            // Jika data dummy cadangan yang jalan
            holder.binding.tvNamaRuang.text = item.id
            holder.binding.tvKapasitas.text = item.author
        } else {
            // JIKA API INTERNET YANG JALAN (Kodingan asli Anda tidak hilang)
            holder.binding.tvNamaRuang.text = "Gedung / Aula Pertemuan #${item.id}"
            holder.binding.tvKapasitas.text = "PJ Fasilitas: ${item.author}"
        }

        // Mengunduh foto menggunakan Glide (Bisa dari url API picsum atau url unsplash)
        Glide.with(holder.itemView.context)
            .load(item.download_url)
            .centerCrop()
            .into(holder.binding.imgRuang)
    }

    override fun getItemCount(): Int = items.size
}