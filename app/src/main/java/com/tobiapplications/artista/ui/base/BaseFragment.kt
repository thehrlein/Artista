package com.tobiapplications.artista.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tobiapplications.artista.ui.fragments.FragmentComponent
import com.tobiapplications.artista.utils.general.CoreService
import com.tobiapplications.artista.utils.mvvm.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), FragmentComponent {

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var coreService: CoreService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle(getToolbarTitle())
        setToolbarBackButtonEnabled(isToolbarBackButtonEnabled())

        init()
    }

    private fun setToolbarBackButtonEnabled(enabled: Boolean) {
        coreService.setBackButtonEnabled(enabled)
    }

    fun setToolbarTitle(title: String) {
        coreService.setTitle(title)
    }

    abstract fun init()
    abstract fun getLayout(): Int
}

