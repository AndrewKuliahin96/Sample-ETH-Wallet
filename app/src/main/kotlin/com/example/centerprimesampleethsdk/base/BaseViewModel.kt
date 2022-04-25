package com.example.centerprimesampleethsdk.base

import androidx.lifecycle.ViewModel
import com.centerprime.ethereum_client_sdk.EthManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T> : ViewModel() {

    // TODO 1. Add more Base (Activity, VM error handling)
    // TODO 2. Check out source code and implement everything on Web3J
    // TODO 3. Add Gas current price handling
    // TODO 4. Fix UI
    // TODO 5. Add NFT
    // TODO 6. Add possibility to change networks (Test net / Main net)

    protected val ethManager: EthManager = EthManager.getInstance()

    private val errorMessageFlow = MutableStateFlow("")
    abstract val resultFlow: MutableStateFlow<T>

    val result: StateFlow<T>
        get() = resultFlow.asStateFlow()

    val errorMessage: StateFlow<String>
        get() = errorMessageFlow.asStateFlow()

    private var compositeDisposable: Disposable = CompositeDisposable()

    init {
        ethManager.init("https://mainnet.infura.io/v3/a396c3461ac048a59f389c7778f06689")
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun Single<T>.pushResult() {
        compositeDisposable = subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resultFlow.value = it }, ::handleError)
    }

    protected fun <I> Single<I>.processResult(onResult: (I) -> Unit) {
        compositeDisposable = subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onResult(it) }, ::handleError)
    }

    private fun handleError(throwable: Throwable) {
        throwable.message?.let { message -> errorMessageFlow.value = message }
    }
}
