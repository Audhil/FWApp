package com.example.fwapp.ui.main

import io.reactivex.disposables.Disposable

interface IMainRepo {
    fun grabUsersFromServer(): Disposable
}