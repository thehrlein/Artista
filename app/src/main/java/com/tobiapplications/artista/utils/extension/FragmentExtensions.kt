package com.tobiapplications.artista.utils.extension

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tobiapplications.artista.ui.activities.MainActivity
import com.tobiapplications.artista.ui.base.BaseFragment

/**
 * Divided into fragment and dialog fragment methods
 */


/**
 *  < =============================== Fragments =============================== >
 */

/**
 * Obtain viewModel. Automatically takes the correct viewModel based on it's fragment variable declaration
 * Bound to fragment scope.
 */
inline fun <reified VM : ViewModel> BaseFragment.obtainViewModel() =
        ViewModelProviders.of(this, factory).get(VM::class.java)

/**
 * Create a toast by simple calling "toast(message)"
 * @param messageRes non-null int res
 */
fun BaseFragment.toast(messageRes : Int) {
    toast(getString(messageRes))
}

/**
 * Create a toast by simple calling "toast(message)"
 * @param message non-null string
 */
fun BaseFragment.toast(message : String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

/**
 * Get Color from resources
 */
fun BaseFragment.getColor(res: Int) : Int {
    return ContextCompat.getColor(requireContext(), res)
}

fun BaseFragment.replaceFragment(fragment: BaseFragment, container: Int = MainActivity.FRAGMENT_CONTAINER_ID, addToStack: Boolean = true) {
    fragmentManager?.beginTransaction()?.apply {
        replace(container, fragment)
        if (addToStack) {
            addToBackStack(null)
        }
        commitAllowingStateLoss()
    }
}