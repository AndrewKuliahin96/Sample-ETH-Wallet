package com.example.centerprimesampleethsdk.base

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity<T, VM : BaseViewModel<T>, VB : ViewBinding> : AppCompatActivity() {

    abstract val viewModel: VM

    abstract val bindingFactory: (LayoutInflater) -> VB

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)

        setContentView(binding.root)
        observeViewModel()
        initUI()
    }

    abstract fun onResult(result: T)

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.result.collectLatest(::onResult)
                }

                launch {
                    viewModel.errorMessage.collectLatest(::showMessage)
                }
            }
        }
    }

    abstract fun initUI()

    protected fun showMessage(message: String) {
        message.takeIf { it.isNotEmpty() }?.let {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    protected fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)

        clipboard.setPrimaryClip(clip)
        showMessage("Copied!")
    }
}
