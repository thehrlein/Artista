package com.tobiapplications.artista.utils.extension

import androidx.lifecycle.ViewModel
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

inline fun <reified T : ViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}
