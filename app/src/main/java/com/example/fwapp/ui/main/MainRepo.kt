package com.example.fwapp.ui.main

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

    val TAG = "TAG"

    override fun onSuccess(obj: Any?, tag: String) {
        super.onSuccess(obj, tag)
    }

    override fun grabUsersFromServer(): Disposable =
        api.grabUsers().makeFlowableRxConnection(this, TAG)
}