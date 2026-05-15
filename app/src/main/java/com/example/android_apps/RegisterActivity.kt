package com.example.android_apps

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Spinner Agama
        val agamaList = arrayOf("Pilih Agama", "Islam", "Kristen", "Katolik", "Hindu", "Buddha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agamaList)
        binding.spAgama.adapter = adapter

        // 2. Setup DatePicker
        binding.etTanggal.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(this, { _, y, m, d ->
                binding.etTanggal.setText("$d/${m + 1}/$y")
                binding.etTanggal.error = null // Hilangkan error jika sudah diisi
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        // 3. Tombol Submit Registrasi
        binding.btnRegister.setOnClickListener {
            jalankanValidasi()
        }
    }

    private fun jalankanValidasi() {
        val nama = binding.etNama.text.toString().trim()
        val tanggal = binding.etTanggal.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirm = binding.etConfirmPassword.text.toString().trim()

        // Ambil value RadioButton
        val gender = if (binding.rbLaki.isChecked) "Laki-Laki"
        else if (binding.rbPerempuan.isChecked) "Perempuan"
        else ""

        // Ambil value Spinner
        val agama = binding.spAgama.selectedItem.toString()

        var isValid = true

        // Cek Nama
        if (nama.isEmpty()) {
            binding.etNama.error = "Nama lengkap tidak boleh kosong"
            isValid = false
        }

        // Cek Tanggal
        if (tanggal.isEmpty()) {
            binding.etTanggal.error = "Harap pilih tanggal lahir"
            isValid = false
        }

        // Cek Gender (Karena RadioButton tidak punya .error, kita pakai cara alternatif)
        if (gender.isEmpty()) {
            binding.rbPerempuan.error = "Pilih salah satu"
            isValid = false
        } else {
            binding.rbPerempuan.error = null
        }

        // Cek Agama
        if (agama == "Pilih Agama") {
            val errorText = binding.spAgama.selectedView as TextView
            errorText.error = "Pilih agama"
            errorText.text = "Pilih agama" // Menampilkan teks error di spinner
            isValid = false
        }

        // Cek Username
        if (username.isEmpty()) {
            binding.etUsername.error = "Username wajib diisi"
            isValid = false
        }

        // Cek Password
        if (password.isEmpty()) {
            binding.etPassword.error = "Password tidak boleh kosong"
            isValid = false
        }

        // Cek Konfirmasi Password
        if (confirm != password) {
            binding.etConfirmPassword.error = "Konfirmasi password tidak cocok"
            isValid = false
        } else if (confirm.isEmpty()) {
            binding.etConfirmPassword.error = "Ulangi password di sini"
            isValid = false
        }

        // JIKA SEMUA VALID -> SIMPAN KE SHAREDPREFERENCES
        if (isValid) {
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("nama", nama)
            editor.putString("tanggal", tanggal)
            editor.putString("gender", gender)
            editor.putString("agama", agama)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            // Hanya gunakan Toast untuk sukses saja
            Toast.makeText(this, "Data Berhasil Disimpan!", Toast.LENGTH_LONG).show()

            // Tutup halaman register dan kembali ke Login
            finish()
        }
    }
}