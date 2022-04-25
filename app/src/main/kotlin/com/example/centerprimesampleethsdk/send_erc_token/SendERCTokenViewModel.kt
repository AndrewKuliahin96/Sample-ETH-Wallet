package com.example.centerprimesampleethsdk.send_erc_token

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import java.math.BigDecimal
import java.math.BigInteger
import kotlinx.coroutines.flow.MutableStateFlow

class SendERCTokenViewModel : BaseViewModel<String>() {

    override val resultFlow = MutableStateFlow("")

    fun sendToken(
        walletAddress: String,
        password: String,
        gasPrice: BigInteger,
        gasLimit: BigInteger,
        tokenAmount: BigDecimal,
        receiverAddress: String,
        erc20TokenContractAddress: String,
        context: Context
    ) {
        ethManager.sendToken(
            walletAddress,
            password,
            gasPrice,
            gasLimit,
            tokenAmount,
            receiverAddress,
            erc20TokenContractAddress,
            context
        ).pushResult()
    }
}
