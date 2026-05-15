package com.example.android_apps.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.RegisterActivity
import com.example.android_apps.databinding.ActivityLoginBinding
import com.example.android_apps.pertemuan_4.DashboardActivity
import com.example.android_apps.pertemuan_6.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Login
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        // Teks Daftar
        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleLogin() {
        val userTyped = binding.etUsername.text.toString().trim()
        val passTyped = binding.etPassword.text.toString().trim()

        // 1. AMBIL DATA DARI SHAREDPREFERENCES (Hasil Registrasi)
        val pref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val savedUser = pref.getString("username", "")
        val savedPass = pref.getString("password", "")

        when {
            userTyped.isEmpty() || passTyped.isEmpty() -> {
                Toast.makeText(this, "Username dan Password wajib diisi!", Toast.LENGTH_SHORT).show()
            }

            // RULE 1: Jika username sama dengan password (Aturan Praktikum)
            userTyped == passTyped -> {
                lanjutkanKeDashboard(userTyped)
            }

            // RULE 2: Jika cocok dengan data di SharedPreferences (Hasil Register)
            userTyped == savedUser && passTyped == savedPass && savedUser != "" -> {
                lanjutkanKeDashboard(userTyped)
            }

            else -> {
                Toast.makeText(this, "Login Gagal! Akun tidak ditemukan atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun lanjutkanKeDashboard(username: String) {
        // Simpan Session agar user tidak perlu login ulang saat buka app (Pertemuan 6)
        val session = SessionManager(this)
        session.saveLoginStatus(true, username)

        Toast.makeText(this, "Selamat Datang, $username!", Toast.LENGTH_SHORT).show()

        // Pindah ke halaman Dashboard
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish() // Menutup LoginActivity agar tidak bisa balik lagi tekan tombol back
    }
}