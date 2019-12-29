package com.example.fwapp.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.fwapp.AppApplication

inline fun Any.showVLog(log: () -> String) =
    FLog.v("---" + this::class.java.simpleName, log())

inline fun Any.showELog(log: () -> String) =
    FLog.e("---" + this::class.java.simpleName, log())

inline fun Any.showDLog(log: () -> String) =
    FLog.d("---" + this::class.java.simpleName, log())

inline fun Any.showILog(log: () -> String) =
    FLog.i("---" + this::class.java.simpleName, log())

inline fun Any.showWLog(log: () -> String) =
    FLog.w("---" + this::class.java.simpleName, log())

object FLog {
    val DEBUG_BOOL = true

    fun v(tag: String, msg: String) {
        if (DEBUG_BOOL)
            Log.v(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (DEBUG_BOOL)
            Log.e(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (DEBUG_BOOL)
            Log.d(tag, msg)
    }

    fun i(tag: String, msg: String) {
        if (DEBUG_BOOL)
            Log.i(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (DEBUG_BOOL)
            Log.w(tag, msg)
    }
}

var toast: Toast? = null

fun Any.showToast(context: Context? = AppApplication.INSTANCE, duration: Int = Toast.LENGTH_SHORT) {
    toast?.cancel()
    toast = when (this) {
        is String ->
            Toast.makeText(context, this, duration)
        is Int ->
            Toast.makeText(context, this, duration)
        else ->
            Toast.makeText(context, "Invalid input to Toast! :-(", duration)
    }
    toast?.show()
}