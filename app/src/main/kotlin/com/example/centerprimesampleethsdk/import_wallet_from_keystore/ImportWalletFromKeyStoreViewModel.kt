package com.example.centerprimesampleethsdk.import_wallet_from_keystore

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ImportWalletFromKeyStoreViewModel : BaseViewModel<String>() {

    override val resultFlow = MutableStateFlow("")

    fun importFromKeystore(keystore: String, password: String, context: Context) {
        ethManager.importFromKeystore(keystore, password, context).pushResult()
    }
}
