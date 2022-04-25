package com.example.centerprimesampleethsdk.export_private_key

import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivityExportPrivateKeyBinding

class ExportPrivateKeyActivity :
    BaseActivity<String, ExportPrivateKeyViewModel, ActivityExportPrivateKeyBinding>() {

    override val viewModel by viewModels<ExportPrivateKeyViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivityExportPrivateKeyBinding =
        ActivityExportPrivateKeyBinding::inflate

    override fun onResult(result: String) {
        if (result.isNotEmpty()) {
            with(binding) {
                privateKey.text = result
                copy.visibility = View.VISIBLE
            }
        }
    }

    override fun initUI() {
        binding.button.setOnClickListener {
            var walletAddress = binding.address.text.toString()

            if (walletAddress.startsWith("0x")) {
                walletAddress = walletAddress.substring(2)
            }

            viewModel.exportPrivateKey(walletAddress, binding.password.text.toString(), this)
        }

        binding.copy.setOnClickListener { copyToClipboard(binding.privateKey.text.toString()) }
    }
}
