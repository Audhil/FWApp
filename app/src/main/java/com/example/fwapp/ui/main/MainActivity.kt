package com.example.fwapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.fwapp.BR
import com.example.fwapp.R
import com.example.fwapp.databinding.ActivityMainBinding
import com.example.fwapp.ui.base.BaseLifeCycleActivity
import com.example.fwapp.util.NetworkError
import com.example.fwapp.util.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseLifeCycleActivity<ActivityMainBinding, MainViewModel>() {

    override fun getBindingVariable(): Int = BR.view_model

    override fun initErrorObserver() =
        viewModel.errorLiveData.observe(this, Observer {
            when (it) {
                NetworkError.UNKNOWN,
                NetworkError.SOCKET_TIMEOUT,
                NetworkError.DISCONNECTED,
                NetworkError.BAD_URL ->
                    getString(R.string.something_went_wrong).showToast()

                else ->
                    Unit
            }
        })

    override fun getLayoutId(): Int = R.layout.activity_main

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort -> {
                "sort".showToast()
            }

            R.id.action_search ->
                "search".showToast()

            else ->
                Unit
        }
        return super.onOptionsItemSelected(item)
    }
}