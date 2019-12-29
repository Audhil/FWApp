package com.example.fwapp.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.example.fwapp.model.api.UserDetail
import com.example.fwapp.ui.base.BaseViewModel
import com.example.fwapp.util.NetworkError
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val mainRepo: MainRepo
) : BaseViewModel() {

    val progressVisibility = ObservableField<Boolean>(false)

    val isListEmpty = ObservableField<Boolean>(false)

    val errorLiveData: LiveData<NetworkError> = mainRepo.errorLiveData

    val userListLiveData: LiveData<List<UserDetail>> = mainRepo._userListMutableLiveData

    fun getUsers() = run {
        progressVisibility.set(true)
        compositeDisposable.add(
            mainRepo.grabUsersFromServer()
        )
    }
}