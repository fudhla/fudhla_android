package com.example.android_apps.pertemuan_6.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.android_apps.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar dan Tombol Back
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set Adapter ke ViewPager2
        val tabsAdapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // Hubungkan TabLayout & ViewPager2 serta Customization Icon + Badge
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    // Menggunakan icon bawaan android agar tidak error mencari file drawable
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_info)

                    // Badge Titik Merah
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Tab B"
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_email)

                    // Badge dengan Angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
            }
        }.attach()
    }
}