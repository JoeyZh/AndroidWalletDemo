package com.joeyzh.android.walletdemo.model

/**
 * @Date 2025/7/10
 * @Create Joey 周晔
 * @Description 货币列表的数据
 */
data class CurrencyViewData(
    val imgUrl:String?,
    val name:String,
    val id:String,
    val rate:List<Rate>?,
    val wallet: Wallet?,
    var price:Double
)