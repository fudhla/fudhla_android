package com.example.android_apps.pertemuan_3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_apps.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    // Inisialisasi binding
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Aktifkan fitur Edge-to-Edge
        enableEdgeToEdge()

        // 2. Setup View Binding
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Atur Padding Sistem (Status Bar & Navigation Bar) agar tidak tertutup konten
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Ambil Data dari Intent (Username dari Login)
        val name = intent.getStringExtra("USERNAME") ?: "Fudhla"

        // 5. Tampilkan data ke UI
        binding.tvWelcome.text = "Halo $name! "

        // 6. Setup Listener Tombol
        setupActionButtons()
    }

    private fun setupActionButtons() {
        // Tombol Detail (ID sesuai desain modern terakhir)
        binding.btnDetailInventaris.setOnClickListener {
            showToast("Membuka detail inventaris... 🏛️")
        }

        // Tombol Kembali
        binding.btnBackToDashboard.setOnClickListener {
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}