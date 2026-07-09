package com.example.android_apps.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

object ReminderHelper {
    fun setReminderMinutes(
        context: Context,
        minutesFromNow: Int,
        title: String,
        message: String,
        targetActivityName: String
    ) {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.MINUTE, minutesFromNow) // Menambahkan sekian menit dari waktu sekarang
        }

        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("title", title)
            putExtra("message", message)
            putExtra("target_activity", targetActivityName)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            101, // ID unik untuk reminder peminjaman ruang
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}