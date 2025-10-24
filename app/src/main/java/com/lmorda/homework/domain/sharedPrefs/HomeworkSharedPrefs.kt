package com.lmorda.homework.domain.sharedPrefs

interface HomeworkSharedPrefs {
    fun putLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long? = null): Long?
    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String? = null): String?
    fun remove(key: String)
    fun clear()
}
