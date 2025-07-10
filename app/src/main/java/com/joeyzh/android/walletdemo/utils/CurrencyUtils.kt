package com.joeyzh.android.walletdemo.utils

import com.joeyzh.android.walletdemo.model.Tier
import com.joeyzh.android.walletdemo.model.Wallet

/**
 * @Date 2025/7/10
 * @Create Joey 周晔
 * @Description 货币工具类
 */
class CurrencyUtils {

    fun getPrice(wallet: Wallet?, tier: Tier?): Double {
        if (wallet == null || tier == null || tier.rates == null || tier.rates.isEmpty()) {
            return 0.0
        }
        val amount = wallet.amount
        val rate = tier.rates[0].rate
        return amount.toDouble() * rate
    }
}