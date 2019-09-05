package com.tobiapplications.artista.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tobiapplications.artista.ui.fragments.FragmentComponent
import com.tobiapplications.artista.utils.general.CoreService
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope


abstract class BaseFragment : Fragment(), FragmentComponent {

    private val coreService: CoreService by inject()

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

