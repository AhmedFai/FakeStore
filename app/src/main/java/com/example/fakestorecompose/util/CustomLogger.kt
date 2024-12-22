package com.example.fakestorecompose.util

import android.util.Log

sealed class LogLevel {
    data object Debug : LogLevel()
    data object Info : LogLevel()
    data object Warning : LogLevel()
    data object Error : LogLevel()
}

class CustomLogger private constructor() {

    companion object {
        @Volatile
        private var instance: CustomLogger? = null

        fun getInstance(): CustomLogger {
            return instance ?: synchronized(this) {
                instance ?: CustomLogger().also { instance = it }
            }
        }
    }

    fun log(logLevel: LogLevel, TAG: String, message: String) {
        when (logLevel) {
            LogLevel.Debug -> Log.d(TAG, message)
            LogLevel.Info -> Log.i(TAG, message)
            LogLevel.Warning -> Log.w(TAG, message)
            LogLevel.Error -> Log.e(TAG, message)
        }

//        if (BuildConfig.DEBUG) {
//            when (logLevel) {
//                LogLevel.Debug -> Log.d(TAG, message)
//                LogLevel.Info -> Log.i(TAG, message)
//                LogLevel.Warning -> Log.w(TAG, message)
//                LogLevel.Error -> Log.e(TAG, message)
//            }
//        } else {
//            when (logLevel) {
//                LogLevel.Debug -> { }
//                LogLevel.Info -> { }
//                LogLevel.Warning -> { }
//                LogLevel.Error -> { }
//            }
//        }
    }
}

fun LogLevel.log(message: String, TAG: String = "log_") {
    CustomLogger.getInstance().log(this, TAG, message)
}