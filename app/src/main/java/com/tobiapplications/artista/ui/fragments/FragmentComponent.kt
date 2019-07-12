package com.tobiapplications.artista.ui.fragments


interface FragmentComponent {

    fun getToolbarTitle() : String {
        return "Artista"
    }

    fun isToolbarBackButtonEnabled() : Boolean {
        return false
    }
}