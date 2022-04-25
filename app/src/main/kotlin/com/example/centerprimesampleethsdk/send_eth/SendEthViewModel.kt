package com.example.centerprimesampleethsdk.send_eth

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import java.math.BigDecimal
import java.math.BigInteger
import kotlinx.coroutines.flow.MutableStateFlow

class SendEthViewModel : BaseViewModel<String>() {

    override val resultFlow = MutableStateFlow("")

    fun sendEther(
        walletAddress: String,
        password: String,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
        etherAmount: BigDecimal,
        receiverAddress: String,
        context: Context
    ) {
        ethManager.sendEther(
            walletAddress,
            password,
            gasPrice,
            gasLimit,
            etherAmount,
            receiverAddress,
            context
        ).pushResult()
    }
}
