package com.tobiapplications.artista.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


abstract class BaseActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    val factory: ViewModelProvider.Factory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init(savedInstanceState)
    }

    abstract fun getLayout() : Int
    abstract fun init(savedInstanceState: Bundle?)
}