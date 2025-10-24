package com.lmorda.homework.data.sharedPrefs

import android.content.SharedPreferences
import com.lmorda.homework.domain.sharedPrefs.HomeworkSharedPrefs
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class HomeworkSharedPrefsImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : HomeworkSharedPrefs {

    override fun putLong(key: String, value: Long) {
        sharedPreferences.edit { putLong(key, value) }
    }

    override fun getLong(key: String, defaultValue: Long?): Long? =
        defaultValue?.let { sharedPreferences.getLong(key, it) }


    override fun putString(key: String, value: String) {
        sharedPreferences.edit { putString(key, value) }
    }

    override fun getString(key: String, defaultValue: String?): String? =
        sharedPreferences.getString(key, defaultValue)

    override fun remove(key: String) {
        sharedPreferences.edit { remove(key) }
    }

    override fun clear() {
        sharedPreferences.edit { clear() }
    }
}
