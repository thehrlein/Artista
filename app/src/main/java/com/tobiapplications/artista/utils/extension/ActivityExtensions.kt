package com.tobiapplications.artista.utils.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tobiapplications.artista.ui.activities.MainActivity
import com.tobiapplications.artista.ui.base.BaseActivity

/**
 * Activity extension functions
 */


/**
 * Replace current fragment with
 * @param fragment
 *
 * Default: add transaction to backstack
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, addToStack: Boolean = true) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(MainActivity.FRAGMENT_CONTAINER_ID, fragment)
    if (addToStack) {
        transaction.addToBackStack(null)
    }
    transaction.commitAllowingStateLoss()
}

