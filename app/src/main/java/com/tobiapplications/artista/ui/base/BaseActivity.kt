package com.tobiapplications.artista.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init(savedInstanceState)
    }

    abstract fun getLayout() : Int
    abstract fun init(savedInstanceState: Bundle?)
}