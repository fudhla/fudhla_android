package com.example.android_apps.pertemuan_4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.android_apps.R
import com.example.android_apps.pertemuan_3.LoginActivity
import com.example.android_apps.pertemuan_3.FragmentMore
import com.example.android_apps.pertemuan_6.AboutFragment
// 🔄 DIUBAH: Mengubah import ke alamat HomeFragment yang baru (Pertemuan 10) agar fitur reminder aktif
import com.example.android_apps.pertemuan_6.pertemuan_10.HomeFragment
import com.example.android_apps.pertemuan_6.ProfileFragment
import com.example.android_apps.pertemuan_6.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    // Variabel global untuk session
    private lateinit var session: SessionManager
    // ➕ TAMBAHAN BARU: Referensi global bottomNav agar bisa diakses dari fungsi handleNotificationIntent
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inisialisasi session
        session = SessionManager(this)

        // 1. Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Bina Desa - Dashboard"

        // 2. Default Fragment saat pertama kali dibuka
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // 3. Bottom Navigation dengan 3 Menu Utama (Home, Riwayat, Profile)
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())

                // Mengarahkan menu Riwayat ke FragmentMore (ListView) sesuai materi baru
                R.id.menu_more -> replaceFragment(FragmentMore())

                R.id.nav_profile -> replaceFragment(ProfileFragment())
                else -> false
            }
        }

        // ➕ TAMBAHAN BARU: Cek apakah halaman diakses via klik data pendingintent Notifikasi Pengingat
        handleNotificationIntent(intent)
    } // <- Kurung kurawal tutup onCreate

    // ➕ TAMBAHAN BARU: Menangkap intent baru jika activity sedang aktif di background
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleNotificationIntent(intent)
    }

    // ➕ TAMBAHAN BARU: Fungsi memindahkan halaman secara relevan ke Tab Riwayat (FragmentMore) saat notifikasi diklik
    private fun handleNotificationIntent(intent: Intent?) {
        val destination = intent?.getStringExtra("OPEN_FRAGMENT")
        if (destination == "RIWAYAT") {
            // Mengubah status pilihan item menu bottom nav secara visual ke menu_more
            bottomNav.selectedItemId = R.id.menu_more
            // Mengganti fragment kontainer utama menjadi FragmentMore (Halaman Riwayat)
            replaceFragment(FragmentMore())
        }
    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }

    // Fungsi tambahan jika kamu butuh ambil nama di fragment
    fun getLoggedInUser(): String {
        return session.getUsername() ?: "User"
    }

    fun performLogout() {
        session.saveLoginStatus(false, "")

        val sharedPref = getSharedPreferences("BinaDesaPrefs", Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()

        Snackbar.make(
            findViewById(android.R.id.content),
            "Berhasil logout",
            Snackbar.LENGTH_SHORT
        ).show()

        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        startActivity(intent)
        finish()
    }

    fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Yakin ingin keluar dari application?")
            .setPositiveButton("Ya") { _, _ ->
                performLogout()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
} // <- Kurung kurawal tutup akhir Class DashboardActivity