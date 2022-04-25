package com.example.centerprimesampleethsdk.export_keystore

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.example.centerprimesampleethsdk.base.BaseActivity
import com.example.centerprimesampleethsdk.databinding.ActivityExportKeystoreBinding

class ExportKeyStoreActivity :
    BaseActivity<String, ExportKeystoreViewModel, ActivityExportKeystoreBinding>() {

    override val viewModel by viewModels<ExportKeystoreViewModel>()

    override val bindingFactory: (LayoutInflater) -> ActivityExportKeystoreBinding =
        ActivityExportKeystoreBinding::inflate

    override fun onResult(result: String) {
        if (result.isNotEmpty()) {
            with(binding) {
                keystoreT.visibility = View.VISIBLE
                copy.visibility = View.VISIBLE
                keystoreT.text = result
            }

            hideKeyboard()
        }
    }

    override fun initUI() {
        binding.button.setOnClickListener {
            val address = binding.address.text.toString()

            if (address.isNotEmpty()) {
                viewModel.getTokenBalance(address, this)
            } else {
                showMessage("Enter wallet address")
            }
        }

        binding.copy.setOnClickListener { copyToClipboard(binding.keystoreT.text.toString()) }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = currentFocus

        if (view == null) {
            view = View(this)
        }

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
