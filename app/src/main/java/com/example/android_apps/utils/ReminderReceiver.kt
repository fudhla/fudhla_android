package com.example.android_apps.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Peringatan Bina Desa"
        val message = intent.getStringExtra("message") ?: "Jadwal peminjaman ruangan Anda segera tiba."

        // 🎯 KUNCI 1: Arahkan ke MainActivity sebagai gerbang utama proyek Anda
        val targetIntent = Intent(context, Class.forName("com.example.android_apps.MainActivity")).apply {
            putExtra("OPEN_FRAGMENT", "RIWAYAT")
            // Tambahkan flag agar Activity yang sudah ada tidak ditumpuk berulang kali
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}