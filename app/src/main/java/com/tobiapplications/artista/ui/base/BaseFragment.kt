package com.tobiapplications.artista.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tobiapplications.artista.ui.fragments.FragmentComponent
import com.tobiapplications.artista.utils.general.CoreService
import com.tobiapplications.artista.utils.mvvm.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


abstract class BaseFragment : Fragment(), FragmentComponent, KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val coreService: CoreService by instance()
    val factory: ViewModelFactory by instance()


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

