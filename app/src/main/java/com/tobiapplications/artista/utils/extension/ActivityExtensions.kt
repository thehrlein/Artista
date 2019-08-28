package com.tobiapplications.artista.utils.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tobiapplications.artista.ui.activities.MainActivity
import com.tobiapplications.artista.ui.base.BaseActivity
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

/**
 * Activity extension functions
 */

/**
 * Obtain viewModel. Automatically takes the correct viewModel based on it's fragment variable declaration
 * Bound to activity scope.
 */
inline fun <reified VM : ViewModel> BaseActivity.obtainViewModel() =
        ViewModelProviders.of(this, factory).get(VM::class.java)


inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : BaseActivity {
    return lazy { ViewModelProviders.of(this, direct.instance()).get(VM::class.java) }
}

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

