package com.tobiapplications.artista.utils.repository.base

open class BaseLocalSource<I, T> : LocalSourceDelegate<I, T> {

    private var t : T? = null

    override fun get(): T? {
        return t
    }

    override fun set(response: T?) {
        t = response
    }
}