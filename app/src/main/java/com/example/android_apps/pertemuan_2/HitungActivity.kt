package com.example.android_apps.pertemuan_2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.databinding.ActivityHitungBinding // Pastikan binding sudah aktif
import com.example.android_apps.pertemuan_4.DashboardActivity

class HitungActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHitungBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityHitungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Ambil data Intent dengan safe call
        val title = intent.getStringExtra("TITLE") ?: "Menu Hitung"
        binding.tvTitleHeader.text = title // Set ke header ungu

        setupMenuSwitcher()
        setupKalkulator()

        // 3. Tombol Kembali
        binding.btnBackToDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

    private fun setupMenuSwitcher() {
        binding.menuSegitiga.setOnClickListener {
            updateUI(isSegitiga = true)
        }

        binding.menuKubus.setOnClickListener {
            updateUI(isSegitiga = false)
        }
    }

    private fun updateUI(isSegitiga: Boolean) {
        // Switch Layout
        binding.layoutSegitiga.visibility = if (isSegitiga) View.VISIBLE else View.GONE
        binding.layoutKubus.visibility = if (isSegitiga) View.GONE else View.VISIBLE

        // Switch Button Style (Segitiga)
        binding.menuSegitiga.setBackgroundColor(if (isSegitiga) Color.parseColor("#7C3AED") else Color.TRANSPARENT)
        binding.menuSegitiga.setTextColor(if (isSegitiga) Color.WHITE else Color.parseColor("#7C3AED"))

        // Switch Button Style (Kubus)
        binding.menuKubus.setBackgroundColor(if (isSegitiga) Color.TRANSPARENT else Color.parseColor("#7C3AED"))
        binding.menuKubus.setTextColor(if (isSegitiga) Color.WHITE else Color.parseColor("#7C3AED"))

        binding.txtHasil.text = "0.0"
    }

    private fun setupKalkulator() {
        // Hitung Segitiga
        binding.btnSegitiga.setOnClickListener {
            val a = binding.alas.text.toString()
            val t = binding.tinggi.text.toString()

            if (a.isNotEmpty() && t.isNotEmpty()) {
                val hasil = 0.5 * a.toDouble() * t.toDouble()
                binding.txtHasil.text = "Luas: $hasil cm²"
            } else {
                showToast("Isi alas dan tinggi!")
            }
        }

        // Hitung Kubus
        binding.btnKubus.setOnClickListener {
            val s = binding.sisi.text.toString()

            if (s.isNotEmpty()) {
                val hasil = Math.pow(s.toDouble(), 3.0)
                binding.txtHasil.text = "Volume: $hasil cm³"
            } else {
                showToast("Isi sisi kubus!")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}