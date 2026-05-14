package com.example.android_apps.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.databinding.ActivityLoginBinding
import com.example.android_apps.pertemuan_6.SessionManager
// PERBAIKAN: Pastikan import DashboardActivity lengkap dan sesuai package
import com.example.android_apps.pertemuan_4.DashboardActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Logika Tombol Login
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val user = binding.etUsername.text.toString().trim()
        val pass = binding.etPassword.text.toString().trim()

        when {
            user.isEmpty() || pass.isEmpty() -> {
                showToast("Harap isi username dan password!")
            }
            user.length < 3 -> {
                showToast("Username minimal 3 karakter")
            }
            pass.length < 6 -> {
                showToast("Password minimal 6 karakter")
            }
            else -> {
                // SIMPAN SESSION
                val session = SessionManager(this)
                session.saveLoginStatus(true, user)

                showToast("Selamat Datang, $user!")

                // PINDAH KE DASHBOARD
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}