package com.example.fwapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.example.fwapp.BR
import com.example.fwapp.R
import com.example.fwapp.databinding.ActivityMainBinding
import com.example.fwapp.model.api.UserDetail
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

    private val adapter by lazy {
        ContactsAdapter()
    }

    private var reverseToggle = false
    private val reversedList by lazy {
        arrayListOf<UserDetail>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.contacts)
        initRecyclerView()
        initDataObserver()
        viewModel.getUsers()
    }

    private fun initRecyclerView() {
        viewDataBinding.recyclerView.adapter = adapter
    }

    private fun initDataObserver() {
        viewModel.userListLiveData.observe(this, Observer {
            viewModel.progressVisibility.set(false)
            adapter.addItem(it.sortedBy { it.name })
        })
    }

    var searchView: SearchView? = null
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        searchView = (menu.findItem(R.id.action_search).actionView as SearchView)
        searchView?.apply {
            queryHint = getString(R.string.search_contacts_hint)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    adapter.filter.filter(newText)
                    viewDataBinding.root.postDelayed(
                        { viewModel.isListEmpty.set(adapter.itemCount == 0) },
                        10
                    )
                    return true
                }
            })
            imeOptions = EditorInfo.IME_ACTION_DONE
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_sort -> {
                (if (reverseToggle)
                    "sorted (A-Z)!"
                else
                    "sorted (Z-A)!").showToast()
                reverseToggle = !reverseToggle
                reversedList.apply {
                    clear()
                    addAll(adapter.listItemsFull.asReversed())
                }
                adapter.addItem(reversedList)
            }

            else ->
                Unit
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (searchView != null && !searchView?.isIconified!!) {
            searchView?.isIconified = true
            return
        }
        super.onBackPressed()
    }
}