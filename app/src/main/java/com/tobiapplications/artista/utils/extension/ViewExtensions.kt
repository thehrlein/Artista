package com.tobiapplications.artista.utils.extension

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.functions.Consumer
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by tobias.hehrlein on 07.02.19.
 */

private const val DEFAULT_CLICK_THROTTLE = 1000L
private val DEFAULT_ERROR_CONSUMER = Consumer<Throwable> { throwable -> Timber.d(throwable) }

fun View.onClick(func: () -> Unit) {
    onClick(func, DEFAULT_ERROR_CONSUMER)
}

fun View.onClick(func: () -> Unit, throttleMilliseconds: Long) {
    onClick(func, DEFAULT_ERROR_CONSUMER, throttleMilliseconds)
}

fun View.onClick(func: () -> Unit, error : Consumer<in Throwable>) {
    onClick(func, error, DEFAULT_CLICK_THROTTLE)
}

@SuppressLint("CheckResult")
fun View.onClick(func: () -> Unit, error : Consumer<in Throwable>, throttleMilliseconds : Long) {
    RxView.clicks(this)
            .throttleFirst(throttleMilliseconds, TimeUnit.MILLISECONDS)
            .subscribe(Consumer { func() }, error)
}


fun View.closeKeyboard() {
    post {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}
