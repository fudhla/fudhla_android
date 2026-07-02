package com.example.android_apps.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.android_apps.R
import com.example.android_apps.pertemuan_3.LoginActivity

class Onboarding3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Membaca layout fragment_onboarding3
        val view = inflater.inflate(R.layout.fragment_onboarding3, container, false)

        // Menghubungkan variabel tombol dengan ID btnStart di XML
        val btnStart = view.findViewById<Button>(R.id.btnStart)

        // Aksi ketika tombol ditekan
        btnStart.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }
}