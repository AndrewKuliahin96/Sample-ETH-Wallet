package com.example.centerprimesampleethsdk.export_keystore

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ExportKeystoreViewModel : BaseViewModel<String>() {

    override val resultFlow = MutableStateFlow("")

    fun getTokenBalance(walletAddress: String, context: Context) {
        ethManager.exportKeyStore(walletAddress, context).pushResult()
    }
}
