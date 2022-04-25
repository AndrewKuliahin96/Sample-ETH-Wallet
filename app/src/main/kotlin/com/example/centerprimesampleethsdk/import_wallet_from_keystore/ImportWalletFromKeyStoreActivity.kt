package com.example.centerprimesampleethsdk.import_wallet_from_keystore

import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivityImportWalletKeystoreBinding

class ImportWalletFromKeyStoreActivity :
    BaseActivity<String, ImportWalletFromKeyStoreViewModel, ActivityImportWalletKeystoreBinding>() {

    override val viewModel by viewModels<ImportWalletFromKeyStoreViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivityImportWalletKeystoreBinding =
        ActivityImportWalletKeystoreBinding::inflate

    override fun onResult(result: String) {
        if (result.isNotEmpty()) {
            with(binding) {
                walletAddress.text = result
                copy.visibility = View.VISIBLE
            }
        }
    }

    override fun initUI() {
        binding.importBtn.setOnClickListener {
            val password = binding.password.text.toString()
            val keystore = binding.keystoreT.text.toString()

            viewModel.importFromKeystore(keystore, password, this)
        }

        binding.copy.setOnClickListener { copyToClipboard(binding.walletAddress.text.toString()) }
    }
}
