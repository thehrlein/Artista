package com.tobiapplications.artista.utils.extension

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import java.text.NumberFormat
import java.util.*


fun Int.formatTime() : String {
    val min = (this / 60).toString().padStart(2, '0')
    val sec = (this % 60).toString().padStart(2, '0')
    return "$min:$sec min"
}

fun Long.formatThousand() : String {
    return NumberFormat.getNumberInstance(Locale.GERMAN).format(this)
}

/**
 * Apply's basic schedulers
 */
fun <T> Observable<T>.applyScheduler() : Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


inline fun <reified T : ViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}
