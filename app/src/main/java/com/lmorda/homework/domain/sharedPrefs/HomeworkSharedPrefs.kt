package com.lmorda.homework.domain.sharedPrefs

interface HomeworkSharedPrefs {
    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String? = null): String?
    fun remove(key: String)
    fun clear()
}
