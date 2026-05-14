package com.example.android_apps.pertemuan_6

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.android_apps.R
import com.example.android_apps.pertemuan_4.DashboardActivity
import com.google.android.material.button.MaterialButton

/**
 * Fragment untuk menampilkan profil pengguna dan opsi logout.
 */
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Tombol Logout
        // Pastikan ID di fragment_profile.xml adalah btnLogoutInFragment
        val btnLogout = view.findViewById<MaterialButton>(R.id.btnLogoutInFragment)

        btnLogout?.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showLogoutConfirmation() {
        // Memanggil fungsi showLogoutDialog yang ada di DashboardActivity
        val dashboard = activity as? DashboardActivity

        if (dashboard != null) {
            dashboard.showLogoutDialog()
        } else {
            // Fallback jika fragment tidak menempel pada DashboardActivity
            activity?.finish()
        }
    }
}