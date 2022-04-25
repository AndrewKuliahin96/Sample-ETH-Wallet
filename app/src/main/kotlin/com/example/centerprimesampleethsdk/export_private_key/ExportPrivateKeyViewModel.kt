package com.example.centerprimesampleethsdk.export_private_key

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ExportPrivateKeyViewModel : BaseViewModel<String>() {

    override val resultFlow = MutableStateFlow("")

    fun exportPrivateKey(walletAddress: String, password: String, context: Context) {
        ethManager.exportPrivateKey(walletAddress, password, context).pushResult()
    }
}
