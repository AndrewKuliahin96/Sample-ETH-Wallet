package com.example.centerprimesampleethsdk.check_balance

import android.content.Context
import com.example.centerprimesampleethsdk.base.BaseViewModel
import java.math.BigDecimal
import kotlinx.coroutines.flow.MutableStateFlow

class CheckBalanceViewModel : BaseViewModel<BigDecimal>() {

    override val resultFlow = MutableStateFlow(BigDecimal(Int.MIN_VALUE))

    fun getBalanceInEth(address: String, context: Context) {
        ethManager.balanceInEth(address, context).pushResult()
    }
}
