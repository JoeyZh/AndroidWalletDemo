package com.joeyzh.android.walletdemo.model

data class LiveratesObject(
    val ok: Boolean,
    val tiers: List<Tier>,
    val warning: String
)