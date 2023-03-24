package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class PreferenceUtil(context: Context) {
    private val prefs : SharedPreferences =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    fun get(key: String, value : String) : String{
        return prefs.getString(key, value).toString()
    }
    fun get(key: String, value : Int) : Int{
        return prefs.getInt(key, value)
    }



    fun set(key: String, value : String){
        return prefs.edit().putString(key, value).apply()
    }
    fun set(key: String, value : Int){
        return prefs.edit().putInt(key, value).apply()
    }

}