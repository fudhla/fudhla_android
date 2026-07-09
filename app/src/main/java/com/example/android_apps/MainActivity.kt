package com.example.android_apps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast // ➕ TAMBAHAN IMPORT BARU
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_apps.databinding.ActivityMainBinding
import com.example.android_apps.pertemuan_3.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Aktifkan fitur Edge-to-Edge
        enableEdgeToEdge()

        // 2. Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Handle Window Insets menggunakan binding.root
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ➕ TAMBAHAN BARU: Memeriksa apakah activity dibuka via klik notifikasi sebelum pindah halaman
        handleNotificationIntent(intent)

        // 4. Navigasi Otomatis ke LoginActivity
        navigateToLogin()
    }

    // ➕ TAMBAHAN BARU: Menangani event jika notifikasi diklik saat aplikasi sedang berjalan di background
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleNotificationIntent(intent)
    }

    // ➕ TAMBAHAN BARU: Fungsi pendeteksi data navigasi dari ReminderReceiver
    private fun handleNotificationIntent(intent: Intent?) {
        val destination = intent?.getStringExtra("OPEN_FRAGMENT")
        if (destination == "RIWAYAT") {
            // Memunculkan pesan indikator bahwa notifikasi berhasil merespon halaman yang relevan
            Toast.makeText(this, "Membuka Riwayat Peminjaman Ruang Desa...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)

        // 🎯 KUNCI PENYEMPURNAAN LANGKAH 2:
        // Ambil data dari intent Notifikasi secara eksplisit dan masukkan ke dalam loginIntent
        val destination = intent?.getStringExtra("OPEN_FRAGMENT")
        if (destination != null) {
            loginIntent.putExtra("OPEN_FRAGMENT", destination)
        }

        startActivity(loginIntent)

        // Selesaikan MainActivity
        finish()
    }
}