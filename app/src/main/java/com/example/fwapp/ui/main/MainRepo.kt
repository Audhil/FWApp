package com.example.fwapp.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.fwapp.model.api.UserDetail
import com.example.fwapp.network.API
import com.example.fwapp.rx.makeFlowableRxConnection
import com.example.fwapp.ui.base.BaseRepo
import com.example.fwapp.util.ErrorLiveData
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.TestScheduler
import javax.inject.Inject

class MainRepo
@Inject
constructor(
    private val api: API,
    errorLiveDataa: ErrorLiveData,
    private val testScheduler: TestScheduler? = null
) : BaseRepo(errorLiveDataa), IMainRepo {

    private val TAG = "TAG"

    val _userListMutableLiveData by lazy {
        MutableLiveData<List<UserDetail>>()
    }

    override fun onSuccess(obj: Any?, tag: String) {
        when (tag) {
            TAG ->
                (obj as? ArrayList<*>)?.let {
                    val userList = arrayListOf<UserDetail>()
                    it.forEach {
                        (it as? UserDetail)?.let {
                            userList.add(it)
                        }
                    }
                    _userListMutableLiveData.value = userList
                }

            else ->
                Unit
        }
    }

    override fun grabUsersFromServer(): Disposable =
        api.grabUsers().makeFlowableRxConnection(this, TAG, testScheduler)
}