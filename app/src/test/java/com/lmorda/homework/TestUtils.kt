package com.lmorda.homework

import androidx.lifecycle.LiveData
import com.jraska.livedata.TestObserver

fun <T> LiveData<T>.testObserver(): TestObserver<T> {
    val observer = TestObserver.test(this)
    observeForever(observer)
    return observer
}
