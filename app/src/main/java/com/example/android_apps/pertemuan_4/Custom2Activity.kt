package com.example.android_apps.pertemuan_4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_apps.R
import com.example.android_apps.databinding.ActivityCustom2Binding

class Custom2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Aktifkan tampilan layar penuh (Edge-to-Edge)
        enableEdgeToEdge()

        // 2. Inisialisasi View Binding
        binding = ActivityCustom2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Memperbaiki error 'main' dengan menghubungkan ID main di XML
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Ambil data dari Intent (Dikirim dari Dashboard)
        val title = intent.getStringExtra("TITLE") ?: "Balai Desa Fudhla"
        val desc = intent.getStringExtra("DESC") ?: "Fasilitas desa untuk masyarakat."

        // 5. Update data ke UI
        binding.tvTitle.text = title
        binding.tvDesc.text = desc

        // 6. Klik tombol Ajukan Peminjaman
        binding.btnOrder.setOnClickListener {
            Toast.makeText(this, "Permintaan pinjam $title sedang diproses! 🏛️", Toast.LENGTH_LONG).show()
        }

        // 7. Tombol Kembali
        binding.btnBackToDashboard.setOnClickListener {
            finish()
        }
    }
}