package com.moneybox.minib.core.data.store

import android.content.SharedPreferences
import javax.inject.Inject

const val HIDDEN_TEXT = "hidden_text"

interface EncryptedPrefManager {
    fun saveToken(resource: String)
    fun getToken(): String?
}

class EncryptedPrefManagerImpl @Inject constructor(
    private val encryptedPreferences: SharedPreferences
) : EncryptedPrefManager {
    override fun saveToken(resource: String) {
        encryptedPreferences.edit().putString(HIDDEN_TEXT, resource).apply()
    }

    override fun getToken(): String?  =
        encryptedPreferences.getString(HIDDEN_TEXT, null)
}