package com.example.centerprimesampleethsdk.create_wallet

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CreateWalletViewModel : BaseViewModel<String>() {

    override val resultFlow = MutableStateFlow("")

    fun createWallet(password: String, context: Context) {
        ethManager.createWallet(password, context).processResult { resultFlow.value = it.address }
    }
}
