package com.example.filmax.util

import android.content.Context
import android.content.SharedPreferences
import java.nio.channels.spi.AbstractSelectionKey

class Preference (val context: Context) {
    companion object {
        const val USER_PREFF = "USER_PREFF"
    }

    var sharedPreferences = context.getSharedPreferences(USER_PREFF, 0)

    fun setValue(key: String, value: String){
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun getValues (key: String) : String? {
        return sharedPreferences.getString(key, "")
    }
}