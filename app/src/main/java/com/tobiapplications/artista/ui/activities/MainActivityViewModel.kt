package com.tobiapplications.artista.ui.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobiapplications.artista.utils.general.CoreService
import rx.Observer

class MainActivityViewModel constructor(private val coreService: CoreService) : ViewModel() {

    var title = MutableLiveData<String>()
    var toolbarBackButton = MutableLiveData<Boolean>()

    init {
        observeTitle()
        observeMenuBackButton()
    }

    private fun observeTitle() {
        coreService.subscribeTitle(object : Observer<String> {
            override fun onError(e: Throwable?) {}
            override fun onNext(t: String?) { title.value = t }
            override fun onCompleted() {}
        })
    }

    private fun observeMenuBackButton() {
        coreService.subscribeToolbarBackButton(object : Observer<Boolean> {
            override fun onError(e: Throwable?) {}
            override fun onNext(t: Boolean?) { toolbarBackButton.value = t }
            override fun onCompleted() {}
        })
    }
}