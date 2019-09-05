package com.tobiapplications.artista.utils.extension

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
