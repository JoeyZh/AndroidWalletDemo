package com.joeyzh.android.walletdemo.model

data class CurrenciesObject(
    val currencies: List<CurrencyData>,
    val ok: Boolean,
    val total: Int
)