package com.example.centerprimesampleethsdk.import_by_private_key

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivityImportPrivateKeyBinding

class ImportByPrivateKeyActivity :
    BaseActivity<String, ImportByPrivateKeyViewModel, ActivityImportPrivateKeyBinding>() {

    override val viewModel by viewModels<ImportByPrivateKeyViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivityImportPrivateKeyBinding =
        ActivityImportPrivateKeyBinding::inflate

    override fun onResult(result: String) {
        if (result.isNotEmpty()) {
            with(binding) {
                address.text = "0x$result"
                copyBtn.visibility = android.view.View.VISIBLE
            }
        }
    }

    override fun initUI() {
        binding.checkBtn.setOnClickListener {
            val privateKey = binding.privateKey.text.toString()

            if (privateKey.isNotEmpty()) {
                viewModel.importFromPrivateKey(privateKey, this)
            } else {
                showMessage("Please provide private key!")
            }
        }

        binding.copyBtn.setOnClickListener { copyToClipboard(binding.address.text.toString()) }
    }
}
