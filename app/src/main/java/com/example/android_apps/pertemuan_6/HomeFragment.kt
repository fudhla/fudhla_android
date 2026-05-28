package com.example.android_apps.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.android_apps.R
import com.example.android_apps.pertemuan_2.HitungActivity
import com.example.android_apps.pertemuan_3.WelcomeActivity
import com.example.android_apps.pertemuan_4.Custom2Activity
import com.google.android.material.button.MaterialButton

/**
 * Fragment utama (Home) yang berisi navigasi ke berbagai fitur aplikasi.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Tombol Utama Dashboard
        val btn1 = view.findViewById<MaterialButton>(R.id.btn1)
        val btn2 = view.findViewById<MaterialButton>(R.id.btn2)
        val btn3 = view.findViewById<MaterialButton>(R.id.btn3)
        val btnWelcome = view.findViewById<MaterialButton>(R.id.btnWelcome)

        // 2. Logika Navigasi (Intent) Menuju Masing-Masing Halaman

        // Pindah ke HitungActivity (Pertemuan 2)
        btn1.setOnClickListener {
            startActivity(Intent(requireContext(), HitungActivity::class.java))
        }

        // Pindah ke Custom2Activity (Pertemuan 4)
        btn2.setOnClickListener {
            startActivity(Intent(requireContext(), Custom2Activity::class.java))
        }

        // Pindah ke WebViewActivity (Pertemuan 6)
        btn3.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Pindah ke WelcomeActivity dengan membawa data Nama
        btnWelcome.setOnClickListener {
            val intent = Intent(requireContext(), WelcomeActivity::class.java).apply {
                putExtra("USERNAME", "Fudhla")
            }
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Factory method untuk membuat instance baru dari fragment ini.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}