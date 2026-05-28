package com.example.android_apps.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.R
import com.example.android_apps.activity_formulir_pinjam
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // 1. Sinkronisasi nama: Memastikan teks yang muncul tetap "Fudhla"
        var username = intent.getStringExtra("USERNAME") ?: "Fudhla"
        if (username == "Nabila" || username == "Warga") {
            username = "Fudhla"
        }
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        tvWelcome?.text = "Halo $username! 👋"

        // 2. Inisialisasi komponen tombol utama bawaan
        val btnDetailInventaris = findViewById<MaterialButton>(R.id.btnDetailInventaris)
        val btnBackToDashboard = findViewById<MaterialButton>(R.id.btnBackToDashboard)

        // 3. Inisialisasi ChipGroup dan Logika Filter Kategori
        val chipGroupFilter = findViewById<ChipGroup>(R.id.chipGroupFilter)

        chipGroupFilter?.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull() // Mengambil ID chip yang aktif dipilih
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                // Menampilkan Toast notifikasi saat filter kategori ditekan warga
                Toast.makeText(this, "Kategori: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. [MATERI BARU] Inisialisasi & Logika Klik Tombol Fasilitas di dalam GridLayout
        val btnFasilitasBalai = findViewById<MaterialButton>(R.id.btnFasilitasBalai)
        val btnFasilitasSport = findViewById<MaterialButton>(R.id.btnFasilitasSport)
        val btnFasilitasMobil = findViewById<MaterialButton>(R.id.btnFasilitasMobil)
        val btnFasilitasTenda = findViewById<MaterialButton>(R.id.btnFasilitasTenda)
        val btnFasilitasAudio = findViewById<MaterialButton>(R.id.btnFasilitasAudio)
        val btnFasilitasTani = findViewById<MaterialButton>(R.id.btnFasilitasTani)

        btnFasilitasBalai?.setOnClickListener {
            Toast.makeText(this, "Menampilkan Detail Sewa Gedung Balai Desa", Toast.LENGTH_SHORT).show()
        }

        btnFasilitasSport?.setOnClickListener {
            Toast.makeText(this, "Menampilkan Jadwal Lapangan Olahraga", Toast.LENGTH_SHORT).show()
        }

        // Khusus tombol Mobil Siaga, kita buat langsung berpindah ke halaman Formulir Peminjaman
        btnFasilitasMobil?.setOnClickListener {
            val intentKeFormulir = Intent(this, activity_formulir_pinjam::class.java)
            startActivity(intentKeFormulir)
        }

        btnFasilitasTenda?.setOnClickListener {
            Toast.makeText(this, "Membuka Inventaris Tenda & Kursi RT", Toast.LENGTH_SHORT).show()
        }

        btnFasilitasAudio?.setOnClickListener {
            Toast.makeText(this, "Membuka Status Peminjaman Sound System", Toast.LENGTH_SHORT).show()
        }

        btnFasilitasTani?.setOnClickListener {
            Toast.makeText(this, "Membuka Layanan Peminjaman Alat Pertanian", Toast.LENGTH_SHORT).show()
        }

        // 5. Aksi tombol menuju halaman Formulir Peminjaman (Card Utama)
        btnDetailInventaris?.setOnClickListener {
            val intentKeFormulir = Intent(this, activity_formulir_pinjam::class.java)
            startActivity(intentKeFormulir)
        }

        // 6. Aksi kembali ke Dashboard utama
        btnBackToDashboard?.setOnClickListener {
            finish()
        }
    }
}