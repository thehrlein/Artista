package com.tobiapplications.artista.model.exception

import com.tobiapplications.artista.utils.general.ArtistaConstants

class RequestFailedException(val reason: String? = ArtistaConstants.DEFAULT_STRING, val code: Int = 400) : Exception()