package com.example.fwapp.ui.base

import com.example.fwapp.rx.IRxListeners
import com.example.fwapp.util.ErrorLiveData
import com.example.fwapp.util.NetworkError
import com.example.fwapp.util.showELog
import com.example.fwapp.util.showVLog

abstract class BaseRepo(val errorLiveData: ErrorLiveData) : IRxListeners<Any> {

    override fun onSuccess(obj: Any?, tag: String) {
        showVLog { "onSuccess() :: $tag" }
    }

    override fun onSocketTimeOutException(t: Throwable?, tag: String) {
        showELog { "onSocketTimeOutException :: + tag :" + tag + " :: t?.message :: " + t?.message }
        errorLiveData.setNetworkError(NetworkError.SOCKET_TIMEOUT)
    }

    override fun onUnknownHostException(t: Throwable?, tag: String) {
        showELog { "onUnknownHostException :: + tag :" + tag + " :: t?.message :: " + t?.message }
        errorLiveData.setNetworkError(NetworkError.DISCONNECTED)
    }

    override fun onIllegalArgumentException(t: Throwable?, tag: String) {
        showELog { "onIllegalArgumentException :: + tag :" + tag + " :: t?.message :: " + t?.message }
        errorLiveData.setNetworkError(NetworkError.BAD_URL)
    }

    override fun onUnKnownException(t: Throwable?, tag: String) {
        showELog { "onUnKnownException :: + tag :" + tag + " :: t?.message :: " + t?.message }
        errorLiveData.setNetworkError(NetworkError.UNKNOWN)
    }

    override fun onComplete(tag: String) {
        showELog { "onComplete() :: tag :$tag" }
    }
}