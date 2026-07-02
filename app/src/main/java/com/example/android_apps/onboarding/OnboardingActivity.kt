package com.example.android_apps.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_apps.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // List fragment onboarding bertema Bina Desa
        val onboardingFragments = listOf(
            Onboarding1Fragment(),
            Onboarding2Fragment(),
            Onboarding3Fragment()
        )

        // Setup adapter ke ViewPager2
        val adapter = OnboardingAdapter(this, onboardingFragments)
        binding.viewPagerOnboarding.adapter = adapter

        // Hubungkan dots indicator dengan ViewPager2
        binding.dotIndicator.attachTo(binding.viewPagerOnboarding)
    }
}