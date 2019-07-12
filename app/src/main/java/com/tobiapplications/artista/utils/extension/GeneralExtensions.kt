package com.tobiapplications.artista.utils.extension

import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar

/** Convenience for callbacks/listeners whose return value indicates an event was consumed. */
inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

/**
 * Post the runnable with given delay
 */
fun postDelayed(f: () -> Unit, delay : Long) {
    Handler().postDelayed( { f() }, delay)
}

fun showSnackBar(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}