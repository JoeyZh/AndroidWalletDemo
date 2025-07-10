package com.joeyzh.android.walletdemo.ui.dashboard

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.DataBindingHolder
import com.joeyzh.android.walletdemo.R
import com.joeyzh.android.walletdemo.databinding.ItemCryptocurrencyBinding
import com.joeyzh.android.walletdemo.extensions.setUrl
import com.joeyzh.android.walletdemo.model.CurrencyData
import com.joeyzh.android.walletdemo.model.CurrencyViewData

/**
 * @Date 2025/7/10
 * @Create Joey 周晔
 * @Description 货币item Adapter
 */
class CurrencyItemAdapter():
    BaseQuickAdapter<CurrencyViewData, DataBindingHolder<ItemCryptocurrencyBinding>>() {
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): DataBindingHolder<ItemCryptocurrencyBinding> {
        return  DataBindingHolder(R.layout.item_cryptocurrency, parent)
    }

    override fun onBindViewHolder(
        holder: DataBindingHolder<ItemCryptocurrencyBinding>,
        position: Int,
        item: CurrencyViewData?
    ) {

        holder.binding.apply {
            if(item == null){
                return
            }
            tvCoinName.text = item.name
            ivCoins.setUrl(item.imgUrl,R.drawable.logo)
            tvUsdValue.text = item.id
            tvCoinPrice.text = '$'+(item.price).toString()
        }

    }
}