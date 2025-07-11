package com.joeyzh.android.walletdemo.ui.wallet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.joeyzh.android.walletdemo.api.MockData
import com.joeyzh.android.walletdemo.model.CurrencyData
import com.joeyzh.android.walletdemo.model.CurrencyViewData
import com.joeyzh.android.walletdemo.model.Tier
import com.joeyzh.android.walletdemo.model.Wallet
import com.joeyzh.android.walletdemo.utils.CurrencyUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class WalletViewModel(app: Application) : AndroidViewModel(app) {

    val mockData by lazy {
        MockData()
    }
    private val _currenciesMLV by lazy {
        MutableLiveData<List<CurrencyData>>()
    }
    private val _walletBalancesMLV by lazy {
        MutableLiveData<List<Wallet>>()
    }
    private val _liveRatesMLV by lazy { MutableLiveData<List<Tier>>() }

    val mCurrencyViewDataMLV by lazy {
        MutableLiveData<List<CurrencyViewData>>()
    }

    val mUSDSum by lazy {
        MutableLiveData<Double>()
    }

    fun loadData() {
        viewModelScope.launch {
            dataFlow().collect {
                mUSDSum.value = it.first
                mCurrencyViewDataMLV.value = it.second
            }

        }
    }

    fun dataFlow(): Flow<Pair<Double, List<CurrencyViewData>>> = flow {
        println("Flow started")
        val currencies = mockData.mockCurrencies(getApplication())
        val walletBalances = mockData.mockWalletBalances(getApplication())
        val tiers = mockData.mockLiveRates(getApplication())

        val list = currencies.map {
            val wallet = walletBalances.find { w -> w.currency == it.coin_id }
            val tier = tiers.find { t -> t.from_currency == it.coin_id }
            //。例如，如果用户有 0.0026 BTC，而 BTC 对 USD 的实时汇率是 9194.9300000000，那么该货币的 USD 余额就是 0.0026×9194.9300000000=23.906818 美元
            var price = CurrencyUtils().getPrice(wallet, tier)
            CurrencyViewData(
                imgUrl = it.colorful_image_url,
                name = it.name,
                rate = tier?.rates,
                wallet = wallet,
                id = it.coin_id,
                price = price
            )
        }.toList()
        val sum = list.sumOf { it.price }
        emit(Pair(sum, list))
    }
}