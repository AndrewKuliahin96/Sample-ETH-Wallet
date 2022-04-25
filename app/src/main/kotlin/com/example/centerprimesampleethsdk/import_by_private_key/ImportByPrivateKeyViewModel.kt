package com.example.centerprimesampleethsdk.import_by_private_key

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ImportByPrivateKeyViewModel : BaseViewModel<String>() {

    override val resultFlow = MutableStateFlow("")

    fun importFromPrivateKey(privateKey: String, context: Context) {
        ethManager.importFromPrivateKey(privateKey, context).pushResult()
    }
}
