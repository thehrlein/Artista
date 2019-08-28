package com.tobiapplications.artista.ui.activities

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tobiapplications.artista.R
import com.tobiapplications.artista.ui.base.BaseActivity
import com.tobiapplications.artista.ui.fragments.main.MainFragment
import com.tobiapplications.artista.utils.extension.obtainViewModel
import com.tobiapplications.artista.utils.extension.postDelayed
import com.tobiapplications.artista.utils.extension.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainActivityViewModel

    companion object {
        const val FRAGMENT_CONTAINER_ID = R.id.fragment_container
    }

    override fun init(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            initViews()
            initViewModel()
        }
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        postDelayed( { replaceFragment(MainFragment.newInstance(), false) }, 100)
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        viewModel.title.observe(this, Observer { toolbar.title = it })
        viewModel.toolbarBackButton.observe(this, Observer { enableToolbarBackButton(it) })
    }

    private fun enableToolbarBackButton(enable: Boolean) {
        if (enable) {
            toolbar.navigationIcon = getDrawable(R.drawable.ic_arrow_left_white)
            toolbar.setNavigationOnClickListener { onBackPressed() }
        } else {
            toolbar.navigationIcon = null
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }
}
