package com.mx.android.common.extensionFunctions

import android.util.Log
import com.mx.android.common.BuildConfig

fun String.logd(message: String?) {
    if (BuildConfig.DEBUG) Log.d(this, message ?: this)
}