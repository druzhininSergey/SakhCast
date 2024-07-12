package com.example.sakhcast.data.firebase_messaging

import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

class FirebaseCrashReporter @Inject constructor(
    private val firebaseCrashlytics: FirebaseCrashlytics
) : CrashReporter {
    override fun logError(message: String) {
        firebaseCrashlytics.log(message)
    }

    override fun setCustomKey(key: String, value: String) {
        firebaseCrashlytics.setCustomKey(key, value)
    }

    override fun recordException(throwable: Throwable) {
        firebaseCrashlytics.recordException(throwable)
    }
}