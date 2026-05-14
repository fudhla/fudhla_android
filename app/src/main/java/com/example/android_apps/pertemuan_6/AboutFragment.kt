package com.example.android_apps.pertemuan_6

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.android_apps.R

/**
 * Fragment untuk menampilkan informasi aplikasi (About).
 */
class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Jika Anda ingin melakukan inisialisasi view (seperti tombol atau teks),
        // lakukan di sini. Contoh:
        // val tvVersion = view.findViewById<TextView>(R.id.tvVersion)
    }
}