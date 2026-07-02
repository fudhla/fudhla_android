package com.example.android_apps.data.model

// Model untuk cek status ringkas aula/ruang hari ini
data class StatusRuangModel(
    val namaRuang: String,
    val status: String,
    val catatan: String
)

// Model untuk daftar seluruh ruangan desa dari API
data class RuangModel(
    val id: String,
    val author: String, // Meniru skema picsum untuk simulasi penanggung jawab ruang
    val download_url: String // URL foto gedung dari internet
)