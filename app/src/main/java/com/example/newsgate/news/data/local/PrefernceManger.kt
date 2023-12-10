package com.example.newsgate.news.data.local

import android.content.Context
import android.content.SharedPreferences

class MyPreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("pref_manger", Context.MODE_PRIVATE)

    private fun saveBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun isFirstTimeLaunch(): Boolean {
        val isFirstTime = sharedPreferences.getBoolean("is_first_time_launch", true)

        if (isFirstTime) {
            // Update the flag to indicate that the app has been launched before
            saveBoolean("is_first_time_launch", false)
        }

        return isFirstTime
    }

    fun clearPreferences() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        const val isFirstOpenKey = "firstOpen"
    }
}
