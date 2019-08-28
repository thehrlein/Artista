package com.tobiapplications.artista.utils.general

import rx.Observer
import rx.subjects.PublishSubject

class CoreService {

    private var titleObservable : PublishSubject<String> = PublishSubject.create()
    private var toolbarBackButton : PublishSubject<Boolean> = PublishSubject.create()

    fun setTitle(title : String) {
        titleObservable.onNext(title)
    }

    fun subscribeTitle(observer: Observer<String>) {
        titleObservable.subscribe(observer)
    }

    fun setBackButtonEnabled(enabled: Boolean) {
        toolbarBackButton.onNext(enabled)
    }

    fun subscribeToolbarBackButton(observer: Observer<Boolean>) {
        toolbarBackButton.subscribe(observer)
    }
}