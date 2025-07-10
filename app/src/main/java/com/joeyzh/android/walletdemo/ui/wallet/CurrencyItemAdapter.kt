package com.joeyzh.android.walletdemo.ui.wallet

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.DataBindingHolder
import com.joeyzh.android.walletdemo.R
import com.joeyzh.android.walletdemo.databinding.ItemCryptoBinding
import com.joeyzh.android.walletdemo.extensions.setUrl
import com.joeyzh.android.walletdemo.model.CurrencyViewData

/**
 * @Date 2025/7/10
 * @Create Joey 周晔
 * @Description 货币item Adapter
 */
class CurrencyItemAdapter :
    BaseQuickAdapter<CurrencyViewData, DataBindingHolder<ItemCryptoBinding>>() {
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): DataBindingHolder<ItemCryptoBinding> {
        return DataBindingHolder(R.layout.item_crypto, parent)
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<ItemCryptoBinding>,
        position: Int,
        item: CurrencyViewData?
    ) {

        holder.binding.apply {
            if (item == null) {
                return
            }
            tvCoinName.text = item.name
            ivCoins.setUrl(item.imgUrl, R.drawable.logo)
            tvCoinId.text = item.id
            tvCoinAmount.text = (item.wallet?.amount ?: 0).toString()
            tvUsdValue.text = '$' + (item.price).toString()
        }

    }
}