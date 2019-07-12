package com.tobiapplications.artista.ui.base

import android.os.Bundle
import com.tobiapplications.artista.utils.mvvm.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init(savedInstanceState)
    }

    abstract fun getLayout() : Int
    abstract fun init(savedInstanceState: Bundle?)
}