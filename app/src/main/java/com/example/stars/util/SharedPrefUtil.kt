package com.example.stars.view

import android.content.Context

private val DEFAULT_SIGN_VALUE = "sign"
private val STAR_SIGN_PREF = "star_sign_pref"

fun getSelectedSign(context: Context): String {
    val sharedPref = context.getSharedPreferences(STAR_SIGN_PREF,Context.MODE_PRIVATE)
    val selectedSign = sharedPref.getString("sign", DEFAULT_SIGN_VALUE)
    return selectedSign!!
}

fun saveSelectedSign(sign: String, context: Context) {
    val sharedPref = context.getSharedPreferences(STAR_SIGN_PREF,Context.MODE_PRIVATE) ?: return
    with (sharedPref.edit()) {
        putString("sign", sign)
        apply()
    }
}