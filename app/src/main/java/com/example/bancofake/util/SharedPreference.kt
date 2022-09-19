package com.example.bancofake.util

import android.content.Context
import android.content.SharedPreferences


class SharedPreference(context: Context) {
    private val BANCO_FAKE_SHARED = "BANCO_FAKE_SHARED"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(BANCO_FAKE_SHARED, Context.MODE_PRIVATE)

    fun salvarDados (key: String, value: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getDado(key: String): Long = sharedPref.getLong(key,0)

}