package com.example.android_apps.pertemuan_6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_apps.R

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("BinaDesaPrefs", Context.MODE_PRIVATE)

    companion object {
        const val IS_LOGIN = "isLogin"
        const val USERNAME = "username"
    }

    fun saveLoginStatus(isLogin: Boolean, name: String) {
        val editor = prefs.edit()
        editor.putBoolean(IS_LOGIN, isLogin)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(IS_LOGIN, false)
    fun getUsername(): String? = prefs.getString(USERNAME, "User")

    fun logout() {
        prefs.edit().clear().apply()
    }
}