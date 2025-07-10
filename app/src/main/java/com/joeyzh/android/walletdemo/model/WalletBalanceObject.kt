package com.joeyzh.android.walletdemo.model

data class WalletBalanceObject(
    val ok: Boolean,
    val wallet: List<Wallet>,
    val warning: String
)