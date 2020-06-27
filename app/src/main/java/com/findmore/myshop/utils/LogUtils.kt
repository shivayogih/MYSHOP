package com.findmore.myshop.utils

import android.util.Log

/**
 * Created by Shivayogi Hiremath on 07,June,2020
 *
 */
/**
 * This class is used to print the logs.
 * if you dont want to print the logs make LOG_ENABLE=false.(like in production build).
 *
 */
internal object LogUtils {
    private const val LOG_ENABLE = true
    private const val TAG = "GENOVA:"
    @JvmStatic
    fun e(message: String?) {
        if (message != null && LOG_ENABLE) {
            Log.e(TAG, message)
        }
    }

    fun d(message: String?) {
        if (message != null && LOG_ENABLE) {
            Log.d(TAG, message)
        }
    }

    fun i(message: String?) {
        if (message != null && LOG_ENABLE) {
            Log.i(TAG, message)
        }
    }

    fun v(message: String?) {
        if (message != null && LOG_ENABLE) {
            Log.v(TAG, message)
        }
    }
}