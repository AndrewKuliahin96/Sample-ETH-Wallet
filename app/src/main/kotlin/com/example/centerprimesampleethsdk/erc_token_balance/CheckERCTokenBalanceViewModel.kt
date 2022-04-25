package com.example.centerprimesampleethsdk.erc_token_balance

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import java.math.BigDecimal
import kotlinx.coroutines.flow.MutableStateFlow

class CheckERCTokenBalanceViewModel : BaseViewModel<BigDecimal>() {

    override val resultFlow = MutableStateFlow(BigDecimal(Int.MIN_VALUE))

    fun getTokenBalance(
        walletAddress: String,
        password: String,
        erc20TokenContractAddress: String,
        context: Context
    ) {
        ethManager.getTokenBalance(walletAddress, password, erc20TokenContractAddress, context)
            .pushResult()
    }
}
