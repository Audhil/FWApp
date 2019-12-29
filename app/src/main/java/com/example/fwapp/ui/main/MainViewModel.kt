package com.example.fwapp.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.example.fwapp.ui.base.BaseViewModel
import com.example.fwapp.util.NetworkError
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {

    val progressVisibility = ObservableField<Boolean>(false)

    val errorLiveData: LiveData<NetworkError> = mainRepo.errorLiveData

    fun getBlog() = run {
        progressVisibility.set(true)
        compositeDisposable.add(
            mainRepo.grabUsersFromServer()
        )
    }
}