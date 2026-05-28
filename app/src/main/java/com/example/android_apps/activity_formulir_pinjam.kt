package com.example.android_apps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.pertemuan_4.DashboardActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class activity_formulir_pinjam : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Memanggil file layout XML Anda dengan benar
        setContentView(R.layout.activity_formulir_pinjam)

        // 1. Inisialisasi TextInputLayout untuk efek error merah
        val tlNama = findViewById<TextInputLayout>(R.id.tlNama)
        val tlPhone = findViewById<TextInputLayout>(R.id.tlPhone)
        val tlAlasan = findViewById<TextInputLayout>(R.id.tlAlasan)

        // 2. Inisialisasi EditText untuk mengambil input teks
        val etNama = findViewById<TextInputEditText>(R.id.etNama)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
        val etAlasan = findViewById<TextInputEditText>(R.id.etAlasan)

        // 3. Inisialisasi Tombol Kirim dan Tombol Kembali
        val btnSubmitPinjam = findViewById<MaterialButton>(R.id.btnSubmitPinjam)
        val btnKembaliDashboard = findViewById<MaterialButton>(R.id.btnKembaliDashboard)

        // 4. Logika Validasi ketika tombol kirim ditekan
        btnSubmitPinjam?.setOnClickListener {
            val nama = etNama?.text.toString().trim()
            val phone = etPhone?.text.toString().trim()
            val alasan = etAlasan?.text.toString().trim()

            // Reset error teks
            tlNama?.error = null
            tlPhone?.error = null
            tlAlasan?.error = null

            if (nama.isEmpty()) {
                tlNama?.error = "Nama peminjam wajib diisi!"
            } else if (phone.isEmpty()) {
                tlPhone?.error = "Nomor kontak tidak boleh kosong!"
            } else if (alasan.isEmpty()) {
                tlAlasan?.error = "Harap tentukan keperluan acara!"
            } else if (alasan.length > 50) {
                tlAlasan?.error = "Teks melebihi batas 50 karakter!"
            } else {
                Toast.makeText(this, "Formulir $nama berhasil diajukan! 🏛️", Toast.LENGTH_LONG).show()
                // Jika sukses kirim, otomatis balik ke dashboard utama
                keDashboardUtama()
            }
        }

        // 5. Logika ketika tombol "Kembali ke Dashboard Utama" ditekan
        btnKembaliDashboard?.setOnClickListener {
            keDashboardUtama()
        }
    }

    // Fungsi untuk kembali ke DashboardActivity dan menghapus tumpukan history halaman Welcome
    private fun keDashboardUtama() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}