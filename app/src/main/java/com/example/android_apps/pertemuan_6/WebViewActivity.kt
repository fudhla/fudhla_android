package com.example.android_apps.pertemuan_6

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupWebView()
        setupBackNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Website Bina Desa"
            setDisplayHomeAsUpEnabled(true)
            // Menggunakan icon back standar android jika diperlukan
            setHomeButtonEnabled(true)
        }
    }

    private fun setupBackNavigation() {
        // Callback untuk menangani tombol back fisik/gesture navigasi
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webViewDesa.canGoBack()) {
                    binding.webViewDesa.goBack()
                } else {
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setupWebView() {
        binding.webViewDesa.apply {
            // Konfigurasi Standar
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true

            // WebViewClient: Memastikan link dibuka di dalam aplikasi, bukan browser eksternal
            webViewClient = WebViewClient()

            // WebChromeClient: Untuk menangani progress bar (loading)
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    binding.progressBar.apply {
                        progress = newProgress
                        visibility = if (newProgress < 100) View.VISIBLE else View.GONE
                    }
                }
            }

            // Load URL Website Anda
            loadUrl("http://fudhla-peminjaman.alwaysdata.net/")        }
    }

    // Menangani klik tombol Back di Toolbar (Panah atas)
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}